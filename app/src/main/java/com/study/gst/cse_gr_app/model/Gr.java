package com.study.gst.cse_gr_app.model;

public class Gr {

    private int id;
    private String CONTENT;

    public Gr(int id, String CONTENT) {
        this.id = id;
        this.CONTENT = CONTENT;
    }

    public String getContent(){return CONTENT; }
    public int getId(){return id;}
}
