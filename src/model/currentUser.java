package model;

public class currentUser {

    private int user_id;

    private String rol;

    private String userName;

    private static currentUser user;

    public currentUser(){
    }

    public static currentUser getCurrentUser(){
        if (user == null){
            user = new currentUser();
        }
        return user;
    }

    public int get_userId(){
        return this.user_id;
    }

    public void setUser_id(int userId){
        this.user_id = userId;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getName_Role(){
        return new StringBuilder().append(this.userName).append(this.rol).toString();
    }
}
