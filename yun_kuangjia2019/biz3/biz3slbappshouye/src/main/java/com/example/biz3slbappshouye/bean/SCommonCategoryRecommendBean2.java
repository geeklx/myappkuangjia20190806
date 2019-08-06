package com.example.biz3slbappshouye.bean;

import java.io.Serializable;

public class SCommonCategoryRecommendBean2 implements Serializable {
    private static final long serialVersionUID = 1L;
    private String pId;
    private String itemId;
    private String sourceType;
    private SListCommBean3 cornerMap;
    private String bgImg;
    private String layoutTag;
    private String itemCountStr;
    private String title;
    private String descr;
    private String hios;

    public SCommonCategoryRecommendBean2() {
    }

    public String getpId() {
        return pId;
    }

    public void setpId(String pId) {
        this.pId = pId;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getSourceType() {
        return sourceType;
    }

    public void setSourceType(String sourceType) {
        this.sourceType = sourceType;
    }

    public String getBgImg() {
        return bgImg;
    }

    public void setBgImg(String bgImg) {
        this.bgImg = bgImg;
    }

    public String getLayoutTag() {
        return layoutTag;
    }

    public void setLayoutTag(String layoutTag) {
        this.layoutTag = layoutTag;
    }

    public SListCommBean3 getCornerMap() {
        return cornerMap;
    }

    public void setCornerMap(SListCommBean3 cornerMap) {
        this.cornerMap = cornerMap;
    }

    public String getItemCountStr() {
        return itemCountStr;
    }

    public void setItemCountStr(String itemCountStr) {
        this.itemCountStr = itemCountStr;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescr() {
        return descr;
    }

    public void setDescr(String descr) {
        this.descr = descr;
    }

    public String getHios() {
        return hios;
    }

    public void setHios(String hios) {
        this.hios = hios;
    }
}
