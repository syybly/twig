package com.rmit.twig.view;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.github.jjobes.slidedatetimepicker.SlideDateTimeListener;
import com.github.jjobes.slidedatetimepicker.SlideDateTimePicker;
import com.rmit.twig.controller.ClickListener_Post;
import com.rmit.twig.controller.DataHolder;
import com.rmit.twig.R;
import com.rmit.twig.model.EventPost;
import com.rmit.twig.service.Service_Location;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.Date;
import java.util.HashSet;

public class Activity_CreateEvent extends AppCompatActivity {
    private ImageView userphoto;
    private TextView name;
    private TextView post;
    private Activity activity;
    public RelativeLayout categorylayout;
    public static TextView location;
    public static HashSet<String> categories;
    private RelativeLayout timedate;
    private String eventtime;
    private RelativeLayout addlocation;
    private ImageButton addimage;
    private ImageView postaddimage1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_event);
        activity=this;
        DataHolder.newpost=new EventPost(DataHolder.currentuser,"event");
        DataHolder.newpost.setUser(DataHolder.users.get(DataHolder.currentuser));
        categorylayout=findViewById(R.id.addcategory);
        post=findViewById(R.id.post);
        post.setOnClickListener(new ClickListener_Post(activity,"event"));
        userphoto= findViewById(R.id.feed_userphoto);
        name=findViewById(R.id.feed_name);
        name.setText(DataHolder.users.get(DataHolder.currentuser).getFullname());
        addlocation=findViewById(R.id.addlocation);
        addlocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent locationactivity=new Intent(activity,Activity_AddLocation.class);
                startActivity(locationactivity);
            }
        });
        timedate=findViewById(R.id.addtimedate);
        timedate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new SlideDateTimePicker.Builder(getSupportFragmentManager())
                        .setListener(listener)
                        .setInitialDate(new Date())
                        .build()
                        .show();
            }
        });
        addimage=findViewById(R.id.addimage);
        postaddimage1=findViewById(R.id.addpostimage1);
        addimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String[] PERMISSIONS ={  Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE };
                if ( ContextCompat.checkSelfPermission( activity, Manifest.permission.READ_EXTERNAL_STORAGE ) != PackageManager.PERMISSION_GRANTED ) {
                    ActivityCompat.requestPermissions( activity, PERMISSIONS ,113);
                }
                else {
                    Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    intent.setType("image/*");
//                    intent.setAction(Intent.ACTION_GET_CONTENT);
                    startActivityForResult(Intent.createChooser(intent, "Complete action using"), 5);
                }
            }

        });
        Picasso.with(this)
                .load(DataHolder.users.get(DataHolder.currentuser).getPhotourl())
                .placeholder(R.drawable.nophoto)
                .error(R.drawable.nophoto)
                .into(userphoto);
        location=findViewById(R.id.feed_location);
        location.setVisibility(View.INVISIBLE);
        categorylayout=findViewById(R.id.addcategory);
        post=findViewById(R.id.post);
        categorylayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent catintent=new Intent(activity, Activity_PostCategory.class);
                startActivity(catintent);
            }
        });
    }

    private SlideDateTimeListener listener = new SlideDateTimeListener() {

        @Override
        public void onDateTimeSet(Date date)
        {
            eventtime=date.toString();
            long timeInMilliSeconds = date.getTime();
            DataHolder.newpost.setDate(timeInMilliSeconds);
        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // Check which request we're responding to
        if (requestCode == 5) {
            // Make sure the request was successful
            if (resultCode == RESULT_OK) {
                postaddimage1.setImageURI(data.getData());
                postaddimage1.setVisibility(View.VISIBLE);
                Uri imageUri = data.getData();
                String imagepath=imageUri.getPath();
                String[] filePathColumn = { MediaStore.Images.Media.DATA };
                Cursor cursor = getContentResolver().query(imageUri,
                        filePathColumn, null, null, null);
                cursor.moveToFirst();
                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                imagepath= cursor.getString(columnIndex);
                cursor.close();
                File imagefile=new File(imagepath);
                DataHolder.newpost.getNewpostimages().add(imagefile);
//                try {
//                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), imageUri);
//                    imagefiles.add(bitmap);
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }

            }
        }
    }
}
