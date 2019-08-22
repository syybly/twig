package com.rmit.twig.Model;

public class GeneralPost extends Post {

    public GeneralPost(String postID, User user, String content, String location) {
        super(postID, user, content, location);
    }

    public GeneralPost(User user, String content) {
        super(user, content);
    }

    public GeneralPost(User user, String content, String location) {
        super(user, content, location);
    }
}
