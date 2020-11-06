package model;

public class Log_Person extends Log  {


    private String id_persona;

    public Log_Person(String logID, String eventType, String create_date, String creator_name,String id_persona) {
        super(logID, eventType, create_date, creator_name);
        this.id_persona = id_persona;
    }

    public String getId_persona() {
        return id_persona;
    }
}
