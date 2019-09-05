package com.rmit.twig.com;

import android.os.AsyncTask;

import com.rmit.twig.controller.DataHolder;
import com.rmit.twig.model.Post;
import com.rmit.twig.view.Adapter_Feedlist;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class AsyncTask_DeletePost extends AsyncTask<String, String, String> {

    private Adapter_Feedlist adapter;

    public AsyncTask_DeletePost(Adapter_Feedlist adapter) {
        this.adapter = adapter;
    }

    @Override
    protected String doInBackground(String... params) {
        try {
            URL url = new URL("https://twig-api-v2.herokuapp.com" + params[0] + params[1]);

            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .header("Content-Type", "application/json")
                    .header("x-auth", DataHolder.users.get(DataHolder.currentuser).getToken())
                    .delete()
                    .url(url)
                    .build();
            Response response = client.newCall(request).execute();
            int statusCode = response.code();
            if (statusCode != 200) {
                return null;
            }
            String responseBody = response.body().string();
            JSONObject deleteStatus = new JSONObject(responseBody);

            boolean status = (boolean) deleteStatus.get("status");
            if (status) ;
            {
                return params[1];
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPostExecute(String postID) {
        if (postID == null) {
            return;
        }

        for (Post post : DataHolder.posts) {
            if (post.getPostID().equals(postID)) {
                DataHolder.posts.remove(post);
                adapter.notifyDataSetChanged();
                return;
            }
        }
    }
}