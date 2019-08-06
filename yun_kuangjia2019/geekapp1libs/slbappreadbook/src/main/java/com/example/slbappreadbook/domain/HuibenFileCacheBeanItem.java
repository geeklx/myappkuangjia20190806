package com.example.slbappreadbook.domain;

import java.io.Serializable;

/**
 * 保存一本绘本的收藏，旋转，封面，id等属性
 */
public class HuibenFileCacheBeanItem implements Serializable {
    private static final long serialVersionUID = 1L;
    private String id;
    private String name;
    private String urlImg;
    private String urlMp3;
    private boolean vertical;
    private boolean collect;
    private String source_type;

    public HuibenFileCacheBeanItem() {
    }

    public HuibenFileCacheBeanItem(String id, String name, String urlImg, String urlMp3, boolean vertical, boolean collect, String source_type) {
        this.id = id;
        this.name = name;
        this.urlImg = urlImg;
        this.urlMp3 = urlMp3;
        this.vertical = vertical;
        this.collect = collect;
        this.source_type = source_type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrlImg() {
        return urlImg;
    }

    public void setUrlImg(String urlImg) {
        this.urlImg = urlImg;
    }

    public String getUrlMp3() {
        return urlMp3;
    }

    public void setUrlMp3(String urlMp3) {
        this.urlMp3 = urlMp3;
    }

    public boolean isVertical() {
        return vertical;
    }

    public void setVertical(boolean vertical) {
        this.vertical = vertical;
    }

    public boolean isCollect() {
        return collect;
    }

    public void setCollect(boolean collect) {
        this.collect = collect;
    }

    public String getSource_type() {
        return source_type;
    }

    public void setSource_type(String source_type) {
        this.source_type = source_type;
    }
}
