package com.tadosoft.krowdit.po;

import java.util.Date;


/**
 * TableBeFans entity. @author MyEclipse Persistence Tools
 */

public class TableBeFans  implements java.io.Serializable {


    // Fields    

     private long beFansId;
     private long uid;
     private long fansId;
     private Date beFansTime;


    // Constructors

    /** default constructor */
    public TableBeFans() {
    }

    
    /** full constructor */
    public TableBeFans(long uid, long fansId, Date beFansTime) {
        this.uid = uid;
        this.fansId = fansId;
        this.beFansTime = beFansTime;
    }

   
    // Property accessors

    public long getBeFansId() {
        return this.beFansId;
    }
    
    public void setBeFansId(long beFansId) {
        this.beFansId = beFansId;
    }

    public long getUid() {
        return this.uid;
    }
    
    public void setUid(long uid) {
        this.uid = uid;
    }

    public long getFansId() {
        return this.fansId;
    }
    
    public void setFansId(long fansId) {
        this.fansId = fansId;
    }

    public Date getBeFansTime() {
        return this.beFansTime;
    }
    
    public void setBeFansTime(Date beFansTime) {
        this.beFansTime = beFansTime;
    }
   








}