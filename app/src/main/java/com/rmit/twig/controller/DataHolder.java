package com.rmit.twig.controller;

import com.rmit.twig.model.Post;
import com.rmit.twig.model.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class DataHolder {
   public static Map<String,User> users=new HashMap<>();

   public static User user;
   public static ArrayList<Post> posts;
   public static ArrayList<String> postimagefiles;
   public static HashSet<String> postcategories;
   public static Post newpost;
   public DataHolder(){
      if(posts==null)
      posts=new ArrayList<Post>();
      postimagefiles=new ArrayList<>();
      postcategories=new HashSet<>();
   }

//   public static void initposts(){
//      posts.add(new GeneralPost(DataHolder.user,"First General Post","Melbourne"));
//      posts.add(new EventPost(DataHolder.user,"Second Event Post","Sydney"));
//      posts.add(new GeneralPost(DataHolder.user,"Third General Post","Melbourne"));
//      posts.add(new OppotunityPost(DataHolder.user,"Forth Opportunity Post","Sydney"));
//   }

}
