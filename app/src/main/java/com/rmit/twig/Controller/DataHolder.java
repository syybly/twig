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
   public static Post[] posts=new Post[4];
   public DataHolder(){
       Student student=new Student("student","123","student");
       users.put(student.getEmail(),student);
       users.put("mentor",new Mentor("mentor","123","mentor"));
       users.put("admin",new Admin("admin","123","admin"));
       posts[0]=new GeneralPost("1",student,"First General Post","17/08/2019 12:00:00 pm");
       posts[1]=new EventPost("2",student,"Second Event Post","17/08/2019 12:00:00 pm");
       posts[2]=new GeneralPost("3",student,"Third General Post","17/08/2019 12:00:00 pm");
       posts[3]=new OppotunityPost("4",student,"Forth Opportunity Post","17/08/2019 12:00:00 pm");
   }

}
