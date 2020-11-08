package model;

public class Log_Role_User extends Log{

    private String userAttached;
    private String roleAttached;

    public Log_Role_User(String logID, String eventType, String create_date, String creator_name, String userAttached, String roleAttached) {
        super(logID, eventType, create_date, creator_name);
        this.userAttached = userAttached;
        this.roleAttached = roleAttached;
    }

    public String getUserAttached() {
        return userAttached;
    }

    public String getRoleAttached() {
        return roleAttached;
    }
}
