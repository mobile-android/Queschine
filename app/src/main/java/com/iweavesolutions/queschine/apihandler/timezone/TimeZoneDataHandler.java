package com.iweavesolutions.queschine.apihandler.timezone;

import com.android.volley.Request;
import com.iweavesolutions.queschine.QueschineApplication;
import com.iweavesolutions.queschine.volley.DataHandler;
import com.iweavesolutions.queschine.volley.request.VolleyRequest;

/**
 * Created by bharath.simha on 16/04/16.
 */
abstract public class TimeZoneDataHandler extends DataHandler<Object> {

    public void onGetTimeZone() {
        TimeZoneRequest timeZoneRequest = new TimeZoneRequest(Request.Method.GET, VolleyRequest.TIMEZONE_API, listner, errorListner);
        this.request = timeZoneRequest;
        QueschineApplication.addToRequestQueue(timeZoneRequest);
    }
}
