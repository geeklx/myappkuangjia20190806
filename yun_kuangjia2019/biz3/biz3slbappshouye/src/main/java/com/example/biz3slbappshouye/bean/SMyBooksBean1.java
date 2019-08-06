package com.example.biz3slbappshouye.bean;

import java.io.Serializable;

public class SMyBooksBean1 implements Serializable {
    private static final long serialVersionUID = 1L;
    private String collect;
    private SListCommBean3 cornerMap;
    private String coverImg;
    private String hios;
    private String itemId;
    private String pId;
    private String readMode;
    private String sourceType;
    private String title;
    private boolean isRetweet;

    public SMyBooksBean1() {
    }

    public String getCollect() {
        return collect;
    }

    public void setCollect(String collect) {
        this.collect = collect;
    }

    public SListCommBean3 getCornerMap() {
        return cornerMap;
    }

    public void setCornerMap(SListCommBean3 cornerMap) {
        this.cornerMap = cornerMap;
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

    public String getpId() {
        return pId;
    }

    public void setpId(String pId) {
        this.pId = pId;
    }

    public String getReadMode() {
        return readMode;
    }

    public void setReadMode(String readMode) {
        this.readMode = readMode;
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

    public boolean isRetweet() {
        return isRetweet;
    }

    public void setRetweet(boolean retweet) {
        isRetweet = retweet;
    }
}
