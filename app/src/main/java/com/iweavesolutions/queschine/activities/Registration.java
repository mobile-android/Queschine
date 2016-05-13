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
import com.iweavesolutions.queschine.apihandler.registration.RegistrationBO;
import com.iweavesolutions.queschine.apihandler.registration.RegistrationDataHandler;
import com.iweavesolutions.queschine.apihandler.registration.RegistrationPayload;
import com.iweavesolutions.queschine.utilities.KeyBoardUtil;
import com.iweavesolutions.queschine.utilities.Utils;

/**
 * Created by bharath.simha on 06/05/16.
 */
public class Registration extends AppCompatActivity {

    private AppCompatEditText name, email, password, retypePassword, mobile;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_registration);
        onInit();
    }

    private void onInit() {

        ScrollView scrollView = (ScrollView) findViewById(R.id.scrollView);
        KeyBoardUtil keyBoardUtil = new KeyBoardUtil(Registration.this, scrollView);
        keyBoardUtil.enable();
        name = (AppCompatEditText) findViewById(R.id.nameRegistration);
        email = (AppCompatEditText) findViewById(R.id.emailRegistration);
        password = (AppCompatEditText) findViewById(R.id.passwordRegistration);
        retypePassword = (AppCompatEditText) findViewById(R.id.repasswordRegistration);
        mobile = (AppCompatEditText) findViewById(R.id.mobileRegistration);
        TextView signIn = (TextView) findViewById(R.id.signIn);

        Button submit = (Button) findViewById(R.id.submit);

        assert submit != null;
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nameValue, emailValue, passwordValue, retypePasswordValue, mobileValue;
                nameValue = name.getText().toString();
                emailValue = email.getText().toString();
                passwordValue = password.getText().toString();
                retypePasswordValue = retypePassword.getText().toString();
                mobileValue = mobile.getText().toString();

                if (Utils.isNullOrEmpty(nameValue)) {
                    Toast.makeText(getApplicationContext(), "Name should not empty", Toast.LENGTH_LONG).show();
                } else if (!Utils.isValidEmail(emailValue)) {
                    Toast.makeText(getApplicationContext(), "Not a valid Email", Toast.LENGTH_LONG).show();
                } else if (Utils.isNullOrEmpty(passwordValue)) {
                    Toast.makeText(getApplicationContext(), "password should not empty", Toast.LENGTH_LONG).show();
                } else if (Utils.isNullOrEmpty(retypePasswordValue)) {
                    Toast.makeText(getApplicationContext(), "re-password should not empty", Toast.LENGTH_LONG).show();
                } else if (!passwordValue.equalsIgnoreCase(retypePasswordValue)) {
                    Toast.makeText(getApplicationContext(), "both passwords should match", Toast.LENGTH_LONG).show();
                } else if (Utils.isValidMobile(mobileValue)) {
                    Toast.makeText(getApplicationContext(), "Enter a valid mobile number", Toast.LENGTH_LONG).show();
                } else {
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
                startActivity(new Intent(Registration.this, LoginActivity.class));
                finish();
            }
        });
    }

    private void onRegisterUser(RegistrationPayload registrationPayload) {

        RegistrationDataHandler registrationDataHandler = new RegistrationDataHandler() {
            @Override
            public void resultReceived(RegistrationBO response, boolean fromDB) {
                Toast.makeText(getApplicationContext(), response.getMessage(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void errorReceived(RegistrationBO response) {
                Toast.makeText(getApplicationContext(), response.getMessage(), Toast.LENGTH_SHORT).show();

            }
        };
        registrationDataHandler.onRegisterUser("users", registrationPayload);
    }
}
