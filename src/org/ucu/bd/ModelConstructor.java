package org.ucu.bd;

import javax.xml.crypto.Data;
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
}
