package com.study.gst.cse_gr_app.model;

public class Subject {
    private int id;
    private String GPSX;
    private String GPSY;
    private String LARG_CATE;
    private String MID_CATE;
    private String SMALL_CATE;
    private String NAME;
    private String IMAGENAME;
    private String IMAGE;
    private String DISTANCE;
    public Subject(int id, String gpsx, String gpsy, String larg_cate, String mid_cate, String small_cate, String name, String imagename, String image, String distnace) {

        this.id = id;
        this.GPSX = gpsx;
        this.GPSY = gpsy;
        this.LARG_CATE = larg_cate;
        this.MID_CATE = mid_cate;
        this.SMALL_CATE = small_cate;
        this.NAME = name;
        this.IMAGENAME = imagename;
        this.IMAGE = image;
        this.DISTANCE=distnace;
    }

    public int getID(){
        return id;
    }
    public String getGPSX(){
        return GPSX;
    }
    public String getGPSY(){
        return GPSY;
    }
    public String getLARG_CATE(){
        return LARG_CATE;
    }
    public String getMID_CATE(){
        return MID_CATE;
    }
    public String getSMALL_CATE(){
        return SMALL_CATE;
    }
    public String getNAME(){
        return NAME;
    }
    public String getIMAGENAME(){
        return IMAGENAME;
    }
    public String getIMAGE(){
        return IMAGE;
    }
    public String getDISTANCE() { return DISTANCE;}
}

