package com.tadosoft.krowdit.po;



/**
 * TableAlbum entity. @author MyEclipse Persistence Tools
 */

public class TableAlbum  implements java.io.Serializable {


    // Fields    

     private long albumId;
     private long uid;
     private String url;


    // Constructors

    /** default constructor */
    public TableAlbum() {
    }

    
    /** full constructor */
    public TableAlbum(long uid, String url) {
        this.uid = uid;
        this.url = url;
    }

   
    // Property accessors

    public long getAlbumId() {
        return this.albumId;
    }
    
    public void setAlbumId(long albumId) {
        this.albumId = albumId;
    }

    public long getUid() {
        return this.uid;
    }
    
    public void setUid(long uid) {
        this.uid = uid;
    }

    public String getUrl() {
        return this.url;
    }
    
    public void setUrl(String url) {
        this.url = url;
    }
   








}