package com.iweavesolutions.queschine.apihandler.contributor;

import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.google.gson.reflect.TypeToken;
import com.iweavesolutions.queschine.QueschineApplication;
import com.iweavesolutions.queschine.volley.request.VolleyRequest;

/**
 * Created by bharath.simha on 08/05/16.
 */
public class ContributorRequest extends VolleyRequest<ContributorBO> {

    ContributorPayload contributorPayload;

    public ContributorRequest(int method, String url, ContributorPayload contributorPayload, String header, Response.Listener listener, Response.ErrorListener errorListener) {
        super(method, url, new TypeToken<ContributorBO>() {
        }.getType(), listener, errorListener);
        this.contributorPayload = contributorPayload;
        addHeader("Application-Authorization", "Bearer " + header);
    }

    @Override
    public byte[] getBody() throws AuthFailureError {
        String configString = QueschineApplication.getGsonInstance().toJson(contributorPayload);
        Log.d("Payload::ContribReq:", configString);
        return configString.getBytes();
    }
}
