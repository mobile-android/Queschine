package com.iweavesolutions.queschine.apihandler.login;

/**
 * Created by bharath.simha on 05/05/16.
 */
public class LogInBO {

    protected String status;
    protected String message;
    protected LogInAuthTokens data;

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

    public LogInAuthTokens getData() {
        return data;
    }

    public void setData(LogInAuthTokens data) {
        this.data = data;
    }
}
