package com.iweavesolutions.queschine.apihandler.categories;

import com.android.volley.Response;
import com.google.gson.reflect.TypeToken;
import com.iweavesolutions.queschine.volley.request.VolleyRequest;

/**
 * Created by bharath.simha on 06/05/16.
 */
public class CategoriesRequest extends VolleyRequest<CategoriesBO> {

    public CategoriesRequest(int method, String url, String header, Response.Listener listener, Response.ErrorListener errorListener) {
        super(method, url, new TypeToken<CategoriesBO>() {
        }.getType(), listener, errorListener);
        addHeader("Application-Authorization", "Bearer " + header);
    }
}
