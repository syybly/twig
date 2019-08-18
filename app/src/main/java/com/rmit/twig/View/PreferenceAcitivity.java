package com.rmit.twig.View;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.content.Intent;

import com.rmit.twig.R;
import com.rmit.twig.Controller.PreferenceController;

public class PreferenceAcitivity extends AppCompatActivity {

    private CheckBox box1, box2, box3, box4, box5, box6, box7, box8, box9, box10,
            box11, box12, box13, box14, box15, box16, box17, box18, box19, box20;
    private Button preference;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.perpage);

        box1 = (CheckBox) findViewById(R.id.reading);
        box2 = (CheckBox) findViewById(R.id.music);
        box3 = (CheckBox) findViewById(R.id.socialmedia);
        box4 = (CheckBox) findViewById(R.id.animal);
        box5 = (CheckBox) findViewById(R.id.beauty);
        box6 = (CheckBox) findViewById(R.id.travel);
        box7 = (CheckBox) findViewById(R.id.NBA);
        box8 = (CheckBox) findViewById(R.id.shopping);
        box9 = (CheckBox) findViewById(R.id.food);
        box10 = (CheckBox) findViewById(R.id.sporting);
        box11 = (CheckBox) findViewById(R.id.programming);
        box12 = (CheckBox) findViewById(R.id.architecture);
        box13 = (CheckBox) findViewById(R.id.design);
        box14 = (CheckBox) findViewById(R.id.commerce);
        box15 = (CheckBox) findViewById(R.id.engineering);
        box16 = (CheckBox) findViewById(R.id.education);
        box17 = (CheckBox) findViewById(R.id.nursing);
        box18 = (CheckBox) findViewById(R.id.gaming);
        box19 = (CheckBox) findViewById(R.id.volunteer);
        box20 = (CheckBox) findViewById(R.id.gymming);

        preference = (Button) findViewById(R.id.preference);
        preference.setOnClickListener(new PreferenceController(this));


    }
}
