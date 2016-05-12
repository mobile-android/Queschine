package com.iweavesolutions.queschine.apihandler.favourites;

/**
 * Created by bharath.simha on 08/05/16.
 */
public class FavouritesBO {

    protected String status;
    protected String message;
    protected String data;

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
