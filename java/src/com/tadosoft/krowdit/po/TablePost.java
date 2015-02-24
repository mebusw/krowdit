package com.tadosoft.krowdit.po;

import java.util.Date;


/**
 * TablePost entity. @author MyEclipse Persistence Tools
 */

public class TablePost  implements java.io.Serializable {


    // Fields    

     private long postId;
     private long creatorId;
     private Date createTime;
     private Integer positive;
     private Integer negative;
     private String content;
     private long pic;
     private long krowdId;
     private String callAnAction;


    // Constructors

    /** default constructor */
    public TablePost() {
    }

    
    /** full constructor */
    public TablePost(long creatorId, Date createTime, Integer positive, Integer negative, String content, long pic, long krowdId, String callAnAction) {
        this.creatorId = creatorId;
        this.createTime = createTime;
        this.positive = positive;
        this.negative = negative;
        this.content = content;
        this.pic = pic;
        this.krowdId = krowdId;
        this.callAnAction = callAnAction;
    }

   
    // Property accessors

    public long getPostId() {
        return this.postId;
    }
    
    public void setPostId(long postId) {
        this.postId = postId;
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

    public Integer getPositive() {
        return this.positive;
    }
    
    public void setPositive(Integer positive) {
        this.positive = positive;
    }

    public Integer getNegative() {
        return this.negative;
    }
    
    public void setNegative(Integer negative) {
        this.negative = negative;
    }

    public String getContent() {
        return this.content;
    }
    
    public void setContent(String content) {
        this.content = content;
    }

    public long getPic() {
        return this.pic;
    }
    
    public void setPic(long pic) {
        this.pic = pic;
    }

    public long getKrowdId() {
        return this.krowdId;
    }
    
    public void setKrowdId(long krowdId) {
        this.krowdId = krowdId;
    }

    public String getCallAnAction() {
        return this.callAnAction;
    }
    
    public void setCallAnAction(String callAnAction) {
        this.callAnAction = callAnAction;
    }
   








}