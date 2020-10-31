package model;

public class currentUser {

    private int user_id;

    private static currentUser user;

    public currentUser(){
    }

    public static currentUser getCurrentUser(){
        if (user == null){
            user = new currentUser();
        }
        return user;
    }

    public void setUser_id(int userId){
        this.user_id = userId;
    }
}
