package com.model;

public class XieChengModel {
    private String userId;
    private String userType;
    private String score;
    private String commentInfo;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getCommentInfo() {
        return commentInfo;
    }

    public void setCommentInfo(String commentInfo) {
        this.commentInfo = commentInfo;
    }

    @Override
    public String toString() {
        return "XieChengModel{" +
                "userId='" + userId + '\'' +
                ", userType='" + userType + '\'' +
                ", score='" + score + '\'' +
                ", commentInfo='" + commentInfo + '\'' +
                '}';
    }
}
