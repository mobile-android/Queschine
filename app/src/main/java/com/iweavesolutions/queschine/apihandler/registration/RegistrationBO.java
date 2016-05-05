package com.iweavesolutions.queschine.apihandler.registration;

import java.util.ArrayList;

/**
 * Created by bharath.simha on 05/05/16.
 */
public class RegistrationBO {

    protected String status;
    protected String message;
    protected ArrayList<RegistrationAuthTokens> data;

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

    public ArrayList<RegistrationAuthTokens> getData() {
        return data;
    }

    public void setData(ArrayList<RegistrationAuthTokens> data) {
        this.data = data;
    }
}
