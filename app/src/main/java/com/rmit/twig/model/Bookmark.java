package com.rmit.twig.model;

public class Bookmark {
    private String feedid;
    private String type;

    public Bookmark(String feedid, String type) {
        this.feedid = feedid;
        this.type=type;
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
