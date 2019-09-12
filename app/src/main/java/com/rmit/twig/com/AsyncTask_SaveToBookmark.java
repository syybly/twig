package com.rmit.twig.com;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import com.rmit.twig.controller.DataHolder;

import org.apache.http.HttpResponse;
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

public class AsyncTask_SaveToBookmark extends AsyncTask<String,String,String> {
    private Context context;

    public AsyncTask_SaveToBookmark(Context context) {
        this.context = context;
    }

    @Override
    protected String doInBackground(String... strings) {
        try {
        String url="https://twig-api-v2.herokuapp.com/feeds/saved";
        RequestBody requestBody = new FormBody.Builder()
                .add("feedId",strings[0])
                .add("type",strings[1])
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
            Toast nomatch = Toast.makeText(context, "Save bookmark succeeded.", Toast.LENGTH_SHORT);
            nomatch.show();
        }
    }
}
