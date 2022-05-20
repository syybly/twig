package com.rmit.twig.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.rmit.twig.R;

public class AdminWelcomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcomeadmin);
        TextView name=findViewById(R.id.Admin);
        name.setText("Welcome "+getIntent().getStringExtra("name")+" , you have logged in as "+getIntent().getStringExtra("type"));
    }
}
