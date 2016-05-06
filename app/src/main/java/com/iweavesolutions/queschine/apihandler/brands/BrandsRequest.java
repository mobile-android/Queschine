package com.iweavesolutions.queschine.apihandler.brands;

import com.android.volley.Response;
import com.google.gson.reflect.TypeToken;
import com.iweavesolutions.queschine.volley.request.VolleyRequest;

/**
 * Created by bharath.simha on 06/05/16.
 */
public class BrandsRequest extends VolleyRequest<BrandsBO> {
    public BrandsRequest(int method, String url, String header, Response.Listener listener, Response.ErrorListener errorListener) {
        super(method, url, new TypeToken<BrandsBO>() {
        }.getType(), listener, errorListener);
        addHeader("Application-Authorization", "Bearer " + header);
    }
}
