package com.iweavesolutions.queschine.volley;

import com.google.gson.annotations.SerializedName;
import com.iweavesolutions.queschine.volley.request.Request;


/**
 * Created by bharath.simha on 05/15/16.
 */
public class ResponseWrapper<T> {

    @SerializedName("REQUEST")
    private Request request;

    @SerializedName("STATUS_CODE")
    private int status;

    @SerializedName("RESPONSE")
    private T response;

    @SerializedName("ERROR_CODE")
    private int errorCode;

    @SerializedName("ERROR_MESSAGE")
    private String errorMessage;

    @SerializedName("REQUEST-ID")
    private String requestId;

    @SerializedName("hashCode")
    private long hashCode;

    @SerializedName("CACHE_INVALIDATION_TTL")
    private String cacheInvalidationTTL;

    public ResponseWrapper() {
        request = new Request();
    }

    public long getHashCode() {
        return hashCode;
    }

    public void setHashCode(long hashCode) {
        this.hashCode = hashCode;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public T getResponse() {
        return response;
    }

    public void setResponse(T response) {
        this.response = response;
    }

    public Request getRequest() {
        return request;
    }

    public void setRequest(Request request) {
        this.request = request;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }


    public String getCacheInvalidationTTL() {
        return cacheInvalidationTTL;
    }

    public void setCacheInvalidationTTL(String cacheInvalidationTTL) {
        this.cacheInvalidationTTL = cacheInvalidationTTL;
    }

}
