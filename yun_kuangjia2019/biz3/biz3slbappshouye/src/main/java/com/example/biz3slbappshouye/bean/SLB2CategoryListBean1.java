package com.example.biz3slbappshouye.bean;

import java.io.Serializable;

public class SLB2CategoryListBean1 implements Serializable {
    private static final long serialVersionUID = 1L;
    private String bannerImg;
    private SListCommBean3 cornerMap;
    private String coverImg;
    private String hios;
    private String itemCount;
    private String itemId;
    private String pId;
    private String sourceType;
    private String title;
    private String descr;
    private String viewCountStr;
    private boolean free;
    private String priceStr;
    private String itemCountStr;
    private boolean isRetweet;

    public SLB2CategoryListBean1() {
    }

    public String getBannerImg() {
        return bannerImg;
    }

    public void setBannerImg(String bannerImg) {
        this.bannerImg = bannerImg;
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

    public String getItemCount() {
        return itemCount;
    }

    public void setItemCount(String itemCount) {
        this.itemCount = itemCount;
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

    public String getDescr() {
        return descr;
    }

    public void setDescr(String descr) {
        this.descr = descr;
    }

    public String getViewCountStr() {
        return viewCountStr;
    }

    public void setViewCountStr(String viewCountStr) {
        this.viewCountStr = viewCountStr;
    }

    public boolean isFree() {
        return free;
    }

    public void setFree(boolean free) {
        this.free = free;
    }

    public String getPriceStr() {
        return priceStr;
    }

    public void setPriceStr(String priceStr) {
        this.priceStr = priceStr;
    }

    public String getItemCountStr() {
        return itemCountStr;
    }

    public void setItemCountStr(String itemCountStr) {
        this.itemCountStr = itemCountStr;
    }

    public boolean isRetweet() {
        return isRetweet;
    }

    public void setRetweet(boolean retweet) {
        isRetweet = retweet;
    }
}
