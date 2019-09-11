package com.rmit.twig.model;

public class Bookmark {
    private String bookmarkid;
    private String feedid;

    public Bookmark(String bookmarkid, String feedid) {
        this.bookmarkid = bookmarkid;
        this.feedid = feedid;
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
}
