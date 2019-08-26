package com.rmit.twig.View;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;

import com.rmit.twig.Controller.Controller_Post_SubmitCategory;
import com.rmit.twig.Controller.DataHolder;
import com.rmit.twig.R;

public class Activity_PostCategory extends AppCompatActivity {
    private CheckBox box1, box2, box3, box4, box5, box6, box7, box8, box9, box10,
            box11, box12, box13, box14, box15, box16, box17, box18, box19, box20;
    private Button submit;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DataHolder.postcategories.clear();
        setContentView(R.layout.activity_post_category);
        box1 = (CheckBox) findViewById(R.id.Innovation);
        box2 = (CheckBox) findViewById(R.id.Conservation);
        box3 = (CheckBox) findViewById(R.id.SocialImpact);
        box4 = (CheckBox) findViewById(R.id.Environmentalimpact);
        box5 = (CheckBox) findViewById(R.id.Cities);
        box6 = (CheckBox) findViewById(R.id.Biodiversity);
        box7 = (CheckBox) findViewById(R.id.Wildlife);
        box8 = (CheckBox) findViewById(R.id.SustainableCities);
        box9 = (CheckBox) findViewById(R.id.Urbanisation);
        box10 = (CheckBox) findViewById(R.id.FutureDesign);
        box11 = (CheckBox) findViewById(R.id.Industry);
        box12 = (CheckBox) findViewById(R.id.Infrastructure);
        box13 = (CheckBox) findViewById(R.id.EconomicGrowth);
        box14 = (CheckBox) findViewById(R.id.Technology);
        box15 = (CheckBox) findViewById(R.id.Consumption);
        box16 = (CheckBox) findViewById(R.id.Entrepreneurship);
        box17 = (CheckBox) findViewById(R.id.Leadership);
        box18 = (CheckBox) findViewById(R.id.Financialwellbeing);
        box19 = (CheckBox) findViewById(R.id.EntrepreneurialMindset);
        box20 = (CheckBox) findViewById(R.id.SelfDevelopment);

        submit = (Button) findViewById(R.id.submit);
        submit.setOnClickListener(new Controller_Post_SubmitCategory(this));
    }
}
