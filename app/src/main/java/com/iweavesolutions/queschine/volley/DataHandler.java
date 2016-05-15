package com.iweavesolutions.queschine.volley;

import com.android.volley.NoConnectionError;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.google.gson.reflect.TypeToken;
import com.iweavesolutions.queschine.QueschineApplication;
import com.iweavesolutions.queschine.utilities.QSCNLogger;

import java.io.Reader;
import java.lang.reflect.Type;

/**
 * Created by bharath.simha on 05/15/16.
 */
abstract public class DataHandler<T> {

    RequestQueue.RequestFilter requestFilter;
    protected Response.ErrorListener errorListner;
    protected Response.Listener<T> listner;
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
                    } else if (error instanceof NoConnectionError) {
                        errorReceived(-1, -1, "Oops, something went  wrong!");
                    } else if (error.networkResponse != null) {
                        if (error.networkResponse.statusCode == 204) {
                            errorReceived(204, -1, "");
                        } else {
                            Reader jsonReader = ResponseUtils.getJsonReader(error.networkResponse);
                            T responseObject = null;
                            try {
                                if (ctype != null && error.networkResponse.statusCode != 401) {
                                    responseObject = QueschineApplication.getGsonInstance().
                                            fromJson(jsonReader, ctype);
                                    if (responseObject != null) {
                                        errorReceived(responseObject);
                                    }
                                } else {
                                    responseObject = QueschineApplication.getGsonInstance().
                                            fromJson(jsonReader, new TypeToken<Object>() {
                                            }.getType());
                                    if (responseObject != null) {
                                        errorReceived("Some error Occurred, please try again", responseObject);
                                    }
                                }

                            } catch (AssertionError | IllegalStateException ignored) {
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
        } catch (AssertionError ignored) {
        } catch (IllegalStateException ignored) {
        } catch (Exception ignored) {
        }

    }

    public void cancelRequests() {
        RequestQueue queue = QueschineApplication.getRequestQueue();
        if (queue != null && requestFilter != null) {
            queue.cancelAll(requestFilter);
        }
    }


    public void errorReceived(int responseCode, int errorCode, String errorMessage) {
        //nothing to do as of now...
    }

    abstract public void resultReceived(T response, boolean fromDB);

    abstract public void errorReceived(T response);

    abstract public void errorReceived(String message, Object response);

}
