package com.iweavesolutions.queschine.apihandler.otp.request;

import com.android.volley.Request;
import com.iweavesolutions.queschine.QueschineApplication;
import com.iweavesolutions.queschine.volley.DataHandler;
import com.iweavesolutions.queschine.volley.request.VolleyRequest;

/**
 * Created by bharath.simha on 06/05/16.
 */
abstract public class OTPRequestDataHandler extends DataHandler<OTPRequestBO> {

    public void onRequestOTP(String extensionURL, String authKey) {
        OTPRequest otpRequest = new OTPRequest(Request.Method.GET, VolleyRequest.BASE_API_URL + extensionURL, authKey, listner, errorListner);
        this.request = otpRequest;
        QueschineApplication.addToRequestQueue(otpRequest);
    }
}
