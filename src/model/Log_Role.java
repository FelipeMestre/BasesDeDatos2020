package model;

public class Log_Role extends Log {
    private String role_name;

    public Log_Role(String logID, String eventType, String create_date, String creator_name, String role_name) {
        super(logID, eventType, create_date, creator_name);
        this.role_name = role_name;
    }

    public String getRole_name() {
        return role_name;
    }
}
