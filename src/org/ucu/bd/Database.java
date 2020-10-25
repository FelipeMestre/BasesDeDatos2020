package org.ucu.bd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {

    private String username;
    private String password;
    private String uri;
    private Connection db_connection;

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
}
