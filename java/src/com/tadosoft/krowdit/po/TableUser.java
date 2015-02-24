package com.tadosoft.krowdit.po;

import java.util.Date;


/**
 * TableUser entity. @author MyEclipse Persistence Tools
 */

public class TableUser  implements java.io.Serializable {


    // Fields    

     private long uid;
     private String userName;
     private String pwd;
     private String email;
     private Date createTime;
     private long utypeId;


    // Constructors

    /** default constructor */
    public TableUser() {
    }

    
    /** full constructor */
    public TableUser(String userName, String pwd, String email, Date createTime, long utypeId) {
        this.userName = userName;
        this.pwd = pwd;
        this.email = email;
        this.createTime = createTime;
        this.utypeId = utypeId;
    }

   
    // Property accessors

    public long getUid() {
        return this.uid;
    }
    
    public void setUid(long uid) {
        this.uid = uid;
    }

    public String getUserName() {
        return this.userName;
    }
    
    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPwd() {
        return this.pwd;
    }
    
    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getEmail() {
        return this.email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }

    public Date getCreateTime() {
        return this.createTime;
    }
    
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public long getUtypeId() {
        return this.utypeId;
    }
    
    public void setUtypeId(long utypeId) {
        this.utypeId = utypeId;
    }
   








}