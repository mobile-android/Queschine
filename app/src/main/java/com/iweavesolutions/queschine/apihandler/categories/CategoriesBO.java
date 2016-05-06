package com.iweavesolutions.queschine.apihandler.categories;

import java.util.ArrayList;

/**
 * Created by bharath.simha on 06/05/16.
 */
public class CategoriesBO {

    protected String status;
    protected String message;
    protected ArrayList<CategoriesData> data;

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

    public ArrayList<CategoriesData> getData() {
        return data;
    }

    public void setData(ArrayList<CategoriesData> data) {
        this.data = data;
    }
}
