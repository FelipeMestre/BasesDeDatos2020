package model;

public class Log_User extends Log {
    private String user_name;

    public Log_User(String logID, String eventType, String create_date, String creator_name, String user_name) {
        super(logID, eventType, create_date, creator_name);
        this.user_name = user_name;
    }

    public String getUser_name() {return user_name;}
}
