package com.rmit.twig.com;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.Toast;

import com.rmit.twig.Controller.DataHolder;
import com.rmit.twig.Model.Post;
import com.rmit.twig.Model.User;
import com.rmit.twig.View.Activity_Homepage;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;

import javax.net.ssl.HttpsURLConnection;

import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class AsyncTask_Post extends AsyncTask <Object, String, String> {
    @Override
    protected String doInBackground(Object... objects) {
        try {
        MultipartEntityBuilder builder = MultipartEntityBuilder.create();
        builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
        Post post=(Post)objects[0];
        builder.addPart("content",new StringBody(post.getContent(),ContentType.TEXT_PLAIN));
        for(String categories:post.getCategories()) {
            builder.addPart("categories[]",new StringBody(categories,ContentType.TEXT_PLAIN));
        }
//        builder.addPart("file", new FileBody((File) objects[2]));
//            builder.addPart("categories[]",new StringBody("category",ContentType.TEXT_PLAIN));
//        HttpPost request = new HttpPost("https://twig-api-v2.herokuapp.com/posts");
//        request.setHeader("Content-Type", "multipart/form-data");
//        request.setHeader("x-auth",DataHolder.user.getToken());
//        request.setEntity(entity);
//        HttpClient client = HttpClientBuilder.create().build();
            RequestBody requestBody = new MultipartBody.Builder()
                    .setType(MultipartBody.FORM)
                    .addFormDataPart("content",post.getContent())
                    .addFormDataPart("categories[]","Technology")
                    .build();
        OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .header("Content-Type", "multipart/form-data")
                    .header("x-auth",DataHolder.user.getToken())
                    .url("https://twig-api-v2.herokuapp.com/posts")
                    .post(requestBody)
                    .build();
//        HttpResponse response=client.execute(request);
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
        super.onPostExecute(result);
        if(result!=null)
            try {
                JSONObject post=new JSONObject(result);
                String content=post.getString("content");
                String id=post.getString("_id");
                JSONArray categories=post.getJSONArray("categories");
            } catch (JSONException e) {

            }
        else {
//            Toast nomatch = Toast.makeText(context, "Something went wrong, please try again.", Toast.LENGTH_SHORT);
//            nomatch.show();
        }
    }
}
