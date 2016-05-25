package com.iweavesolutions.queschine.apihandler.login.fblogin;

import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.google.gson.reflect.TypeToken;
import com.iweavesolutions.queschine.QueschineApplication;
import com.iweavesolutions.queschine.volley.request.VolleyRequest;

/**
 * Created by bharath.simha on 24/05/16.
 */
public class FBLogInRequest extends VolleyRequest<FBLogInBO> {

    FBLogInPayload fbLogInPayload;

    public FBLogInRequest(int method, String url, FBLogInPayload fbLogInPayload, Response.Listener listener, Response.ErrorListener errorListener) {
        super(method, url, new TypeToken<FBLogInBO>() {
        }.getType(), listener, errorListener);
        this.fbLogInPayload = fbLogInPayload;
    }

    @Override
    public byte[] getBody() throws AuthFailureError {
        String configString = QueschineApplication.getGsonInstance().toJson(fbLogInPayload);
        Log.d("Payload::FBLogInReq:", configString);
        return configString.getBytes();
    }

    @Override
    public String getBodyContentType() {
        return "application/json";
    }
}
