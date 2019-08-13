package com.rmit.twig.View;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabItem;
import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.Toolbar;

import com.rmit.twig.Controller.DataHolder;
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
        DataHolder dataHolder=new DataHolder();
        context=this;
        signin=findViewById(R.id.Login);
        signup=findViewById(R.id.SignUp);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context, SignupActivity.class);
                startActivity(intent);
            }
        });
        signin.setOnClickListener(new SignInController(this));
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onResume(){
        super.onResume();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }
}
