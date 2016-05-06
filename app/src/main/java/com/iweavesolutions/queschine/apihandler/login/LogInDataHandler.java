package com.iweavesolutions.queschine.apihandler.login;

import com.android.volley.Request;
import com.iweavesolutions.queschine.QueschineApplication;
import com.iweavesolutions.queschine.volley.DataHandler;

/**
 * Created by bharath.simha on 06/05/16.
 */
abstract public class LogInDataHandler extends DataHandler<LogInBO> {

    public void onAuthenticateUser(String extensionURL, LogInPayload logInPayload) {
        LogInRequest logInRequest = new LogInRequest(Request.Method.POST, extensionURL, logInPayload, listner, errorListner);
        this.request = logInRequest;
        QueschineApplication.addToRequestQueue(logInRequest);
    }
}
