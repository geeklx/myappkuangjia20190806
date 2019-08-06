package com.example.biz3slbappshouye.bean;

import java.io.Serializable;
import java.util.List;

//{
//        "id":"200",
//        "createTime":"2018-11-29 13:53",
//        "updateTime":"",
//        "delFlag":0,
//        "bookItemId":"21",
//        "bookId":"13",
//        "orders":1,
//        "dltName":"01 (1)",
//        "pic":"https://sairobo-edu-elephant.oss-cn-hangzhou.aliyuncs.com/picbook/13/21/01 (1).jpg",
//        "audio":"https://sairobo-edu-elephant.oss-cn-hangzhou.aliyuncs.com/picbook/13/21/01 (1).mp3",
//        "subtitle":"",
//        "subtitleTime":"",
//        "remark":""
//        },
public class SHuibenDetailBean2 implements Serializable {
    private static final long serialVersionUID = 1L;
    private String  id;
    private String  createTime;
    private String  updateTime;
    private String  delFlag;
    private String  bookItemId;
    private String  bookId;
    private String  orders;
    private String  dltName;
    private String  pic;
    private String  audio;
    private String  subtitle;
    private String  subtitleTime;
    private String  remark;

    public SHuibenDetailBean2() {
    }

    public SHuibenDetailBean2(String id, String createTime, String updateTime, String delFlag, String bookItemId, String bookId, String orders, String dltName, String pic, String audio, String subtitle, String subtitleTime, String remark) {
        this.id = id;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.delFlag = delFlag;
        this.bookItemId = bookItemId;
        this.bookId = bookId;
        this.orders = orders;
        this.dltName = dltName;
        this.pic = pic;
        this.audio = audio;
        this.subtitle = subtitle;
        this.subtitleTime = subtitleTime;
        this.remark = remark;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    public String getBookItemId() {
        return bookItemId;
    }

    public void setBookItemId(String bookItemId) {
        this.bookItemId = bookItemId;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public String getOrders() {
        return orders;
    }

    public void setOrders(String orders) {
        this.orders = orders;
    }

    public String getDltName() {
        return dltName;
    }

    public void setDltName(String dltName) {
        this.dltName = dltName;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getAudio() {
        return audio;
    }

    public void setAudio(String audio) {
        this.audio = audio;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public String getSubtitleTime() {
        return subtitleTime;
    }

    public void setSubtitleTime(String subtitleTime) {
        this.subtitleTime = subtitleTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
