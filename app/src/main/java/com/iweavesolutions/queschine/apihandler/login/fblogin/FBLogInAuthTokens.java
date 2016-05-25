package com.iweavesolutions.queschine.apihandler.login.fblogin;

/**
 * Created by bharath.simha on 24/05/16.
 */
public class FBLogInAuthTokens {

    protected String access_token;
    protected String refresh_token;

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public String getRefresh_token() {
        return refresh_token;
    }

    public void setRefresh_token(String refresh_token) {
        this.refresh_token = refresh_token;
    }
}
