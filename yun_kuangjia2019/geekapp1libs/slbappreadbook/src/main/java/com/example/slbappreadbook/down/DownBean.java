package com.example.slbappreadbook.down;

/**
 * https://github.com/CymChad/BaseRecyclerViewAdapterHelper
 */
public class DownBean {
    private boolean isRetweet;
    private String id;
    private String text;
    private String userName;
    private String userAvatar;
    private String createdAt;

    public DownBean() {
    }

    public DownBean(boolean isRetweet, String id, String text, String userName, String userAvatar, String createdAt) {
        this.isRetweet = isRetweet;
        this.id = id;
        this.text = text;
        this.userName = userName;
        this.userAvatar = userAvatar;
        this.createdAt = createdAt;
    }

    public boolean isRetweet() {
        return isRetweet;
    }

    public void setRetweet(boolean retweet) {
        isRetweet = retweet;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserAvatar() {
        return userAvatar;
    }

    public void setUserAvatar(String userAvatar) {
        this.userAvatar = userAvatar;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "Status{" +
                "isRetweet=" + isRetweet +
                ", id='" + id + '\'' +
                ", text='" + text + '\'' +
                ", userName='" + userName + '\'' +
                ", userAvatar='" + userAvatar + '\'' +
                ", createdAt='" + createdAt + '\'' +
                '}';
    }
}
