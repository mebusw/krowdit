package com.tadosoft.krowdit.servlet;

import java.text.ParseException;
import java.util.*;

import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.tadosoft.krowdit.po.*;
import com.tadosoft.krowdit.type.EC;

public class CreateKrowd extends JSONServlet implements ServletTemplate {

	private static final Logger log = LoggerFactory.getLogger(ListTeams.class);
	private TableKrowdDAO krowdDao;
	private long reqKrowdTypeId;
	private String reqHomeTeamId;
	private String reqAwayTeamId;
	private long reqLocationId;
	private long reqCreatorId;
	private Date reqStartTime;

	public void injectDAO() {
		ApplicationContext ctx = WebApplicationContextUtils
				.getWebApplicationContext(request.getSession()
						.getServletContext());
		krowdDao = (TableKrowdDAO) ctx.getBean("TableKrowdDAO");
	}

	public void parseReqParam() {
		reqKrowdTypeId = parseLong("krowdTypeId");
		reqHomeTeamId = parseStr("homeTeamId");
		reqAwayTeamId = parseStr("awayTeamId");
		reqLocationId = parseLong("locationId");
		reqCreatorId = parseLong("creatorId");
		try {
			reqStartTime = parseDate("startTime");
		} catch (ParseException e) {
			e.printStackTrace();
		}

		log
				.info(String
						.format(
								"krowdTypeId=%d, homeTeamId=%s, awayTeamId=%s, locationId=%d, creatorId=%d, startTime=%s",
								reqKrowdTypeId, reqHomeTeamId, reqAwayTeamId,
								reqLocationId, reqCreatorId, reqStartTime));

	}

	public void processBusiness() {
		json = new JSONObject();
		try {
			Date now = new Date();

			Calendar cal = Calendar.getInstance();
			cal.setTime(reqStartTime);
			cal.add(Calendar.HOUR, 24);
			Date future = cal.getTime();
			TableKrowd krowd = new TableKrowd("noName", reqKrowdTypeId, String
					.valueOf(reqHomeTeamId), String.valueOf(reqAwayTeamId), now,
					reqLocationId, reqCreatorId, reqStartTime, now, future, now);
			krowdDao.save(krowd);

			json.put("error", EC.SUCCESS);
			json.put("krowdId", krowd.getKrowdId());
		} catch (Exception e) {
			e.printStackTrace();
			json.put("error", EC.DAO_FAILED);
		}
	}

	public TableKrowdDAO getKrowdDao() {
		return krowdDao;
	}

	public void setKrowdDao(TableKrowdDAO krowdDao) {
		this.krowdDao = krowdDao;
	}

	public long getReqKrowdTypeId() {
		return reqKrowdTypeId;
	}

	public void setReqKrowdTypeId(long reqKrowdTypeId) {
		this.reqKrowdTypeId = reqKrowdTypeId;
	}

	public String getReqHomeTeamId() {
		return reqHomeTeamId;
	}

	public void setReqHomeTeamId(String reqHomeTeamId) {
		this.reqHomeTeamId = reqHomeTeamId;
	}

	public String getReqAwayTeamId() {
		return reqAwayTeamId;
	}

	public void setReqAwayTeamId(String reqAwayTeamId) {
		this.reqAwayTeamId = reqAwayTeamId;
	}

	public long getReqLocationId() {
		return reqLocationId;
	}

	public void setReqLocationId(long reqLocationId) {
		this.reqLocationId = reqLocationId;
	}

	public long getReqCreatorId() {
		return reqCreatorId;
	}

	public void setReqCreatorId(long reqCreatorId) {
		this.reqCreatorId = reqCreatorId;
	}

	public Date getReqStartTime() {
		return reqStartTime;
	}

	public void setReqStartTime(Date reqStartTime) {
		this.reqStartTime = reqStartTime;
	}

}
