package com.tadosoft.krowdit.po;

import java.util.Date;


/**
 * TableAchievement entity. @author MyEclipse Persistence Tools
 */

public class TableAchievement  implements java.io.Serializable {


    // Fields    

     private long achievementId;
     private String achievementName;
     private String achievementDetail;
     private long creatorId;
     private Date createTime;


    // Constructors

    /** default constructor */
    public TableAchievement() {
    }

    
    /** full constructor */
    public TableAchievement(String achievementName, String achievementDetail, long creatorId, Date createTime) {
        this.achievementName = achievementName;
        this.achievementDetail = achievementDetail;
        this.creatorId = creatorId;
        this.createTime = createTime;
    }

   
    // Property accessors

    public long getAchievementId() {
        return this.achievementId;
    }
    
    public void setAchievementId(long achievementId) {
        this.achievementId = achievementId;
    }

    public String getAchievementName() {
        return this.achievementName;
    }
    
    public void setAchievementName(String achievementName) {
        this.achievementName = achievementName;
    }

    public String getAchievementDetail() {
        return this.achievementDetail;
    }
    
    public void setAchievementDetail(String achievementDetail) {
        this.achievementDetail = achievementDetail;
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