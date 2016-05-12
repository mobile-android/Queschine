package com.iweavesolutions.queschine.apihandler.registration;

import com.android.volley.Request;
import com.google.gson.reflect.TypeToken;
import com.iweavesolutions.queschine.QueschineApplication;
import com.iweavesolutions.queschine.volley.DataHandler;
import com.iweavesolutions.queschine.volley.request.VolleyRequest;

/**
 * Created by bharath.simha on 06/05/16.
 */
abstract public class RegistrationDataHandler extends DataHandler<RegistrationBO> {
    public void onRegisterUser(String extensionURL, RegistrationPayload registrationPayload) {
        RegistrationRequest registrationRequest = new RegistrationRequest(Request.Method.POST, VolleyRequest.BASE_API_URL + extensionURL,
                registrationPayload, listner, errorListner);
        this.request = registrationRequest;
        /*this.ctype = new TypeToken<RegistrationBO>() {
        }.getType();*/
        QueschineApplication.addToRequestQueue(registrationRequest);
    }
}
