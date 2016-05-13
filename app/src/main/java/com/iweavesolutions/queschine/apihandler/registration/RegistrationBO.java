package com.iweavesolutions.queschine.apihandler.registration;

/**
 * Created by bharath.simha on 05/05/16.
 */
public class RegistrationBO {

    protected String status;
    protected String message;
    protected RegistrationAuthTokens data;

    public RegistrationAuthTokens getData() {
        return data;
    }

    public void setData(RegistrationAuthTokens data) {
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
