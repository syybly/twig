package com.rmit.twig.com;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.rmit.twig.R;
import com.rmit.twig.controller.DataHolder;
import com.rmit.twig.model.Post;
import com.rmit.twig.view.Activity_CreateGenralPost;
import com.rmit.twig.view.Adapter_Feedlist;

import java.util.ArrayList;

public class AsyncTask_GetBookmarks extends AsyncTask<String, String, String> {
    private ArrayList<String> bookmarkid=new ArrayList<>();
    private Activity activity;
    private Adapter_Feedlist adapter_feedlist;

    public AsyncTask_GetBookmarks(Activity activity, Adapter_Feedlist adapter_feedlist) {
        this.activity = activity;
        this.adapter_feedlist = adapter_feedlist;
    }

    @Override
    protected String doInBackground(String... params) {
        return null;
    }

    @Override
    protected void onPostExecute(String result) {
        for(String id:bookmarkid){
            for(Post p: DataHolder.posts){
                if(id.equals(p.getPostID())){
                    DataHolder.bookmarks.add(p);
                    RecyclerView l=activity.findViewById(R.id.bookmarklist);
                    adapter_feedlist.notifyDataSetChanged();
                    l.setAdapter(adapter_feedlist);
                    l.setLayoutManager(new LinearLayoutManager(activity));
                }
            }
        }

    }
}
