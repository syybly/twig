package com.rmit.twig.view;

import android.app.ProgressDialog;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.rmit.twig.R;
import com.rmit.twig.com.AsyncTask_GetBookmarks;
import com.rmit.twig.controller.DataHolder;
import com.rmit.twig.model.Post;

import java.util.ArrayList;
import java.util.HashSet;

public class Activity_Bookmarks extends AppCompatActivity {
    private RecyclerView feedlist;
    private Context context;
    private HashSet<Post> bookmarklist;
    private Adapter_Bookmark adapter_bookmark;
    private ArrayList<Post> bookmarkarray;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bookmarks);
        bookmarklist=new HashSet<>();
        bookmarkarray=new ArrayList<>();
        context=this;
        feedlist=findViewById(R.id.bookmarklist);
        adapter_bookmark=new Adapter_Bookmark(context,bookmarkarray);
        feedlist.setAdapter(adapter_bookmark);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(context);
        feedlist.setLayoutManager(linearLayoutManager);
        AsyncTask_GetBookmarks asyncTask_getBookmarks=new AsyncTask_GetBookmarks(this,adapter_bookmark,bookmarkarray);
        asyncTask_getBookmarks.execute(DataHolder.currentuser);
    }

    @Override
    public void onResume() {
        super.onResume();
        adapter_bookmark.notifyDataSetChanged();
    }

    @Override
    public void onStop() {
        super.onStop();
        bookmarklist.clear();
    }
}
