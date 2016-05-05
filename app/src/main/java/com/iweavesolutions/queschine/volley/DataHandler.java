package com.iweavesolutions.queschine.volley;

import com.android.volley.NoConnectionError;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.RequestFuture;
import com.google.gson.reflect.TypeToken;
import com.iweavesolutions.queschine.QueschineApplication;
import com.iweavesolutions.queschine.utilities.QSCNLogger;

import java.io.Reader;
import java.lang.reflect.Type;
import java.util.concurrent.ExecutionException;

/**
 * Created by bharath.simha on 05/15/16.
 */
abstract public class DataHandler<T> {

    RequestQueue.RequestFilter requestFilter;
    protected Response.ErrorListener errorListner;
    protected Response.Listener<T> listner;

    protected com.android.volley.Request<T> request;

    public Type ctype;

    public DataHandler() {
        try {
            errorListner = new Response.ErrorListener() {

                @Override
                public void onErrorResponse(VolleyError error) {
                    if (error.networkResponse != null)
                        QSCNLogger.debug("Some error happened.. " + error + error.networkResponse.statusCode);

                    if (error instanceof TimeoutError) {
                        errorReceived(504, -1, "Request Timedout!!");
                    }
                   /* if(error instanceof SSLError){
                        errorReceived(999, -1, "Certificate validation Error");
                    }*/
                    else if (error instanceof NoConnectionError) {
                        errorReceived(-1, -1, "Oops, something went  wrong!");
                    } else if (error.networkResponse != null) {
                        if (error.networkResponse.statusCode == 204) {
                            errorReceived(204, -1, "");
                        } else {
                            Reader jsonReader = ResponseUtils.getJsonReader(error.networkResponse);
                            ResponseWrapper<T> responseObject = null;
                            try {
                                if (ctype != null) {
                                    responseObject = QueschineApplication.getGsonInstance().
                                            fromJson(jsonReader, ctype);

                                } else {
                                    responseObject = QueschineApplication.getGsonInstance().
                                            fromJson(jsonReader, new TypeToken<ResponseWrapper<T>>() {
                                            }.getType());
                                }

                            } catch (AssertionError | IllegalStateException ignored) {
                            }
                            if (responseObject != null) {
                            /*This method is being called only by V3 APIs which expect error resonse code with statusCode 400 not for older api calls in V2*/
                                //TODO if "com.google.gson.internal.LinkedTreeMap cannot be cast to" exception is coming then you need to satisfy ctype variable in your data handler.
                                if (responseObject.getResponse() != null && error.networkResponse.statusCode == 400) {
                                    errorReceived(error.networkResponse.statusCode, responseObject.getErrorCode(), responseObject.getErrorMessage(), responseObject.getResponse());
//								listner.onResponse(responseObject.getResponse());
                                } else {
                                    errorReceived(error.networkResponse.statusCode, responseObject.getErrorCode(), responseObject.getErrorMessage());
                                }
                            } else {
                                errorReceived(999, -1, "Oops, something went wrong!");
                            }
                        }
                    } else {
                        errorReceived(999, -1, "Oops, something went wrong!");
                    }
                }
            };

            listner = new Response.Listener<T>() {
                @Override
                public void onResponse(T response) {
                    QSCNLogger.debug("into the onresponse");
                    resultReceived(response, false);
                }
            };
        } catch (AssertionError ex) {
        } catch (IllegalStateException e2) {
        } catch (Exception e) {
        }

    }

    public void cancelRequests() {
        RequestQueue queue = QueschineApplication.getRequestQueue();
        if (queue != null && requestFilter != null) {
            queue.cancelAll(requestFilter);
        }
    }

    abstract public void resultReceived(T response, boolean fromDB);

    public void errorReceived(int responseCode, int errorCode, String errorMessage) {
        //nothing to do as of now...
    }

    /*This method is being called only by V3 APIs which expect error resonse code with statusCode 400 not for older api calls in V2*/
    public void errorReceived(int responseCode, int errorCode, String errorMessage, T response) {
        //for v3
        //nothing to do as of now...
//TODO if "com.google.gson.internal.LinkedTreeMap cannot be cast to" exception is coming then you need to satisfy ctype variable in your data handler.
    }

    private T doBlockingCall() {
        if (request == null) {
            return null;
        }

        RequestFuture<T> future = RequestFuture.newFuture();
        QueschineApplication.addToRequestQueue(request);
        try {
            T response = future.get(); // this will block
            return response;
        } catch (InterruptedException e) {
            // exception handling
        } catch (ExecutionException e) {
            // exception handling
        }

        return null;
    }

}
