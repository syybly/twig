package com.rmit.twig.Model;

public class Student extends User {
    public Student(String email, String password, String fullname) {
        super(email, password, fullname,"Student");
    }
}
