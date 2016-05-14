package com.iweavesolutions.queschine.activities;

import android.content.Intent;
import android.os.Bundle;
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
import com.iweavesolutions.queschine.apihandler.login.LogInBO;
import com.iweavesolutions.queschine.apihandler.login.LogInDataHandler;
import com.iweavesolutions.queschine.apihandler.login.LogInPayload;
import com.iweavesolutions.queschine.utilities.KeyBoardUtil;
import com.iweavesolutions.queschine.utilities.Utils;

/**
 * Created by raaj.gopal on 13/05/16.
 */
public class LoginActivity extends AppCompatActivity {

    private AppCompatEditText email, password, mobileNum;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);
        initLogin();
    }

    private void initLogin() {
        ScrollView scrollView = (ScrollView) findViewById(R.id.scrollViewLogin);
        KeyBoardUtil keyBoardUtil = new KeyBoardUtil(LoginActivity.this, scrollView);
        keyBoardUtil.enable();
        email = (AppCompatEditText) findViewById(R.id.emailLogin);
        password = (AppCompatEditText) findViewById(R.id.passwordLogin);
        mobileNum = (AppCompatEditText) findViewById(R.id.mobileNum);
        Button login = (Button) findViewById(R.id.login);
        TextView signUp = (TextView) findViewById(R.id.signUp);

        assert login != null;
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String emailValue = email.getText().toString();
                String passwordValue = password.getText().toString();
                String mobile = mobileNum.getText().toString();

                if (Utils.isNullOrEmpty(emailValue) && Utils.isNullOrEmpty(mobile)) {
                    Toast.makeText(getApplicationContext(), "Enter either Emial Id/Mobile Number", Toast.LENGTH_SHORT).show();
                } else if (Utils.isNullOrEmpty(passwordValue)) {
                    Toast.makeText(getApplicationContext(), "Password cannot be Empty", Toast.LENGTH_SHORT).show();
                } else if (!Utils.isNullOrEmpty(emailValue)) {
                    if (!Utils.isValidEmail(emailValue))
                        Toast.makeText(getApplicationContext(), "Not a valid email", Toast.LENGTH_SHORT).show();
                    else onLogIn(emailValue, passwordValue, mobile);
                } else if (!Utils.isNullOrEmpty(mobile)) {
                    if (!Utils.isValidMobile(mobile))
                        Toast.makeText(getApplicationContext(), "Not a valid mobile", Toast.LENGTH_SHORT).show();
                    else onLogIn(emailValue, passwordValue, mobile);
                }
            }
        });

        assert signUp != null;
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, Registration.class));
                finish();
            }
        });
    }

    private void onLogIn(String emailValue, String passwordValue, String mobile) {
        LogInPayload logInPayload = new LogInPayload();
        logInPayload.setEmailId(emailValue);
        logInPayload.setPassword(passwordValue);
        logInPayload.setPhoneNumber(mobile);
        onLoginUser(logInPayload);
    }

    private void onLoginUser(LogInPayload logInPayload) {
        LogInDataHandler logInDataHandler = new LogInDataHandler() {
            @Override
            public void resultReceived(LogInBO response, boolean fromDB) {
                Toast.makeText(getApplicationContext(), response.getMessage(), Toast.LENGTH_SHORT).show();

            }

            @Override
            public void errorReceived(LogInBO errorResponse) {
                Toast.makeText(getApplicationContext(), errorResponse.getMessage(), Toast.LENGTH_SHORT).show();
            }
        };

        logInDataHandler.onAuthenticateUser("login", logInPayload);
    }

}
