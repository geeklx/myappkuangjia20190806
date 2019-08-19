package com.example.slbappreadbook.pop;

/**
 * https://github.com/CymChad/BaseRecyclerViewAdapterHelper
 */
public class PopRenwuPayBean {
    private String id;
    private String img;
    private String content;

    public PopRenwuPayBean() {
    }

    public PopRenwuPayBean(String id, String img, String content) {
        this.id = id;
        this.img = img;
        this.content = content;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
