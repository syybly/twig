package com.rmit.twig.View;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.rmit.twig.Controller.SignUpController;
import com.rmit.twig.R;

public class SignupActivity extends AppCompatActivity {
    private EditText Email;
    private EditText password;
    private EditText comfirmpass;
    private EditText fullname;
    private Spinner typespin;
    private Context context;
    private Activity activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);
        context=this;
        activity=this;
        Email=findViewById(R.id.EmailAdd);
        password=findViewById(R.id.Password);
        comfirmpass=findViewById(R.id.ConfirmPassword);
        fullname=findViewById(R.id.FullName);
        typespin=findViewById(R.id.Spinner);
        Button signup=findViewById(R.id.SignUp);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!password.getText().toString().equals(comfirmpass.getText().toString())){
                    Toast nomatch=Toast.makeText(context, "Password doesn't match", Toast.LENGTH_SHORT);
                    nomatch.show();
                }
                else if(Email.getText().length()==0&&password.getText().length()==0&&fullname.getText().length()==0){
                    Toast nodetail=Toast.makeText(context, "Please fill out all details", Toast.LENGTH_SHORT);
                    nodetail.show();
                }
                else{
                    SignUpController signUpController=new SignUpController(activity);
                    signUpController.run();
                    Toast succeed=Toast.makeText(context, "Sign up successful, please log in", Toast.LENGTH_SHORT);
                    succeed.show();
                    activity.finish();
                }
            }
        });
    }
}
