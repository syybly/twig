package com.rmit.twig.Model;

import java.util.ArrayList;

public class Admin extends User {

    public Admin(String id, String email,  String fullname, ArrayList<String> preference) {
        super(id, email, fullname, preference);
    }
}
