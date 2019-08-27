package com.rmit.twig.model;

public class OppotunityPost extends Post {

    public OppotunityPost(String postID, User user, String content, String location) {
        super(postID, user, content, location);
    }

    public OppotunityPost(User user, String content) {
        super(user, content);
    }

    public OppotunityPost(User user, String content, String location) {
        super(user, content, location);
    }
}
