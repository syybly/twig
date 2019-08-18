package com.rmit.twig.View;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.rmit.twig.Controller.DataHolder;
import com.rmit.twig.R;

public class ForgetPasswordActivity extends AppCompatActivity {

    private String email;
    private Button Submit;
    private Context context;
    private TextView warning;


    private Activity activity;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fgtpswdpage);

        final EditText Email = findViewById(R.id.email);
        Submit = findViewById(R.id.Submit);
        warning = findViewById(R.id.warning);
//        Submit.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                email=Email.getText().toString();
//                if(DataHolder.users.containsKey(email)) {
//                Intent intent = new Intent(context, SentEmailActivity.class);
//                intent.putExtra("email", email);
//                activity.startActivity(intent);
//                }else {
//                     warning.setText("Invalid Email Address");
//                 }
//            }
//
//
//         });
}
}
