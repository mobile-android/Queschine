package com.iweavesolutions.queschine.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatEditText;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.iweavesolutions.queschine.R;
import com.iweavesolutions.queschine.apihandler.registration.RegistrationBO;
import com.iweavesolutions.queschine.apihandler.registration.RegistrationDataHandler;
import com.iweavesolutions.queschine.apihandler.registration.RegistrationPayload;
import com.iweavesolutions.queschine.customviews.LoadingDialog;
import com.iweavesolutions.queschine.customviews.PasswordEditText;
import com.iweavesolutions.queschine.utilities.KeyBoardUtil;
import com.iweavesolutions.queschine.utilities.PreferenceManager;
import com.iweavesolutions.queschine.utilities.Utils;

/**
 * Created by bharath.simha on 06/05/16.
 */
public class RegistrationActivity extends AppCompatActivity {

    private AppCompatEditText name, email, mobile;
    protected LoadingDialog loadingDialog;
    private PasswordEditText passwordEditText;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_registration);
        onInit();
    }

    private void onInit() {

        ScrollView scrollView = (ScrollView) findViewById(R.id.scrollView);
        KeyBoardUtil keyBoardUtil = new KeyBoardUtil(RegistrationActivity.this, scrollView);
        keyBoardUtil.enable();
        name = (AppCompatEditText) findViewById(R.id.nameRegistration);
        email = (AppCompatEditText) findViewById(R.id.emailRegistration);
        passwordEditText = (PasswordEditText) findViewById(R.id.passwordRegistration);
        assert passwordEditText != null;
        passwordEditText.hideForgot();
        mobile = (AppCompatEditText) findViewById(R.id.mobileRegistration);
        TextView signIn = (TextView) findViewById(R.id.signIn);

        Button submit = (Button) findViewById(R.id.submit);

        name.clearFocus();
        email.clearFocus();
        passwordEditText.getEditText().clearFocus();
        mobile.clearFocus();

        assert submit != null;
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nameValue, emailValue, passwordValue, mobileValue;
                nameValue = name.getText().toString();
                emailValue = email.getText().toString();
                passwordValue = passwordEditText.getText();
                mobileValue = mobile.getText().toString();

                if (Utils.isNullOrEmpty(nameValue)) {
                    Toast.makeText(getApplicationContext(), "Name should not empty", Toast.LENGTH_LONG).show();
                } else if (!Utils.isValidEmail(emailValue)) {
                    Toast.makeText(getApplicationContext(), "Not a valid Email", Toast.LENGTH_LONG).show();
                } else if (Utils.isNullOrEmpty(passwordValue)) {
                    Toast.makeText(getApplicationContext(), "Password should not empty", Toast.LENGTH_LONG).show();
                } else if (!Utils.isValidMobile(mobileValue)) {
                    Toast.makeText(getApplicationContext(), "Enter a valid mobile number", Toast.LENGTH_LONG).show();
                } else {
                    loadingDialog = new LoadingDialog(RegistrationActivity.this, R.style.CustomDialogStyle);
                    loadingDialog.show();
                    RegistrationPayload registrationPayload = new RegistrationPayload();
                    registrationPayload.setName(nameValue);
                    registrationPayload.setEmailId(emailValue);
                    registrationPayload.setPassword(passwordValue);
                    registrationPayload.setPhoneNumber(mobileValue);
                    onRegisterUser(registrationPayload);
                }
            }
        });
        assert signIn != null;
        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegistrationActivity.this, LoginActivity.class));
                finish();
            }
        });
    }

    private void onRegisterUser(RegistrationPayload registrationPayload) {

        RegistrationDataHandler registrationDataHandler = new RegistrationDataHandler() {
            @Override
            public void resultReceived(final RegistrationBO response, boolean fromDB) {
                Toast.makeText(getApplicationContext(), response.getMessage(), Toast.LENGTH_SHORT).show();
                loadingDialog.cancel();

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        PreferenceManager.getManagerInstance().setAccessToken(response.getData().getAccess_token());
                        PreferenceManager.getManagerInstance().setRefreshToken(response.getData().getRefresh_token());
                        PreferenceManager.getManagerInstance().setIsRegistered(true);
                        if (PreferenceManager.getManagerInstance().getIsMobileRegistered()) {
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        finish();
                        } else {
                            startActivity(new Intent(getApplicationContext(), OTPVerificationActivity.class));
                        }
                    }
                }, 500);
            }

            @Override
            public void errorReceived(RegistrationBO response) {
                loadingDialog.cancel();
                Toast.makeText(getApplicationContext(), response.getMessage(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void errorReceived(String message, Object response) {
                loadingDialog.cancel();
                Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
            }
        };
        registrationDataHandler.onRegisterUser("users", registrationPayload);
    }
}
