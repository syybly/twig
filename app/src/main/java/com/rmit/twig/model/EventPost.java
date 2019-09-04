package com.rmit.twig.model;

public class EventPost extends Post {
    private long datetime;

    public EventPost(String postID, String author, String content, String location) {
        super(postID, author, content, location);
    }

    public EventPost(String author, String content) {
        super(author, content);
    }

    public EventPost(String author, String content, String location) {
        super(author, content, location);
    }

    public long getDatetime() {
        return datetime;
    }

    public void setDatetime(long datetime) {
        this.datetime = datetime;
    }
}
