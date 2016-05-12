package com.iweavesolutions.queschine.apihandler.registration;

/**
 * Created by bharath.simha on 05/05/16.
 */
public class RegistrationPayload {

    protected String name = "Gupta1";
    protected String emailId = "testingbharathh4@gmail.com";
    protected String password = "1234561";
    protected String phoneNumber = "9885600908";
    protected String role = "APPUSER";
    protected String channel = "ANDROID";

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = "dadf";
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = "asdfgf@gmaail.com";
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = "asdfasd";
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = "9898980766";
    }
}
