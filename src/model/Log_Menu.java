package model;

public class Log_Menu extends Log {
    private String menu_name;

    public Log_Menu(String logID, String eventType, String create_date, String creator_name, String menu_name) {
        super(logID, eventType, create_date, creator_name);
        this.menu_name = menu_name;
    }

    public String getMenu_name() {
        return menu_name;
    }
}
