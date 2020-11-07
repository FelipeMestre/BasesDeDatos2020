package model;

public abstract class Request {

    private String requesterName;

    public Request(String requesterName) {
        this.requesterName = requesterName;
    }

}
