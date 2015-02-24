package com.tadosoft.krowdit.po;

import java.util.Date;

/**
 * TableLocation entity. @author MyEclipse Persistence Tools
 */

public class TableLocation implements java.io.Serializable {

	// Fields

	private long locationId;
	private String locationName;
	private long locationTypeId;
	private float latitude;
	private float longitude;
	private Date createTime;
	private long creatorId;

	// Constructors

	/** default constructor */
	public TableLocation() {
	}

	/** full constructor */
	public TableLocation(String locationName, long locationTypeId,
			float latitude, float longitude, Date createTime, long creatorId) {
		this.locationName = locationName;
		this.locationTypeId = locationTypeId;
		this.latitude = latitude;
		this.longitude = longitude;
		this.createTime = createTime;
		this.creatorId = creatorId;
	}

	// Property accessors

	public long getLocationId() {
		return this.locationId;
	}

	public void setLocationId(long locationId) {
		this.locationId = locationId;
	}

	public String getLocationName() {
		return this.locationName;
	}

	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}

	public long getLocationTypeId() {
		return this.locationTypeId;
	}

	public void setLocationTypeId(long locationTypeId) {
		this.locationTypeId = locationTypeId;
	}

	public float getLatitude() {
		return this.latitude;
	}

	public void setLatitude(float latitude) {
		this.latitude = latitude;
	}

	public float getLongitude() {
		return this.longitude;
	}

	public void setLongitude(float longitude) {
		this.longitude = longitude;
	}

	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public long getCreatorId() {
		return this.creatorId;
	}

	public void setCreatorId(long creatorId) {
		this.creatorId = creatorId;
	}

}