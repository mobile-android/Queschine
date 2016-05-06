package com.iweavesolutions.queschine.apihandler.otp.validate;

import com.android.volley.Response;
import com.google.gson.reflect.TypeToken;
import com.iweavesolutions.queschine.apihandler.otp.request.OTPRequestBO;
import com.iweavesolutions.queschine.volley.request.VolleyRequest;

/**
 * Created by bharath.simha on 06/05/16.
 */
public class OTPValidateRequest extends VolleyRequest<OTPValidateBO> {
    public OTPValidateRequest(int method, String url, String header, String otpValue, Response.Listener listener, Response.ErrorListener errorListener) {
        super(method, url, new TypeToken<OTPValidateBO>() {
        }.getType(), listener, errorListener);
        addHeader("Application-Authorization", "Bearer " + header);
        addPostParams("reason", "1");
        addPostParams("otp", otpValue);
    }

    @Override
    public String getBodyContentType() {
        return "application/x-www-form-urlencoded";
    }


}
