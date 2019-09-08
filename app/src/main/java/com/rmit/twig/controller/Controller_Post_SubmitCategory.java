package com.rmit.twig.controller;

import android.app.Activity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Toast;

import com.rmit.twig.model.User;
import com.rmit.twig.R;
import com.rmit.twig.view.Activity_CreateEvent;
import com.rmit.twig.view.Activity_CreateGenralPost;

public class Controller_Post_SubmitCategory implements View.OnClickListener {

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
            DataHolder.newpost.getCategories().add("Innovation");
        }
        if (box2.isChecked()) {
            DataHolder.newpost.getCategories().add("Conservation");
        }
        if (box3.isChecked()) {
            DataHolder.newpost.getCategories().add("Social Impact");
        }
        if (box4.isChecked()) {
            DataHolder.newpost.getCategories().add("Environmental Impact");
        }
        if (box5.isChecked()) {
            DataHolder.newpost.getCategories().add("Cities");
        }
        if (box6.isChecked()) {
            DataHolder.newpost.getCategories().add("Biodiversity");
        }
        if (box7.isChecked()) {
            DataHolder.newpost.getCategories().add("Wildlife / animals");
        }
        if (box8.isChecked()) {
            DataHolder.newpost.getCategories().add("Sustainable Cities");
        }
        if (box9.isChecked()) {
            DataHolder.newpost.getCategories().add("Urbanisation");
        }
        if (box10.isChecked()) {
            DataHolder.newpost.getCategories().add("Future Design");
        }
        if (box11.isChecked()) {
            DataHolder.newpost.getCategories().add("Industry");
        }
        if (box12.isChecked()) {
            DataHolder.newpost.getCategories().add("Infrastructure");
        }
        if (box13.isChecked()) {
            DataHolder.newpost.getCategories().add("Economic Growth");
        }
        if (box14.isChecked()) {
            DataHolder.newpost.getCategories().add("Technology");
        }
        if (box15.isChecked()) {
            DataHolder.newpost.getCategories().add("Consumption");
        }
        if (box16.isChecked()) {
            DataHolder.newpost.getCategories().add("Entrepreneurship");
        }
        if (box17.isChecked()) {
            DataHolder.newpost.getCategories().add("Leadership");
        }
        if (box18.isChecked()) {
            DataHolder.newpost.getCategories().add("Financial wellbeing");
        }
        if (box19.isChecked()) {
            DataHolder.newpost.getCategories().add("Entrepreneurial Mindset");
        }
        if (box20.isChecked()) {
            DataHolder.newpost.getCategories().add("Self-Development");
        }
        if (DataHolder.newpost.getCategories().size() < 1) {
            Toast less = Toast.makeText(activity, "Please choose at least one category", Toast.LENGTH_SHORT);
            less.show();
        }
        else if (DataHolder.newpost.getCategories().size()>5){
            Toast morethan = Toast.makeText(activity, "Please choose no more than five category", Toast.LENGTH_SHORT);
            morethan.show();
        }
        String cats="";
        for(String s:DataHolder.newpost.getCategories()){
            cats=cats+"#"+s+"   ";
        }
        if(DataHolder.newpost.getType().equals("event")){
            Activity_CreateEvent.cats.setText(cats);
        }
        if(DataHolder.newpost.getType().equals("post")){
            Activity_CreateGenralPost.cats.setText(cats);
        }
        activity.finish();
    }
}
