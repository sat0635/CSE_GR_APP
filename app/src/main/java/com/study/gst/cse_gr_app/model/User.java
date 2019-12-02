package com.study.gst.cse_gr_app.model;

public class User {
    public static String userName;
    public String profileImageUrl;
    private int id;
    private String major;
    private String track;

    public User(int id, String major, String track) {
        this.id = id;
        this.major =major;
        this.track = track;
    }

    public int getId() {
        return id;
    }

    public String getMajor() {
        return major;
    }

    public String getTrack() {
        return track;
    }

}
