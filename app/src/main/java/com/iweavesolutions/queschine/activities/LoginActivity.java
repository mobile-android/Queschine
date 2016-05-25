package com.iweavesolutions.queschine.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatEditText;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.iweavesolutions.queschine.R;
import com.iweavesolutions.queschine.apihandler.login.LogInBO;
import com.iweavesolutions.queschine.apihandler.login.LogInDataHandler;
import com.iweavesolutions.queschine.apihandler.login.LogInPayload;
import com.iweavesolutions.queschine.apihandler.login.fblogin.FBLogInBO;
import com.iweavesolutions.queschine.apihandler.login.fblogin.FBLogInDataHandler;
import com.iweavesolutions.queschine.apihandler.login.fblogin.FBLogInPayload;
import com.iweavesolutions.queschine.customviews.LoadingDialog;
import com.iweavesolutions.queschine.customviews.PasswordEditText;
import com.iweavesolutions.queschine.utilities.PreferenceManager;
import com.iweavesolutions.queschine.utilities.QSCNLogger;
import com.iweavesolutions.queschine.utilities.Utils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;

/**
 * Created by raaj.gopal on 13/05/16.
 */
public class LoginActivity extends AppCompatActivity {

    private AppCompatEditText logInId;
    private PasswordEditText password;
    LoadingDialog loadingDialog;
    boolean isOnlyNumeric = false;
    TextView facebookSHA;
    ImageButton copy;

    // FB Login
    LoginButton facebookLogin;
    CallbackManager facebookCallbackManager;
    final String fbUserProfile = "public_profile";
    final String fbEmail = "email";
    final String fbUserBirthDay = "user_birthday";
    final String fbUserFriends = "user_friends";

    final String[] facebookPermissions = {fbUserProfile, fbEmail, fbUserBirthDay, fbUserFriends};

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

        facebookSHA = (TextView) findViewById(R.id.facebookSHA);
        copy = (ImageButton) findViewById(R.id.copy);

        facebookSHA.setText(PreferenceManager.getManagerInstance().getFacebookHash());

        if (!facebookSHA.getText().toString().contains("Nothing")) {
            copy.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    int sdk = android.os.Build.VERSION.SDK_INT;
                    if (sdk < android.os.Build.VERSION_CODES.HONEYCOMB) {
                        android.text.ClipboardManager clipboard = (android.text.ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                        clipboard.setText(PreferenceManager.getManagerInstance().getFacebookHash());
                    } else {
                        android.content.ClipboardManager clipboard = (android.content.ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                        android.content.ClipData clip = android.content.ClipData.newPlainText("fb_hash", PreferenceManager.getManagerInstance().getFacebookHash());
                        clipboard.setPrimaryClip(clip);
                        Toast.makeText(getApplicationContext(), "FB hash copied!!, send it to Bharath to use FB Login", Toast.LENGTH_SHORT).show();
                    }

                }
            });
        }

        logInId.clearFocus();
        password.getEditText().clearFocus();

        password.setImeOptions(EditorInfo.IME_ACTION_DONE);

        addTextChangeListener(logInId);
        facebookLogin();

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

    private void facebookLogin() {
        facebookCallbackManager = CallbackManager.Factory.create();
        facebookLogin = (LoginButton) findViewById(R.id.fbLogin);
        facebookLogin.setReadPermissions(Arrays.asList(facebookPermissions));

        facebookLogin.registerCallback(facebookCallbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                QSCNLogger.debug("Login Success", loginResult.getAccessToken().getToken());
                GraphRequest request = GraphRequest.newMeRequest(
                        loginResult.getAccessToken(),
                        new GraphRequest.GraphJSONObjectCallback() {
                            @Override
                            public void onCompleted(JSONObject object, GraphResponse response) {
                                Log.v("FB: User Details ", response.toString());

                                try {
                                    if (loadingDialog == null)
                                        loadingDialog = new LoadingDialog(LoginActivity.this, R.style.CustomDialogStyle);
                                    loadingDialog.show();
                                    String email = object.getString(fbEmail);
                                    String birthday = object.getString("birthday");
                                    String name = object.getString("name");

                                    FBLogInPayload fbLogInPayload = new FBLogInPayload();
                                    fbLogInPayload.setName(name);
                                    fbLogInPayload.setEmailId(email);

                                    FBLogInDataHandler fbLogInDataHandler = new FBLogInDataHandler() {
                                        @Override
                                        public void resultReceived(final FBLogInBO response, boolean fromDB) {
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
                                        public void errorReceived(FBLogInBO errorResponse) {
                                            loadingDialog.cancel();
                                            Toast.makeText(getApplicationContext(), errorResponse.getMessage(), Toast.LENGTH_SHORT).show();
                                        }

                                        @Override
                                        public void errorReceived(String message, Object response) {
                                            loadingDialog.cancel();
                                            Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
                                        }
                                    };
                                    fbLogInDataHandler.getFBLogInStatus("fblogin", fbLogInPayload);


                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                GraphRequest requestFriends = GraphRequest.newMyFriendsRequest(
                        loginResult.getAccessToken(),
                        new GraphRequest.GraphJSONArrayCallback() {
                            @Override
                            public void onCompleted(JSONArray object, GraphResponse response) {
                                Log.v("FB: Friends ", response.toString());

                            }
                        });
                Bundle parameters = new Bundle();
                parameters.putString("fields", "id,name,email,gender,birthday");
                request.setParameters(parameters);
                request.executeAsync();

                Bundle parametersFriends = new Bundle();
                parameters.putString("fields", "id,name,email,gender,birthday");
                requestFriends.setParameters(parametersFriends);
                requestFriends.executeAsync();
            }

            @Override
            public void onCancel() {
                QSCNLogger.debug("FB Login Canceled", "Canceled Facebook Login");
            }

            @Override
            public void onError(FacebookException exception) {
                QSCNLogger.debug("FB Login Error", exception.getMessage());
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        facebookCallbackManager.onActivityResult(requestCode, resultCode, data);
    }

}
