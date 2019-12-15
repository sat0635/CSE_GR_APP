package com.study.gst.cse_gr_app.model;

public class Subject {
    private int id;
    private String CATEGORY;
    private String CONTENT;
    public Subject(int id, String category, String content) {

        this.id = id;
        this.CATEGORY=category;
        this.CONTENT=content;
    }

    public int getID(){
        return id;
    }
    public String getCategory(){return CATEGORY; }
    public String getContent(){return CONTENT;}
}

