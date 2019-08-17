package com.rmit.twig.Controller;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.rmit.twig.View.LoginActivity;

public class ResetPasswordController implements View.OnClickListener{
    private EditText Password;
    private EditText ConfirmPassword;
    private Button ResetPassword;
    private Activity activity;

    public void onClick(View view) {
        Intent intent = new Intent(activity, LoginActivity.class);
        activity.startActivity(intent);
    }



}
