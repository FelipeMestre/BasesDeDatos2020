package model;

public class NewUserRequest extends Request {

    private String newUserName;

    public NewUserRequest(String requesterName, String newUserName) {
        super(requesterName);
        this.newUserName = newUserName;
    }

    public String getNewUserName() {
        return newUserName;
    }

}
