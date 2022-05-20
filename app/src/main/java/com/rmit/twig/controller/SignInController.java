package com.rmit.twig.controller;

import android.app.Activity;
import android.view.View;
import android.widget.EditText;

import com.rmit.twig.R;
import com.rmit.twig.asynctask.SignInAsyncTask;

public class SignInController implements View.OnClickListener {
    private String email;
    private String password;
    private Activity activity;

    public SignInController(Activity activity) {
        this.activity=activity;
    }

    @Override
    public void onClick(View view) {
        //Method to validate username and password, get user type from database
        EditText Email=activity.findViewById(R.id.Email);
        EditText Password=activity.findViewById(R.id.Password);
        this.email=Email.getText().toString();
        this.password=Password.getText().toString();
        new SignInAsyncTask(activity).execute(email,password);

    }
}
