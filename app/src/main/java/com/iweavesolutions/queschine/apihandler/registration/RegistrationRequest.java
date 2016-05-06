package com.iweavesolutions.queschine.apihandler.registration;

import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.google.gson.reflect.TypeToken;
import com.iweavesolutions.queschine.QueschineApplication;
import com.iweavesolutions.queschine.volley.request.VolleyRequest;

import java.util.Map;

/**
 * Created by bharath.simha on 06/05/16.
 */
public class RegistrationRequest extends VolleyRequest<RegistrationBO> {

    RegistrationPayload registrationPayload;

    public RegistrationRequest(int method, String url, RegistrationPayload registrationPayload, Response.Listener listener, Response.ErrorListener errorListener) {
        super(method, url, new TypeToken<RegistrationBO>() {
        }.getType(), listener, errorListener);
        this.registrationPayload = registrationPayload;
    }

    @Override
    public byte[] getBody() throws AuthFailureError {
        String configString = QueschineApplication.getGsonInstance().toJson(registrationPayload);
        Log.d("Payload::RegReq:", configString);
        return configString.getBytes();
    }

    @Override
    public String getBodyContentType() {
        return "application/json";
    }
}
