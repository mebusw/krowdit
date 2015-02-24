package com.tadosoft.krowdit.servlet;

import java.util.*;

import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.tadosoft.krowdit.po.*;

import com.tadosoft.krowdit.type.EC;

public class CreateLocation extends JSONServlet implements ServletTemplate {

	private static final Logger log = LoggerFactory.getLogger(ListTeams.class);
	private TableLocationDAO locationDao;
	private float reqLat;
	private float reqLng;
	private String reqName;
	private long reqCreatorId;
	private long reqTypeId;

	public void injectDAO() {
		ApplicationContext ctx = WebApplicationContextUtils
				.getWebApplicationContext(request.getSession()
						.getServletContext());
		locationDao = (TableLocationDAO) ctx.getBean("TableLocationDAO");
	}

	public void parseReqParam() {
		reqLat = parseFloat("lat");
		reqLng = parseFloat("lng");
		reqName = parseStr("name");
		reqCreatorId = parseLong("creatorId");
		reqTypeId = parseLong("typeId");
		log.info(String.format("name=%s, lat=%f, lng=%f, creatorId=%d, typeId=%d", reqName, reqLat,
				reqLng, reqCreatorId, reqTypeId));

	}

	public void processBusiness() {
		json = new JSONObject();
		try {
			TableLocation location= new TableLocation(reqName, reqTypeId, reqLat, reqLng, new Date(), reqCreatorId);
			locationDao.save(location);

			json.put("error", EC.SUCCESS);
			json.put("locationId", location.getLocationId());
		} catch (Exception e) {
			e.printStackTrace();
			json.put("error", EC.DAO_FAILED);
		}
	}

	public TableLocationDAO getLocationDao() {
		return locationDao;
	}

	public void setLocationDao(TableLocationDAO locationDao) {
		this.locationDao = locationDao;
	}

	public float getReqLat() {
		return reqLat;
	}

	public void setReqLat(float reqLat) {
		this.reqLat = reqLat;
	}

	public float getReqLng() {
		return reqLng;
	}

	public void setReqLng(float reqLng) {
		this.reqLng = reqLng;
	}

	public String getReqName() {
		return reqName;
	}

	public void setReqName(String reqName) {
		this.reqName = reqName;
	}

	public long getReqCreatorId() {
		return reqCreatorId;
	}

	public void setReqCreatorId(long reqCreatorId) {
		this.reqCreatorId = reqCreatorId;
	}

	public long getReqTypeId() {
		return reqTypeId;
	}

	public void setReqTypeId(long reqTypeId) {
		this.reqTypeId = reqTypeId;
	}

}
