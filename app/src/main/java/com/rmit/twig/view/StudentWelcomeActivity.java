package com.rmit.twig.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.rmit.twig.R;

public class StudentWelcomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcomestudent);
        TextView name=findViewById(R.id.Student);
        name.setText("Welcome "+getIntent().getStringExtra("name")+" , you have logged in as "+getIntent().getStringExtra("type"));
    }
}
