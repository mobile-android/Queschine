package com.iweavesolutions.queschine.apihandler.brands;

import com.android.volley.Request;
import com.iweavesolutions.queschine.QueschineApplication;
import com.iweavesolutions.queschine.volley.DataHandler;
import com.iweavesolutions.queschine.volley.request.VolleyRequest;

/**
 * Created by bharath.simha on 06/05/16.
 */
abstract public class BrandsDataHandler extends DataHandler<BrandsBO> {

    public void getBrands(String extensionURL, String authKey) {
        BrandsRequest brandsRequest = new BrandsRequest(Request.Method.GET, VolleyRequest.BASE_API_URL + extensionURL, authKey, listner, errorListner);
        this.request = brandsRequest;
        QueschineApplication.addToRequestQueue(brandsRequest);
    }
}
