package com.thedevpiece.api;

/**
 * @author Gabriel Francisco <peo_gfsilva@uolinc.com>
 */
public class ResponseObject {
    private int status;
    private String location;
    private String body;

    public ResponseObject() {
    }

    public static ResponseObject response() {
        return new ResponseObject();
    }

    public ResponseObject(int status, String location, String body) {
        this.status = status;
        this.location = location;
        this.body = body;
    }

    public ResponseObject withStatus(int status) {
        return new ResponseObject(status, location, body);
    }

    public ResponseObject withLocation(String location) {
        return new ResponseObject(status, location, body);
    }

    public ResponseObject withBody(String body) {
        return new ResponseObject(status, location, body);
    }

    public int getStatus() {
        return status;
    }

    public String getLocation() {
        return location;
    }

    public String getBody() {
        return body;
    }
}
