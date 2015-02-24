package com.tadosoft.krowdit.po;

import java.util.Date;


/**
 * TableAdministrator entity. @author MyEclipse Persistence Tools
 */

public class TableAdministrator  implements java.io.Serializable {


    // Fields    

     private long administratorId;
     private String userName;
     private String pwd;
     private String email;
     private long roleId;
     private Date createTime;
     private Date lastLogin;


    // Constructors

    /** default constructor */
    public TableAdministrator() {
    }

    
    /** full constructor */
    public TableAdministrator(String userName, String pwd, String email, long roleId, Date createTime, Date lastLogin) {
        this.userName = userName;
        this.pwd = pwd;
        this.email = email;
        this.roleId = roleId;
        this.createTime = createTime;
        this.lastLogin = lastLogin;
    }

   
    // Property accessors

    public long getAdministratorId() {
        return this.administratorId;
    }
    
    public void setAdministratorId(long administratorId) {
        this.administratorId = administratorId;
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

    public long getRoleId() {
        return this.roleId;
    }
    
    public void setRoleId(long roleId) {
        this.roleId = roleId;
    }

    public Date getCreateTime() {
        return this.createTime;
    }
    
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getLastLogin() {
        return this.lastLogin;
    }
    
    public void setLastLogin(Date lastLogin) {
        this.lastLogin = lastLogin;
    }
   








}