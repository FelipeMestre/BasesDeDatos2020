package model;

public class NewUserRequest extends Request {

    private String newUserName;

    public NewUserRequest(String requestID, String requesterName, String requesterID ,String newUserName) {
        super(requestID, requesterName, requesterID);
        this.newUserName = newUserName;
    }

    public String getNewUserName() {
        return newUserName;
    }

}
