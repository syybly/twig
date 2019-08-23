package com.rmit.twig.Controller;

import android.app.Activity;
import android.view.View;
import android.widget.EditText;

import com.rmit.twig.Model.GeneralPost;
import com.rmit.twig.R;

public class ClickListener_Post implements View.OnClickListener {
    private String type;
    private Activity activity;
    private EditText postcontent;

    public ClickListener_Post(Activity activity, String type) {
        this.type = type;
        this.activity = activity;
    }

    @Override
    public void onClick(View v) {
        postcontent=activity.findViewById(R.id.createpostcontent);
        if(type.equals("GeneralPost")){
            GeneralPost gp=new GeneralPost(DataHolder.user,postcontent.getText().toString());
            DataHolder.posts.add(gp);
            activity.finish();
        }
    }
}
