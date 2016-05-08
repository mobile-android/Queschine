package com.iweavesolutions.queschine.apihandler.contributor;

import com.android.volley.Request;
import com.iweavesolutions.queschine.QueschineApplication;
import com.iweavesolutions.queschine.volley.DataHandler;
import com.iweavesolutions.queschine.volley.request.VolleyRequest;

/**
 * Created by bharath.simha on 08/05/16.
 */
abstract public class ContributorDataHandler extends DataHandler<ContributorBO> {

    public void becomeContributor(String extensionURL, String authKey, ContributorPayload contributorPayload) {
        ContributorRequest contributorRequest = new ContributorRequest(Request.Method.POST, VolleyRequest.BASE_API_URL + extensionURL,
                contributorPayload, authKey, listner, errorListner);
        this.request = contributorRequest;
        QueschineApplication.addToRequestQueue(contributorRequest);
    }
}
