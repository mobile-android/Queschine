package com.iweavesolutions.queschine.apihandler.otp.validate;

import com.android.volley.Request;
import com.google.gson.reflect.TypeToken;
import com.iweavesolutions.queschine.QueschineApplication;
import com.iweavesolutions.queschine.volley.DataHandler;
import com.iweavesolutions.queschine.volley.request.VolleyRequest;

/**
 * Created by bharath.simha on 06/05/16.
 */
abstract public class OTPValidateDataHandler extends DataHandler<OTPValidateBO> {

    public void onValidateOTP(String extensionURL, String otpValue, String authKey) {
        OTPValidatePayload otpValidatePayload = new OTPValidatePayload();
        otpValidatePayload.setOtp(otpValue);
        OTPValidateRequest otpValidateRequest = new OTPValidateRequest(Request.Method.POST, VolleyRequest.BASE_API_URL + extensionURL,
                authKey, otpValidatePayload, listner, errorListner);
        this.ctype = new TypeToken<OTPValidateBO>() {
        }.getType();
        QueschineApplication.addToRequestQueue(otpValidateRequest);
    }
}
