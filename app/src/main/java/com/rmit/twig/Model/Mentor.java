package com.rmit.twig.Model;

public class Mentor extends User {
    public Mentor(String email, String password, String fullname) {
        super(email, password, fullname,"Mentor");
    }
}
