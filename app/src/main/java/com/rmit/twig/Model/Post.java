package com.rmit.twig.Model;

public class Post {
    private String postID;
    private String userID;
    private String content;
    private String date;

    public Post(String postID, String userID, String content, String date) {
        this.postID = postID;
        this.userID = userID;
        this.content = content;
        this.date = date;
    }

    public String getPostID() {
        return postID;
    }

    public void setPostID(String postID) {
        this.postID = postID;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
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
