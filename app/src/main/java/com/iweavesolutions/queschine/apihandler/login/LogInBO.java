package com.iweavesolutions.queschine.apihandler.login;

import java.util.ArrayList;

/**
 * Created by bharath.simha on 05/05/16.
 */
public class LogInBO {

    protected String status;
    protected String message;
    protected ArrayList<LogInAuthTokens> data;

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

    public ArrayList<LogInAuthTokens> getData() {
        return data;
    }

    public void setData(ArrayList<LogInAuthTokens> data) {
        this.data = data;
    }
}
