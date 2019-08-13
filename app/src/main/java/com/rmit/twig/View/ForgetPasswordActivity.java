package com.rmit.twig.View;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.rmit.twig.Controller.SendToEmailController;
import com.rmit.twig.R;

public class ForgetPasswordActivity extends AppCompatActivity {

    private EditText email;
    private Button Submit;

    private Activity activity;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fgtpswdpage);

        EditText Email = findViewById(R.id.email);
        Submit = findViewById(R.id.Submit);
        Submit.setOnClickListener(new SendToEmailController(this));


    }
}
