package com.rmit.twig.view;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;

import com.rmit.twig.controller.Controller_Post_SubmitCategory;
import com.rmit.twig.controller.DataHolder;
import com.rmit.twig.R;
import com.rmit.twig.model.Post;

import java.util.HashSet;

public class Activity_PostCategory extends AppCompatActivity {
    private CheckBox box1, box2, box3, box4, box5, box6, box7, box8, box9, box10,
            box11, box12, box13, box14, box15, box16, box17, box18, box19, box20;
    private Button submit;
    private Context context;
    private Post post;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_post_category);

        String postID = getIntent().getStringExtra("postID");
        Post post = null;
        if (postID != null) {
            for (Post p : DataHolder.posts) {
                if (p.getPostID().equals(postID)) {
                    post = p;
                }
            }
        }


        if (post == null) {
            post = DataHolder.newpost;
            post.getCategories().clear();
        }

        HashSet<String> categories = post.getCategories();

        box1 = (CheckBox) findViewById(R.id.Innovation);
        if (categories.contains("Innovation")) {
            box1.toggle();
        }

        box2 = (CheckBox) findViewById(R.id.Conservation);
        if (categories.contains("Conservation")) {
            box2.toggle();
        }
        box3 = (CheckBox) findViewById(R.id.SocialImpact);
        if (categories.contains("Social Impact")) {
            box3.toggle();
        }

        box4 = (CheckBox) findViewById(R.id.Environmentalimpact);
        if (categories.contains("Environmental Impact")) {
            box4.toggle();
        }
        box5 = (CheckBox) findViewById(R.id.Cities);
        if (categories.contains("Cities")) {
            box5.toggle();
        }
        box6 = (CheckBox) findViewById(R.id.Biodiversity);
        if (categories.contains("Biodiversity")) {
            box6.toggle();
        }
        box7 = (CheckBox) findViewById(R.id.Wildlife);
        if (categories.contains("Wildlife/ animal")) {
            box7.toggle();
        }
        box8 = (CheckBox) findViewById(R.id.SustainableCities);
        if (categories.contains("Sustainable Cities")) {
            box8.toggle();
        }
        box9 = (CheckBox) findViewById(R.id.Urbanisation);
        if (categories.contains("Urbanisation")) {
            box9.toggle();
        }
        box10 = (CheckBox) findViewById(R.id.FutureDesign);
        if (categories.contains("Future Design")) {
            box10.toggle();
        }
        box11 = (CheckBox) findViewById(R.id.Industry);
        if (categories.contains("Industry")) {
            box11.toggle();
        }
        box12 = (CheckBox) findViewById(R.id.Infrastructure);
        if (categories.contains("Infrastructure")) {
            box12.toggle();
        }
        box13 = (CheckBox) findViewById(R.id.EconomicGrowth);
        if (categories.contains("Economic Growth")) {
            box13.toggle();
        }
        box14 = (CheckBox) findViewById(R.id.Technology);
        if (categories.contains("Technology")) {
            box14.toggle();
        }
        box15 = (CheckBox) findViewById(R.id.Consumption);
        if (categories.contains("Consumption")) {
            box15.toggle();
        }
        box16 = (CheckBox) findViewById(R.id.Entrepreneurship);
        if (categories.contains("Entrepreneurship")) {
            box16.toggle();
        }

        box17 = (CheckBox) findViewById(R.id.Leadership);
        if (categories.contains("Leadership")) {
            box17.toggle();
        }
        box18 = (CheckBox) findViewById(R.id.Financialwellbeing);
        if (categories.contains("Financial wellbeing")) {
            box18.toggle();
        }
        box19 = (CheckBox) findViewById(R.id.EntrepreneurialMindset);
        if (categories.contains("Entrepreneurial Mindset")) {
            box19.toggle();
        }
        box20 = (CheckBox) findViewById(R.id.SelfDevelopment);
        if (categories.contains("Self-Development")) {
            box20.toggle();
        }

        submit = (Button) findViewById(R.id.submit);
        submit.setOnClickListener(new Controller_Post_SubmitCategory(this, post));
    }
}
