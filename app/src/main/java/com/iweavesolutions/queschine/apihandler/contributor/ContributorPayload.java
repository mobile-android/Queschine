package com.iweavesolutions.queschine.apihandler.contributor;

import java.util.ArrayList;

/**
 * Created by bharath.simha on 08/05/16.
 */
public class ContributorPayload {

    protected ArrayList<ContributorBrands> contributingBrands;
    protected ArrayList<ContributorCategories> contributingCategories;

    public ArrayList<ContributorBrands> getContributingBrands() {
        return contributingBrands;
    }

    public void setContributingBrands(ArrayList<ContributorBrands> contributingBrands) {
        this.contributingBrands = contributingBrands;
    }

    public ArrayList<ContributorCategories> getContributingCategories() {
        return contributingCategories;
    }

    public void setContributingCategories(ArrayList<ContributorCategories> contributingCategories) {
        this.contributingCategories = contributingCategories;
    }
}
