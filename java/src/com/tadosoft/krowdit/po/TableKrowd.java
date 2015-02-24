package com.tadosoft.krowdit.po;

import java.util.Date;

/**
 * TableKrowd entity. @author MyEclipse Persistence Tools
 */

public class TableKrowd implements java.io.Serializable {

	// Fields

	private long krowdId;
	private String krowdName;
	private long krowdTypeId;
	private String home;
	private String away;
	private Date createTime;
	private long locationId;
	private long creatorId;
	private Date startTime;
	private Date reachEmptyTime;
	private Date closeTime;
	private Date lastScanTime;

	// Constructors

	/** default constructor */
	public TableKrowd() {
	}

	/** full constructor */
	public TableKrowd(String krowdName, long krowdTypeId, String home,
			String away, Date createTime, long locationId, long creatorId,
			Date startTime, Date reachEmptyTime, Date closeTime,
			Date lastScanTime) {
		this.krowdName = krowdName;
		this.krowdTypeId = krowdTypeId;
		this.home = home;
		this.away = away;
		this.createTime = createTime;
		this.locationId = locationId;
		this.creatorId = creatorId;
		this.startTime = startTime;
		this.reachEmptyTime = reachEmptyTime;
		this.closeTime = closeTime;
		this.lastScanTime = lastScanTime;
	}

	// Property accessors

	public long getKrowdId() {
		return this.krowdId;
	}

	public void setKrowdId(long krowdId) {
		this.krowdId = krowdId;
	}

	public String getKrowdName() {
		return this.krowdName;
	}

	public void setKrowdName(String krowdName) {
		this.krowdName = krowdName;
	}

	public long getKrowdTypeId() {
		return this.krowdTypeId;
	}

	public void setKrowdTypeId(long krowdTypeId) {
		this.krowdTypeId = krowdTypeId;
	}

	public String getHome() {
		return this.home;
	}

	public void setHome(String home) {
		this.home = home;
	}

	public String getAway() {
		return this.away;
	}

	public void setAway(String away) {
		this.away = away;
	}

	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public long getLocationId() {
		return this.locationId;
	}

	public void setLocationId(long locationId) {
		this.locationId = locationId;
	}

	public long getCreatorId() {
		return this.creatorId;
	}

	public void setCreatorId(long creatorId) {
		this.creatorId = creatorId;
	}

	public Date getStartTime() {
		return this.startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getReachEmptyTime() {
		return this.reachEmptyTime;
	}

	public void setReachEmptyTime(Date reachEmptyTime) {
		this.reachEmptyTime = reachEmptyTime;
	}

	public Date getCloseTime() {
		return this.closeTime;
	}

	public void setCloseTime(Date closeTime) {
		this.closeTime = closeTime;
	}

	public Date getLastScanTime() {
		return this.lastScanTime;
	}

	public void setLastScanTime(Date lastScanTime) {
		this.lastScanTime = lastScanTime;
	}

}