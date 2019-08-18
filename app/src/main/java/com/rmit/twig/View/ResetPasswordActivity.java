package com.rmit.twig.View;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;

import com.rmit.twig.Controller.ResetPasswordController;
import com.rmit.twig.R;

public class ResetPasswordActivity extends AppCompatActivity {

    private EditText Password;
    private EditText ConfirmPassword;
    private Button ResetPassword;
    private Activity activity;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fgtpswdpage);

        Password=findViewById(R.id.Password);
        ConfirmPassword=findViewById(R.id.ConfirmPassword);
//        ResetPassword=findViewById(R.id.ResetPassword);
        ResetPassword.setOnClickListener(new ResetPasswordController());



    }
}
