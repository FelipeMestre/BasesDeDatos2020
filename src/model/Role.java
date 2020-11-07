package model;

public class Role {

    private String id_role;
    private String role_name;

    public Role(String id_role, String role_name) {
        this.id_role = id_role;
        this.role_name = role_name;
    }

    public String getId_role() {
        return id_role;
    }

    public String getRole_name() {
        return role_name;
    }

    @Override
    public String toString() {
        return this.role_name;
    }
}
