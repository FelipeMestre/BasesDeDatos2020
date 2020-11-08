package model;

public class AddRoleUserRequest extends Request{
    private String attachedRoleName;
    private String requestedRoleID;
    private String requestedUserID;
    private String attachedUserName;

    public AddRoleUserRequest(String requestID, String requesterName, String requesterID, String attachedRoleName, String attachedUserName,
                              String requestedRoleID, String requestedUserID) {
        super(requestID, requesterName, requesterID);
        this.requestedRoleID = requestedRoleID;
        this.requestedUserID = requestedUserID;
        this.attachedRoleName = attachedRoleName;
        this.attachedUserName = attachedUserName;
    }
    public String getAttachedRoleName() {
        return attachedRoleName;
    }

    public String getAttachedUserName() {
        return attachedUserName;
    }

    public String getRequestedRoleID() {
        return requestedRoleID;
    }

    public String getRequestedUserID() {
        return requestedUserID;
    }
}
