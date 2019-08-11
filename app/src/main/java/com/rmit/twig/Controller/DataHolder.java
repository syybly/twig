package com.rmit.twig.Controller;

import com.rmit.twig.Model.Admin;
import com.rmit.twig.Model.Mentor;
import com.rmit.twig.Model.Student;
import com.rmit.twig.Model.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DataHolder {
   public static Map<String,User> users=new HashMap<>();
   public DataHolder(){
       users.put("student",new Student("student","123","student"));
       users.put("mentor",new Mentor("mentor","123","mentor"));
       users.put("admin",new Admin("admin","123","admin"));
   }
}
