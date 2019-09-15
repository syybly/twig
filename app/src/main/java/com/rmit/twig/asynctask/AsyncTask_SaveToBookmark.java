package com.rmit.twig.asynctask;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import com.rmit.twig.controller.DataHolder;
import com.rmit.twig.model.Post;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.FormBody;
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
            post.setSaved(false);
            Toast nomatch = Toast.makeText(context, "Save bookmark failed, please try again.", Toast.LENGTH_SHORT);
            nomatch.show();
        }
        else{
            DataHolder.users.get(DataHolder.currentuser).getBookmarks().add(post.getPostID());
            Toast nomatch = Toast.makeText(context, "Save bookmark succeeded.", Toast.LENGTH_SHORT);
            nomatch.show();
        }
    }
}
