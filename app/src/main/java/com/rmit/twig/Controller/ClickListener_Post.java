package com.rmit.twig.Controller;

import android.app.Activity;
import android.view.View;
import android.widget.EditText;

import com.rmit.twig.Model.EventPost;
import com.rmit.twig.Model.GeneralPost;
import com.rmit.twig.Model.OppotunityPost;
import com.rmit.twig.Model.Post;
import com.rmit.twig.R;
import com.rmit.twig.View.Activity_CreateEvent;
import com.rmit.twig.View.Activity_CreateGenralPost;
import com.rmit.twig.View.Activity_CreateOppo;

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
        Post gp=null;
        if(type.equals("GeneralPost")){
            if(Activity_CreateGenralPost.location.getText().toString().equals("Locating...")||Activity_CreateGenralPost.location.getText().toString().equals("add location")) {
                 gp= new GeneralPost(DataHolder.user, postcontent.getText().toString());
            }
            else{
                gp=new GeneralPost(DataHolder.user, postcontent.getText().toString(),Activity_CreateGenralPost.location.getText().toString());
            }
        }
        if(type.equals("Opportunity")){
            if(Activity_CreateOppo.location.getText().toString().equals("Locating...")||Activity_CreateOppo.location.getText().toString().equals("add location")) {
                gp= new OppotunityPost(DataHolder.user, postcontent.getText().toString());
            }
            else{
                gp=new OppotunityPost(DataHolder.user, postcontent.getText().toString(),Activity_CreateOppo.location.getText().toString());
            }
        }
        if(type.equals("Event")){
            if(Activity_CreateEvent.location.getText().toString().equals("Locating...")||Activity_CreateEvent.location.getText().toString().equals("add location")) {
                gp= new EventPost(DataHolder.user, postcontent.getText().toString());
            }
            else{
                gp=new EventPost(DataHolder.user, postcontent.getText().toString(),Activity_CreateEvent.location.getText().toString());
            }
        }
        DataHolder.posts.add(gp);
        activity.finish();
    }
}
