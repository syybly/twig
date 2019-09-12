package com.rmit.twig.com;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import com.rmit.twig.controller.DataHolder;
import com.rmit.twig.model.Bookmark;
import com.rmit.twig.model.Post;

import org.apache.http.HttpResponse;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;

import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class AsyncTask_SaveToBookmark extends AsyncTask<Void,String,String> {
    private Context context;
    private Post post;

    public AsyncTask_SaveToBookmark(Context context,Post post) {
        this.context = context;
        this.post=post;
    }

    @Override
    protected String doInBackground(Void... voids) {
        try {
        String url="https://twig-api-v2.herokuapp.com/feeds/saved";
        RequestBody requestBody = new FormBody.Builder()
                .add("feedId",post.getPostID())
                .add("type",post.getType())
                .build();
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .header("x-auth", DataHolder.users.get(DataHolder.currentuser).getToken())
                .url(url)
                .post(requestBody)
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
            String bookmarkid=null;
            try {
                JSONObject newu=new JSONObject(result);
                if(post.getType().equals("post")) {
                    JSONArray newsaved=newu.getJSONArray("savedPosts");
                    for(int i=0;i<newsaved.length();i++){
                        if(newsaved.getJSONObject(i).getString("feed").equals(post.getPostID())){
                            bookmarkid=newsaved.getJSONObject(i).getString("_id");
                        }
                    }
                    Bookmark newmark=new Bookmark(bookmarkid,post.getPostID(),"post");
                    DataHolder.users.get(DataHolder.currentuser).getSavedposts().put(post.getPostID(),newmark);
                }
                if(post.getType().equals("event")){
                    JSONArray newsaved=newu.getJSONArray("savedEvents");
                    for(int i=0;i<newsaved.length();i++){
                        if(newsaved.getJSONObject(i).getString("feed").equals(post.getPostID())){
                            bookmarkid=newsaved.getJSONObject(i).getString("_id");
                        }
                    }
                    Bookmark newmark=new Bookmark(bookmarkid,post.getPostID(),"event");
                    DataHolder.users.get(DataHolder.currentuser).getSavedposts().put(post.getPostID(),newmark);
                }
                post.setSaved(true);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            Toast nomatch = Toast.makeText(context, "Save bookmark succeeded.", Toast.LENGTH_SHORT);
            nomatch.show();
        }
    }
}
