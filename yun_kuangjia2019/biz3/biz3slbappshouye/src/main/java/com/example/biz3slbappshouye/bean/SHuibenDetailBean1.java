package com.example.biz3slbappshouye.bean;

import java.io.Serializable;

//"bookItem":{
//        "id":"21",
//        "createTime":"2018-11-29 13:53",
//        "updateTime":"2018-11-29 17:12",
//        "delFlag":0,
//        "bookId":"13",
//        "bookCode":"",
//        "orders":1,
//        "itemName":"年的故事",
//        "coverImgA":"https://sairobo-edu-elephant.oss-cn-hangzhou.aliyuncs.com/picbook/13/21/coverA.jpg",
//        "coverImgB":"https://sairobo-edu-elephant.oss-cn-hangzhou.aliyuncs.com/picbook/13/21/coverB.jpg",
//        "readmode":"across",
//        "vertical":false,
//        "bkgmusic":"",
//        "remark":"",
//        "status":1,
//        "uploadStatus":1
//        },
public class SHuibenDetailBean1 implements Serializable {
    private static final long serialVersionUID = 1L;
    private String id;
    private String createTime;
    private String updateTime;
    private String delFlag;
    private String bookId;
    private String bookCode;
    private String orders;
    private String itemName;
    private String itemPages;
    private String coverImgA;
    private String coverImgB;
    private String readRight;
    private String readmode;
    private boolean vertical;
    private boolean collect;
    private String bkgmusic;
    private String remark;
    private String showType;
    private String sourceType;
    private String status;
    private String uploadStatus;

    public SHuibenDetailBean1() {
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

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public String getBookCode() {
        return bookCode;
    }

    public void setBookCode(String bookCode) {
        this.bookCode = bookCode;
    }

    public String getOrders() {
        return orders;
    }

    public void setOrders(String orders) {
        this.orders = orders;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemPages() {
        return itemPages;
    }

    public void setItemPages(String itemPages) {
        this.itemPages = itemPages;
    }

    public String getCoverImgA() {
        return coverImgA;
    }

    public void setCoverImgA(String coverImgA) {
        this.coverImgA = coverImgA;
    }

    public String getCoverImgB() {
        return coverImgB;
    }

    public void setCoverImgB(String coverImgB) {
        this.coverImgB = coverImgB;
    }

    public String getReadRight() {
        return readRight;
    }

    public void setReadRight(String readRight) {
        this.readRight = readRight;
    }

    public String getReadmode() {
        return readmode;
    }

    public void setReadmode(String readmode) {
        this.readmode = readmode;
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

    public String getBkgmusic() {
        return bkgmusic;
    }

    public void setBkgmusic(String bkgmusic) {
        this.bkgmusic = bkgmusic;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUploadStatus() {
        return uploadStatus;
    }

    public void setUploadStatus(String uploadStatus) {
        this.uploadStatus = uploadStatus;
    }

}
