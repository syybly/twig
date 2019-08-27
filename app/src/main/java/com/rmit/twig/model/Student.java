package com.rmit.twig.model;

import java.util.ArrayList;

public class Student extends User {

    public Student(String id, String email, String fullname, ArrayList<String> preference) {
        super(id, email, fullname, preference);
    }
}
