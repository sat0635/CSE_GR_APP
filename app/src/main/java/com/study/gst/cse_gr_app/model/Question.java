package com.study.gst.cse_gr_app.model;

import android.graphics.drawable.Drawable;

public class Question {
    private int id;
    private Drawable icon;
    private String title;
    private String desc;
    private String answer;
    public Question(){
    }
    public Question(int id,String title, String desc, String answer){
        this.id =id;
        this.title=title;
        this.desc=desc;
        this.answer=answer;
    }
    public void setIcon(Drawable icon) {
        this.icon = icon;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public void setDesc(String desc) {
        this.desc = desc;
    }
    public void setAnswer(String answer) { this.answer=answer;}

    public Drawable getIcon() {
        return icon;
    }
    public String getAnswer() { return answer;}
    public String getTitle() {
        return title;
    }

    public String getDesc() {
        return desc;
    }
}
