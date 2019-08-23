package com.rmit.twig.View;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.rmit.twig.Controller.ClickListener_Post;
import com.rmit.twig.R;

public class Activity_CreateGenralPost extends AppCompatActivity {
    private TextView post;
    private Activity activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_genral_post);
        activity=this;
        post=findViewById(R.id.post);
        post.setOnClickListener(new ClickListener_Post(activity,"GeneralPost"));
    }
}
