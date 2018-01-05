package com.gzbigone.messagepatform.entity;


import java.util.Date;

public class Comment {

    private Integer commentId;
    private Integer messageId;
    private Integer userId;
    private Date creationDate;
    private String commentl;
    private String characterl;

    public Integer getCommentId() {
        return commentId;
    }

    public void setCommentId(Integer commentId) {
        this.commentId = commentId;
    }

    public Integer getMessageId() {
        return messageId;
    }

    public void setMessageId(Integer messageId) {
        this.messageId = messageId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public String getCommentl() {
        return commentl;
    }

    public void setCommentl(String commentl) {
        this.commentl = commentl;
    }

    public String getCharacterl() {
        return characterl;
    }

    public void setCharacterl(String characterl) {
        this.characterl = characterl;
    }
}