package com.iweavesolutions.queschine.apihandler.timezone;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.google.gson.reflect.TypeToken;
import com.iweavesolutions.queschine.volley.ResponseWrapper;
import com.iweavesolutions.queschine.volley.request.VolleyRequest;

/**
 * Created by bharath.simha on 05/05/16.
 */
public class TimeZoneRequest extends VolleyRequest<Object> {

    public TimeZoneRequest(int method, String url, Response.Listener listener, Response.ErrorListener errorListener) {
        super(method, url, new TypeToken<ResponseWrapper<Object>>() {
        }.getType(), listener, errorListener);
    }

    @Override
    public void performJsonUpdate(byte[] responseStream, Object response, boolean isGzipped) {

    }

    @Override
    public byte[] getBody() throws AuthFailureError {
        return super.getBody();
    }
}
