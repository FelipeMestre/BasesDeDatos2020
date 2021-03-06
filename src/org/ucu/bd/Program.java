package org.ucu.bd;

import java.util.HashMap;

import ui.*;

public class Program {

    private static String CONFIG_PATH = "config.txt";
    private static HashMap<String, Object> CONFIG;
    private static String user = "postgres";
    private static String password = "ELPEPE";
    private static String host = "obligatoriodb.ddns.net";
    private static int port = 5432;
    private static String db_name = "postgres";

    public static void main(String[] args) {
        try {
            Database primerDB = new Database(user, password, Database.generateUri(host, port, db_name));
            primerDB.initConnection();
            ModelConstructor model = new ModelConstructor(primerDB);
            Login user_interface = new Login(primerDB, model);
            user_interface.initialize();
        } catch(Exception e){
            System.out.println("Error durante la inicialización\n" + e.getMessage());
            e.printStackTrace();
        }
    }

    private static void Initialize() throws Exception{
        TxtDictionaryBuilder dictionaryBuilder = new TxtDictionaryBuilder();
        CONFIG = dictionaryBuilder.buildDictionary(CONFIG_PATH);

        if (!CONFIG.containsKey("usuario")){
            throw new Exception("La configuración no tiene usuario");
        }
        if (!CONFIG.containsKey("password")){
            throw new Exception("La configuración no tiene contraseña");
        }
        if (!CONFIG.containsKey("host")){
            throw new Exception("La configuración no tiene un host");
        }
        if (!CONFIG.containsKey("db_name")){
            throw new Exception("La configuración no tiene un nombre de base de datos");
        }

        password = CONFIG.get("password").toString();
        user = CONFIG.get("usuario").toString();
        host = CONFIG.get("host").toString();
        db_name = CONFIG.get("db_name").toString();
    }

}
