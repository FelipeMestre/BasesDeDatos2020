package org.ucu.bd;

import model.Log;
import model.Log_Role;

import javax.swing.*;
import java.awt.*;
import java.sql.ResultSet;

public class ModelConstructor {

    private Database db;

    public ModelConstructor(Database db){
        this.db = db;
    }

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

    public int totalRoles(){
        return this.db.getTableCount("rol");
    }

    public int activeRoles() {
        return this.db.getTableCount("vista_roles_activos");
    }

    public String[][] getUsuarios() {
        return this.getStringArray(db.getAllElements("vista_usuarios"));
    }

    public int totalUsers() { return this.db.getTableCount("usuario"); }

    public int bloquedUsers() { return this.db.getTableCount("vista_usuario_bloqueado"); }

    public String[][] getPersonas() {
        return this.getStringArray(db.getAllElements("vista_personas"));
    }

    public int totalPersons() { return this.db.getTableCount("persona"); }

    public boolean createModel(String name, String description, String tablename, Component father){
        if (name != null && name.length() <= 50 && description != null && description.length() <= 200){
            return this.db.createModel(name,description,tablename,1);
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

    public void updateRole(String role_id, String newName, String newDesc){
        db.updateModel(role_id, newName, newDesc,"Rol");
    }

    public Log_Role[] getRoleLog() {
        ResultSet rs = retrieveRoleLog();
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

    private ResultSet retrieveRoleLog(){
        return db.getAllElements("vista_cambios_roles");
    }
    public void updatePerson(String id, String newName, String newAdres,String newPhone){
        db.updatePerson(id, newName, newAdres,newPhone);
    }

    public void deleteModel(String id, String tablename){
        if (tablename.equals("persona")){
            db.deleteRowPersona(tablename,id);
        } else {
            db.deleteRow(tablename, id);
        }
    }


    public boolean createPersona(int ci, String name, String direction, int phone){
        return this.db.createPerson(ci,name,direction,phone, 1);
    }

}
