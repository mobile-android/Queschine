package com.iweavesolutions.queschine.apihandler.otp.request;

import android.util.Log;

import com.android.volley.Response;
import com.google.gson.reflect.TypeToken;
import com.iweavesolutions.queschine.volley.request.VolleyRequest;

/**
 * Created by bharath.simha on 06/05/16.
 */
public class OTPRequest extends VolleyRequest<OTPRequestBO> {
    public OTPRequest(int method, String url, String header, Response.Listener listener, Response.ErrorListener errorListener) {
        super(method, url, new TypeToken<OTPRequestBO>() {
        }.getType(), listener, errorListener);
        addHeader("Application-Authorization", "Bearer " + header);
        Log.d("Authorization", "Bearer " + header);
    }

    @Override
    public String getBodyContentType() {
        return "application/x-www-form-urlencoded";
    }
}
