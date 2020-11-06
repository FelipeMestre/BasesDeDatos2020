package org.ucu.bd;

import Utils.PasswordManager;
import model.CurrentUser;

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

    //Driver methods
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

    //Login and password checking
    public boolean login (String user,String passwordText, String tableName, Component parent){
        if (isConnected()) {
            try {
                stmt = db_connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
                ResultSet rs = stmt.executeQuery("SELECT * FROM " + tableName + " WHERE nombre_usuario = '" + user
                        + "'");

                if (!rs.first()){ //Si no hay resultados

                    JOptionPane.showMessageDialog(parent, "Usuario Incorrecto");

                } else { //Si hay resultados
                    int tries = rs.getInt("availabletries");
                    if (rs.getBoolean("admin")){ //Si es administrador
                        if (!rs.getBoolean("bloqueado")){ //Si no esta bloqueado
                            if(PasswordManager.getInstance().validatePassword(rs.getString("Contraseña"),
                                    passwordText,rs.getInt("id_usuario")) ){
                                //Iniciar programa
                                if (tries != 5){
                                    rs.updateInt("availableTries",5);
                                    rs.updateRow();
                                }
                                CurrentUser logedUser = CurrentUser.getCurrentUser();
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
                        JOptionPane.showMessageDialog(parent, "Usted no Es Administrador");
                    }
                }
                rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return false;
    }

    public boolean checkIfCorrectPassword (String userId,String user,String passwordText, String tableName, Component parent){
        if (isConnected()) {
            try {
                stmt = db_connection.createStatement(ResultSet.CONCUR_UPDATABLE,ResultSet.TYPE_FORWARD_ONLY);
                ResultSet rs = stmt.executeQuery("SELECT * FROM " + tableName + " WHERE nombre_usuario = '" + user
                        + "'");

                if (!rs.first()){ //Si no hay resultados

                    JOptionPane.showMessageDialog(parent, "Usuario Incorrecto");

                } else { //Si hay resultados
                    int tries = rs.getInt("availabletries");
                    if (rs.getBoolean("admin")){ //Si es administrador
                        if (!rs.getBoolean("bloqueado")){ //Si no esta bloqueado
                            if(PasswordManager.getInstance().validatePassword(rs.getString("Contraseña"),passwordText,Integer.valueOf(userId))){
                                //Iniciar programa
                                if (tries != 5){
                                    rs.updateInt("availableTries",5);
                                    rs.updateRow();
                                }
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

    //Creation of things
    public boolean createPerson(int ci, String name, String direction, int phone) {
        if(isConnected()) {
            try {
                stmt = db_connection.createStatement(ResultSet.CONCUR_UPDATABLE, ResultSet.TYPE_FORWARD_ONLY);
                stmt.executeUpdate("INSERT INTO persona (cedula, nombre, direccion, telefono) VALUES ('" + ci  + "','" + name + "','" + direction + "','" + phone + "')",Statement.RETURN_GENERATED_KEYS);

                //Agarramos la key de la persona creada
                ResultSet rs = stmt.getGeneratedKeys();
                if ( rs.next() ) {
                    int personKey = rs.getInt(1);
                    java.sql.Date date = new java.sql.Date(Calendar.getInstance().getTime().getTime());
                    int eventKey = createEvento("Persona",date);
                    String query = new StringBuilder().append("INSERT INTO log_").append("Persona").append(" (id_").append("Persona").append(", fecha_registro, id_usuario, id_evento) VALUES ('").append(String.valueOf(personKey)).append("','").append(date).append("','").append(CurrentUser.getCurrentUser().get_userId()).append("','").append(eventKey).append("')").toString();
                    stmt.executeUpdate(query);
                    rs.close();
                    return true;
                }
            }
            catch (SQLException ex) {
                Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return false;
    }

    private int createEvento(String tablename,java.sql.Date date ) throws SQLException {
        String nombre_rol = CurrentUser.getCurrentUser().getUserName(); //Se puede actualizar a que sea nombre rol. no se
        String eventDescription =  tablename + " creado el " + date + " por " + nombre_rol;
        stmt.executeUpdate(new StringBuilder().append("INSERT INTO evento (nombre_rol, descripcion) ").
                append("VALUES ('").append(nombre_rol).append("','").append(eventDescription).
                append("')").toString(),Statement.RETURN_GENERATED_KEYS);
        ResultSet rs = stmt.getGeneratedKeys();
        if ( rs.next() ) {
            int eventKey = rs.getInt(1);
            return eventKey;
        }
        rs.close();
        return 0;
    }

    public boolean createUser(String name, String password, int ci, boolean admin, int creator, boolean blocked) {
        if(isConnected()) {
            try {
                stmt = db_connection.createStatement(ResultSet.CONCUR_UPDATABLE, ResultSet.TYPE_FORWARD_ONLY);
                stmt.executeUpdate(
                                "INSERT INTO usuario (nombre_usuario, contraseña, ci_persona, admin, " +
                                        "creador, bloqueado) VALUES ('" + name + "',' a" +
                                        "'," + ci + ",'" + admin + "'," + creator + ",'" + blocked + "')",
                        Statement.RETURN_GENERATED_KEYS);
                ResultSet rs = stmt.getGeneratedKeys();
                int id_usuario = 0;
                if(rs.first()){
                    id_usuario = rs.getInt(1);
                    String newPassword = PasswordManager.getInstance().generatePassword(password,id_usuario);
                    stmt.executeUpdate(
                            "update usuario set contraseña ='" + newPassword + "' where id_usuario = " + id_usuario);
                    stmt.close();
                    return true;
                }
                return false;
            }
            catch (SQLException ex) {
                Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return false;
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

    public boolean createModel(String name, String description, String tablename) {
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
                   int eventKey = createEvento(tablename,date);
                    String query = new StringBuilder().append("INSERT INTO log_").append(tablename).append(" (id_").append(tablename).append(", fecha_registro, id_usuario, id_evento) VALUES ('").append(String.valueOf(modelKey)).append("','").append(date).append("','").append(CurrentUser.getCurrentUser().get_userId()).append("','").append(eventKey).append("')").toString();
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

    public ResultSet deleteRow(String table_name, String row_id, String col_name) {
        if(isConnected()) {
            try {
                stmt = db_connection.createStatement(ResultSet.CONCUR_UPDATABLE, ResultSet.TYPE_FORWARD_ONLY);
                stmt.executeUpdate("DELETE FROM " + table_name + " WHERE " + col_name + " = " + row_id);
            }
            catch (SQLException ex) {
                Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }


    //Updates
    public void updateModel(String row_id, String new_name, String new_desc,String tablename){
        if(isConnected()) {
            try {
                stmt = db_connection.createStatement(ResultSet.CONCUR_UPDATABLE, ResultSet.TYPE_FORWARD_ONLY);
                stmt.executeUpdate(String.format("UPDATE "+ tablename + " SET nombre_"+tablename+ "= \'%s\',descripcion = \'%s\' WHERE id_"+tablename+" = %d",new_name,new_desc,Integer.parseInt(row_id)));
            }
            catch (SQLException ex) {
                Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void updatePerson(String id, String newName, String newAdres, String newPhone){
        if(isConnected()) {
            try {
                stmt = db_connection.createStatement(ResultSet.CONCUR_UPDATABLE, ResultSet.TYPE_FORWARD_ONLY);
                stmt.executeUpdate(String.format("UPDATE persona SET nombre = \'%s\',direccion = \'%s\' " +
                        ", telefono = \'%s\' WHERE cedula = %d",newName,newAdres,newPhone,Integer.parseInt(id)));
            }
            catch (SQLException ex) {
                Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void updateUser(String id, String username, String password, boolean blocked, boolean withPassword){
        if(isConnected()) {
            String newPassword = PasswordManager.getInstance().generatePassword(password,Integer.valueOf(id));
            try {
                stmt = db_connection.createStatement(ResultSet.CONCUR_UPDATABLE, ResultSet.TYPE_FORWARD_ONLY);

                if (withPassword){
                    stmt.executeUpdate(String.format("UPDATE usuario SET nombre_usuario = \'%s\', contraseña = " +
                            "\'%s\' " + ", bloqueado = \'%s\' WHERE id_usuario = " +
                            "%d",username,newPassword,blocked,Integer.parseInt(id)));
                } else {
                    stmt.executeUpdate(String.format("UPDATE usuario SET nombre_usuario = \'%s\', bloqueado = \'%s\' " +
                            "WHERE id_usuario = " + "%d",username,blocked,Integer.parseInt(id)));
                }
            }
            catch (SQLException ex) {
                Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
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

    //Existance
    public boolean existsPerson(String ci){
        ResultSet person = search("persona","cedula",ci);
        try {
            return person.first();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }
}
