package org.ucu.bd;

import Utils.PasswordManager;

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
        return this.db.createPerson(ci,name,direction,phone);
    }

    public boolean createUser(String username, String password, int ci,boolean admin,int creator){
        return db.createUser(username,password,ci,admin,creator,false);
    }

    //Updates
    public void updateRole(String role_id, String newName, String newDesc){
        db.updateModel(role_id, newName, newDesc,"Rol");
    }

    public void updatePerson(String id, String newName, String newAdres,String newPhone){
        db.updatePerson(id, newName, newAdres,newPhone);
    }

    public void updateUser(String userId,String newUsername,String newPassword,boolean blocked,boolean withPassword){
        db.updateUser(userId,newUsername,newPassword,blocked,withPassword);
    }

    //Strings de arrays

    private String[][] getStringArray(ResultSet rs) {
        String[][] result = null;
        try {
            int columnsNumber = rs.getMetaData().getColumnCount();
            rs.last();
            result = new String[rs.getRow()][columnsNumber];
            if (rs.first()){
                int rowNumber = 0;
                do {
                    for (int i = 1; i <= columnsNumber; i++) {
                        result[rowNumber][i - 1] = rs.getString(i);
                    }
                    rowNumber++;
                } while(rs.next());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public String[][] getRoles() {
        return this.getStringArray(db.getAllElements("vista_roles"));
    }

    public String[][] getUsuarios() {
        return this.getStringArray(db.getAllElements("vista_usuarios"));
    }

    public String[][] getPersonas() {
        return this.getStringArray(db.getAllElements("vista_personas"));
    }

    //Totals

    public int totalRoles(){
        return this.db.getTableCount("rol");
    }

    public int totalUsers() { return this.db.getTableCount("usuario"); }

    public int totalPersons() { return this.db.getTableCount("persona"); }

    //Model Methods

    public boolean createModel(String name, String description, String tablename, Component father){
        if (name != null && name.length() <= 50 && description != null && description.length() <= 200){
            return this.db.createModel(name,description,tablename);
        } else {
            if(name == null){
                JOptionPane.showMessageDialog(father,"Ingrese Nombre");
            } else if (name.length() > 50){
                JOptionPane.showMessageDialog(father,"El nombre debe ser menor a 50 caracteres");
            } else if(description == null){
                JOptionPane.showMessageDialog(father,"Ingrese Descripcion");
            } else if(description.length() > 200){
                JOptionPane.showMessageDialog(father,"La descripcion debe ser menor a 200 caracteres");
            }
            return false;
        }

    }

    public void deleteModel(String id, String tablename){
        if (tablename.equals("persona")){
            db.deleteRow(tablename,id,"cedula");
        } else {
            db.deleteRow(tablename, id, "id_" + tablename);
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
