package org.ucu.bd;

import javax.swing.*;
import javax.xml.crypto.Data;
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
            result = new String[rs.getRow()][columnsNumber];
            rs.first();

            int rowNumber = 0;
            do {
                for (int i = 1; i <= columnsNumber; i++) {
                    result[rowNumber][i - 1] = rs.getString(i);
                }
                rowNumber++;
            } while(rs.next());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public String[][] getRoles() {
        return this.getStringArray(db.getAllElements("vista_roles"));
    }

    public int totalRoles(){
        return this.db.getTableCount("rol");
    }

    public int activeRoles() {
        return this.db.getTableCount("vista_roles_activos");
    }

    public boolean createModel(String name, String description, String tablename, Component father){
        if (name != null && name.length() <= 50 && description != null && description.length() <= 200){
            this.db.createRol(name,description,tablename);
            return true;
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

}
