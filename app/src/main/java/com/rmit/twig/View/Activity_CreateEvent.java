package com.rmit.twig.View;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.rmit.twig.Controller.ClickListener_Post;
import com.rmit.twig.Controller.DataHolder;
import com.rmit.twig.R;
import com.squareup.picasso.Picasso;

import java.util.HashSet;

public class Activity_CreateEvent extends AppCompatActivity {
    private ImageView userphoto;
    private TextView name;
    private TextView post;
    private Activity activity;
    public RelativeLayout categorylayout;
    public static HashSet<String> categories;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_event);
        activity=this;
        categorylayout=findViewById(R.id.addcategory);
        post=findViewById(R.id.post);
        post.setOnClickListener(new ClickListener_Post(activity,"Event"));
        userphoto= findViewById(R.id.feed_userphoto);
        name=findViewById(R.id.feed_name);
        name.setText(DataHolder.user.getFullname());
        Picasso.with(this)
                .load(DataHolder.user.getPhotourl())
                .placeholder(R.drawable.nophoto)
                .error(R.drawable.nophoto)
                .into(userphoto);
    }
}
