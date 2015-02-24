package com.tadosoft.krowdit.po;

import java.util.Date;


/**
 * TableComment entity. @author MyEclipse Persistence Tools
 */

public class TableComment  implements java.io.Serializable {


    // Fields    

     private long commentId;
     private long postId;
     private String content;
     private long creatorId;
     private Date createTime;


    // Constructors

    /** default constructor */
    public TableComment() {
    }

    
    /** full constructor */
    public TableComment(long postId, String content, long creatorId, Date createTime) {
        this.postId = postId;
        this.content = content;
        this.creatorId = creatorId;
        this.createTime = createTime;
    }

   
    // Property accessors

    public long getCommentId() {
        return this.commentId;
    }
    
    public void setCommentId(long commentId) {
        this.commentId = commentId;
    }

    public long getPostId() {
        return this.postId;
    }
    
    public void setPostId(long postId) {
        this.postId = postId;
    }

    public String getContent() {
        return this.content;
    }
    
    public void setContent(String content) {
        this.content = content;
    }

    public long getCreatorId() {
        return this.creatorId;
    }
    
    public void setCreatorId(long creatorId) {
        this.creatorId = creatorId;
    }

    public Date getCreateTime() {
        return this.createTime;
    }
    
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
   








}