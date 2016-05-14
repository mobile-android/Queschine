package com.iweavesolutions.queschine.apihandler.login;

import com.android.volley.Request;
import com.google.gson.reflect.TypeToken;
import com.iweavesolutions.queschine.QueschineApplication;
import com.iweavesolutions.queschine.volley.DataHandler;
import com.iweavesolutions.queschine.volley.request.VolleyRequest;

/**
 * Created by bharath.simha on 06/05/16.
 */
abstract public class LogInDataHandler extends DataHandler<LogInBO> {

    public void onAuthenticateUser(String extensionURL, LogInPayload logInPayload) {
        LogInRequest logInRequest = new LogInRequest(Request.Method.POST, VolleyRequest.BASE_API_URL + extensionURL, logInPayload, listner, errorListner);
        this.request = logInRequest;
        this.ctype = new TypeToken<LogInBO>() {
        }.getType();
        QueschineApplication.addToRequestQueue(logInRequest);
    }
}
