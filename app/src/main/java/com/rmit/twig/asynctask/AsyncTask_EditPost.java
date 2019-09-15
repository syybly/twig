package com.rmit.twig.asynctask;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.widget.Toast;

import com.rmit.twig.controller.DataHolder;
import com.rmit.twig.model.Post;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


public class AsyncTask_EditPost extends AsyncTask<Object, String, String> {
    private ProgressDialog pd;
    private Activity activity;
    private Post post;
    private URL url;

    public AsyncTask_EditPost(Activity activity) {
        this.activity = activity;
    }

    protected void onPreExecute() {
        super.onPreExecute();
        pd = new ProgressDialog(activity);
        pd.setMessage("Please wait");
        pd.setCancelable(false);
        pd.show();
    }

    @Override
    protected String doInBackground(Object... objects) {
        try {
            post = (Post) objects[0];

            MultipartBody.Builder mutipartbuilder = new MultipartBody.Builder()
                    .setType(MultipartBody.FORM)
                    .addFormDataPart("content", post.getContent());
            if (post.getLocation() != null) {
                mutipartbuilder.addFormDataPart("location", post.getLocation());
            }
            JSONArray cats = new JSONArray();
            for (String cat : post.getCategories()) {
                cats.put(cat);
            }
            mutipartbuilder.addFormDataPart("categories", cats.toString());

            for (int i = 0; i < post.getNewpostimages().size(); i++) {
                File f = post.getNewpostimages().get(i);
                mutipartbuilder.addFormDataPart("images", f.getName(), RequestBody.create(f, MediaType.parse("image/*png")));
            }

            post.getNewpostimages().clear();

            if (post.getType().equals("post")) {
                url = new URL("https://twig-api-v2.herokuapp.com/posts/" + post.getPostID());
            }
            if (post.getType().equals("event")) {
                url = new URL("https://twig-api-v2.herokuapp.com/events/" + post.getPostID());
                mutipartbuilder.addFormDataPart("time", post.getDate().toString());
                mutipartbuilder.addFormDataPart("title", post.getTitle());
            }

            RequestBody requestBody = mutipartbuilder.build();
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .header("Content-Type", "multipart/form-data")
                    .header("x-auth", DataHolder.users.get(DataHolder.currentuser).getToken())
                    .url(url)
                    .patch(requestBody)
                    .build();

            Response response = client.newCall(request).execute();
            int statusCode = response.code();
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
        if (pd.isShowing()) {
            pd.dismiss();
        }
        Toast nomatch = Toast.makeText(activity, "Something went wrong, please try again.", Toast.LENGTH_SHORT);
        if (result != null)
            try {
                JSONObject getpost = new JSONObject(result);
                String id = getpost.getString("_id");
                JSONArray imagearray = getpost.getJSONArray("images");

                if (imagearray.length() > 0) {
                    post.getImageurl().clear();
                    for (int i = 0; i < imagearray.length(); i++) {
                        JSONObject image = imagearray.getJSONObject(i);
                        String imageurl = image.getString("path");
                        post.getImageurl().add(imageurl);
                    }
                }

                long createtime = getpost.getLong("createdTime");
                post.setCreatetime(createtime);

                activity.finish();
            } catch (JSONException e) {
                nomatch.show();
            }
        else {
            nomatch.show();
        }
    }
}
