package com.iweavesolutions.queschine.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;
import android.widget.Toast;

import com.iweavesolutions.queschine.utilities.Utils;

/**
 * Created by bharath.simha on 05/05/16.
 */
public class SplashActivity extends AppCompatActivity {

    static final int SPLASH_TIME_OUT = 1500;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        if (Utils.isNetworkAvailable(getApplicationContext())) {
            new Handler().postDelayed(new Runnable() {

                @Override
                public void run() {

                    startActivity(new Intent(getApplicationContext(), LogInActivity.class));
                   /* overridePendingTransition(R.anim.slide_in_from_bottom,
                            R.anim.slide_out_to_top);*/
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
