package com.rmit.twig.View;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.rmit.twig.Controller.SignInController;
import com.rmit.twig.R;

public class LoginActivity extends AppCompatActivity {

    private EditText Email;
    private EditText password;
    private Button signin;
    private Button signup;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loginpage);
        context=this;
        Email=findViewById(R.id.Email);
        password=findViewById(R.id.Password);
        signin=findViewById(R.id.Login);
        signup=findViewById(R.id.SignUp);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context, SignupActivity.class);
                startActivity(intent);
            }
        });
        if(Email.getText().length()>0&&password.getText().length()>0) {
            signin.setOnClickListener(new SignInController(Email.getText().toString(),password.getText().toString(),this));
        }
    }
}
