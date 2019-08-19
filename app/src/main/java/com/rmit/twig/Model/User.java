package com.rmit.twig.Model;

import java.util.ArrayList;

public class User {
    private String id;
    private String email;
    private String fullname;
    private String des;
    private String type;
    private ArrayList<String> preference;

    public User(String id,String email, String fullname, String type,ArrayList<String> preference) {
        this.id=id;
        this.email = email;
        this.fullname = fullname;
        this.type=type;
        this.preference=preference;
    }

    public User(String id,String email, String fullname, ArrayList<String> preference) {
        this.id=id;
        this.email = email;
        this.fullname = fullname;
        this.type="student";
        this.preference=preference;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ArrayList<String> getPreference() {
        return preference;
    }

    public void setPreference(ArrayList<String> preference) {
        this.preference = preference;
    }
}
