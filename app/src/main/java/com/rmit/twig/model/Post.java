package com.rmit.twig.model;

import java.util.HashSet;

public class Post {
    private String postID;
    private String author;
    private String content;
    private String location;
    private String date;
    private HashSet<String> categories;
    private String imageurl;
    private User user;

    public Post(String postID, String author, String content, String location) {
        this.postID = postID;
        this.author=author;
        this.content = content;
        this.location=location;
    }

    public Post(String author, String content) {
        this.author=author;
        this.content = content;
    }

    public Post(String author, String content, String location) {
        this.author=author;
        this.content = content;
        this.location=location;
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
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

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
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
}
