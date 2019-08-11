package com.rmit.twig.Controller;

import android.app.Activity;
import android.content.Intent;
import android.view.View;

import com.rmit.twig.View.AdminWelcomeActivity;
import com.rmit.twig.View.MentorWelcomeActivity;
import com.rmit.twig.View.StudentWelcomeActivity;

public class SignInController implements View.OnClickListener {
    private String email;
    private String password;
    private String type;
    private Activity activity;
    private boolean valid;

    public SignInController(String email, String password, Activity activity) {
        this.email = email;
        this.password = password;
        this.activity=activity;
    }

    @Override
    public void onClick(View view) {
        //Method to validate username and password, get user type from database

        if(valid) {
            if (type.equals("student")) {
                Intent student = new Intent(activity, StudentWelcomeActivity.class);
                activity.startActivity(student);
                activity.finish();
            }
            if (type.equals("mentor")) {
                Intent student = new Intent(activity, MentorWelcomeActivity.class);
                activity.startActivity(student);
                activity.finish();
            }
            if (type.equals("admin")) {
                Intent student = new Intent(activity, AdminWelcomeActivity.class);
                activity.startActivity(student);
                activity.finish();
            }
        }
    }
}
