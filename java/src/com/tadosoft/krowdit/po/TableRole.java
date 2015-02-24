package com.tadosoft.krowdit.po;



/**
 * TableRole entity. @author MyEclipse Persistence Tools
 */

public class TableRole  implements java.io.Serializable {


    // Fields    

     private long roleId;
     private String roleName;


    // Constructors

    /** default constructor */
    public TableRole() {
    }

    
    /** full constructor */
    public TableRole(String roleName) {
        this.roleName = roleName;
    }

   
    // Property accessors

    public long getRoleId() {
        return this.roleId;
    }
    
    public void setRoleId(long roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return this.roleName;
    }
    
    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
   








}