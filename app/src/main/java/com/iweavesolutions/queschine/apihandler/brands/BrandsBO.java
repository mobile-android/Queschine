package com.iweavesolutions.queschine.apihandler.brands;

import com.iweavesolutions.queschine.apihandler.categories.CategoriesData;

import java.util.ArrayList;

/**
 * Created by bharath.simha on 06/05/16.
 */
public class BrandsBO {

    protected String status;
    protected String message;
    protected ArrayList<BrandsData> data;

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

    public ArrayList<BrandsData> getData() {
        return data;
    }

    public void setData(ArrayList<BrandsData> data) {
        this.data = data;
    }
}
