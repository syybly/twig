package com.rmit.twig.Controller;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.CheckBox;

import com.rmit.twig.R;
import com.rmit.twig.View.AdminWelcomeActivity;
import com.rmit.twig.View.MentorWelcomeActivity;
import com.rmit.twig.View.StudentWelcomeActivity;
import com.rmit.twig.Model.User;

public class PreferenceController implements View.OnClickListener{

    private String type;
    private Activity activity;
    private String name;
    private CheckBox box1, box2, box3, box4, box5, box6, box7, box8, box9, box10,
            box11, box12, box13, box14, box15, box16, box17, box18, box19, box20;
    private User user;

    public PreferenceController(Activity activity) {
        this.activity=activity;
    }

    public void onClick(View view) {
        box1 = activity.findViewById(R.id.reading);
        box2 = activity.findViewById(R.id.music);
        box3 = activity.findViewById(R.id.socialmedia);
        box4 = activity.findViewById(R.id.animal);
        box5 = activity.findViewById(R.id.beauty);
        box6 = activity.findViewById(R.id.travel);
        box7 = activity.findViewById(R.id.NBA);
        box8 = activity.findViewById(R.id.shopping);
        box9 = activity.findViewById(R.id.food);
        box10 = activity.findViewById(R.id.sporting);
        box11 = activity.findViewById(R.id.programming);
        box12 = activity.findViewById(R.id.architecture);
        box13 = activity. findViewById(R.id.design);
        box14 = activity.findViewById(R.id.commerce);
        box15 = activity.findViewById(R.id.engineering);
        box16 = activity.findViewById(R.id.education);
        box17 = activity.findViewById(R.id.nursing);
        box18 = activity.findViewById(R.id.gaming);
        box19 = activity.findViewById(R.id.volunteer);
        box20 = activity.findViewById(R.id.gymming);
        if(box1.isChecked()){
            user.addPreference("reading");
        }
        if(box2.isChecked()){
            user.addPreference("music");
        }
        if(box3.isChecked()){
            user.addPreference("socialmedia");
        }
        if(box4.isChecked()){
            user.addPreference("animal");
        }
        if(box5.isChecked()){
            user.addPreference("beauty");
        }
        if(box6.isChecked()){
            user.addPreference("travel");
        }
        if(box7.isChecked()){
            user.addPreference("NBA");
        }
        if(box8.isChecked()){
            user.addPreference("shopping");
        }
        if(box9.isChecked()){
            user.addPreference("food");
        }
        if(box10.isChecked()){
            user.addPreference("sporting");
        }
        if(box11.isChecked()){
            user.addPreference("programming");
        }
        if(box12.isChecked()){
            user.addPreference("architecture");
        }
        if(box13.isChecked()){
            user.addPreference("design");
        }
        if(box14.isChecked()){
            user.addPreference("commerce");
        }
        if(box15.isChecked()){
            user.addPreference("engieneering");
        }
        if(box16.isChecked()){
            user.addPreference("education");
        }
        if(box17.isChecked()){
            user.addPreference("nursing");
        }
        if(box18.isChecked()){
            user.addPreference("gaming");
        }
        if(box19.isChecked()){
            user.addPreference("volunteer");
        }
        if(box20.isChecked()){
            user.addPreference("gymming");
        }

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
}
