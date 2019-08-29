package com.rmit.twig.com;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.widget.ProgressBar;

import com.rmit.twig.controller.DataHolder;
import com.rmit.twig.model.Post;
import com.rmit.twig.view.Activity_CreateGenralPost;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class AsyncTask_Post extends AsyncTask <Object, String, String> {
    private ProgressBar progressBar;
    private ProgressDialog pd;
    private Activity activity;
    private Post post;

    public AsyncTask_Post(Activity activity) {
        this.activity = activity;
    }

//    @Override
//    protected void onPreExecute() {
//        super.onPreExecute();
//        // setting progress bar to zero
//        progressBar.setProgress(0);
//    }

    protected void onPreExecute() {
        super.onPreExecute();
        pd = new ProgressDialog(activity);
        pd.setMessage("Please wait");
        pd.setCancelable(false);
        pd.show();
    }

//    @Override
//    protected void onProgressUpdate(Integer... progress) {
//        // Making progress bar visible
//        super.onProgressUpdate();
//        progressBar.setVisibility(View.VISIBLE);
//
//        // updating progress bar value
//        progressBar.setProgress(progress[0]);
//
//        // updating percentage value
//        txtPercentage.setText(String.valueOf(progress[0]) + "%");
//    }

    @Override
    protected String doInBackground(Object... objects) {
        try {
            post=(Post)objects[0];
            MultipartBody.Builder mutipartbuilder=new MultipartBody.Builder()
                    .setType(MultipartBody.FORM)
                    .addFormDataPart("content",post.getContent());
            if(post.getLocation()!=null){
                mutipartbuilder.addFormDataPart("location",post.getLocation());
            }
            for(String cat:DataHolder.postcategories){
                mutipartbuilder.addFormDataPart("categories[]",cat);
            }
        if(Activity_CreateGenralPost.imagefiles.size()>0){
            for(int i=0;i<Activity_CreateGenralPost.imagefiles.size();i++){
                File f=Activity_CreateGenralPost.imagefiles.get(i);
//            for(File f:Activity_CreateGenralPost.imagefiles){
//                BitmapFactory.Options o = new BitmapFactory.Options();
////                o.inJustDecodeBounds = true;
////                o.inSampleSize = 6;
////                ByteArrayOutputStream bos = new ByteArrayOutputStream();
////                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bos);
////                byte[] data = bos.toByteArray();
//                String newdata=Base64.encodeToString(data,Base64.DEFAULT);
                mutipartbuilder.addFormDataPart("images", f.getName(),RequestBody.create(f,MediaType.parse("image/*png")));
            }
        }
            RequestBody requestBody=mutipartbuilder.build();
        OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .header("Content-Type", "multipart/form-data")
                    .header("x-auth",DataHolder.users.get(DataHolder.currentuser).getToken())
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
                JSONObject getpost=new JSONObject(result);
                String content=getpost.getString("content");
                String id=getpost.getString("_id");
                JSONArray categories=getpost.getJSONArray("categories");
                JSONArray imagearray = getpost.getJSONArray("images");
                String location=getpost.getString("location");
                if(location!="null"){
                    post.setLocation(location);
                }
                else{
                    post.setLocation(null);
                }
                if (imagearray.length() > 0) {
                    for(int i=0;i<imagearray.length();i++) {
                        JSONObject image = imagearray.getJSONObject(i);
                        String imageurl = image.getString("path");
                        post.setImageurl(imageurl);
                    }
                }
               DataHolder.posts.add(0,post);
                if (pd.isShowing()){
                    pd.dismiss();
                }
                activity.finish();
            } catch (JSONException e) {

            }
        else {
//            Toast nomatch = Toast.makeText(context, "Something went wrong, please try again.", Toast.LENGTH_SHORT);
//            nomatch.show();
        }
    }
}
