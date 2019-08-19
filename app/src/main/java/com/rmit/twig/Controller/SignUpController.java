package com.rmit.twig.Controller;

import android.app.Activity;
import android.content.Intent;
import android.widget.EditText;
import android.widget.Spinner;

import com.rmit.twig.Model.Admin;
import com.rmit.twig.Model.Mentor;
import com.rmit.twig.Model.Student;
import com.rmit.twig.Model.User;
import com.rmit.twig.R;
import com.rmit.twig.View.PreferenceAcitivity;

public class SignUpController{

    private Activity activity;
    private EditText name;
    private EditText email;
    private EditText password;
//    private Spinner type;
    private String Type;

    public SignUpController(Activity activity) {
        this.activity = activity;
        name=activity.findViewById(R.id.FullName);
        email=activity.findViewById(R.id.EmailAdd);
        password=activity.findViewById(R.id.Password);
//        type=activity.findViewById(R.id.Spinner);
    }

    public void run() {
//        Type=type.getSelectedItem().toString();
        Type="Student";
        String Email=email.getText().toString();
        String Password=password.getText().toString();
        String fullname=name.getText().toString();
//        User user=new User(email.getText().toString(),password.getText().toString(),name.getText().toString(),Type);
//        if(Type.equals("Student")){
//            DataHolder.users.put(email.getText().toString(),new Student(Email,Password,fullname));
//        }
//        else if(Type.equals("Mentor")){
//            DataHolder.users.put(email.getText().toString(),new Mentor(Email,Password,fullname));
//        }
//        else if(Type.equals("Admin")){
//            DataHolder.users.put(email.getText().toString(),new Admin(Email,Password,fullname));
//        }
        Intent intent=new Intent(activity, PreferenceAcitivity.class);
        activity.startActivity(intent);
    }
}
