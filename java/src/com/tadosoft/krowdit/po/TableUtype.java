package com.tadosoft.krowdit.po;



/**
 * TableUtype entity. @author MyEclipse Persistence Tools
 */

public class TableUtype  implements java.io.Serializable {


    // Fields    

     private long utypeId;
     private String utypeName;


    // Constructors

    /** default constructor */
    public TableUtype() {
    }

    
    /** full constructor */
    public TableUtype(String utypeName) {
        this.utypeName = utypeName;
    }

   
    // Property accessors

    public long getUtypeId() {
        return this.utypeId;
    }
    
    public void setUtypeId(long utypeId) {
        this.utypeId = utypeId;
    }

    public String getUtypeName() {
        return this.utypeName;
    }
    
    public void setUtypeName(String utypeName) {
        this.utypeName = utypeName;
    }
   








}