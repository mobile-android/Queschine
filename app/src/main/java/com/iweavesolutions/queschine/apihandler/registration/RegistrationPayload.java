package com.iweavesolutions.queschine.apihandler.registration;

/**
 * Created by bharath.simha on 05/05/16.
 */
public class RegistrationPayload {

    protected String name;
    protected String emailId;
    protected String password;
    protected String phoneNumber;
    protected String role = "APPUSER";
    protected String channel = "ANDROID";

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

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
