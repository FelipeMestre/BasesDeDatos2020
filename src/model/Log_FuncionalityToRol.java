package model;

public class Log_FuncionalityToRol extends Log {

    String role_name;
    String funcionalidad_name;
    public Log_FuncionalityToRol(String logID, String eventType, String create_date, String creator_name, String id_rol, String id_funcionality) {
        super(logID, eventType, create_date, creator_name);
        this.role_name = id_rol;
        this.funcionalidad_name = id_funcionality;
    }

    public String getRole_name() {
        return role_name;
    }

    public String getFuncionalidad_name() {
        return funcionalidad_name;
    }
}
