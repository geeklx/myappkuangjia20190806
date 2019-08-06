package com.example.biz3slbappshouye.bean;

import java.io.Serializable;

//	"list": [
//            {
//            "bannerImg": "",
//            "collect": false,
//            "cornerMap": {},
//            "coverImg": "",
//            "descr": "",
//            "hios": "",
//            "iosRoute": "",
//            "itemCountString": "",
//            "itemId": 0,
//            "orders": 0,
//            "pId": 0,
//            "readRight": 0,
//            "sourceType": "",
//            "title": "",
//            "vertical": false
//            }
//            ],
public class SLB4CategoryListDetailBean1 implements Serializable {
    private static final long serialVersionUID = 1L;
    private String audioUrl;
    private boolean collect;
    private String coverImg;
    private String hios;
    private String itemId;
    private String orders;
    private String pId;
    private String readRight;
    private String readStatus;
    private String source;
    private String sourceType;
    private String title;
    private String thirdItemId;
    private String thirdPid;

    public SLB4CategoryListDetailBean1() {
    }

    public String getAudioUrl() {
        return audioUrl;
    }

    public void setAudioUrl(String audioUrl) {
        this.audioUrl = audioUrl;
    }

    public boolean isCollect() {
        return collect;
    }

    public void setCollect(boolean collect) {
        this.collect = collect;
    }

    public String getCoverImg() {
        return coverImg;
    }

    public void setCoverImg(String coverImg) {
        this.coverImg = coverImg;
    }

    public String getHios() {
        return hios;
    }

    public void setHios(String hios) {
        this.hios = hios;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getOrders() {
        return orders;
    }

    public void setOrders(String orders) {
        this.orders = orders;
    }

    public String getpId() {
        return pId;
    }

    public void setpId(String pId) {
        this.pId = pId;
    }

    public String getReadRight() {
        return readRight;
    }

    public void setReadRight(String readRight) {
        this.readRight = readRight;
    }

    public String getReadStatus() {
        return readStatus;
    }

    public void setReadStatus(String readStatus) {
        this.readStatus = readStatus;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getSourceType() {
        return sourceType;
    }

    public void setSourceType(String sourceType) {
        this.sourceType = sourceType;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getThirdItemId() {
        return thirdItemId;
    }

    public void setThirdItemId(String thirdItemId) {
        this.thirdItemId = thirdItemId;
    }

    public String getThirdPid() {
        return thirdPid;
    }

    public void setThirdPid(String thirdPid) {
        this.thirdPid = thirdPid;
    }
}
