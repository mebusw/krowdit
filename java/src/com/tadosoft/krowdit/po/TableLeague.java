package com.tadosoft.krowdit.po;



/**
 * TableLeague entity. @author MyEclipse Persistence Tools
 */

public class TableLeague  implements java.io.Serializable {


    // Fields    

     private long teamId;
     private long krowdTypeId;
     private String teamName;
     private String logo;
     private long color1;
     private long color2;
     private long color3;
     private long homeLocationId;


    // Constructors

    /** default constructor */
    public TableLeague() {
    }

    
    /** full constructor */
    public TableLeague(long krowdTypeId, String teamName, String logo, long color1, long color2, long color3, long homeLocationId) {
        this.krowdTypeId = krowdTypeId;
        this.teamName = teamName;
        this.logo = logo;
        this.color1 = color1;
        this.color2 = color2;
        this.color3 = color3;
        this.homeLocationId = homeLocationId;
    }

   
    // Property accessors

    public long getTeamId() {
        return this.teamId;
    }
    
    public void setTeamId(long teamId) {
        this.teamId = teamId;
    }

    public long getKrowdTypeId() {
        return this.krowdTypeId;
    }
    
    public void setKrowdTypeId(long krowdTypeId) {
        this.krowdTypeId = krowdTypeId;
    }

    public String getTeamName() {
        return this.teamName;
    }
    
    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public String getLogo() {
        return this.logo;
    }
    
    public void setLogo(String logo) {
        this.logo = logo;
    }

    public long getColor1() {
        return this.color1;
    }
    
    public void setColor1(long color1) {
        this.color1 = color1;
    }

    public long getColor2() {
        return this.color2;
    }
    
    public void setColor2(long color2) {
        this.color2 = color2;
    }

    public long getColor3() {
        return this.color3;
    }
    
    public void setColor3(long color3) {
        this.color3 = color3;
    }

    public long getHomeLocationId() {
        return this.homeLocationId;
    }
    
    public void setHomeLocationId(long homeLocationId) {
        this.homeLocationId = homeLocationId;
    }
   








}