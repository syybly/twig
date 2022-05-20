package com.rmit.twig.model;

import java.util.ArrayList;

public class Mentor extends User {

    public Mentor(String id, String email,String fullname, ArrayList<String> preference) {
        super(id, email, fullname, preference);
    }
}
