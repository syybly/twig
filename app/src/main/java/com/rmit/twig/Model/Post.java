package com.rmit.twig.Model;

import java.util.HashSet;

public class Post {
    private String postID;
    private User user;
    private String content;
    private String location;
    private String date;
    private HashSet<String> categories;

    public Post(String postID, User user, String content, String location) {
        this.postID = postID;
        this.user = user;
        this.content = content;
        this.location=location;
    }

    public Post(User user, String content) {
        this.user = user;
        this.content = content;
    }

    public Post(User user, String content, String location) {
        this.user = user;
        this.content = content;
        this.location=location;
    }

    public String getPostID() {
        return postID;
    }

    public void setPostID(String postID) {
        this.postID = postID;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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
}
