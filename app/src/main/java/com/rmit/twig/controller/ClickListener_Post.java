package com.rmit.twig.controller;

import android.app.Activity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.rmit.twig.model.EventPost;
import com.rmit.twig.model.GeneralPost;
import com.rmit.twig.model.OppotunityPost;
import com.rmit.twig.model.Post;
import com.rmit.twig.R;
import com.rmit.twig.view.Activity_CreateEvent;
import com.rmit.twig.view.Activity_CreateGenralPost;
import com.rmit.twig.view.Activity_CreateOppo;
import com.rmit.twig.com.AsyncTask_Post;

public class ClickListener_Post implements View.OnClickListener {
    private String type;
    private Activity activity;
    private EditText postcontent;
    private boolean validpost;

    public ClickListener_Post(Activity activity, String type) {
        this.type = type;
        this.activity = activity;
    }

    @Override
    public void onClick(View v) {
        postcontent=activity.findViewById(R.id.createpostcontent);
        validpost=true;
        if(postcontent.getText()==null){
            Toast less = Toast.makeText(activity, "Post content is empty", Toast.LENGTH_SHORT);
            less.show();
            validpost=false;
        }
        if (DataHolder.postcategories.size() < 1) {
            Toast less = Toast.makeText(activity, "Please choose at least one category", Toast.LENGTH_SHORT);
            less.show();
            validpost=false;
        }
        if(DataHolder.newpost.getType().equals("Event")){
            if(DataHolder.newpost.getDate()==null){
                Toast less = Toast.makeText(activity, "Please set a time for the event", Toast.LENGTH_SHORT);
                less.show();
                validpost=false;
            }
            if(DataHolder.newpost.getLocation()==null){
                Toast less = Toast.makeText(activity, "Please set a location for the event", Toast.LENGTH_SHORT);
                less.show();
                validpost=false;
            }
        }
        if(validpost) {
            DataHolder.newpost.setContent(postcontent.getText().toString());
            DataHolder.newpost.setCategories(DataHolder.postcategories);
            AsyncTask_Post asyncTask_post = new AsyncTask_Post(activity);
            asyncTask_post.execute(DataHolder.newpost);

        }
    }
}
