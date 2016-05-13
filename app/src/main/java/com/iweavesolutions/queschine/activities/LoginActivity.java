package com.iweavesolutions.queschine.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatEditText;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ScrollView;
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
public class LoginActivity extends AppCompatActivity{

    private AppCompatEditText email, password, mobileNum;
    private Button login,cancel;
    private ScrollView scrollView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);
        initLogin();
    }
    private void initLogin(){
        scrollView = (ScrollView) findViewById(R.id.scrollViewLogin);
        KeyBoardUtil keyBoardUtil = new KeyBoardUtil(LoginActivity.this,scrollView);
        keyBoardUtil.enable();
        email = (AppCompatEditText) findViewById(R.id.emailLogin);
        password = (AppCompatEditText) findViewById(R.id.passwordLogin);
        mobileNum = (AppCompatEditText)findViewById(R.id.mobileNum);
        login = (Button)findViewById(R.id.login);
        cancel = (Button)findViewById(R.id.cancel);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String emailValue = email.getText().toString();
                String passwordValue = password.getText().toString();
                String mobile = mobileNum.getText().toString();

                if(Utils.isNullOrEmpty(emailValue)){
                    Toast.makeText(getApplicationContext(),"Email cannot be Empty",Toast.LENGTH_SHORT).show();
                }
                else if(Utils.isNullOrEmpty(passwordValue)){
                    Toast.makeText(getApplicationContext(),"Password cannot be Empty",Toast.LENGTH_SHORT).show();
                }
                else if(Utils.isNullOrEmpty(mobile)){
                    Toast.makeText(getApplicationContext(),"Mobile cannot be Empty",Toast.LENGTH_SHORT).show();
                }
                else if(mobile.length() != 10){
                    Toast.makeText(getApplicationContext(),"Mobile Number Length should be 10",Toast.LENGTH_SHORT).show();
                }
                else {
                    LogInPayload logInPayload = new LogInPayload();
                    logInPayload.setEmailId(emailValue);
                    logInPayload.setPassword(passwordValue);
                    logInPayload.setPhoneNumber(mobile);
                    onLoginUser(logInPayload);
                }
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email.setText("");
                password.setText("");
                mobileNum.setText("");
            }
        });


    }

    private void onLoginUser(LogInPayload logInPayload){
        LogInDataHandler logInDataHandler = new LogInDataHandler() {
            @Override
            public void resultReceived(LogInBO response, boolean fromDB) {
                Toast.makeText(getApplicationContext(),response.getMessage(),Toast.LENGTH_SHORT).show();

            }
            @Override
            public void errorReceived(LogInBO errorResponse){
                Toast.makeText(getApplicationContext(),errorResponse.getMessage(),Toast.LENGTH_SHORT).show();
            }
        };

        logInDataHandler.onAuthenticateUser("login",logInPayload);
    }

}
