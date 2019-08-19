package com.rmit.twig.Controller;

import com.rmit.twig.Model.Admin;
import com.rmit.twig.Model.EventPost;
import com.rmit.twig.Model.GeneralPost;
import com.rmit.twig.Model.Mentor;
import com.rmit.twig.Model.OppotunityPost;
import com.rmit.twig.Model.Post;
import com.rmit.twig.Model.Student;
import com.rmit.twig.Model.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DataHolder {
   public static Map<String,User> users=new HashMap<>();

   public static User user;
   public DataHolder(){

   }

}
