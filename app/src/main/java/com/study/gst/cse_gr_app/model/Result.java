package com.study.gst.cse_gr_app.model;

public class Result {
    private int id;
    private boolean resultValue;
    public Result(int id, boolean resultValue) {
        this.id=id;
        this.resultValue = resultValue;
    }
    public boolean getResultValue(){return resultValue; };
}
