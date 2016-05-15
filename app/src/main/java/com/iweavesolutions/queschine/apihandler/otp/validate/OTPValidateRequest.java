package com.iweavesolutions.queschine.apihandler.otp.validate;

import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.google.gson.reflect.TypeToken;
import com.iweavesolutions.queschine.QueschineApplication;
import com.iweavesolutions.queschine.volley.request.VolleyRequest;

/**
 * Created by bharath.simha on 06/05/16.
 */
public class OTPValidateRequest extends VolleyRequest<OTPValidateBO> {

    OTPValidatePayload otpValidatePayload;

    public OTPValidateRequest(int method, String url, String header, OTPValidatePayload otpValidatePayload, Response.Listener listener, Response.ErrorListener errorListener) {
        super(method, url, new TypeToken<OTPValidateBO>() {
        }.getType(), listener, errorListener);
        this.otpValidatePayload = otpValidatePayload;
        addHeader("Application-Authorization", "Bearer " + header);
        Log.d("Authorization", "Bearer " + header);
    }

    @Override
    public String getBodyContentType() {
        return "application/x-www-form-urlencoded";
    }

    @Override
    public byte[] getBody() throws AuthFailureError {
        String configString = QueschineApplication.getGsonInstance().toJson(otpValidatePayload);
        Log.d("Payload::OTPValdateReq:", configString);
        return configString.getBytes();
    }
}
