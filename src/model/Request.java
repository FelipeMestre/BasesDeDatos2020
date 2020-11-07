package model;

public abstract class Request {

    private String requesterName;
    private String requestID;
    private String requesterID;

    public Request(String requestID, String requesterName, String requesterID) {
        this.requesterName = requesterName;
        this.requestID = requestID;
        this.requesterID  =requesterID;
    }

    public String getRequesterID() {
        return requesterID;
    }

    public String getRequesterName() {
        return requesterName;
    }

    public String getRequestID() {
        return requestID;
    }
}
