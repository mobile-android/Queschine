package com.iweavesolutions.queschine.apihandler.login;

/**
 * Created by bharath.simha on 05/05/16.
 */
public class LogInPayload {

    protected String emailId;
    protected String password;
    protected String phoneNumber;

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
