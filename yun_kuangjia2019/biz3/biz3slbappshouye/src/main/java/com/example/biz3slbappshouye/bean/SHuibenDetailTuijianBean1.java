package com.example.biz3slbappshouye.bean;

import java.io.Serializable;

public class SHuibenDetailTuijianBean1 implements Serializable {
    private static final long serialVersionUID = 1L;
    private String audioUrl;
    private String bannerImg;
    private String bookId;
    private String bookItemId;
    private String bookName;
    private String bookNameHtml;
    private boolean collect;
    private String cornerType;
    private String coverImg;
    private String hios;
    private String itemCount;
    private String orders;
    private String readCount;
    private String readmode;
    private String showType;
    private String sourceType;
    private boolean vertical;

    public SHuibenDetailTuijianBean1() {
    }

    public SHuibenDetailTuijianBean1(String audioUrl, String bannerImg, String bookId, String bookItemId, String bookName, String bookNameHtml, boolean collect, String cornerType, String coverImg, String hios, String itemCount, String orders, String readCount, String readmode, String showType, String sourceType, boolean vertical) {
        this.audioUrl = audioUrl;
        this.bannerImg = bannerImg;
        this.bookId = bookId;
        this.bookItemId = bookItemId;
        this.bookName = bookName;
        this.bookNameHtml = bookNameHtml;
        this.collect = collect;
        this.cornerType = cornerType;
        this.coverImg = coverImg;
        this.hios = hios;
        this.itemCount = itemCount;
        this.orders = orders;
        this.readCount = readCount;
        this.readmode = readmode;
        this.showType = showType;
        this.sourceType = sourceType;
        this.vertical = vertical;
    }

    public String getAudioUrl() {
        return audioUrl;
    }

    public void setAudioUrl(String audioUrl) {
        this.audioUrl = audioUrl;
    }

    public String getBannerImg() {
        return bannerImg;
    }

    public void setBannerImg(String bannerImg) {
        this.bannerImg = bannerImg;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public String getBookItemId() {
        return bookItemId;
    }

    public void setBookItemId(String bookItemId) {
        this.bookItemId = bookItemId;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getBookNameHtml() {
        return bookNameHtml;
    }

    public void setBookNameHtml(String bookNameHtml) {
        this.bookNameHtml = bookNameHtml;
    }

    public boolean isCollect() {
        return collect;
    }

    public void setCollect(boolean collect) {
        this.collect = collect;
    }

    public String getCornerType() {
        return cornerType;
    }

    public void setCornerType(String cornerType) {
        this.cornerType = cornerType;
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

    public String getOrders() {
        return orders;
    }

    public void setOrders(String orders) {
        this.orders = orders;
    }

    public String getReadCount() {
        return readCount;
    }

    public void setReadCount(String readCount) {
        this.readCount = readCount;
    }

    public String getReadmode() {
        return readmode;
    }

    public void setReadmode(String readmode) {
        this.readmode = readmode;
    }

    public String getShowType() {
        return showType;
    }

    public void setShowType(String showType) {
        this.showType = showType;
    }

    public String getSourceType() {
        return sourceType;
    }

    public void setSourceType(String sourceType) {
        this.sourceType = sourceType;
    }

    public boolean isVertical() {
        return vertical;
    }

    public void setVertical(boolean vertical) {
        this.vertical = vertical;
    }
}
