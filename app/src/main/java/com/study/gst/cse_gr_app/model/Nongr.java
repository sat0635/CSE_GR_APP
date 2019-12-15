package com.study.gst.cse_gr_app.model;

public class Nongr {
    private int id;
    private String CATEGORY;
    private String CONTENT;

    public Nongr(int id, String CATEGORY, String CONTENT) {
        this.id = id;
        this.CATEGORY = CATEGORY;
        this.CONTENT = CONTENT;
    }

    public String getContent(){return CONTENT; }
    public String getCategory(){return CATEGORY;}
    public int getId(){return id;}
}
