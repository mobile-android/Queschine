package com.iweavesolutions.queschine.apihandler.otp.validate;

import com.android.volley.Request;
import com.iweavesolutions.queschine.QueschineApplication;
import com.iweavesolutions.queschine.volley.DataHandler;
import com.iweavesolutions.queschine.volley.request.VolleyRequest;

/**
 * Created by bharath.simha on 06/05/16.
 */
abstract public class OTPDataHandler extends DataHandler<OTPValidateBO> {

    public void onValidateOTP(String extensionURL, String otpValue, String authKey) {
        OTPValidateRequest otpValidateRequest = new OTPValidateRequest(Request.Method.POST, VolleyRequest.BASE_API_URL + extensionURL,
                authKey, otpValue, listner, errorListner);
        this.request = otpValidateRequest;
        QueschineApplication.addToRequestQueue(otpValidateRequest);
    }
}
