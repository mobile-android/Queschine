package com.iweavesolutions.queschine.utilities;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by bharath.simha on 15/04/16.
 */
public class PreferenceManager {

    private static PreferenceManager managerInstance = null;
    private SharedPreferences sharedPreferences;

    static final String IS_REGISTERED = "is_registered";
    static final String IS_LOGIN = "is_login";

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

    public Boolean getIsRegistered() {
        return this.sharedPreferences.getBoolean(IS_REGISTERED, false);
    }

    public void setIsRegistered(boolean isRegistered) {
        this.sharedPreferences.edit().putBoolean(IS_REGISTERED, isRegistered).apply();
    }

    public Boolean getIsLogin() {
        return this.sharedPreferences.getBoolean(IS_LOGIN, false);
    }

    public void setIsLogin(boolean isLogin) {
        this.sharedPreferences.edit().putBoolean(IS_LOGIN, isLogin).apply();
    }


}
