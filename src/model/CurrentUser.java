package model;

public class CurrentUser {

    private int user_id;

    private String rol;

    private String userName;

    private static CurrentUser user;

    public CurrentUser(){
    }

    public static CurrentUser getCurrentUser(){
        if (user == null){
            user = new CurrentUser();
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
