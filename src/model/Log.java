package model;

public abstract class Log {
    private String logID;
    private String eventType;
    private String create_date;
    private String creator_name;


    public Log(String logID, String eventType, String create_date, String creator_name) {
        this.logID = logID;
        this.eventType = eventType;
        this.create_date = create_date;

        this.creator_name = creator_name;
    }

    public String getCreator_name() {
        return creator_name;
    }

    public String getLogID() {
        return logID;
    }

    public String getEventType() {
        return eventType;
    }

    public String getCreate_date() {
        return create_date;
    }

}
