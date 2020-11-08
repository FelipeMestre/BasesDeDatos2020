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

    public Database(String username, String password, String uri) {
        this.username = username;
        this.password = password;
        this.uri = uri;
    }

    //Driver methods
    public void initConnection() throws SQLException {
        this.db_connection = DriverManager.getConnection(this.uri, this.username, this.password);
    }

    public boolean isConnected() {
        boolean result = false;
        try {
            result = !db_connection.isClosed();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static String generateUri(String ip, int port, String DB_name) {
        return "jdbc:postgresql://" + ip + ":" + port + "/" + DB_name;
    }

    //Login and password checking
    public boolean login(String user, String passwordText, String tableName, Component parent) {
        if (isConnected()) {
            try {
                stmt = db_connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
                ResultSet rs = stmt.executeQuery("SELECT * FROM " + tableName + " WHERE nombre_usuario = '" + user
                        + "' and activo = true");

                if (!rs.first()) { //Si no hay resultados

                    JOptionPane.showMessageDialog(parent, "Usuario Incorrecto");

                } else { //Si hay resultados
                    int tries = rs.getInt("availabletries");
                    if (rs.getBoolean("admin")) { //Si es administrador
                        if (!rs.getBoolean("bloqueado")) { //Si no esta bloqueado
                            if (PasswordManager.getInstance().validatePassword(rs.getString("Contraseña"),
                                    passwordText, rs.getInt("id_usuario"))) {
                                //Iniciar programa
                                if (tries != 5) {
                                    rs.updateInt("availableTries", 5);
                                    rs.updateRow();
                                }
                                CurrentUser logedUser = CurrentUser.getCurrentUser();
                                logedUser.setUser_id(rs.getInt("id_usuario"));
                                logedUser.setUserName(rs.getString("nombre_usuario"));
                                return true;
                            } else {
                                JOptionPane.showMessageDialog(parent, "Contraseña Incorrecta");
                                if (tries == 0) {
                                    rs.updateBoolean("bloqueado", true);
                                } else {
                                    rs.updateInt("availableTries", --tries);
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

    public boolean checkIfCorrectPassword(String userId, String user, String passwordText, String tableName, Component parent) {
        if (isConnected()) {
            try {
                stmt = db_connection.createStatement(ResultSet.CONCUR_UPDATABLE, ResultSet.TYPE_FORWARD_ONLY);
                ResultSet rs = stmt.executeQuery("SELECT * FROM " + tableName + " WHERE nombre_usuario = '" + user
                        + "'");

                if (!rs.first()) { //Si no hay resultados

                    JOptionPane.showMessageDialog(parent, "Usuario Incorrecto");

                } else { //Si hay resultados
                    int tries = rs.getInt("availabletries");
                    if (rs.getBoolean("admin")) { //Si es administrador
                        if (!rs.getBoolean("bloqueado")) { //Si no esta bloqueado
                            if (PasswordManager.getInstance().validatePassword(rs.getString("Contraseña"), passwordText, Integer.valueOf(userId))) {
                                //Iniciar programa
                                if (tries != 5) {
                                    rs.updateInt("availableTries", 5);
                                    rs.updateRow();
                                }
                                return true;
                            } else {
                                JOptionPane.showMessageDialog(parent, "Contraseña Incorrecta");
                                if (tries == 0) {
                                    rs.updateBoolean("bloqueado", true);
                                } else {
                                    rs.updateInt("availableTries", --tries);
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


    //Create Methods
    public boolean createPerson(int ci, String name, String direction, int phone, int event_type) {
        if (isConnected()) {
            try {
                stmt = db_connection.createStatement(ResultSet.CONCUR_UPDATABLE, ResultSet.TYPE_FORWARD_ONLY);
                stmt.executeUpdate("INSERT INTO persona (cedula, nombre, direccion, telefono) VALUES ('" + ci + "','" + name + "','" + direction + "','" + phone + "')", Statement.RETURN_GENERATED_KEYS);

                //Agarramos la key de la persona creada
                ResultSet rs = stmt.getGeneratedKeys();
                logActivity(rs, "persona", event_type);
                return true;
            } catch (SQLException ex) {
                Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return false;
    }

    public boolean createUser(String name, String password, int ci, boolean admin, int creator, boolean blocked, int eventType) {
        if (isConnected()) {
            try {
                stmt = db_connection.createStatement(ResultSet.CONCUR_UPDATABLE, ResultSet.TYPE_FORWARD_ONLY);
                stmt.executeUpdate(
                        "INSERT INTO usuario (nombre_usuario, contraseña, ci_persona, admin, " +
                                "creador, bloqueado) VALUES ('" + name + "',' a" +
                                "'," + ci + ",'" + admin + "'," + creator + ",'" + blocked + "')",
                        Statement.RETURN_GENERATED_KEYS);
                ResultSet rs = stmt.getGeneratedKeys();
                int id_usuario = 0;
                //Se crea el usuario y se genera la contraseña segun el id
                if (rs.first()) {
                    id_usuario = rs.getInt(1);
                    String newPassword = PasswordManager.getInstance().generatePassword(password, id_usuario);
                    stmt.executeUpdate(
                            "update usuario set contraseña ='" + newPassword + "' where id_usuario = " + id_usuario);
                    Date date = new Date(Calendar.getInstance().getTime().getTime());
                    stmt.executeUpdate("INSERT INTO log_usuario (id_usuario,fecha_registro,id_evento,usuario_" +
                            "modificado) VALUES (" + CurrentUser.getCurrentUser().get_userId() + ",'" + date + "',"
                            + eventType + "," + id_usuario + ")");

                    return true;
                }
                return false;
            } catch (SQLException ex) {
                Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return false;
    }

    public boolean createAddRoleToUserRequest(String id_role, String id_user, String id_creator) {
        boolean result = false;
        if (isConnected()) {
            try {
                stmt = db_connection.createStatement(ResultSet.CONCUR_UPDATABLE, ResultSet.TYPE_FORWARD_ONLY);
                String sql = "SELECT * FROM usuario_rol WHERE id_rol = " + id_role + " AND id_usuario = " + id_user;
                ResultSet existsRegister = stmt.executeQuery(sql);

                if (!existsRegister.first()) {
                    result = true;
                    stmt = db_connection.createStatement(ResultSet.CONCUR_UPDATABLE, ResultSet.TYPE_FORWARD_ONLY);
                    sql = "INSERT INTO usuario_rol (id_rol, id_usuario, activo, id_creador) VALUES(" + id_role + "," + id_user + ", true," + id_creator + ")";
                    stmt.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
                    logPermission(id_user, id_role, "log_usuario_rol", 1);
                }

            } catch (SQLException ex) {
                Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return result;
    }

    public void addRelation(String id_Quitado, String tablename, String id_alQueQuitan, String secondTablename, String id_creator) {
        if (isConnected()) {
            try {
                stmt = db_connection.createStatement(ResultSet.CONCUR_UPDATABLE, ResultSet.TYPE_FORWARD_ONLY);
                String sql = "INSERT INTO " + tablename + "_" + secondTablename + "(id_" + tablename + ", id_" + secondTablename +
                        ") VALUES ( " + id_Quitado + " , " + id_alQueQuitan + " ) ON CONFLICT (id_" + tablename + ", id_" + secondTablename + ") " +
                        "DO UPDATE set activo = true ; ";
                stmt.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);

                logRelation(id_Quitado, id_alQueQuitan,tablename, secondTablename, 2);
            } catch (SQLException ex) {
                Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void logRelation(String id_quitado,String id_alQueQuitan, String tablename1, String tablename2, int eventType ){
        String tablename =  "log_" + tablename1 + "_" + tablename2;
        try {
            Date date = new Date(Calendar.getInstance().getTime().getTime());
            String sql = "INSERT INTO " + tablename + " (id_evento,fecha_registro,id_usuario,id_" + tablename +
                    ",id_" + tablename2 +") VALUES (" + eventType + ",\'" + date + "\'," +
                    CurrentUser.getCurrentUser().get_userId() + "," + id_quitado + "," + id_alQueQuitan + ")";
            System.out.println(sql);
            stmt.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean createUserEdit(String username, String password, int ci_persona, int creador, int autorizador, boolean admin, boolean blocked, boolean activo, int availabletries) {
        if (isConnected()) {
            try {
                stmt = db_connection.createStatement(ResultSet.CONCUR_UPDATABLE, ResultSet.TYPE_FORWARD_ONLY);
                stmt.executeUpdate(
                        "INSERT INTO usuario (nombre_usuario, contraseña, ci_persona, admin, " +
                                "creador, bloqueado, autorizador, activo, availabletries) VALUES ('" + username + "',' a" +
                                "'," + ci_persona + ",'" + admin + "'," + creador + ",'" + blocked + "'," + autorizador + "," + activo + "," + availabletries + ")",
                        Statement.RETURN_GENERATED_KEYS);
                ResultSet rs = stmt.getGeneratedKeys();
                int id_usuario = 0;
                //Se crea el usuario y se genera la contraseña segun el id
                if (rs.first()) {
                    id_usuario = rs.getInt(1);
                    String newPassword = PasswordManager.getInstance().generatePassword(password, id_usuario);
                    stmt.executeUpdate(
                            "update usuario set contraseña ='" + newPassword + "' where id_usuario = " + id_usuario);
                    Date date = new Date(Calendar.getInstance().getTime().getTime());
                    stmt.executeUpdate("INSERT INTO log_usuario (id_usuario,fecha_registro,id_evento,usuario_" +
                            "modificado) VALUES (" + CurrentUser.getCurrentUser().get_userId() + ",'" + date + "',"
                            + 3 + "," + id_usuario + ")");

                    return true;
                }
                return false;
            } catch (SQLException ex) {
                Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return false;
    }

    public boolean createModel(String name, String description, String tablename, int event_type) {
        if (isConnected()) {
            try {
                stmt = db_connection.createStatement();
                String query2 = "INSERT INTO " + tablename + " (nombre_" + tablename +
                        ", descripcion) VALUES ('" + name + "'," + description + ")";
                stmt.executeUpdate("INSERT INTO " + tablename + " (nombre_" + tablename +
                        ", descripcion) VALUES ('" + name + "','" + description + "')", Statement.RETURN_GENERATED_KEYS);

                //Agarramos la key del model creado
                ResultSet rs = stmt.getGeneratedKeys();
                if (rs.next()) {
                    int modelKey = rs.getInt(1);
                    Date date = new Date(Calendar.getInstance().getTime().getTime());
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append("INSERT INTO log_");
                    stringBuilder.append(tablename);
                    stringBuilder.append(" (id_");
                    stringBuilder.append(tablename);
                    stringBuilder.append(", fecha_registro, id_usuario, id_evento) VALUES ('");
                    stringBuilder.append(String.valueOf(modelKey));
                    stringBuilder.append("','");
                    stringBuilder.append(date);
                    stringBuilder.append("','");
                    stringBuilder.append(CurrentUser.getCurrentUser().get_userId());
                    stringBuilder.append("','");
                    stringBuilder.append(event_type);
                    stringBuilder.append("')");
                    String query = stringBuilder.toString();
                    stmt.executeUpdate(query);
                    return true;
                }
            } catch (SQLException ex) {
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
        if (isConnected()) {
            try {
                stmt = db_connection.createStatement(ResultSet.CONCUR_UPDATABLE, ResultSet.TYPE_FORWARD_ONLY);
                ResultSet rs = stmt.executeQuery("SELECT * FROM " + tableName);
                return rs;
            } catch (SQLException ex) {
                Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }

    public int getTableCount(String tableName) {
        if (isConnected()) {
            try {
                stmt = db_connection.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT count(*) FROM " + tableName);
                rs.next();
                int count = rs.getInt(1);
                rs.close();
                return count;
            } catch (SQLException ex) {
                Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return 0;
    }

    private void logActivity(ResultSet rs, String tablename, int event_type) {
        try {
            if (rs.next()) {
                int modelKey = rs.getInt(1);
                logActivity(String.valueOf(modelKey), tablename, event_type);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void logPermission(String obj1, String obj2, String tablename, int event_type) {
        try {
            Date date = new Date(Calendar.getInstance().getTime().getTime());
            String sql = "INSERT INTO " + tablename + " (id_evento,fecha_registro,id_usuario,id_usuario_modificado,id_rol) VALUES (" + event_type + ",\'" + date + "\'," +
                    CurrentUser.getCurrentUser().get_userId() + "," + obj1 + "," + obj2 + ")";
            System.out.println(sql);
            stmt.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void logActivity(String modelKey, String tablename, int event_type) {
        try {
            Date date = new Date(Calendar.getInstance().getTime().getTime());
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("INSERT INTO log_");
            stringBuilder.append(tablename);
            if (tablename.equals("usuario")) {
                stringBuilder.append(" (usuario_modificado");
            } else {
                stringBuilder.append(" (id_");
                stringBuilder.append(tablename);
            }
            stringBuilder.append(", fecha_registro, id_usuario, id_evento) VALUES ('");
            stringBuilder.append(modelKey);
            stringBuilder.append("','");
            stringBuilder.append(date);
            stringBuilder.append("','");
            stringBuilder.append(CurrentUser.getCurrentUser().get_userId());
            stringBuilder.append("','");
            stringBuilder.append(event_type);
            stringBuilder.append("')");
            String query = stringBuilder.toString();
            stmt.executeUpdate(query);
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //Deletes
    public void removeRelation(String id_Quitado, String tablename, String id_alQueQuitan, String secondTablename, String id_creator) {
        if (isConnected()) {
            try {
                stmt = db_connection.createStatement(ResultSet.CONCUR_UPDATABLE, ResultSet.TYPE_FORWARD_ONLY);
                String sql = "UPDATE " + tablename + "_" + secondTablename + " SET activo = false WHERE " +
                        "id_" + secondTablename + " = " + id_alQueQuitan + " AND id_" + tablename + " = " +
                        id_Quitado + " ;";
                stmt.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
            } catch (SQLException ex) {
                Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public ResultSet deleteRow(String table_name, String row_id, boolean deleting) {
        if (isConnected()) {
            try {
                stmt = db_connection.createStatement(ResultSet.CONCUR_UPDATABLE, ResultSet.TYPE_FORWARD_ONLY);
                stmt.executeUpdate("UPDATE " + table_name + " SET activo = false WHERE  id_" + table_name + " = " + row_id, Statement.RETURN_GENERATED_KEYS);
                ResultSet rs = stmt.getGeneratedKeys();
                if (deleting) {
                    logActivity(rs, table_name, 2);
                }
            } catch (SQLException ex) {
                Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }

    public ResultSet deleteRowPersona(String table_name, String row_id) {
        if (isConnected()) {
            try {
                stmt = db_connection.createStatement(ResultSet.CONCUR_UPDATABLE, ResultSet.TYPE_FORWARD_ONLY);
                stmt.executeUpdate("UPDATE " + table_name + " SET activo = false WHERE cedula = " + row_id, Statement.RETURN_GENERATED_KEYS);
                ResultSet rs = stmt.getGeneratedKeys();
                logActivity(rs, table_name, 2);
            } catch (SQLException ex) {
                Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }


    //Updates
    public void updateModel(String row_id, String new_name, String new_desc, String tablename) {
        this.deleteRow(tablename, row_id, false);
        this.createModel(new_name, new_desc, tablename, 3);
    }

    public void updatePerson(String id, String newName, String newAdres, String newPhone) {
        this.deleteRowPersona("persona", id);
        this.createPerson(Integer.parseInt(id), newName, newAdres, Integer.parseInt(newPhone), 1);
    }

    public void updateUser(String id, String username, String password, boolean blocked, boolean withPassword, int eventType, int creador, int autorizador, int ci_persona, boolean activo, boolean admin, int availabletries) {
        this.deleteRow("usuario", id, false);
        this.createUserEdit(username, password, ci_persona, creador, autorizador, admin, blocked, activo, availabletries);
    }

    //Role asignments
    public void putAutorization(String id_usuario, String autorizante) {
        if (isConnected()) {
            try {
                stmt = db_connection.createStatement(ResultSet.CONCUR_UPDATABLE, ResultSet.TYPE_FORWARD_ONLY);
                String sql = "UPDATE usuario SET autorizador = " + autorizante + " WHERE id_usuario = " + id_usuario;
                stmt.executeUpdate(sql, 1);
                this.logActivity(id_usuario, "usuario", 5);
            } catch (SQLException ex) {
                Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public ResultSet getFuncionalitiesForRole(String id_role) {
        if (isConnected()) {
            try {
                stmt = db_connection.createStatement(ResultSet.CONCUR_UPDATABLE, ResultSet.TYPE_FORWARD_ONLY);
                String sql = "SELECT id_funcionalidad, nombre_funcionalidad, descripcion, CAST(CASE WHEN (EXISTS (SELECT id_rol" +
                        " from funcionalidad_rol " +
                        "where id_rol = \'" + id_role + "\' AND funcionalidad_Rol.id_funcionalidad = funcionalidad.id_funcionalidad" +
                        " AND funcionalidad_rol.activo = true )) " +
                        "THEN true ELSE false END AS BOOL) AS Seleccionado FROM funcionalidad where funcionalidad.activo = true";
                return stmt.executeQuery(sql);
            } catch (SQLException ex) {
                Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }

    public ResultSet getUsersForRole(String id_role) {
        if (isConnected()) {
            try {
                stmt = db_connection.createStatement(ResultSet.CONCUR_UPDATABLE, ResultSet.TYPE_FORWARD_ONLY);
                String sql = "SELECT id_usuario, nombre_usuario, CAST(CASE WHEN (EXISTS (SELECT id_rol from usuario_rol where id_rol = \'" + id_role +
                        "\' AND usuario_rol.id_usuario = usuario.id_usuario AND usuario_rol.id_autorizante IS NOT NULL AND usuario_rol.activo = true)) " +
                        "THEN true ELSE false END AS BOOL) AS Seleccionado FROM usuario";
                return stmt.executeQuery(sql);
            } catch (SQLException ex) {
                Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }

    public ResultSet getMenusForFuncionality(String funcionalityId) {
        if (isConnected()) {
            try {
                stmt = db_connection.createStatement(ResultSet.CONCUR_UPDATABLE, ResultSet.TYPE_FORWARD_ONLY);
                String sql = "SELECT nombre_menu, descripcion, id_menu, CAST(CASE WHEN (EXISTS (SELECT id_funcionalidad" +
                        " from funcionalidad_menu " +
                        "where id_funcionalidad = \'" + funcionalityId + "\' AND funcionalidad_menu.id_menu = menu.id_menu" +
                        " AND funcionalidad_menu.activo = true )) " +
                        "THEN true ELSE false END AS BOOL) AS Seleccionado FROM menu where menu.activo = true";
                return stmt.executeQuery(sql);
            } catch (SQLException ex) {
                Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }

    //Existance
    public boolean existsPerson(String ci) {
        ResultSet person = search("persona", "cedula", ci);
        try {
            return person.first();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    public void putRoleToUserAutorization(String userToAttach, String roleToAttach, String authorizer) {
        if (isConnected()) {
            try {
                stmt = db_connection.createStatement(ResultSet.CONCUR_UPDATABLE, ResultSet.TYPE_FORWARD_ONLY);
                System.out.println(userToAttach);
                System.out.println(roleToAttach);
                System.out.println(authorizer);
                String sql = "UPDATE usuario_rol SET id_autorizante = " + authorizer + " WHERE id_usuario = " + userToAttach + " AND id_rol = " + roleToAttach;
                System.out.println(sql);
                stmt.executeUpdate(sql);
                logPermission(userToAttach, roleToAttach, "log_usuario_rol", 5);
            } catch (SQLException ex) {
                Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
