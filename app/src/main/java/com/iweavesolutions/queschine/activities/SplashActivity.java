package com.iweavesolutions.queschine.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.widget.Toast;

import com.iweavesolutions.queschine.utilities.PreferenceManager;
import com.iweavesolutions.queschine.utilities.Utils;

/**
 * Created by bharath.simha on 05/05/16.
 */
public class SplashActivity extends Activity {

    static final int SPLASH_TIME_OUT = 1500;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Utils.isNetworkAvailable(getApplicationContext())) {
            new Handler().postDelayed(new Runnable() {

                @Override
                public void run() {
                    if (!PreferenceManager.getManagerInstance().getIsFirstLaunch()) {
                        startActivity(new Intent(getApplicationContext(), RegistrationActivity.class));
                        PreferenceManager.getManagerInstance().setIsFirstLaunch(true);
                        finish();
                        return;
                    }
                    if (!PreferenceManager.getManagerInstance().getIsLogin() && !PreferenceManager.getManagerInstance().getIsRegistered())
                        startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                    else if (!PreferenceManager.getManagerInstance().getIsMobileRegistered())
                        startActivity(new Intent(getApplicationContext(), OTPVerificationActivity.class));
                    else
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));

                    finish();

                }
            }, SPLASH_TIME_OUT);

        } else {
            Toast.makeText(getApplicationContext(),
                    "please connect to working Internet", Toast.LENGTH_LONG)
                    .show();
            new Handler().postDelayed(new Runnable() {

                @Override
                public void run() {
                    finish();
                }
            }, SPLASH_TIME_OUT);

        }
    }

}
