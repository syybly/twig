package com.rmit.twig.controller;

import android.app.Activity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.rmit.twig.R;
import com.rmit.twig.com.AsyncTask_EditPost;
import com.rmit.twig.model.Post;


public class ClickListener_EditPost implements View.OnClickListener {
    private String type;
    private Activity activity;
    private EditText postcontent;
    private boolean validpost;
    private EditText title;
    private Post post;

    public ClickListener_EditPost(Activity activity, Post post) {
        this.post = post;
        this.activity = activity;
    }

    @Override
    public void onClick(View v) {
        postcontent = activity.findViewById(R.id.editpostcontent);
        validpost = true;
        if (postcontent.getText() == null) {
            Toast less = Toast.makeText(activity, "Post content is empty", Toast.LENGTH_SHORT);
            less.show();
            validpost = false;
        }
        if (post.getCategories().size() < 1) {
            Toast less = Toast.makeText(activity, "Please choose at least one category", Toast.LENGTH_SHORT);
            less.show();
            validpost = false;
        }
        if (post.getType().equals("event")) {
            if (post.getDate() == null) {
                Toast less = Toast.makeText(activity, "Please set a time for the event", Toast.LENGTH_SHORT);
                less.show();
                validpost = false;
            }
            if (post.getLocation() == null) {
                Toast less = Toast.makeText(activity, "Please set a location for the event", Toast.LENGTH_SHORT);
                less.show();
                validpost = false;
            }
            title = activity.findViewById(R.id.eventtitle);
            post.setTitle(title.getText().toString());
            if (post.getTitle().length() == 0) {
                Toast less = Toast.makeText(activity, "Please set a title for the event", Toast.LENGTH_SHORT);
                less.show();
                validpost = false;
            }
        }
        if (validpost) {
            post.setContent(postcontent.getText().toString());
            AsyncTask_EditPost asyncTask_editPost = new AsyncTask_EditPost(activity);
            asyncTask_editPost.execute(post);
        }
    }
}