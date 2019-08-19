package com.example.slbappreadbook.domain;

import java.io.Serializable;

/**
 * 保存一本绘本的图片和声音列表
 */
public class HuibenFileCacheBean implements Serializable {

    private String id;
    private String url;
    private String url_mp3;

    public HuibenFileCacheBean() {
    }

    public HuibenFileCacheBean(String id, String url, String url_mp3) {
        this.id = id;
        this.url = url;
        this.url_mp3 = url_mp3;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
}
