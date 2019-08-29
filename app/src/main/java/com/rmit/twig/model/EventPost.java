package com.rmit.twig.model;

public class EventPost extends Post {

    public EventPost(String postID, String author, String content, String location) {
        super(postID, author, content, location);
    }

    public EventPost(String author, String content) {
        super(author, content);
    }

    public EventPost(String author, String content, String location) {
        super(author, content, location);
    }
}
