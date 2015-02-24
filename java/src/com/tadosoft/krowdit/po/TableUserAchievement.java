package com.tadosoft.krowdit.po;

import java.util.Date;


/**
 * TableUserAchievement entity. @author MyEclipse Persistence Tools
 */

public class TableUserAchievement  implements java.io.Serializable {


    // Fields    

     private long uaid;
     private long achievementId;
     private long uid;
     private Date createTime;


    // Constructors

    /** default constructor */
    public TableUserAchievement() {
    }

    
    /** full constructor */
    public TableUserAchievement(long achievementId, long uid, Date createTime) {
        this.achievementId = achievementId;
        this.uid = uid;
        this.createTime = createTime;
    }

   
    // Property accessors

    public long getUaid() {
        return this.uaid;
    }
    
    public void setUaid(long uaid) {
        this.uaid = uaid;
    }

    public long getAchievementId() {
        return this.achievementId;
    }
    
    public void setAchievementId(long achievementId) {
        this.achievementId = achievementId;
    }

    public long getUid() {
        return this.uid;
    }
    
    public void setUid(long uid) {
        this.uid = uid;
    }

    public Date getCreateTime() {
        return this.createTime;
    }
    
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
   








}