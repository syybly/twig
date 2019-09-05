package com.rmit.twig.model;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;

public class Post {
    private String postID;
    private String author;
    private String content;
    private String location;
    private Long date;
    private HashSet<String> categories;
    private ArrayList<String> imageurl;
    private User user;
    private String type;
    private ArrayList<File> newpostimages;

    public Post(String postID, String author, String content, String location) {
        this.postID = postID;
        this.author=author;
        this.content = content;
        this.location=location;
        this.imageurl=new ArrayList<>();
        this.newpostimages=new ArrayList<>();
    }

    public Post(String author, String content, String location) {
        this.author=author;
        this.content = content;
        this.location=location;
        this.imageurl=new ArrayList<>();
        this.newpostimages=new ArrayList<>();
    }

    public Post(String author, String type){
        this.author=author;
        this.type=type;
        this.imageurl=new ArrayList<>();
        this.newpostimages=new ArrayList<>();
    }

    public String getPostID() {
        return postID;
    }

    public void setPostID(String postID) {
        this.postID = postID;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getDate() {
        return date;
    }

    public void setDate(Long date) {
        this.date = date;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public HashSet<String> getCategories() {
        return categories;
    }

    public void setCategories(HashSet<String> categories) {
        this.categories = categories;
    }

    public ArrayList<String> getImageurl() {
        return imageurl;
    }

    public void setImageurl(ArrayList<String> imageurl) {
        this.imageurl = imageurl;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public ArrayList<File> getNewpostimages() {
        return newpostimages;
    }

    public void setNewpostimages(ArrayList<File> newpostimages) {
        this.newpostimages = newpostimages;
    }
}
