package com.iweavesolutions.queschine.apihandler.favourites;

import java.util.ArrayList;

/**
 * Created by bharath.simha on 08/05/16.
 */
public class FavouritesPayload {

    public ArrayList<String> getFavCategories() {
        return favCategories;
    }

    public void setFavCategories(ArrayList<String> favCategories) {
        this.favCategories = favCategories;
    }

    public ArrayList<String> getFavBrands() {
        return favBrands;
    }

    public void setFavBrands(ArrayList<String> favBrands) {
        this.favBrands = favBrands;
    }

    protected ArrayList<String> favCategories;
    protected ArrayList<String> favBrands;
}
