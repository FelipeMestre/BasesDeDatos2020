package org.ucu.bd;

import Utils.PasswordManager;
import model.*;

import javax.swing.*;
import java.awt.*;
import java.sql.ResultSet;

public class ModelConstructor {

    private Database db;

    public ModelConstructor(Database db){
        this.db = db;
    }

    //Creates

    public boolean createPersona(int ci, String name, String direction, int phone){
        return this.db.createPerson(ci,name,direction,phone, 1);
    }

    public boolean createUser(String username, String password, int ci,boolean admin,int creator){
        return db.createUser(username,password,ci,admin,creator,false,1);
    }

    public boolean createUserEdit(String username, String password, int ci_persona, int creador, int autorizador, boolean admin, boolean blocked, boolean activo, int availabletries) {
        return db.createUserEdit(username, password, ci_persona, creador, autorizador, admin, blocked, activo, availabletries);
    }

    public boolean createModel(String name, String description, String tablename, Component father){
        if (!name.equals("") && name.length() <= 50 && !description.equals("") && description.length() <= 200){
            return this.db.createModel(name,description,tablename,1);
        } else {
            if(name.equals("")){
                JOptionPane.showMessageDialog(father,"Ingrese Nombre");
            } else if (name.length() > 50){
                JOptionPane.showMessageDialog(father,"El nombre debe ser menor a 50 caracteres");
            } else if(description.equals("")){
                JOptionPane.showMessageDialog(father,"Ingrese Descripcion");
            } else if(description.length() > 200){
                JOptionPane.showMessageDialog(father,"La descripcion debe ser menor a 200 caracteres");
            }
            return false;
        }
    }

    //Updates
    public void updateRole(String role_id, String newName, String newDesc){
        db.updateModel(role_id, newName, newDesc,"Rol");
    }

    public void updatePerson(String id, String newName, String newAdres,String newPhone){
        db.updatePerson(id, newName, newAdres,newPhone);
    }

    public void updateUser(String userId,String newUsername,String newPassword,boolean blocked,boolean withPassword, int creador, int autorizador, int ci_persona, boolean activo, boolean admin, int availabletries){
        db.updateUser(userId,newUsername,newPassword,blocked,withPassword,3, creador, autorizador, ci_persona, activo, admin, availabletries);
    }

    public void updateFuncionality(String role_id, String newName, String newDesc){
        db.updateModel(role_id, newName, newDesc,"Funcionalidad");
    }
    public boolean approveUser(String userToApprove, String Authorizer){
        boolean result = !Authorizer.equals(String.valueOf(CurrentUser.getCurrentUser().get_userId()));
        if (result){
            db.putAutorization(userToApprove, Authorizer);
        }
        return result;
    }

    public ResultSet search(String tableName, String column, String value) {
        return db.search(tableName, column, value);
    }

    //Strings de arrays

    private String[][] getStringArray(ResultSet rs) {
        String[][] result = null;
        try {
            int columnsNumber = rs.getMetaData().getColumnCount();
            rs.last();
            int rowsNumber = rs.getRow();
            if (rowsNumber > 0) {
                result = new String[rs.getRow()][columnsNumber];
                rs.first();

                int rowNumber = 0;
                do {
                    for (int i = 1; i <= columnsNumber; i++) {
                        result[rowNumber][i - 1] = rs.getString(i);
                    }
                    rowNumber++;
                } while (rs.next());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public String[][] getRoles() {
        return this.getStringArray(db.getAllElements("vista_roles_activos"));
    }

    public String[][] getUsuarios() {
        return this.getStringArray(db.getAllElements("vista_usuarios_con_autorizacion"));
    }

    public NewUserRequest[][] getUsersPendingAuthorizations(){
        ResultSet rs = retrieveLog("vista_usuarios_sin_autorizacion");
        NewUserRequest[][] result = new NewUserRequest[0][0];
        try {
            rs.last();
            int size = rs.getRow();
            if (size > 0) {
                result = new NewUserRequest[1][size];
                rs.first();
                int rowNumber = 0;
                do {
                    result[0][rowNumber] = new NewUserRequest(rs.getString(1),rs.getString(3), rs.getString(4), rs.getString(2));
                    rowNumber++;
                } while (rs.next());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public String[][] getPersonas() {
        return this.getStringArray(db.getAllElements("vista_personas"));
    }

    public String[][] getMenus(){
        return this.getStringArray(db.getAllElements("vista_menus"));
    }

    public String[][] getFuncionalities(){
        return this.getStringArray(db.getAllElements("vista_funcionalidades"));
    }

    //Logs

    public Log_Person[] getPersonLog(){
        ResultSet rs = retrieveLog("vista_cambios_personas");
        Log_Person[] result = new Log_Person[0];
        try {
            rs.last();
            int size = rs.getRow();
            if (size > 0) {
                result = new Log_Person[size];
                rs.first();
                int rowNumber = 0;
                do {
                    result[rowNumber] = new Log_Person(rs.getString(1), rs.getString(2), rs.getString(4), rs.getString(3), rs.getString(5));
                    rowNumber++;
                } while (rs.next());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
    public Log_Role[] getRoleLog() {
        ResultSet rs = retrieveLog("vista_cambios_roles");
        Log_Role[] result = new Log_Role[0];
        try {
            rs.last();
            int size = rs.getRow();
            if (size > 0) {
                result = new Log_Role[size];
                rs.first();
                int rowNumber = 0;
                do {
                    result[rowNumber] = new Log_Role(rs.getString(1), rs.getString(2), rs.getString(4), rs.getString(3), rs.getString(5));
                    rowNumber++;
                } while (rs.next());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
    public Log_User[] getUserLog(){
        ResultSet rs = retrieveLog("vista_cambios_usuarios");
        Log_User[] result = new Log_User[0];
        try {
            rs.last();
            int size = rs.getRow();
            if (size > 0) {
                result = new Log_User[size];
                rs.first();
                int rowNumber = 0;
                do {
                    result[rowNumber] = new Log_User(rs.getString("id_registro"),rs.getString("nombre_evento"),
                            rs.getString("fecha_registro"),rs.getString("actor"),rs.getString("victima") );
                    rowNumber++;
                } while (rs.next());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
    private ResultSet retrieveLog(String view){
        return db.getAllElements(view);
    }

    //Totals

    public int totalRoles(){
        return this.db.getTableCount("rol");
    }

    public int totalUsers() { return this.db.getTableCount("usuario"); }

    public int activeUsers(){return this.db.getTableCount("vista_usuarios_activos");}

    public int totalPersons() { return this.db.getTableCount("persona"); }

    public int totalAutorizations(){
        return this.db.getTableCount("modelo_vista_usuarios_sin_autorizacion");
    }

    public void deleteModel(String id, String tablename, boolean deleting) {
        if (tablename.equals("persona")) {
            db.deleteRowPersona(tablename, id);
        } else {
            db.deleteRow(tablename,id, deleting);
        }
    }

    //Utils
    public boolean existsPerson(String ci){
        return db.existsPerson(ci);
    }

    public int bloquedUsers() { return this.db.getTableCount("vista_usuario_bloqueado"); }

    public int activeRoles() {
        return this.db.getTableCount("vista_roles_activos");
    }

    public boolean checkIfCorrectPassword(String userId,String userName, String password, String tablename,Container parent){
        return db.checkIfCorrectPassword(userId,userName, password, "usuario", parent);
    }
}
