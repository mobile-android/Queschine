package com.iweavesolutions.queschine.volley.request;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.iweavesolutions.queschine.QueschineApplication;
import com.iweavesolutions.queschine.volley.ResponseUtils;

import java.io.Reader;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by bharath.simha on 05/05/16.
 */
abstract public class VolleyRequest<T> extends Request<T> {

    Map<String, String> postParams;
    private Map<String, String> headers;
    private Response.Listener<T> listner;
    private final Type classType;

    // testing to check Volley is working or not
    public static final String TIMEZONE_API = "http://api.timezonedb.com/?key=9VDCF1OL04M7&zone=Asia/Kolkata&format=json";
    public static final String BASE_API_URL = "http://iweavesolutions.com/queschine/services/";


    public VolleyRequest(int method, String url, Type classType, Response.Listener listener, Response.ErrorListener errorListener) {
        super(method, url, errorListener);
        this.listner = listener;
        this.classType = classType;
        headers = new HashMap<>();
    }

    @Override
    protected void deliverResponse(T response) {
        if (response != null)
            listner.onResponse(response);
    }

    @Override
    public void deliverError(VolleyError error) {
        if (error != null) {

        }
        super.deliverError(error);
    }

    @Override
    public Map<String, String> getHeaders() throws AuthFailureError {
        return headers != null ? headers : super.getHeaders();
    }

    public void addHeader(String key, String value) {
        headers.put(key, value);
    }

    @Override
    protected Map<String, String> getPostParams() throws AuthFailureError {
        return postParams != null ? postParams : super.getPostParams();
    }

    public void addPostParams(String key, String value) {
        postParams.put(key, value);
    }

    @Override
    protected Response<T> parseNetworkResponse(NetworkResponse networkResponse) {
        Gson gson = QueschineApplication.getGsonInstance();

        Reader jsonReader = ResponseUtils.getJsonReader(networkResponse);
        T response = gson.fromJson(jsonReader, classType);
        if (networkResponse != null && networkResponse.data != null) {
            if (response == null && networkResponse.statusCode == 200)
                response = (T) "Success";
        }

        return Response.success(response, null);
    }

    @Override
    public String getBodyContentType() {
        return super.getBodyContentType();
    }
}
