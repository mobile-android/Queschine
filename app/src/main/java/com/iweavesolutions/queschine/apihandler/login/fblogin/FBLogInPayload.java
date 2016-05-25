package com.iweavesolutions.queschine.apihandler.login.fblogin;

/**
 * Created by bharath.simha on 24/05/16.
 */
public class FBLogInPayload {

    protected String emailId;

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    protected String name;
    protected String channel = "ANDROID";

}
