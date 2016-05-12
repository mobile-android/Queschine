package com.iweavesolutions.queschine.apihandler.favourites;

import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.google.gson.reflect.TypeToken;
import com.iweavesolutions.queschine.QueschineApplication;
import com.iweavesolutions.queschine.volley.request.VolleyRequest;

/**
 * Created by bharath.simha on 08/05/16.
 */
public class FavouritesRequest extends VolleyRequest<FavouritesBO> {

    FavouritesPayload favouritesPayload;

    public FavouritesRequest(int method, String url, String header, FavouritesPayload favouritesPayload, Response.Listener listener, Response.ErrorListener errorListener) {
        super(method, url, new TypeToken<FavouritesBO>() {
        }.getType(), listener, errorListener);
        this.favouritesPayload = favouritesPayload;
        addHeader("Application-Authorization", "Bearer " + header);
    }

    @Override
    public byte[] getBody() throws AuthFailureError {
        String configString = QueschineApplication.getGsonInstance().toJson(favouritesPayload);
        Log.d("Payload::FavouritedReq:", configString);
        return configString.getBytes();
    }
}
