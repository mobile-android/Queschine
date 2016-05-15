package com.iweavesolutions.queschine.apihandler.categories;

import com.android.volley.Request;
import com.google.gson.reflect.TypeToken;
import com.iweavesolutions.queschine.QueschineApplication;
import com.iweavesolutions.queschine.volley.DataHandler;
import com.iweavesolutions.queschine.volley.request.VolleyRequest;

/**
 * Created by bharath.simha on 06/05/16.
 */
abstract public class CategoriesDataHandler extends DataHandler<CategoriesBO> {

    public void getCategories(String extensionURl, String authKey) {
        CategoriesRequest categoriesRequest = new CategoriesRequest(Request.Method.GET, VolleyRequest.BASE_API_URL, authKey, listner, errorListner);
        this.ctype = new TypeToken<CategoriesBO>() {
        }.getType();
        QueschineApplication.addToRequestQueue(categoriesRequest);
    }
}
