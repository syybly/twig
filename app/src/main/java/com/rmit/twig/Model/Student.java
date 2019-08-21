package com.rmit.twig.Model;

import java.util.ArrayList;

public class Student extends User {

    public Student(String id, String email, String fullname, ArrayList<String> preference) {
        super(id, email, fullname, preference);
    }
}
