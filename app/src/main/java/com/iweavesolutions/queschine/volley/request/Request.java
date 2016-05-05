package com.iweavesolutions.queschine.volley.request;

/**
 * Created by bharath.simha on 05/15/16.
 */
public class Request<T> {

    private String requestType;
    private String version;

    public String getRequestType() {
        return requestType;
    }

    public void setRequestType(String requestType) {
        this.requestType = requestType;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

}
