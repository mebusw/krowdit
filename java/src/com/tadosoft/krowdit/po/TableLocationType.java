package com.tadosoft.krowdit.po;



/**
 * TableLocationType entity. @author MyEclipse Persistence Tools
 */

public class TableLocationType  implements java.io.Serializable {


    // Fields    

     private long locationTypeId;
     private String locationTypeName;


    // Constructors

    /** default constructor */
    public TableLocationType() {
    }

    
    /** full constructor */
    public TableLocationType(String locationTypeName) {
        this.locationTypeName = locationTypeName;
    }

   
    // Property accessors

    public long getLocationTypeId() {
        return this.locationTypeId;
    }
    
    public void setLocationTypeId(long locationTypeId) {
        this.locationTypeId = locationTypeId;
    }

    public String getLocationTypeName() {
        return this.locationTypeName;
    }
    
    public void setLocationTypeName(String locationTypeName) {
        this.locationTypeName = locationTypeName;
    }
   








}