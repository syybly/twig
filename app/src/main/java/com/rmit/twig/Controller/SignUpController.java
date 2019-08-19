package com.rmit.twig.Controller;

import android.app.Activity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.rmit.twig.Model.User;
import com.rmit.twig.R;
import com.rmit.twig.com.CheckEmail;
import com.rmit.twig.com.SignUpAsyncTask;

public class SignUpController implements View.OnClickListener {

    private Activity activity;
    private EditText name;
    private EditText email;
    private EditText password;
    private EditText comfirmpass;

    public SignUpController(Activity activity) {
        this.activity = activity;

    }

    @Override
    public void onClick(View v) {
        name=activity.findViewById(R.id.FullName);
        email=activity.findViewById(R.id.EmailAdd);
        password=activity.findViewById(R.id.PasswordSignup);
        comfirmpass=activity.findViewById(R.id.ConfirmPassword);
        String Email=email.getText().toString();
        String Password=password.getText().toString();
        String fullname=name.getText().toString();
        String Comfirmpass=comfirmpass.getText().toString();
        if(!Password.equals(Comfirmpass)){
            Toast nomatch=Toast.makeText(activity, "Password doesn't match", Toast.LENGTH_SHORT);
            nomatch.show();
        }
        else if(Email.length()==0&&password.getText().length()==0&&fullname.length()==0){
            Toast nodetail=Toast.makeText(activity, "Please fill out all details", Toast.LENGTH_SHORT);
            nodetail.show();
        }
        else{
            email.getText().clear();
            password.getText().clear();
            comfirmpass.getText().clear();
            name.getText().clear();
            DataHolder.user=new User(Email,Password,fullname);
            String url ="https://twig-api-v2.herokuapp.com/users/check-user/";
            new CheckEmail(activity).execute(url+Email);
        }
    }
}
