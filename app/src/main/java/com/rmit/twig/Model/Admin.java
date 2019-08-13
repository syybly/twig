package com.rmit.twig.Model;

public class Admin extends User {
    public Admin(String email, String password, String fullname) {
        super(email, password, fullname,"Admin");
    }
}
