package com.rmit.twig.view;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.rmit.twig.R;
import com.rmit.twig.controller.DataHolder;

public class Activity_AddLocation extends AppCompatActivity {
    private EditText eventlocation;
    private Button locationsubmit;
    private Activity activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_location);
        activity=this;
        eventlocation=findViewById(R.id.eventlocation);
        locationsubmit=findViewById(R.id.locationsubmit);
        locationsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(eventlocation.getText()==null){
                    Toast toast=Toast.makeText(activity, "You must set a location for the event", Toast.LENGTH_SHORT);
                    toast.show();
                }
                else{
                    DataHolder.newpost.setLocation(eventlocation.getText().toString());
                    Activity_CreateEvent.locationtext.setText(eventlocation.getText().toString());
                    activity.finish();
                }
            }
        });
    }
}
