package com.rmit.twig.controller;

import com.rmit.twig.model.Post;
import com.rmit.twig.model.User;

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
   public static ArrayList<Post> bookmarks;


   public DataHolder(){
      if(posts==null)
      posts=new ArrayList<Post>();
      bookmarks=new ArrayList<>();
      users=new HashMap<>();
      userholder=new User("Loading","Loading","Loading");
   }

//   public static void initposts(){
//      posts.add(new GeneralPost(DataHolder.user,"First General Post","Melbourne"));
//      posts.add(new EventPost(DataHolder.user,"Second Event Post","Sydney"));
//      posts.add(new GeneralPost(DataHolder.user,"Third General Post","Melbourne"));
//      posts.add(new OppotunityPost(DataHolder.user,"Forth Opportunity Post","Sydney"));
//   }

}
