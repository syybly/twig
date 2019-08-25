package com.rmit.twig.View;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.os.Handler;
import android.os.ResultReceiver;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.rmit.twig.Controller.ClickListener_Post;
import com.rmit.twig.Controller.DataHolder;
import com.rmit.twig.R;
import com.rmit.twig.Service.Service_Location;
import com.squareup.picasso.Picasso;

import java.util.HashSet;

public class Activity_CreateGenralPost extends AppCompatActivity implements LocationListener {
    private TextView post;
    private Activity activity;
    public RelativeLayout categorylayout;
    public static HashSet<String> categories;
    private ImageView userphoto;
    private TextView name;
    public static TextView location;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_genral_post);
        categories=new HashSet<>();
        activity=this;
        userphoto= findViewById(R.id.feed_userphoto);
        name=findViewById(R.id.feed_name);
        name.setText(DataHolder.user.getFullname());
        Picasso.with(this)
                .load(DataHolder.user.getPhotourl())
                .placeholder(R.drawable.nophoto)
                .error(R.drawable.nophoto)
                .into(userphoto);
        categorylayout=findViewById(R.id.addcategory);
        post=findViewById(R.id.post);
        categorylayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent catintent=new Intent(activity, Activity_PostCategory.class);
                startActivity(catintent);
            }
        });
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
                    intent.putExtra("type","general");
                    activity.startService(intent);
                }
            }
        });
        post.setOnClickListener(new ClickListener_Post(activity,"GeneralPost"));
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onResume() {
        super.onResume();
        for (String s:categories){
            System.out.println(s);
        }
    }

    @Override
    public void onLocationChanged(Location location) {

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }
}
