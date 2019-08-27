package com.rmit.twig.model;

public class EventPost extends Post {

    public EventPost(String postID, User user, String content, String location) {
        super(postID, user, content, location);
    }

    public EventPost(User user, String content) {
        super(user, content);
    }

    public EventPost(User user, String content, String location) {
        super(user, content, location);
    }
}
