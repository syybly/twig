package com.rmit.twig.Model;

public class Post {
    private String postID;
    private User user;
    private String content;
    private String date;

    public Post(String postID, User user, String content, String date) {
        this.postID = postID;
        this.user = user;
        this.content = content;
        this.date = date;
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
}
