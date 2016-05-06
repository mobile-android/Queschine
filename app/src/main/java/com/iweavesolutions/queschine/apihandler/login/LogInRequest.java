package com.iweavesolutions.queschine.apihandler.login;

import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.google.gson.reflect.TypeToken;
import com.iweavesolutions.queschine.QueschineApplication;
import com.iweavesolutions.queschine.volley.request.VolleyRequest;

/**
 * Created by bharath.simha on 06/05/16.
 */
public class LogInRequest extends VolleyRequest<LogInBO> {

    LogInPayload logInPayload;

    public LogInRequest(int method, String url, LogInPayload logInPayload, Response.Listener listener, Response.ErrorListener errorListener) {
        super(method, url, new TypeToken<LogInBO>() {
        }.getType(), listener, errorListener);
        this.logInPayload = logInPayload;
    }

    @Override
    public byte[] getBody() throws AuthFailureError {
        String configString = QueschineApplication.getGsonInstance().toJson(logInPayload);
        Log.d("Payload::LogInReq:", configString);
        return configString.getBytes();
    }

    @Override
    public String getBodyContentType() {
        return "application/json";
    }
}
