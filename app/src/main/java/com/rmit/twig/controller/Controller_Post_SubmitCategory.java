package com.rmit.twig.controller;

import android.app.Activity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Toast;

import com.rmit.twig.model.User;
import com.rmit.twig.R;
import com.rmit.twig.view.Activity_CreateGenralPost;

public class Controller_Post_SubmitCategory implements View.OnClickListener {

    private String type;
    private Activity activity;
    private String name;
    private CheckBox box1, box2, box3, box4, box5, box6, box7, box8, box9, box10,
            box11, box12, box13, box14, box15, box16, box17, box18, box19, box20;
    private User user;

    public Controller_Post_SubmitCategory(Activity activity){
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
        box13 = activity.findViewById(R.id.EconomicGrowth);
        box14 = activity.findViewById(R.id.Technology);
        box15 = activity.findViewById(R.id.Consumption);
        box16 = activity.findViewById(R.id.Entrepreneurship);
        box17 = activity.findViewById(R.id.Leadership);
        box18 = activity.findViewById(R.id.Financialwellbeing);
        box19 = activity.findViewById(R.id.EntrepreneurialMindset);
        box20 = activity.findViewById(R.id.SelfDevelopment);
        if (box1.isChecked()) {
            DataHolder.postcategories.add("Innovation");
        }
        if (box2.isChecked()) {
            DataHolder.postcategories.add("Conservation");
        }
        if (box3.isChecked()) {
            DataHolder.postcategories.add("Social Impact");
        }
        if (box4.isChecked()) {
            DataHolder.postcategories.add("Environmental Impact");
        }
        if (box5.isChecked()) {
            DataHolder.postcategories.add("Cities");
        }
        if (box6.isChecked()) {
            DataHolder.postcategories.add("Biodiversity");
        }
        if (box7.isChecked()) {
            DataHolder.postcategories.add("Wildlife / animals");
        }
        if (box8.isChecked()) {
            DataHolder.postcategories.add("Sustainable Cities");
        }
        if (box9.isChecked()) {
            DataHolder.postcategories.add("Urbanisation");
        }
        if (box10.isChecked()) {
            DataHolder.postcategories.add("Future Design");
        }
        if (box11.isChecked()) {
            DataHolder.postcategories.add("Industry");
        }
        if (box12.isChecked()) {
            DataHolder.postcategories.add("Infrastructure");
        }
        if (box13.isChecked()) {
            DataHolder.postcategories.add("Economic Growth");
        }
        if (box14.isChecked()) {
            DataHolder.postcategories.add("Technology");
        }
        if (box15.isChecked()) {
            DataHolder.postcategories.add("Consumption");
        }
        if (box16.isChecked()) {
            DataHolder.postcategories.add("Entrepreneurship");
        }
        if (box17.isChecked()) {
            DataHolder.postcategories.add("Leadership");
        }
        if (box18.isChecked()) {
            DataHolder.postcategories.add("Financial wellbeing");
        }
        if (box19.isChecked()) {
            DataHolder.postcategories.add("Entrepreneurial Mindset");
        }
        if (box20.isChecked()) {
            DataHolder.postcategories.add("Self-Development");
        }
        if (DataHolder.postcategories.size() < 1) {
            Toast less = Toast.makeText(activity, "Please choose at least one category", Toast.LENGTH_SHORT);
            less.show();
        }
        else if (DataHolder.postcategories.size()>5){
            Toast morethan = Toast.makeText(activity, "Please choose no more than five category", Toast.LENGTH_SHORT);
            morethan.show();
        }
        Activity_CreateGenralPost.categories.clear();
//        for (String s:DataHolder.postcategories){
//            Activity_CreateGenralPost.categories.add(s);
//        }
        activity.finish();
    }
}
