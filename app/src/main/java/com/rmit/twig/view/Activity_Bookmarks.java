package com.rmit.twig.view;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.rmit.twig.R;
import com.rmit.twig.com.AsyncTask_GetBookmarks;
import com.rmit.twig.controller.DataHolder;

import java.util.ArrayList;

public class Activity_Bookmarks extends AppCompatActivity {
    private RecyclerView feedlist;
    private Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bookmarks);
        context=this;
        feedlist=findViewById(R.id.bookmarklist);
        Adapter_Feedlist adapter=new Adapter_Feedlist(context, DataHolder.bookmarks,feedlist);
        feedlist.setAdapter(adapter);
        feedlist.setLayoutManager(new LinearLayoutManager(context));
        AsyncTask_GetBookmarks asyncTask_getBookmarks=new AsyncTask_GetBookmarks(this,adapter);
        asyncTask_getBookmarks.execute(DataHolder.currentuser);
    }

    @Override
    public void onResume() {
        super.onResume();
        Adapter_Feedlist adapter2=new Adapter_Feedlist(context,DataHolder.bookmarks,feedlist);
        feedlist.setAdapter(adapter2);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(context);
        feedlist.setLayoutManager(linearLayoutManager);
    }
}
