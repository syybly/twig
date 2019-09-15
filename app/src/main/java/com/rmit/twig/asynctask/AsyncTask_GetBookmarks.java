package com.rmit.twig.asynctask;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.widget.Toast;

import com.rmit.twig.controller.DataHolder;
import com.rmit.twig.model.Post;
import com.rmit.twig.view.Adapter_Bookmark;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class AsyncTask_GetBookmarks extends AsyncTask<String, String, String> {
    private ArrayList<String> bookmarkid=new ArrayList<>();
    private Activity activity;
    private Adapter_Bookmark adapter_bookmark;
    private ArrayList<Post> bookmarkarray;
    public static ProgressDialog pd;

    public AsyncTask_GetBookmarks(Activity activity, Adapter_Bookmark adapter_bookmark, ArrayList<Post> bookmarkarray) {
        this.activity = activity;
        this.adapter_bookmark=adapter_bookmark;
        this.bookmarkarray=bookmarkarray;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        pd = new ProgressDialog(activity);
        pd.setMessage("Please wait");
        pd.setCancelable(false);
        pd.show();
    }

    @Override
    protected String doInBackground(String... params) {
        try {
            String url = "https://twig-api-v2.herokuapp.com/feeds/saved-merge";
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
        if (pd.isShowing()){
            pd.dismiss();
        }
        if(result.equals("false")){
            Toast nomatch = Toast.makeText(activity, "Something went wrong, please try again.", Toast.LENGTH_SHORT);
            nomatch.show();
        }
        else {
            try {
                JSONArray jsonArray = new JSONArray(result);
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject feedjson = jsonArray.getJSONObject(i);
                    String feedid = feedjson.getString("_id");
                    for (Post p : DataHolder.posts) {
                        if (feedid.equals(p.getPostID()) && !bookmarkarray.contains(p)) {
                            bookmarkarray.add(p);
                            adapter_bookmark.notifyDataSetChanged();
                        }
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}
