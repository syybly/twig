package com.rmit.twig.Controller;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.EditText;

import com.rmit.twig.R;
import com.rmit.twig.View.SentEmailActivity;

public class SendToEmailController implements View.OnClickListener {
    private String email;
    private Activity activity;

    public SendToEmailController(Activity activity) {
        this.activity = activity;
    }

    public void onClick(View view) {
        //Method to validate username and password, get user type from database
        EditText Email = activity.findViewById(R.id.email);
        this.email=Email.getText().toString();
        Intent intent = new Intent(activity, SentEmailActivity.class);
        intent.putExtra("email",email);
        activity.startActivity(intent);


    }
}