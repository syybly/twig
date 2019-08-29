package com.rmit.twig.model;

public class GeneralPost extends Post {

    public GeneralPost(String postID, String author, String content, String location) {
        super(postID, author, content, location);
    }

    public GeneralPost(String author, String content) {
        super(author, content);
    }

    public GeneralPost(String author, String content, String location) {
        super(author, content, location);
    }
}
