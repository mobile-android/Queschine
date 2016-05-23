package com.iweavesolutions.queschine.utilities;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by bharath.simha on 15/04/16.
 */
public class PreferenceManager {

    private static PreferenceManager managerInstance = null;
    private SharedPreferences sharedPreferences;

    final String IS_FIRST_LAUNCH = "is_first_launch";
    final String IS_REGISTERED = "is_registered";
    final String IS_LOGIN = "is_login";
    final String ACCESS_TOKEN = "access_token";
    final String REFRESH_TOKEN = "refresh_token";
    final String IS_MOBILE_REGISTERED = "is_mobile_registered";
    final String NAME = "name";
    final String PROFILE_PIC = "profile_pic";

    public static PreferenceManager getManagerInstance() {
        if (managerInstance == null) {
            synchronized (PreferenceManager.class) {
                if (managerInstance == null)
                    managerInstance = new PreferenceManager();
            }
        }
        return managerInstance;
    }

    public void initialize(Context context) {
        this.sharedPreferences = android.preference.PreferenceManager.getDefaultSharedPreferences(context);
    }

    public Boolean getIsFirstLaunch() {
        return this.sharedPreferences.getBoolean(IS_FIRST_LAUNCH, false);
    }

    public void setIsFirstLaunch(boolean isRegistered) {
        this.sharedPreferences.edit().putBoolean(IS_FIRST_LAUNCH, isRegistered).apply();
    }

    public Boolean getIsRegistered() {
        return this.sharedPreferences.getBoolean(IS_REGISTERED, false);
    }

    public void setIsRegistered(boolean isRegistered) {
        this.sharedPreferences.edit().putBoolean(IS_REGISTERED, isRegistered).apply();
    }

    public Boolean getIsMobileRegistered() {
        return this.sharedPreferences.getBoolean(IS_MOBILE_REGISTERED, false);
    }

    public void setIsMobileRegistered(boolean isRegistered) {
        this.sharedPreferences.edit().putBoolean(IS_MOBILE_REGISTERED, isRegistered).apply();
    }

    public Boolean getIsLogin() {
        return this.sharedPreferences.getBoolean(IS_LOGIN, false);
    }

    public void setIsLogin(boolean isLogin) {
        this.sharedPreferences.edit().putBoolean(IS_LOGIN, isLogin).apply();
    }

    public void setAccessToken(String accessToken) {
        this.sharedPreferences.edit().putString(ACCESS_TOKEN, accessToken).apply();
    }

    public void setRefreshToken(String refreshToken) {
        this.sharedPreferences.edit().putString(REFRESH_TOKEN, refreshToken).apply();
    }

    public String getRefreshToken() {
        return this.sharedPreferences.getString(REFRESH_TOKEN, null);
    }

    public void setUserName(String userName) {
        this.sharedPreferences.edit().putString(NAME, userName).apply();
    }

    public String getUserName() {
        return this.sharedPreferences.getString(NAME, null);
    }

    public void setProfilePic(String profilePicURL) {
        this.sharedPreferences.edit().putString(PROFILE_PIC, profilePicURL).apply();
    }

    public String getProfilePic() {
        return this.sharedPreferences.getString(PROFILE_PIC, null);
    }

    public String getAccessToken() {
        return this.sharedPreferences.getString(ACCESS_TOKEN, null);
    }


}
