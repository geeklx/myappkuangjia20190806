package com.example.slbappindex.vip;

/**
 * https://github.com/CymChad/BaseRecyclerViewAdapterHelper
 */
public class VipBean {
    private String id;
    private String content1;
    private String content2;
    private String content3;
    private String content4;
    private String content5;
    private String content6;
    private String content7;
    private String content8;
    private String content9;
    private boolean isRetweet;

    public VipBean() {
    }

    public VipBean(String id, String content1, String content2, String content3, String content4, String content5, String content6, String content7, String content8, String content9, boolean isRetweet) {
        this.id = id;
        this.content1 = content1;
        this.content2 = content2;
        this.content3 = content3;
        this.content4 = content4;
        this.content5 = content5;
        this.content6 = content6;
        this.content7 = content7;
        this.content8 = content8;
        this.content9 = content9;
        this.isRetweet = isRetweet;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContent1() {
        return content1;
    }

    public void setContent1(String content1) {
        this.content1 = content1;
    }

    public String getContent2() {
        return content2;
    }

    public void setContent2(String content2) {
        this.content2 = content2;
    }

    public String getContent3() {
        return content3;
    }

    public void setContent3(String content3) {
        this.content3 = content3;
    }

    public String getContent4() {
        return content4;
    }

    public void setContent4(String content4) {
        this.content4 = content4;
    }

    public String getContent5() {
        return content5;
    }

    public void setContent5(String content5) {
        this.content5 = content5;
    }

    public String getContent6() {
        return content6;
    }

    public void setContent6(String content6) {
        this.content6 = content6;
    }

    public String getContent7() {
        return content7;
    }

    public void setContent7(String content7) {
        this.content7 = content7;
    }

    public String getContent8() {
        return content8;
    }

    public void setContent8(String content8) {
        this.content8 = content8;
    }

    public String getContent9() {
        return content9;
    }

    public void setContent9(String content9) {
        this.content9 = content9;
    }

    public boolean isRetweet() {
        return isRetweet;
    }

    public void setRetweet(boolean retweet) {
        isRetweet = retweet;
    }
}
