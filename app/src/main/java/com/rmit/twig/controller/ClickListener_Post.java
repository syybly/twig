package com.rmit.twig.controller;

import android.app.Activity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.rmit.twig.R;
import com.rmit.twig.com.AsyncTask_Post;

public class ClickListener_Post implements View.OnClickListener {
    private String type;
    private Activity activity;
    private EditText postcontent;
    private boolean validpost;
    private EditText title;

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
        if (DataHolder.newpost.getCategories().size() < 1) {
            Toast less = Toast.makeText(activity, "Please choose at least one category", Toast.LENGTH_SHORT);
            less.show();
            validpost=false;
        }
        if(DataHolder.newpost.getType().equals("event")){
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
            title=activity.findViewById(R.id.eventtitle);
            DataHolder.newpost.setTitle(title.getText().toString());
            if(DataHolder.newpost.getTitle().length()==0){
                Toast less = Toast.makeText(activity, "Please set a title for the event", Toast.LENGTH_SHORT);
                less.show();
                validpost=false;
            }
        }
        if(validpost) {
            DataHolder.newpost.setContent(postcontent.getText().toString());
            AsyncTask_Post asyncTask_post = new AsyncTask_Post(activity);
            asyncTask_post.execute(DataHolder.newpost);

        }
    }
}
