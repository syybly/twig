package com.rmit.twig.view;

import android.app.ProgressDialog;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.rmit.twig.R;
import com.rmit.twig.com.AsyncTask_GetBookmarks;
import com.rmit.twig.controller.DataHolder;
import com.rmit.twig.model.Post;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.concurrent.ExecutionException;

public class Activity_Bookmarks extends AppCompatActivity {
    private RecyclerView feedlist;
    private Context context;
    private HashSet<Post> bookmarklist;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bookmarks);
        bookmarklist=new HashSet<>();
        context=this;
        feedlist=findViewById(R.id.bookmarklist);

//        feedlist.setAdapter(adapter);
//        feedlist.setLayoutManager(new LinearLayoutManager(context));
        AsyncTask_GetBookmarks asyncTask_getBookmarks=new AsyncTask_GetBookmarks(this);
        asyncTask_getBookmarks.execute(DataHolder.currentuser);
        try {
            String result=asyncTask_getBookmarks.get();
            if(result.equals("false")){

            }
            else{
                JSONObject resultjson=new JSONObject(result);
                JSONArray jsonArray=resultjson.getJSONArray("savedPosts");
                System.out.println(DataHolder.users.get(DataHolder.currentuser).getToken());
                for(int i=0;i<jsonArray.length();i++){
                    JSONObject feedjson=jsonArray.getJSONObject(i).getJSONObject("feed");
                    String feedid=feedjson.getString("_id");
                    for(Post p:DataHolder.posts){
                        if(feedid.equals(p.getPostID())){
                            System.out.println(p.getContent());
                            bookmarklist.add(p);
                        }
                    }
                }
            }
        } catch (ExecutionException | InterruptedException | JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        ArrayList<Post> bookmarkarray=new ArrayList<>();
        bookmarkarray.addAll(bookmarklist);
        Adapter_Bookmark adapter2=new Adapter_Bookmark(context,bookmarkarray,feedlist);
        feedlist.setAdapter(adapter2);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(context);
        feedlist.setLayoutManager(linearLayoutManager);
    }

    @Override
    public void onStop() {
        super.onStop();
        bookmarklist.clear();
    }
}
