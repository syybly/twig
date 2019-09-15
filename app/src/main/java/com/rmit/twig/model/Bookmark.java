package com.rmit.twig.model;

public class Bookmark {
    private String bookmarkid;
    private String feedid;
    private String type;

    public Bookmark(String bookmarkid, String feedid, String type) {
        this.bookmarkid = bookmarkid;
        this.feedid = feedid;
        this.type=type;
    }

    public String getBookmarkid() {
        return bookmarkid;
    }

    public void setBookmarkid(String bookmarkid) {
        this.bookmarkid = bookmarkid;
    }

    public String getFeedid() {
        return feedid;
    }

    public void setFeedid(String feedid) {
        this.feedid = feedid;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
