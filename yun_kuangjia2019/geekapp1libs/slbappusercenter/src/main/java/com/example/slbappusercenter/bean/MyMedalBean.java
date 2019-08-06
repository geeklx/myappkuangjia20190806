package com.example.slbappusercenter.bean;

import androidx.annotation.DrawableRes;

import java.io.Serializable;

/**
 * https://github.com/CymChad/BaseRecyclerViewAdapterHelper
 */
public class MyMedalBean implements Serializable {
    private static final long serialVersionUID = 1L;
    private String id;
    private String img;
    @DrawableRes
    private int icon;
    @DrawableRes
    private int icon2;
    private boolean isRetweet;

    public MyMedalBean() {
    }

    public MyMedalBean(String id, String img, int icon, int icon2, boolean isRetweet) {
        this.id = id;
        this.img = img;
        this.icon = icon;
        this.icon2 = icon2;
        this.isRetweet = isRetweet;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public int getIcon2() {
        return icon2;
    }

    public void setIcon2(int icon2) {
        this.icon2 = icon2;
    }

    public boolean isRetweet() {
        return isRetweet;
    }

    public void setRetweet(boolean retweet) {
        isRetweet = retweet;
    }
}
