package com.rmit.twig.controller;

import android.util.JsonReader;

import com.rmit.twig.model.Post;
import com.rmit.twig.model.User;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class DataHolder {
   public static Map<String,User> users;

//   public static User user;
   public static User newuser;
   public static String currentuser;
   public static ArrayList<Post> posts;
//   public static ArrayList<String> postimagefiles;
   public static Post newpost;
   public static User userholder;
//   public static ArrayList<Post> bookmarks;


   public DataHolder(){
      if(posts==null)
      posts=new ArrayList<Post>();
//      bookmarks=new ArrayList<>();
      users=new HashMap<>();
      userholder=new User("Loading","Loading","Loading");
   }





}
