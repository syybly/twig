package com.rmit.twig.View;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.rmit.twig.R;

public class SentEmailActivity extends AppCompatActivity {
    private Context context;
    private Button button;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sentemail);

        TextView sentConfirm = findViewById(R.id.confirmation);
        sentConfirm.setText("A code has been already sent to Email "+getIntent().getStringExtra("email"));

        button.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ResetPasswordActivity.class);
                startActivity(intent);
            }
        }));
    }
}
