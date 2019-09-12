package com.rmit.twig.com;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import com.rmit.twig.controller.DataHolder;
import com.rmit.twig.model.Bookmark;
import com.rmit.twig.model.Post;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class AsyncTask_RemoveBookmark extends AsyncTask<Void,String,String> {
    private Context context;
    private Post post;
    private Bookmark bookmarks;

    public AsyncTask_RemoveBookmark(Context context,Post post) {
        this.context = context;
        this.post=post;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected String doInBackground(Void... voids) {

        if(post.getType().equals("post")) {
            bookmarks= DataHolder.users.get(DataHolder.currentuser).getSavedposts().get(post.getPostID());
        }
        if(post.getType().equals("event")) {
            bookmarks= DataHolder.users.get(DataHolder.currentuser).getSavedevents().get(post.getPostID());
        }
        if(bookmarks==null){
            return null;
        }
        try {
            String url1="https://twig-api-v2.herokuapp.com/feeds/saved/";
            String url=url1+bookmarks.getBookmarkid();
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .header("x-auth", DataHolder.users.get(DataHolder.currentuser).getToken())
                    .url(url)
                    .delete()
                    .build();
            Response response = client.newCall(request).execute();
            int statusCode=response.code();
            if (statusCode != 200) {
                return null;
            }
            return response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(String result) {
        if(result==null){
            Toast nomatch = Toast.makeText(context, "Save bookmark failed, please try again.", Toast.LENGTH_SHORT);
            nomatch.show();
        }
        else{
            if(bookmarks.getType().equals("post")) {
                DataHolder.users.get(DataHolder.currentuser).getSavedposts().remove(bookmarks.getFeedid());
            }
            if(bookmarks.getType().equals("event")) {
                DataHolder.users.get(DataHolder.currentuser).getSavedevents().remove(bookmarks.getFeedid());
            }
            post.setSaved(false);
            Toast nomatch = Toast.makeText(context, "Remove bookmark succeeded.", Toast.LENGTH_SHORT);
            nomatch.show();
        }
    }
}
