package org.ucu.bd;

import Utils.PasswordManager;
import model.currentUser;

import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Database {

    private String username;
    private String password;
    private String uri;
    private Connection db_connection;
    private Statement stmt = null;

    public Database(String username, String password, String uri){
        this.username = username;
        this.password = password;
        this.uri = uri;
    }

    public void initConnection() throws SQLException {
        this.db_connection = DriverManager.getConnection(this.uri, this.username, this.password);
    }

    public boolean isConnected(){
        boolean result = false;
        try {
            result = !db_connection.isClosed();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static String generateUri(String ip, int port, String DB_name){
        return "jdbc:postgresql://" + ip + ":" +  port + "/" + DB_name;
    }

    //Login and user creation
    public boolean login (String user,String passwordText, String tableName, Component parent){
        if (isConnected()) {
            try {
                stmt = db_connection.createStatement(ResultSet.CONCUR_UPDATABLE,ResultSet.TYPE_FORWARD_ONLY);
                ResultSet rs = stmt.executeQuery("SELECT * FROM " + tableName + " WHERE nombre_usuario = '" + user
                        + "'");

                if (!rs.next()){ //Si no hay resultados

                    JOptionPane.showMessageDialog(parent, "Usuario Incorrecto");

                } else { //Si hay resultados
                    int tries = rs.getInt("availabletries");
                    if (rs.getBoolean("admin")){ //Si es administrador
                        if (!rs.getBoolean("bloqueado")){ //Si no esta bloqueado
                            if(PasswordManager.validatePassword2(rs.getString("Contraseña"),passwordText) ){
                                //Iniciar programa
                                if (tries != 5){
                                    rs.updateInt("availableTries",5);
                                    rs.updateRow();
                                }
                                currentUser logedUser = currentUser.getCurrentUser();
                                logedUser.setUser_id(rs.getInt("id_usuario"));
                                logedUser.setUserName(rs.getString("nombre_usuario"));
                                return true;
                            } else {
                                JOptionPane.showMessageDialog(parent, "Contraseña Incorrecta");
                                if(tries == 0){
                                    rs.updateBoolean("bloqueado",true);
                                } else {
                                    rs.updateInt("availableTries",--tries);
                                }
                                rs.updateRow();
                            }
                        } else {
                            JOptionPane.showMessageDialog(parent, "Cuenta Bloqueada.\nContacte al administrador");
                        }
                    } else {
                        JOptionPane.showMessageDialog(parent, "Usuario Incorrecto");
                    }
                }
                rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return false;
    }

    public boolean createPerson(int ci, String name, String direction, int phone) {
        if(isConnected()) {
            try {
                stmt = db_connection.createStatement(ResultSet.CONCUR_UPDATABLE, ResultSet.TYPE_FORWARD_ONLY);
                stmt.executeUpdate("INSERT INTO persona (cedula, nombre, direccion, telefono) VALUES ('" + ci  + "','" + name + "','" + direction + "','" + phone + "')",Statement.RETURN_GENERATED_KEYS);

                //Agarramos la key de la persona creada
                ResultSet rs = stmt.getGeneratedKeys();
                logActivity(rs, "persona", 1);
                return true;
            }
            catch (SQLException ex) {
                Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return false;
    }

    private int createEvento(String tablename,java.sql.Date date ) throws SQLException {
        String nombre_rol = currentUser.getCurrentUser().getUserName(); //Se puede actualizar a que sea nombre rol. no se
        String eventDescription =  tablename + " creado el " + date + " por " + nombre_rol;
        stmt.executeUpdate(new StringBuilder().append("INSERT INTO evento (nombre_rol, descripcion) ").append("VALUES ('").append(nombre_rol).append("','").append(eventDescription).append("')").toString(),Statement.RETURN_GENERATED_KEYS);
        ResultSet rs = stmt.getGeneratedKeys();
        if ( rs.next() ) {
            int eventKey = rs.getInt(1);
            return eventKey;
        }
        rs.close();
        return 0;
    }

    public ResultSet createUser(String name, String password, int ci, boolean admin, int creator, boolean blocked) {
        if(isConnected()) {
            try {
                stmt = db_connection.createStatement(ResultSet.CONCUR_UPDATABLE, ResultSet.TYPE_FORWARD_ONLY);
                ResultSet rs =
                        stmt.executeQuery(
                                "INSERT INTO usuario (nombre_usuario, contraseña, ci_persona, admin, creador, bloqueado) VALUES ('" + name + "','" + password + "'," + ci + ",'" + admin + "'," + creator + ",'" + blocked + "'");
                return rs;
            }
            catch (SQLException ex) {
                Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }


    //Generic table treatment
    public ResultSet search(String tableName, String column, String value) {
        try {
            stmt = db_connection.createStatement(ResultSet.CONCUR_UPDATABLE, ResultSet.TYPE_FORWARD_ONLY);
            ResultSet rs = stmt.executeQuery("SELECT * FROM " + tableName + " WHERE " + column + " = '" + value
                    + "'");
            return rs;
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public ResultSet getAllElements(String tableName) {
        if(isConnected()) {
            try {
                stmt = db_connection.createStatement(ResultSet.CONCUR_UPDATABLE, ResultSet.TYPE_FORWARD_ONLY);
                ResultSet rs = stmt.executeQuery("SELECT * FROM " + tableName);
                return rs;
            }
            catch (SQLException ex) {
                Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }

    public int getTableCount(String tableName) {
        if(isConnected()) {
            try {
                stmt = db_connection.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT count(*) FROM " + tableName);
                rs.next();
                int count = rs.getInt(1);
                rs.close();
                return count;
            }
            catch (SQLException ex) {
                Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return 0;
    }

    public boolean createModel(String name, String description, String tablename, int event_type) {
        if(isConnected()) {
            try {
                stmt = db_connection.createStatement();
                stmt.executeUpdate("INSERT INTO "+ tablename +" (nombre_"+ tablename +
                        ", descripcion) VALUES ('" + name + "',"+"'" + description + "')",Statement.RETURN_GENERATED_KEYS);

                //Agarramos la key del model creado
                ResultSet rs = stmt.getGeneratedKeys();
                if ( rs.next() ) {
                    int modelKey = rs.getInt(1);
                    java.sql.Date date = new java.sql.Date(Calendar.getInstance().getTime().getTime());
                    String query = new StringBuilder().append("INSERT INTO log_").append(tablename).append(" (id_").append(tablename).append(", fecha_registro, id_usuario, id_evento) VALUES ('").append(String.valueOf(modelKey)).append("','").append(date).append("','").append(currentUser.getCurrentUser().get_userId()).append("','").append(event_type).append("')").toString();
                    stmt.executeUpdate(query);
                    return true;
                }
            }
            catch (SQLException ex) {
                Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return false;
    }

    private void logActivity(ResultSet rs, String tablename, int event_type){
        try {
            if (rs.next()) {
                int modelKey = rs.getInt(1);
                java.sql.Date date = new java.sql.Date(Calendar.getInstance().getTime().getTime());
                String query = new StringBuilder().append("INSERT INTO log_").append(tablename).append(" (id_").append(tablename).append(", fecha_registro, id_usuario, id_evento) VALUES ('").append(String.valueOf(modelKey)).append("','").append(date).append("','").append(currentUser.getCurrentUser().get_userId()).append("','").append(event_type).append("')").toString();
                stmt.executeUpdate(query);
            }
        }   catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ResultSet deleteRow(String table_name, String row_id) {
        if(isConnected()) {
            try {
                stmt = db_connection.createStatement(ResultSet.CONCUR_UPDATABLE, ResultSet.TYPE_FORWARD_ONLY);
                stmt.executeUpdate("UPDATE " + table_name + " SET activo = false WHERE  id_" + table_name  + " = " + row_id, Statement.RETURN_GENERATED_KEYS);
                ResultSet rs = stmt.getGeneratedKeys();
                logActivity(rs, table_name, 2);
            }
            catch (SQLException ex) {
                Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }

    public ResultSet deleteRowPersona(String table_name, String row_id) {
        if(isConnected()) {
            try {
                stmt = db_connection.createStatement(ResultSet.CONCUR_UPDATABLE, ResultSet.TYPE_FORWARD_ONLY);
                stmt.executeUpdate("UPDATE " + table_name + " SET activo = false WHERE cedula = " + row_id);
            }
            catch (SQLException ex) {
                Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }


    //Updates
    public void updateModel(String row_id, String new_name, String new_desc,String tablename){
        this.deleteRow(tablename, row_id);
        this.createModel(new_name, new_desc, tablename,3);
    }

    public void updatePerson(String id, String newName, String newAdres, String newPhone){
        this.deleteRowPersona("persona", id);
        this.createPerson(Integer.parseInt(id), newName, newAdres, Integer.parseInt(newPhone));
    }

    //Role asignments
    public ResultSet putAutorization(String id_usuario, int autorizante) {
        if(isConnected()) {
            try {
                stmt = db_connection.createStatement(ResultSet.CONCUR_UPDATABLE, ResultSet.TYPE_FORWARD_ONLY);
                ResultSet rs =
                        stmt.executeQuery("UPDATE usuario SET autorizador = " + autorizante + "WHERE id_usuario = " + id_usuario);
                return rs;
            }
            catch (SQLException ex) {
                Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }
}
