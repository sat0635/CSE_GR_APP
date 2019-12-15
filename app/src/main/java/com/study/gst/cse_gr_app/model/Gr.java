package com.study.gst.cse_gr_app.model;

public class Gr {

    private int id;
    private String CATEGORY;
    private  String  GRADE;

    public Gr(int id, String CATEGORY, String GRADE) {
        this.id = id;
        this.CATEGORY = CATEGORY;
        this.GRADE = GRADE;
    }

    public String getCategory(){return CATEGORY; }
    public String getGrade(){return GRADE;}
    public int getId(){return id;}
}
