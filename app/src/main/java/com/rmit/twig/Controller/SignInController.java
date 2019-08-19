package com.rmit.twig.Controller;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.rmit.twig.R;
import com.rmit.twig.View.AdminWelcomeActivity;
import com.rmit.twig.View.HomepageActivity;
import com.rmit.twig.View.MentorWelcomeActivity;
import com.rmit.twig.com.SignInAsyncTask;

import org.json.JSONException;
import org.json.JSONObject;

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
        validation();
//        if(validation()) {
//            if (type.equals("Student")) {
//                Intent student = new Intent(activity, HomepageActivity.class);
//                student.putExtra("name",name);
//                student.putExtra("type",type);
//                activity.startActivity(student);
//                activity.finish();
//            }
//            if (type.equals("Mentor")) {
//                Intent intent = new Intent(activity, MentorWelcomeActivity.class);
//                intent.putExtra("name",name);
//                intent.putExtra("type",type);
//                activity.startActivity(intent);
//                activity.finish();
//            }
//            if (type.equals("Admin")) {
//                Intent intent = new Intent(activity, AdminWelcomeActivity.class);
//                intent.putExtra("name",name);
//                intent.putExtra("type",type);
//                activity.startActivity(intent);
//                activity.finish();
//            }
//        }
//        else{
//            Toast nomatch=Toast.makeText(activity, "Please check your email or password", Toast.LENGTH_SHORT);
//            nomatch.show();
//        }
    }

    public boolean validation(){
        JSONObject signindata = new JSONObject();
        String url ="https://twig-api-v2.herokuapp.com/users/signin";
        try {
            signindata.put("email", email);
            signindata.put("password", password);
            new SignInAsyncTask(activity).execute(url,signindata.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return false;
}
}
