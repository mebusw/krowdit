package com.tadosoft.krowdit.po;

import java.util.Date;


/**
 * TableProfile entity. @author MyEclipse Persistence Tools
 */

public class TableProfile  implements java.io.Serializable {


    // Fields    

     private long uid;
     private long picId;
     private Date lastLogin;
     private Integer totalKrowdsJoined;
     private Integer krowdsOf25members;
     private Integer numberOfTextPosts;
     private Integer numberOfPicPosts;
     private long largestKrowdId;
     private long favoriteKrowdTypeId;
     private long favoriteLocationId;


    // Constructors

    /** default constructor */
    public TableProfile() {
    }

    
    /** full constructor */
    public TableProfile(long picId, Date lastLogin, Integer totalKrowdsJoined, Integer krowdsOf25members, Integer numberOfTextPosts, Integer numberOfPicPosts, long largestKrowdId, long favoriteKrowdTypeId, long favoriteLocationId) {
        this.picId = picId;
        this.lastLogin = lastLogin;
        this.totalKrowdsJoined = totalKrowdsJoined;
        this.krowdsOf25members = krowdsOf25members;
        this.numberOfTextPosts = numberOfTextPosts;
        this.numberOfPicPosts = numberOfPicPosts;
        this.largestKrowdId = largestKrowdId;
        this.favoriteKrowdTypeId = favoriteKrowdTypeId;
        this.favoriteLocationId = favoriteLocationId;
    }

   
    // Property accessors

    public long getUid() {
        return this.uid;
    }
    
    public void setUid(long uid) {
        this.uid = uid;
    }

    public long getPicId() {
        return this.picId;
    }
    
    public void setPicId(long picId) {
        this.picId = picId;
    }

    public Date getLastLogin() {
        return this.lastLogin;
    }
    
    public void setLastLogin(Date lastLogin) {
        this.lastLogin = lastLogin;
    }

    public Integer getTotalKrowdsJoined() {
        return this.totalKrowdsJoined;
    }
    
    public void setTotalKrowdsJoined(Integer totalKrowdsJoined) {
        this.totalKrowdsJoined = totalKrowdsJoined;
    }

    public Integer getKrowdsOf25members() {
        return this.krowdsOf25members;
    }
    
    public void setKrowdsOf25members(Integer krowdsOf25members) {
        this.krowdsOf25members = krowdsOf25members;
    }

    public Integer getNumberOfTextPosts() {
        return this.numberOfTextPosts;
    }
    
    public void setNumberOfTextPosts(Integer numberOfTextPosts) {
        this.numberOfTextPosts = numberOfTextPosts;
    }

    public Integer getNumberOfPicPosts() {
        return this.numberOfPicPosts;
    }
    
    public void setNumberOfPicPosts(Integer numberOfPicPosts) {
        this.numberOfPicPosts = numberOfPicPosts;
    }

    public long getLargestKrowdId() {
        return this.largestKrowdId;
    }
    
    public void setLargestKrowdId(long largestKrowdId) {
        this.largestKrowdId = largestKrowdId;
    }

    public long getFavoriteKrowdTypeId() {
        return this.favoriteKrowdTypeId;
    }
    
    public void setFavoriteKrowdTypeId(long favoriteKrowdTypeId) {
        this.favoriteKrowdTypeId = favoriteKrowdTypeId;
    }

    public long getFavoriteLocationId() {
        return this.favoriteLocationId;
    }
    
    public void setFavoriteLocationId(long favoriteLocationId) {
        this.favoriteLocationId = favoriteLocationId;
    }
   








}