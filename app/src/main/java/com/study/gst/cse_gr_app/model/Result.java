package com.study.gst.cse_gr_app.model;

public class Result {
    private int id;
    private int resultValue;
    public Result(int id, int resultValue) {
        this.id=id;
        this.resultValue = resultValue;
    }
    public int getResultValue(){return resultValue; };
}
