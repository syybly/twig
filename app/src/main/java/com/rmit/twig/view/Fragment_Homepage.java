package com.rmit.twig.view;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.rmit.twig.com.AsyncTask_GetUser;
import com.rmit.twig.controller.DataHolder;
import com.rmit.twig.model.Post;
import com.rmit.twig.R;

import java.util.ArrayList;

public class Fragment_Homepage extends Fragment {
    private ListView feedlist;
    private View view;
    private Context context;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_homepage, container, false);
        context=view.getContext();
        feedlist=view.findViewById(R.id.feedlist);
        Adapter_Feedlist adapter=new Adapter_Feedlist(context,DataHolder.posts);
        feedlist.setAdapter(adapter);
        AsyncTask_GetUser getUser=new AsyncTask_GetUser(view,adapter);
        getUser.execute();
        return  view;
    }

    @Override
    public void onResume() {
        super.onResume();
        Adapter_Feedlist adapter2=new Adapter_Feedlist(context,DataHolder.posts);
        feedlist.setAdapter(adapter2);
    }
}
