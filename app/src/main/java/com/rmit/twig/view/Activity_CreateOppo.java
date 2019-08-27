package com.rmit.twig.view;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.rmit.twig.controller.ClickListener_Post;
import com.rmit.twig.controller.DataHolder;
import com.rmit.twig.R;
import com.rmit.twig.service.Service_Location;
import com.squareup.picasso.Picasso;

import java.util.HashSet;

public class Activity_CreateOppo extends AppCompatActivity {
    private ImageView userphoto;
    private TextView post;
    private Activity activity;
    public RelativeLayout categorylayout;
    public static HashSet<String> categories;
    private TextView name;
    public static TextView location;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_oppo);
        activity=this;
        categorylayout=findViewById(R.id.addcategory);
        post=findViewById(R.id.post);
        post.setOnClickListener(new ClickListener_Post(activity,"Opportunity"));
        userphoto= findViewById(R.id.feed_userphoto);
        name=findViewById(R.id.feed_name);
        name.setText(DataHolder.user.getFullname());
        Picasso.with(this)
                .load(DataHolder.user.getPhotourl())
                .placeholder(R.drawable.nophoto)
                .error(R.drawable.nophoto)
                .into(userphoto);
        location=findViewById(R.id.feed_location);
        location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                location.setText("Locating...");
                String[] PERMISSIONS ={  android.Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION };
                if ( ContextCompat.checkSelfPermission( activity, android.Manifest.permission.ACCESS_COARSE_LOCATION ) != PackageManager.PERMISSION_GRANTED ) {
                    ActivityCompat.requestPermissions( activity, PERMISSIONS ,112);
                }
                else {
                    Intent intent=new Intent(activity, Service_Location.class);
                    intent.putExtra("type","oppo");
                    activity.startService(intent);
                }
            }
        });
    }
}
