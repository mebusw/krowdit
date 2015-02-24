package com.tadosoft.krowdit.po;

import java.util.Date;


/**
 * TableDeal entity. @author MyEclipse Persistence Tools
 */

public class TableDeal  implements java.io.Serializable {


    // Fields    

     private long dealId;
     private String dealName;
     private String content;
     private long shopId;
     private Date startTime;
     private Date stopTime;


    // Constructors

    /** default constructor */
    public TableDeal() {
    }

    
    /** full constructor */
    public TableDeal(String dealName, String content, long shopId, Date startTime, Date stopTime) {
        this.dealName = dealName;
        this.content = content;
        this.shopId = shopId;
        this.startTime = startTime;
        this.stopTime = stopTime;
    }

   
    // Property accessors

    public long getDealId() {
        return this.dealId;
    }
    
    public void setDealId(long dealId) {
        this.dealId = dealId;
    }

    public String getDealName() {
        return this.dealName;
    }
    
    public void setDealName(String dealName) {
        this.dealName = dealName;
    }

    public String getContent() {
        return this.content;
    }
    
    public void setContent(String content) {
        this.content = content;
    }

    public long getShopId() {
        return this.shopId;
    }
    
    public void setShopId(long shopId) {
        this.shopId = shopId;
    }

    public Date getStartTime() {
        return this.startTime;
    }
    
    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getStopTime() {
        return this.stopTime;
    }
    
    public void setStopTime(Date stopTime) {
        this.stopTime = stopTime;
    }
   








}