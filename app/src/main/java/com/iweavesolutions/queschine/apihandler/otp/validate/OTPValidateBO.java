package com.iweavesolutions.queschine.apihandler.otp.validate;

/**
 * Created by bharath.simha on 06/05/16.
 */
public class OTPValidateBO {

    protected String status;
    protected String message;
    protected String data;

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

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
