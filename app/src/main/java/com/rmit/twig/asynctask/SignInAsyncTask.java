package com.rmit.twig.asynctask;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import com.rmit.twig.controller.DataHolder;
import com.rmit.twig.model.Post;
import com.rmit.twig.model.User;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;

public class SignInAsyncTask extends AsyncTask<String, String, String> {
    private Context context;
    public static ProgressDialog pd;
    private Map<String, List<String>> headers;

    public SignInAsyncTask(Context context) {
        this.context = context;
    }

    protected void onPreExecute() {
        super.onPreExecute();
        pd = new ProgressDialog(context);
        pd.setMessage("Please wait");
        pd.setCancelable(false);
        pd.show();
    }

    protected String doInBackground(String... params) {
        HttpsURLConnection connection = null;
        BufferedReader reader = null;

        try {
            JSONObject signindata = new JSONObject();
            signindata.put("email", params[0]);
            signindata.put("password", params[1]);
            URL url = new URL("https://twig-api-v2.herokuapp.com/users/signin");
            connection = (HttpsURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("Accept","application/json");
            connection.setDoOutput(true);
            connection.connect();

            DataOutputStream wr = new DataOutputStream(connection.getOutputStream());
            wr.writeBytes(signindata.toString());

            int status=connection.getResponseCode();
            headers = connection.getHeaderFields();

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

        } catch (IOException | JSONException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
        if(result!=null)
        try {
            JSONObject user=new JSONObject(result);
            JSONArray jsonArray=user.getJSONArray("interests");
            String id=user.getString("_id");
            String name=user.getString("name");
            String email=user.getString("email");
            JSONArray savedposts=user.getJSONArray("savedPosts");
            JSONArray savedevents=user.getJSONArray("savedEvents");
            ArrayList<String> interests=new ArrayList<>();
            for (int i=0;i<jsonArray.length();i++) {
                interests.add(jsonArray.get(i).toString());
            }
            String token=headers.get("x-auth").get(0);
            User newuser=new User(id,email,name,interests);
            for(int q=0;q<savedposts.length();q++){
                String feedid=savedposts.getJSONObject(q).getString("_id");
//                Bookmark b=new Bookmark(feedid,"post");
//                newuser.getSavedposts().put(feedid,b);
//                Post post=jsontopost(savedposts.getJSONObject(q),"post");
//                post.setSaved(true);
//                DataHolder.posts.add(post);
                newuser.getBookmarks().add(feedid);
            }
            for(int q=0;q<savedevents.length();q++){
                String feedid=savedposts.getJSONObject(q).getString("_id");
//                Bookmark b2=new Bookmark(feedid,"event");
//                newuser.getSavedposts().put(feedid,b2);
//                Post post=jsontopost(savedevents.getJSONObject(q),"event");
//                post.setSaved(true);
//                DataHolder.posts.add(post);
                newuser.getBookmarks().add(feedid);
            }
            newuser.setToken(token);
            DataHolder.users.put(id,newuser);
            DataHolder.currentuser=id;
            GetPostListAsyncTask getPostListAsyncTask=new GetPostListAsyncTask(context);
            getPostListAsyncTask.execute();
            SignInAsyncTask.pd.dismiss();


        } catch (JSONException e) {

        }

        else {
            if (pd.isShowing()){
            pd.dismiss();
        }
            Toast nomatch = Toast.makeText(context, "Invalid Credentials!", Toast.LENGTH_SHORT);
            nomatch.show();
        }

    }

    public Post jsontopost(JSONObject post,String type) {
        try {
            JSONArray imagearray = post.getJSONArray("images");
            String content = post.getString("content");
            String id = post.getString("_id");
            long createtime = post.getLong("createdTime");
            JSONObject author = post.getJSONObject("author");
            String authorid = author.getString("_id");
            Post feed = new Post(authorid, type);
            feed.setCreatetime(createtime);
            if (type.equals("event")) {
                long eventdate = post.getLong("time");
                feed.setDate(eventdate);
                feed.setTitle(post.getString("title"));
            }
            feed.setContent(content);
            if (!post.getString("location").equals("null")) {
                feed.setLocation(post.getString("location"));
            }
            JSONArray categories = post.getJSONArray("categories");
            HashSet<String> cats = new HashSet<>();
            for (int i = 0; i < categories.length(); i++) {
                cats.add(categories.getString(i));
            }
            feed.setCategories(cats);
            feed.setPostID(id);
            if (imagearray.length() > 0) {
                for (int i = 0; i < imagearray.length(); i++) {
                    JSONObject image = imagearray.getJSONObject(i);
                    String imageurl = image.getString("path");
                    feed.getImageurl().add(imageurl);
                }
            }
            feed.setUser(DataHolder.userholder);
            return feed;
        }
        catch (JSONException e){
            e.printStackTrace();
        }
        return null;
    }
}
