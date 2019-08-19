package com.rmit.twig.View;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.rmit.twig.Controller.DataHolder;
import com.rmit.twig.Model.EventPost;
import com.rmit.twig.Model.GeneralPost;
import com.rmit.twig.Model.OppotunityPost;
import com.rmit.twig.Model.Post;
import com.rmit.twig.R;

public class Fragment_Homepage extends Fragment {
    private ListView feedlist;
    private View view;
    private Activity activity;
    private Context context;
    public static Post[] posts=new Post[4];


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        posts[0]=new GeneralPost("1",DataHolder.user,"First General Post","17/08/2019 12:00:00 pm");
        posts[1]=new EventPost("2",DataHolder.user,"Second Event Post","17/08/2019 12:00:00 pm");
        posts[2]=new GeneralPost("3",DataHolder.user,"Third General Post","17/08/2019 12:00:00 pm");
        posts[3]=new OppotunityPost("4",DataHolder.user,"Forth Opportunity Post","17/08/2019 12:00:00 pm");
        view=inflater.inflate(R.layout.fragment_homepage, container, false);
        context=view.getContext();
        feedlist=view.findViewById(R.id.feedlist);
        feedlist.setAdapter(new FeedlistAdapter(context,posts));
        return  view;
    }
}
