package com.tadosoft.krowdit.po;

import java.util.Date;

/**
 * TableJoinKrowd entity. @author MyEclipse Persistence Tools
 */

public class TableJoinKrowd implements java.io.Serializable {

	// Fields

	private long joinId;
	private long krowdId;
	private long uid;
	private Date joinTime;
	private long supportingTeamId;

	// Constructors

	/** default constructor */
	public TableJoinKrowd() {
	}

	/** full constructor */
	public TableJoinKrowd(long krowdId, long uid, Date joinTime,
			long supportingTeamId) {
		this.krowdId = krowdId;
		this.uid = uid;
		this.joinTime = joinTime;
		this.supportingTeamId = supportingTeamId;
	}

	// Property accessors

	public long getJoinId() {
		return this.joinId;
	}

	public void setJoinId(long joinId) {
		this.joinId = joinId;
	}

	public long getKrowdId() {
		return this.krowdId;
	}

	public void setKrowdId(long krowdId) {
		this.krowdId = krowdId;
	}

	public long getUid() {
		return this.uid;
	}

	public void setUid(long uid) {
		this.uid = uid;
	}

	public Date getJoinTime() {
		return this.joinTime;
	}

	public void setJoinTime(Date joinTime) {
		this.joinTime = joinTime;
	}

	public long getSupportingTeamId() {
		return this.supportingTeamId;
	}

	public void setSupportingTeamId(long supportingTeamId) {
		this.supportingTeamId = supportingTeamId;
	}

}