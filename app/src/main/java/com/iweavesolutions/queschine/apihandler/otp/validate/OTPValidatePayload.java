package com.iweavesolutions.queschine.apihandler.otp.validate;

/**
 * Created by bharath.simha on 08/05/16.
 */
public class OTPValidatePayload {

    protected String reason = "1";
    protected String otp;

    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }
}
