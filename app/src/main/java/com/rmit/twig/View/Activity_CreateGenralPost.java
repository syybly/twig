package com.rmit.twig.View;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.rmit.twig.Controller.ClickListener_Post;
import com.rmit.twig.Controller.DataHolder;
import com.rmit.twig.R;
import com.squareup.picasso.Picasso;

import java.util.HashSet;

public class Activity_CreateGenralPost extends AppCompatActivity {
    private TextView post;
    private Activity activity;
    public RelativeLayout categorylayout;
    public static HashSet<String> categories;
    private ImageView userphoto;
    private TextView name;
    private TextView location;

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
        post.setOnClickListener(new ClickListener_Post(activity,"GeneralPost"));
        categorylayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent catintent=new Intent(activity, Activity_PostCategory.class);
                startActivity(catintent);
            }
        });
        location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                location.setText("Locating...");
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
}
