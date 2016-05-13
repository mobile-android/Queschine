package com.iweavesolutions.queschine.apihandler.favourites;

import com.android.volley.Request;
import com.google.gson.reflect.TypeToken;
import com.iweavesolutions.queschine.QueschineApplication;
import com.iweavesolutions.queschine.volley.DataHandler;
import com.iweavesolutions.queschine.volley.request.VolleyRequest;

/**
 * Created by bharath.simha on 08/05/16.
 */
abstract public class FavouritesDataHandler extends DataHandler<FavouritesBO> {

    public void onSetAsFavourites(String extensionURL, String authKey, FavouritesPayload favouritesPayload) {
        FavouritesRequest favouritesRequest = new FavouritesRequest(Request.Method.POST, VolleyRequest.BASE_API_URL + extensionURL,
                authKey, favouritesPayload, listner, errorListner);
        this.ctype = new TypeToken<FavouritesBO>() {
        }.getType();
        this.request = favouritesRequest;
        QueschineApplication.addToRequestQueue(favouritesRequest);
    }
}
