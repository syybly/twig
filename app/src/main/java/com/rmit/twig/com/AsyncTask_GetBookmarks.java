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

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class AsyncTask_GetBookmarks extends AsyncTask<String, String, String> {
    private ArrayList<String> bookmarkid=new ArrayList<>();
    private Activity activity;

    public AsyncTask_GetBookmarks(Activity activity) {
        this.activity = activity;
    }

    @Override
    protected String doInBackground(String... params) {
        try {
            String url = "https://twig-api-v2.herokuapp.com/feeds/saved";
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .header("x-auth", DataHolder.users.get(DataHolder.currentuser).getToken())
                    .url(url)
                    .get()
                    .build();
            Response response = client.newCall(request).execute();
            int statusCode = response.code();
            if (statusCode != 200) {
                return "false";
            }
            return response.body().string();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return "false";
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
//        for(String id:bookmarkid){
//            for(Post p: DataHolder.posts){
//                if(id.equals(p.getPostID())){
//                    DataHolder.bookmarks.add(p);
//                    RecyclerView l=activity.findViewById(R.id.bookmarklist);
//                    adapter_feedlist.notifyDataSetChanged();
//                    l.setAdapter(adapter_feedlist);
//                    l.setLayoutManager(new LinearLayoutManager(activity));
//                }
//            }
//        }

    }
}
