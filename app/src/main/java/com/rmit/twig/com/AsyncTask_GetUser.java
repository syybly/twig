package com.rmit.twig.com;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ListView;

import com.rmit.twig.R;
import com.rmit.twig.controller.DataHolder;
import com.rmit.twig.model.Post;
import com.rmit.twig.model.User;
import com.rmit.twig.view.Activity_Homepage;
import com.rmit.twig.view.Adapter_Feedlist;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.ref.WeakReference;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;

import javax.net.ssl.HttpsURLConnection;

public class AsyncTask_GetUser extends AsyncTask<Void, String, String> {
    private View view;
    private int size;
    private HashSet<String> userids;
    private Adapter_Feedlist adapter_feedlist;

    public AsyncTask_GetUser(View view,Adapter_Feedlist adapter_feedlist) {
        this.view=view;
        userids=new HashSet<>();
        this.adapter_feedlist=adapter_feedlist;
    }

    @Override
    protected void onPreExecute() {
        for(Post p:DataHolder.posts){
            userids.add(p.getAuthor());
        }
    }

    @Override
    protected String doInBackground(Void... params) {
        size=params.length;
        for(String uid:userids){
            HttpsURLConnection connection = null;
            BufferedReader reader = null;
            try {
                URL url = new URL("https://twig-api-v2.herokuapp.com/users/" + uid);
                connection = (HttpsURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                connection.setRequestProperty("Content-Type", "application/json");
                connection.setRequestProperty("Accept", "application/json");
                connection.connect();
                int status = connection.getResponseCode();

                if (status == 400) {
                    return null;
                }

                InputStream stream = connection.getInputStream();
                reader = new BufferedReader(new InputStreamReader(stream));

                StringBuffer buffer = new StringBuffer();
                String line = "";

                while ((line = reader.readLine()) != null) {
                    buffer.append(line + "\n");
                }
                String result=buffer.toString();
                JSONObject user=new JSONObject(result);
                JSONArray jsonArray=user.getJSONArray("interests");
                String id=user.getString("_id");
                String name=user.getString("name");
                String email=user.getString("email");
                ArrayList<String> interests=new ArrayList<>();
                for (int k=0;k<jsonArray.length();k++) {
                    interests.add(jsonArray.get(k).toString());
                }
                User newuser=new User(id,email,name,interests);
                if(!DataHolder.users.containsKey(id)){
                    DataHolder.users.put(id,newuser);
                }

            } catch (IOException | JSONException e) {
                e.printStackTrace();
            }
        }
            return null;
    }

    @Override
    protected void onPostExecute(String result) {
        for(Post p:DataHolder.posts){
            for(String key:DataHolder.users.keySet()) {
                if (p.getAuthor().equals(key))
                    p.setUser(DataHolder.users.get(key));
            }
        }
            RecyclerView l=view.findViewById(R.id.feedlist);
            adapter_feedlist.notifyDataSetChanged();
            l.setAdapter(adapter_feedlist);
            l.setLayoutManager(new LinearLayoutManager(view.getContext()));
    }
}
