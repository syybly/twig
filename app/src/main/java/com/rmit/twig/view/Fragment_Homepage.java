package com.rmit.twig.view;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rmit.twig.asynctask.AsyncTask_GetUser;
import com.rmit.twig.controller.DataHolder;
import com.rmit.twig.R;

public class Fragment_Homepage extends Fragment {
    private RecyclerView feedlist;
    private View view;
    private Context context;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_homepage, container, false);
        context=view.getContext();
        feedlist=view.findViewById(R.id.feedlist);
        Adapter_Feedlist adapter=new Adapter_Feedlist(context,DataHolder.posts,feedlist);
        feedlist.setAdapter(adapter);
        feedlist.setLayoutManager(new LinearLayoutManager(context));
        AsyncTask_GetUser getUser=new AsyncTask_GetUser(view,adapter);
        getUser.execute();
        return  view;
    }

    @Override
    public void onResume() {
        super.onResume();
        Adapter_Feedlist adapter2=new Adapter_Feedlist(context,DataHolder.posts,feedlist);
        feedlist.setAdapter(adapter2);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(context);
        feedlist.setLayoutManager(linearLayoutManager);
    }
}
