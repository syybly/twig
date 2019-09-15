package com.rmit.twig.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class User {
    private String id;
    private String email;
    private String fullname;
    private String des;
    private String type;
    private String password;
    private ArrayList<String> preference;
    private String photourl;
    private String token;
    private Map<String,Bookmark> savedposts;
    private ArrayList<String> bookmarks;
//    private Map<String,Bookmark> savedevents;

    public User(String id,String email, String fullname, String type,ArrayList<String> preference) {
        this.id=id;
        this.email = email;
        this.fullname = fullname;
        this.type=type;
        this.preference=preference;
        this.photourl="https://microhealth.com/assets/images/illustrations/personal-user-illustration-@2x.png";
        this.savedposts =new HashMap<>();
        this.bookmarks=new ArrayList<>();
//        this.savedevents=new HashMap<>();
    }

    public User(String id,String email, String fullname, ArrayList<String> preference) {
        this.id=id;
        this.email = email;
        this.fullname = fullname;
        this.type="student";
        this.preference=preference;
        this.photourl="https://microhealth.com/assets/images/illustrations/personal-user-illustration-@2x.png";
        this.savedposts =new HashMap<>();
        this.bookmarks=new ArrayList<>();
//        this.savedevents=new HashMap<>();
    }

    public User(String email, String password, String fullname) {
        this.email = email;
        this.password=password;
        this.fullname = fullname;
        this.type="student";
        this.photourl="https://microhealth.com/assets/images/illustrations/personal-user-illustration-@2x.png";
        this.savedposts =new HashMap<>();
        this.bookmarks=new ArrayList<>();
//        this.savedevents=new HashMap<>();
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhotourl() {
        return photourl;
    }

    public void setPhotourl(String photourl) {
        this.photourl = photourl;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Map<String, Bookmark> getSavedposts() {
        return savedposts;
    }

    public void setSavedposts(Map<String, Bookmark> savedposts) {
        this.savedposts = savedposts;
    }

    public ArrayList<String> getBookmarks() {
        return bookmarks;
    }

    public void setBookmarks(ArrayList<String> bookmarks) {
        this.bookmarks = bookmarks;
    }
}
