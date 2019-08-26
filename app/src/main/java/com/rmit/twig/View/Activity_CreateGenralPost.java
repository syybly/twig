package com.rmit.twig.View;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Location;
import android.location.LocationListener;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.rmit.twig.Controller.ClickListener_Post;
import com.rmit.twig.Controller.DataHolder;
import com.rmit.twig.R;
import com.rmit.twig.Service.Service_Location;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.HashSet;

public class Activity_CreateGenralPost extends AppCompatActivity implements LocationListener {
    private TextView post;
    private Activity activity;
    public RelativeLayout categorylayout;
    public static HashSet<String> categories;
    private ImageView userphoto;
    private TextView name;
    public static TextView location;
    private ImageButton addimage;
    private ImageView postaddimage1;

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
        addimage=findViewById(R.id.addimage);
        postaddimage1=findViewById(R.id.addpostimage1);
        addimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Complete action using"), 1);
            }
        });
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Check which request we're responding to
        if (requestCode == 1) {
            // Make sure the request was successful
            if (resultCode == RESULT_OK) {
                postaddimage1.setImageURI(data.getData());
                postaddimage1.setVisibility(View.VISIBLE);
                BitmapFactory.Options o = new BitmapFactory.Options();
                o.inJustDecodeBounds = true;
                o.inSampleSize = 6;
//                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver() , data.getData());
                File f = new File(Environment.getExternalStorageDirectory().toString());
                Bitmap bitmap=BitmapFactory.decodeFile(f.getPath(), o);

            }
        }
    }
}
