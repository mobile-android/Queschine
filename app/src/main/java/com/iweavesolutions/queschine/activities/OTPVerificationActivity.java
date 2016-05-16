package com.iweavesolutions.queschine.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.Toast;

import com.iweavesolutions.queschine.R;
import com.iweavesolutions.queschine.apihandler.otp.request.OTPRequestBO;
import com.iweavesolutions.queschine.apihandler.otp.request.OTPRequestDataHandler;
import com.iweavesolutions.queschine.apihandler.otp.validate.OTPValidateBO;
import com.iweavesolutions.queschine.apihandler.otp.validate.OTPValidateDataHandler;
import com.iweavesolutions.queschine.utilities.PreferenceManager;

/**
 * Created by bharath.simha on 15/05/16.
 */
public class OTPVerificationActivity extends AppCompatActivity {

    protected EditText otpValue;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_verification);
        onInit();
    }

    private void onInit() {
        otpValue = (EditText) findViewById(R.id.otpValue);

        assert otpValue != null;
        otpValue.clearFocus();
        otpValue.setImeOptions(EditorInfo.IME_ACTION_DONE);

        OTPRequestDataHandler otpRequestDataHandler = new OTPRequestDataHandler() {
            @Override
            public void resultReceived(final OTPRequestBO response, boolean fromDB) {
                Toast.makeText(getApplicationContext(), response.getMessage(), Toast.LENGTH_SHORT).show();

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        otpValue.setText(response.getData());
                        onVerifyOTPValue(response.getData());
                    }
                }, 3000);
            }

            @Override
            public void errorReceived(OTPRequestBO response) {
                Toast.makeText(getApplicationContext(), response.getMessage(), Toast.LENGTH_SHORT).show();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        finish();
                    }
                }, 1500);
            }

            @Override
            public void errorReceived(String message, Object response) {
                Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                        finish();
                    }
                }, 1500);
            }
        };
        otpRequestDataHandler.onRequestOTP("otp?reason=1", PreferenceManager.getManagerInstance().getAccessToken());
    }

    private void onVerifyOTPValue(String otp) {
        OTPValidateDataHandler otpValidateDataHandler = new OTPValidateDataHandler() {
            @Override
            public void resultReceived(final OTPValidateBO response, boolean fromDB) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(), response.getMessage(), Toast.LENGTH_SHORT).show();

                    }
                }, 1000);

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        PreferenceManager.getManagerInstance().setIsMobileRegistered(true);
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        finish();
                    }
                }, 3000);

            }

            @Override
            public void errorReceived(OTPValidateBO response) {
                Toast.makeText(getApplicationContext(), response.getMessage(), Toast.LENGTH_SHORT).show();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        finish();
                    }
                }, 1500);
            }

            @Override
            public void errorReceived(String message, Object response) {
                Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                        finish();
                    }
                }, 1500);
            }
        };
        otpValidateDataHandler.onValidateOTP("otp", otp, PreferenceManager.getManagerInstance().getAccessToken());
    }
}
