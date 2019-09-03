package com.rmit.twig.com;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;

import com.rmit.twig.controller.DataHolder;
import com.rmit.twig.model.Post;
import com.rmit.twig.model.User;
import com.rmit.twig.view.Activity_Homepage;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;

import javax.net.ssl.HttpsURLConnection;

public class GetPostListAsyncTask extends AsyncTask<String, String, String> {

    private Context context;


    public GetPostListAsyncTask(Context context) {
        this.context = context;
    }

    protected String doInBackground(String... params) {
        HttpsURLConnection connection = null;
        BufferedReader reader = null;
        try {
            URL url = new URL("https://twig-api-v2.herokuapp.com/posts");
            connection = (HttpsURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("Accept","application/json");
            connection.setRequestProperty("x-auth",DataHolder.users.get(DataHolder.currentuser).getToken());
            connection.connect();
            int status=connection.getResponseCode();

            if (status==400) {
                return null;
            }
            InputStream stream = connection.getInputStream();
            reader = new BufferedReader(new InputStreamReader(stream));

            StringBuffer buffer = new StringBuffer();
            String line = "";

            while ((line = reader.readLine()) != null) {
                buffer.append(line + "\n");
            }
            return buffer.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public User getuser(String id){
        HttpsURLConnection connection = null;
        BufferedReader reader = null;
        try {
            URL url = new URL("https://twig-api-v2.herokuapp.com/users/"+id);
            connection = (HttpsURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("Accept","application/json");
            connection.connect();
            int status=connection.getResponseCode();

            if (status==400) {
                return null;
            }
            InputStream stream = connection.getInputStream();
            reader = new BufferedReader(new InputStreamReader(stream));

            StringBuffer buffer = new StringBuffer();
            String line = "";

            while ((line = reader.readLine()) != null) {
                buffer.append(line + "\n");
            }
            String userresult= buffer.toString();
            JSONObject user=new JSONObject(userresult);
            JSONArray jsonArray=user.getJSONArray("interests");
            String userid=user.getString("_id");
            String name=user.getString("name");
            String email=user.getString("email");
            ArrayList<String> interests=new ArrayList<>();
            for (int i=0;i<jsonArray.length();i++) {
                interests.add(jsonArray.get(i).toString());
            }
            User newuser=new User(userid,email,name,interests);
            DataHolder.users.put(userid,newuser);
            return newuser;
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(String result) {
        HashSet<String> userids=new HashSet<>();
        try {
            JSONArray postarray=new JSONArray(result);
            for(int k=0;k<postarray.length();k++) {
                JSONObject post = postarray.getJSONObject(k);
                System.out.println(post);
                JSONArray imagearray = post.getJSONArray("images");
                String content = post.getString("content");
                String id = post.getString("_id");
                String author = post.getString("author");
                userids.add(author);
                Post feed = new Post(author,content);
                if(!post.getString("location").equals("null")){
                    feed.setLocation(post.getString("location"));
                }
                JSONArray categories = post.getJSONArray("categories");
                HashSet<String> cats=new HashSet<>();
                for(int i=0;i<categories.length();i++){
                    cats.add(categories.getString(i));
                }
                feed.setCategories(cats);
                feed.setPostID(id);
                if (imagearray.length() > 0) {
                    for(int i=0;i<imagearray.length();i++) {
                        JSONObject image = imagearray.getJSONObject(i);
                        String imageurl = image.getString("path");
                        feed.setImageurl(imageurl);
                    }
                }
                feed.setUser(DataHolder.userholder);
                DataHolder.posts.add(feed);


            }
            Intent intent = new Intent(context, Activity_Homepage.class);
            context.startActivity(intent);


//            String[] userexecute=new String[userids.size()];
//            userids.toArray(userexecute);
//            getUser.execute(userexecute);

        }catch (JSONException e) {

        }
    }
}
