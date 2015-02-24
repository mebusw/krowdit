package com.tadosoft.krowdit.servlet;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.tadosoft.krowdit.po.TableLocation;
import com.tadosoft.krowdit.po.TableLocationDAO;
import com.tadosoft.krowdit.type.EC;

public class ListLocations extends JSONServlet implements ServletTemplate {
	private static final Logger log = LoggerFactory
			.getLogger(ListLocations.class);
	private float reqLat;
	private float reqLng;
	private TableLocationDAO locationDao;

	public void injectDAO() {
		ApplicationContext ctx = WebApplicationContextUtils
				.getWebApplicationContext(request.getSession()
						.getServletContext());
		locationDao = (TableLocationDAO) ctx.getBean("TableLocationDAO");
	}

	public void parseReqParam() {
		reqLat = parseFloat("lat");
		reqLng = parseFloat("lng");
		log.info(String.format("lat=%f, lng=%f", reqLat, reqLng));

	}

	public void processBusiness() {
		json = new JSONObject();
		try {
			List<HashMap> locationsList = new LinkedList();
			List<TableLocation> locations = locationDao.findAll();
			for (TableLocation loc : locations) {
				HashMap locationMap = new HashMap();
				locationMap.put("locationId", loc.getLocationId());
				locationMap.put("locationName", loc.getLocationName());
				locationMap.put("lat", loc.getLatitude());
				locationMap.put("lng", loc.getLongitude());
				locationsList.add(locationMap);
			}

			json.put("error", EC.SUCCESS);
			json.put("locations", locationsList);
		} catch (Exception e) {
			e.printStackTrace();
			json.put("error", EC.DAO_FAILED);
		}
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

	public TableLocationDAO getLocationDao() {
		return locationDao;
	}

	public void setLocationDao(TableLocationDAO locationDao) {
		this.locationDao = locationDao;
	}

}
