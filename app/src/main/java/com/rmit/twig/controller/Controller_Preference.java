package com.rmit.twig.controller;

import android.app.Activity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Toast;

import com.rmit.twig.R;
import com.rmit.twig.model.User;
import com.rmit.twig.com.SignUpAsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Controller_Preference implements View.OnClickListener{

    private String type;
    private Activity activity;
    private String name;
    private CheckBox box1, box2, box3, box4, box5, box6, box7, box8, box9, box10,
            box11, box12, box13, box14, box15, box16, box17, box18, box19, box20;
    private User user;

    public Controller_Preference(Activity activity) {
        this.activity=activity;
    }

    public void onClick(View view) {
        box1 = activity.findViewById(R.id.Innovation);
        box2 = activity.findViewById(R.id.Conservation);
        box3 = activity.findViewById(R.id.SocialImpact);
        box4 = activity.findViewById(R.id.Environmentalimpact);
        box5 = activity.findViewById(R.id.Cities);
        box6 = activity.findViewById(R.id.Biodiversity);
        box7 = activity.findViewById(R.id.Wildlife);
        box8 = activity.findViewById(R.id.SustainableCities);
        box9 = activity.findViewById(R.id.Urbanisation);
        box10 = activity.findViewById(R.id.FutureDesign);
        box11 = activity.findViewById(R.id.Industry);
        box12 = activity.findViewById(R.id.Infrastructure);
        box13 = activity. findViewById(R.id.EconomicGrowth);
        box14 = activity.findViewById(R.id.Technology);
        box15 = activity.findViewById(R.id.Consumption);
        box16 = activity.findViewById(R.id.Entrepreneurship);
        box17 = activity.findViewById(R.id.Leadership);
        box18 = activity.findViewById(R.id.Financialwellbeing);
        box19 = activity.findViewById(R.id.EntrepreneurialMindset);
        box20 = activity.findViewById(R.id.SelfDevelopment);
        ArrayList<String> pre=new ArrayList<>();
        if(box1.isChecked()){
            pre.add("Innovation");
        }
        if(box2.isChecked()){
            pre.add("Conservation");
        }
        if(box3.isChecked()){
            pre.add("Social Impact");
        }
        if(box4.isChecked()){
            pre.add("Environmental Impact");
        }
        if(box5.isChecked()){
            pre.add("Cities");
        }
        if(box6.isChecked()){
            pre.add("Biodiversity");
        }
        if(box7.isChecked()){
            pre.add("Wildlife / animals");
        }
        if(box8.isChecked()){
            pre.add("Sustainable Cities");
        }
        if(box9.isChecked()){
            pre.add("Urbanisation");
        }
        if(box10.isChecked()){
            pre.add("Future Design");
        }
        if(box11.isChecked()){
            pre.add("Industry");
        }
        if(box12.isChecked()){
            pre.add("Infrastructure");
        }
        if(box13.isChecked()){
            pre.add("Economic Growth");
        }
        if(box14.isChecked()){
            pre.add("Technology");
        }
        if(box15.isChecked()){
            pre.add("Consumption");
        }
        if(box16.isChecked()){
            pre.add("Entrepreneurship");
        }
        if(box17.isChecked()){
            pre.add("Leadership");
        }
        if(box18.isChecked()){
            pre.add("Financial wellbeing");
        }
        if(box19.isChecked()){
            pre.add("Entrepreneurial Mindset");
        }
        if(box20.isChecked()){
            pre.add("Self-Development");
        }
        if(pre.size()<5){
            Toast lespre = Toast.makeText(activity, "Please choose at least five interests", Toast.LENGTH_SHORT);
            lespre.show();
        }
        if(pre.size()>=5) {
            DataHolder.newuser.setPreference(pre);
            JSONObject signindata = new JSONObject();
            String url = "https://twig-api-v2.herokuapp.com/users/signup";
            try {
                signindata.put("email", DataHolder.newuser.getEmail());
                signindata.put("password", DataHolder.newuser.getPassword());
                signindata.put("name", DataHolder.newuser.getFullname());
                signindata.put("interests", new JSONArray(DataHolder.newuser.getPreference()));
                new SignUpAsyncTask(activity).execute(url, signindata.toString());
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

//        if (type.equals("Student")) {
//            Intent student = new Intent(activity, StudentWelcomeActivity.class);
//            student.putExtra("name",name);
//            student.putExtra("type",type);
//            activity.startActivity(student);
//            activity.finish();
//        }
//        if (type.equals("Mentor")) {
//            Intent intent = new Intent(activity, MentorWelcomeActivity.class);
//            intent.putExtra("name",name);
//            intent.putExtra("type",type);
//            activity.startActivity(intent);
//            activity.finish();
//        }
//        if (type.equals("Admin")) {
//            Intent intent = new Intent(activity, AdminWelcomeActivity.class);
//            intent.putExtra("name",name);
//            intent.putExtra("type",type);
//            activity.startActivity(intent);
//            activity.finish();
//        }


    }
}
