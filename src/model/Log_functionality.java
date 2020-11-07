package model;

public class Log_functionality extends Log {
    private String func_name;

    public Log_functionality(String logID, String eventType, String create_date, String creator_name, String func_name) {
        super(logID, eventType, create_date, creator_name);
        this.func_name = func_name;
    }

    public String getFunc_name() {
        return func_name;
    }
}
