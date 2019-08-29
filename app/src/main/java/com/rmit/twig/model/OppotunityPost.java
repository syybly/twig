package com.rmit.twig.model;

public class OppotunityPost extends Post {

    public OppotunityPost(String postID, String author, String content, String location) {
        super(postID, author, content, location);
    }

    public OppotunityPost(String author, String content) {
        super(author, content);
    }

    public OppotunityPost(String author, String content, String location) {
        super(author, content, location);
    }
}
