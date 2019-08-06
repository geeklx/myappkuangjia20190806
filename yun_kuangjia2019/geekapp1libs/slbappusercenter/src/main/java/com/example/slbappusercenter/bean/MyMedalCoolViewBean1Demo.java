package com.example.slbappusercenter.bean;

//import android.support.annotation.DrawableRes;

import androidx.annotation.DrawableRes;

import java.io.Serializable;

/**
 * https://github.com/CymChad/BaseRecyclerViewAdapterHelper
 */
public class MyMedalCoolViewBean1Demo implements Serializable {
    private static final long serialVersionUID = 1L;
    private String id;
    private String img;
    @DrawableRes
    private int icon;

    public MyMedalCoolViewBean1Demo() {
    }

    public MyMedalCoolViewBean1Demo(String id, String img, int icon) {
        this.id = id;
        this.img = img;
        this.icon = icon;
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
}
