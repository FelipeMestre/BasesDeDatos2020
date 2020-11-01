package org.ucu.bd;

import java.sql.*;
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

    public void initConnection() {
        try {
            this.db_connection = DriverManager.getConnection(this.uri, this.username, this.password);
        } catch (SQLException e) {
            System.out.println("Connection Failed\n" + e.getMessage());
            e.printStackTrace();
        }
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

    public ResultSet login (String user,String tableName){
        if (isConnected()) {
            try {
                stmt = db_connection.createStatement(ResultSet.CONCUR_UPDATABLE,ResultSet.TYPE_FORWARD_ONLY);
                ResultSet rs = stmt.executeQuery("SELECT * FROM " + tableName + " WHERE nombre_usuario = '" + user
                        + "'");
                return rs;
            } catch (SQLException ex) {
                this.initConnection();
                Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }

    public void join(String userName, String password, String person_ci){
        try {
            stmt = db_connection.createStatement();
            ResultSet rs = stmt.executeQuery("INSERT INTO usuario (nombre_usuario,contraseña,ci_persona)" );
        } catch (SQLException ex) {
            this.initConnection();
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ResultSet search(String tableName, String column, String value) {
        try {
            stmt = db_connection.createStatement(ResultSet.CONCUR_UPDATABLE, ResultSet.TYPE_FORWARD_ONLY);
            ResultSet rs = stmt.executeQuery("SELECT * FROM " + tableName + " WHERE " + column + " = '" + value
                    + "'");
            return rs;
        } catch (SQLException ex) {
            this.initConnection();
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
                this.initConnection();
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
                return rs.getInt(1);
            }
            catch (SQLException ex) {
                this.initConnection();
                Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return 0;
    }

    public ResultSet getPersonWithCI(String tableName, int ci) {
        if(isConnected()) {
            try {
                stmt = db_connection.createStatement(ResultSet.CONCUR_UPDATABLE, ResultSet.TYPE_FORWARD_ONLY);
                ResultSet rs = stmt.executeQuery("SELECT * FROM" + tableName + " WHERE ci = '" + ci + "'");
                return rs;
            }
            catch (SQLException ex) {
                this.initConnection();
                Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }

    public ResultSet createPerson(int ci, String name, String direction, int phone) {
        if(isConnected()) {
            try {
                stmt = db_connection.createStatement(ResultSet.CONCUR_UPDATABLE, ResultSet.TYPE_FORWARD_ONLY);
                ResultSet rs = stmt.executeQuery("INSERT INTO persona (cedula, nombre, direccion, telefono) VALUES (" + ci  + ",'" + name + "','" + direction + "'," + phone);
                return rs;
            }
            catch (SQLException ex) {
                this.initConnection();
                Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
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
                this.initConnection();
                Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }

    public ResultSet putAutorization(String id_usuario, int autorizante) {
        if(isConnected()) {
            try {
                stmt = db_connection.createStatement(ResultSet.CONCUR_UPDATABLE, ResultSet.TYPE_FORWARD_ONLY);
                ResultSet rs =
                        stmt.executeQuery("UPDATE usuario SET autorizador = " + autorizante + "WHERE id_usuario = " + id_usuario);
                return rs;
            }
            catch (SQLException ex) {
                this.initConnection();
                Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }

    public ResultSet createMenu(String name, String description) {
        if(isConnected()) {
            try {
                stmt = db_connection.createStatement(ResultSet.CONCUR_UPDATABLE, ResultSet.TYPE_FORWARD_ONLY);
                ResultSet rs = stmt.executeQuery("INSERT INTO menu (nombre_menu, descripcion) VALUES ('" + name + "','" + description + "'");
                return rs;
            }
            catch (SQLException ex) {
                this.initConnection();
                Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }

    public ResultSet createRol(String name, String description) {
        if(isConnected()) {
            try {
                stmt = db_connection.createStatement(ResultSet.CONCUR_UPDATABLE, ResultSet.TYPE_FORWARD_ONLY);
                ResultSet rs = stmt.executeQuery("INSERT INTO rol (nombre_rol, descripcion) VALUES ('" + name + "','" + description + "'");
                return rs;
            }
            catch (SQLException ex) {
                this.initConnection();
                Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }

    public ResultSet createUserRol(int user_id, int rol_id) {
        if(isConnected()) {
            try {
                stmt = db_connection.createStatement(ResultSet.CONCUR_UPDATABLE, ResultSet.TYPE_FORWARD_ONLY);
                ResultSet rs = stmt.executeQuery("INSERT INTO usuario_rol (id_rol, id_usuario) VALUES (" + rol_id + "," + user_id);
                return rs;
            }
            catch (SQLException ex) {
                this.initConnection();
                Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }

    public ResultSet createMenuRol(int menu_id, int rol_id) {
        if(isConnected()) {
            try {
                stmt = db_connection.createStatement(ResultSet.CONCUR_UPDATABLE, ResultSet.TYPE_FORWARD_ONLY);
                ResultSet rs = stmt.executeQuery("INSERT INTO menu_rol (id_rol, id_menu) VALUES (" + rol_id + "," + menu_id);
                return rs;
            }
            catch (SQLException ex) {
                this.initConnection();
                Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }
}
