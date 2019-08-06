package com.example.slbappreadbook.domain;

import android.graphics.Bitmap;

import androidx.annotation.DrawableRes;

public class BaseBean2 {
    private int id;
    private @DrawableRes
    int drawable;
    private String url;
    private String url_mp3;
    private Bitmap bitmap;

    public BaseBean2() {
    }

    public BaseBean2(int id, String url, String url_mp3) {
        this.id = id;
        this.url = url;
        this.url_mp3 = url_mp3;
    }

    public BaseBean2(int id, int drawable, String url, String url_mp3, Bitmap bitmap) {
        this.id = id;
        this.drawable = drawable;
        this.url = url;
        this.url_mp3 = url_mp3;
        this.bitmap = bitmap;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDrawable() {
        return drawable;
    }

    public void setDrawable(int drawable) {
        this.drawable = drawable;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrl_mp3() {
        return url_mp3;
    }

    public void setUrl_mp3(String url_mp3) {
        this.url_mp3 = url_mp3;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }
}
