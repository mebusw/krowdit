package com.tadosoft.krowdit.po;

/**
 * TableKrowdType entity. @author MyEclipse Persistence Tools
 */

public class TableKrowdType implements java.io.Serializable {

	// Fields

	private long krowdTypeId;
	private String krowdTypeName;
	private boolean public_;

	// Constructors

	/** default constructor */
	public TableKrowdType() {
	}

	/** full constructor */
	public TableKrowdType(String krowdTypeName, boolean public_) {
		this.krowdTypeName = krowdTypeName;
		this.public_ = public_;
	}

	// Property accessors

	public long getKrowdTypeId() {
		return this.krowdTypeId;
	}

	public void setKrowdTypeId(long krowdTypeId) {
		this.krowdTypeId = krowdTypeId;
	}

	public String getKrowdTypeName() {
		return this.krowdTypeName;
	}

	public void setKrowdTypeName(String krowdTypeName) {
		this.krowdTypeName = krowdTypeName;
	}

	public boolean getPublic_() {
		return this.public_;
	}

	public void setPublic_(boolean public_) {
		this.public_ = public_;
	}

}