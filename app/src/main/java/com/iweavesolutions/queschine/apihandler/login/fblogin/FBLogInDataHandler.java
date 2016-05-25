package com.iweavesolutions.queschine.apihandler.login.fblogin;

import com.android.volley.Request;
import com.google.gson.reflect.TypeToken;
import com.iweavesolutions.queschine.QueschineApplication;
import com.iweavesolutions.queschine.volley.DataHandler;
import com.iweavesolutions.queschine.volley.request.VolleyRequest;

/**
 * Created by bharath.simha on 24/05/16.
 */
abstract public class FBLogInDataHandler extends DataHandler<FBLogInBO> {
    public void getFBLogInStatus(String extensionURL, FBLogInPayload fbLogInPayload) {
        FBLogInRequest fbLogInRequest = new FBLogInRequest(Request.Method.POST, VolleyRequest.BASE_API_URL + extensionURL, fbLogInPayload, listner, errorListner);
        this.ctype = new TypeToken<FBLogInBO>() {
        }.getType();
        QueschineApplication.addToRequestQueue(fbLogInRequest);
    }
}
