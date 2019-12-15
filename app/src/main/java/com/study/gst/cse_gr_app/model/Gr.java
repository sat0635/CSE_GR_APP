package com.study.gst.cse_gr_app.model;

public class Gr {

    private int id;
    private String CATEGORY;
    private int GRADE;

    public Gr(int id, String CATEGORY, int GRADE) {
        this.id = id;
        this.CATEGORY = CATEGORY;
        this.GRADE = GRADE;
    }

    public String getCategory(){return CATEGORY; }
    public int getGrade(){return GRADE;}
    public int getId(){return id;}
}
