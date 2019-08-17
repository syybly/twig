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
import com.rmit.twig.Model.Post;
import com.rmit.twig.R;

public class Fragment_Homepage extends Fragment {
    private ListView feedlist;
    private View view;
    private Activity activity;
    private Context context;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_homepage, container, false);
        context=view.getContext();
        feedlist=view.findViewById(R.id.feedlist);
        feedlist.setAdapter(new FeedlistAdapter(context,DataHolder.posts));
        return  view;
    }
}
