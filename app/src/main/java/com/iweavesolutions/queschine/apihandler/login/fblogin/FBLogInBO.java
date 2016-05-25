package com.iweavesolutions.queschine.apihandler.login.fblogin;

/**
 * Created by bharath.simha on 24/05/16.
 */
public class FBLogInBO {

    protected String status;
    protected String message;
    protected FBLogInAuthTokens data;

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

    public FBLogInAuthTokens getData() {
        return data;
    }

    public void setData(FBLogInAuthTokens data) {
        this.data = data;
    }
}
