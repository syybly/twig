package com.rmit.twig.Controller;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.rmit.twig.R;
import com.rmit.twig.View.AdminWelcomeActivity;
import com.rmit.twig.View.MentorWelcomeActivity;
import com.rmit.twig.View.StudentWelcomeActivity;

public class SignInController implements View.OnClickListener {
    private String email;
    private String password;
    private String type;
    private Activity activity;
    private String name;

    public SignInController(Activity activity) {
        this.activity=activity;
    }

    @Override
    public void onClick(View view) {
        //Method to validate username and password, get user type from database
        EditText Email=activity.findViewById(R.id.Email);
        EditText Password=activity.findViewById(R.id.Password);
        this.email=Email.getText().toString();
        this.password=Password.getText().toString();
        if(validation()) {
            if (type.equals("Student")) {
                Intent student = new Intent(activity, StudentWelcomeActivity.class);
                student.putExtra("name",name);
                student.putExtra("type",type);
                activity.startActivity(student);
                activity.finish();
            }
            if (type.equals("Mentor")) {
                Intent intent = new Intent(activity, MentorWelcomeActivity.class);
                intent.putExtra("name",name);
                intent.putExtra("type",type);
                activity.startActivity(intent);
                activity.finish();
            }
            if (type.equals("Admin")) {
                Intent intent = new Intent(activity, AdminWelcomeActivity.class);
                intent.putExtra("name",name);
                intent.putExtra("type",type);
                activity.startActivity(intent);
                activity.finish();
            }
        }
        else{
            Toast nomatch=Toast.makeText(activity, "Please check your email or password", Toast.LENGTH_SHORT);
            nomatch.show();
        }
    }

    public boolean validation(){
        if(DataHolder.users.containsKey(email)){
            if (this.password.equals(DataHolder.users.get(email).getPassword())){
                name=DataHolder.users.get(email).getFullname();
                type=DataHolder.users.get(email).getType();
                return true;
            }
        }
        return false;
    }
}
