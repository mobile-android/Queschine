package com.iweavesolutions.queschine.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatEditText;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.iweavesolutions.queschine.R;
import com.iweavesolutions.queschine.apihandler.login.LogInBO;
import com.iweavesolutions.queschine.apihandler.login.LogInDataHandler;
import com.iweavesolutions.queschine.apihandler.login.LogInPayload;
import com.iweavesolutions.queschine.customviews.LoadingDialog;
import com.iweavesolutions.queschine.customviews.PasswordEditText;
import com.iweavesolutions.queschine.utilities.PreferenceManager;
import com.iweavesolutions.queschine.utilities.Utils;

/**
 * Created by raaj.gopal on 13/05/16.
 */
public class LoginActivity extends AppCompatActivity {

    private AppCompatEditText logInId;
    private PasswordEditText password;
    LoadingDialog loadingDialog;
    boolean isOnlyNumeric = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);
        initLogin();
    }

    private void initLogin() {
        logInId = (AppCompatEditText) findViewById(R.id.emailLogin);
        password = (PasswordEditText) findViewById(R.id.passwordLogin);
        Button login = (Button) findViewById(R.id.login);
        TextView signUp = (TextView) findViewById(R.id.signUp);

        logInId.clearFocus();
        password.getEditText().clearFocus();

        password.setImeOptions(EditorInfo.IME_ACTION_DONE);

        addTextChangeListener(logInId);

        assert login != null;
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String logInValue = logInId.getText().toString();
                String passwordValue = password.getText();

                if (Utils.isNullOrEmpty(logInValue)) {
                    Toast.makeText(getApplicationContext(), "Enter either Email Id/Mobile Number", Toast.LENGTH_SHORT).show();
                } else if (Utils.isNullOrEmpty(passwordValue)) {
                    Toast.makeText(getApplicationContext(), "Password cannot be Empty", Toast.LENGTH_SHORT).show();
                } else {
                    if (isOnlyNumeric) {
                        if (Utils.isValidMobile(logInValue))
                            onLogIn("", passwordValue, logInValue);
                        else
                            Toast.makeText(getApplicationContext(), "Not a Valid mobile", Toast.LENGTH_SHORT).show();
                    } else {
                        if (Utils.isValidEmail(logInValue))
                            onLogIn(logInValue, passwordValue, "");
                        else
                            Toast.makeText(getApplicationContext(), "Not a Valid Email", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        assert signUp != null;
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, RegistrationActivity.class));
                finish();
            }
        });
    }

    protected void addTextChangeListener(final EditText editText) {
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if (!TextUtils.isEmpty(s)) {
                    isOnlyNumeric = isOnlyDigits(s.toString());
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }

    public boolean isOnlyDigits(String str) {
        try {
            return str.matches(Utils.MOBILE_EXPRESSION);
        } catch (Exception e) {
            return false;
        }
    }

    private void onLogIn(String emailValue, String passwordValue, String mobile) {
        loadingDialog = new LoadingDialog(LoginActivity.this, R.style.CustomDialogStyle);
        loadingDialog.show();
        LogInPayload logInPayload = new LogInPayload();
        logInPayload.setEmailId(emailValue);
        logInPayload.setPassword(passwordValue);
        logInPayload.setPhoneNumber(mobile);
        onLoginUser(logInPayload);
    }

    private void onLoginUser(LogInPayload logInPayload) {
        LogInDataHandler logInDataHandler = new LogInDataHandler() {
            @Override
            public void resultReceived(final LogInBO response, boolean fromDB) {
                Toast.makeText(getApplicationContext(), response.getMessage(), Toast.LENGTH_SHORT).show();
                PreferenceManager.getManagerInstance().setIsLogin(true);
                loadingDialog.cancel();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        PreferenceManager.getManagerInstance().setAccessToken(response.getData().getAccess_token());
                        PreferenceManager.getManagerInstance().setRefreshToken(response.getData().getRefresh_token());
                        PreferenceManager.getManagerInstance().setIsLogin(true);
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
            public void errorReceived(LogInBO errorResponse) {
                loadingDialog.cancel();
                Toast.makeText(getApplicationContext(), errorResponse.getMessage(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void errorReceived(String message, Object response) {
                loadingDialog.cancel();
                Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
            }
        };

        logInDataHandler.onAuthenticateUser("login", logInPayload);
    }

}
