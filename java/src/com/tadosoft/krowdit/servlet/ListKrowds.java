package com.tadosoft.krowdit.servlet;

import java.util.*;

import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.tadosoft.krowdit.po.*;

import com.tadosoft.krowdit.type.EC;
import com.tadosoft.krowdit.util.DateFormatter;

public class ListKrowds extends JSONServlet implements ServletTemplate {

	private static final Logger log = LoggerFactory.getLogger(ListKrowds.class);
	private float reqLat;
	private float reqLng;
	private int reqOffset;
	private TableKrowdDAO krowdDao;
	private TableLocationDAO locationDao;
	private TableLeagueDAO leagueDao;
	private KrowdSearchDAO krowdSearchDao;
	private TableUserDAO userDAO;
	private TableKrowdTypeDAO krowdTypeDao;
	private TableJoinKrowdDAO joinKrowdDao;

	public void injectDAO() {
		ApplicationContext ctx = WebApplicationContextUtils
				.getWebApplicationContext(request.getSession()
						.getServletContext());
		krowdDao = (TableKrowdDAO) ctx.getBean("TableKrowdDAO");
		locationDao = (TableLocationDAO) ctx.getBean("TableLocationDAO");
		leagueDao = (TableLeagueDAO) ctx.getBean("TableLeagueDAO");
		krowdSearchDao = (KrowdSearchDAO) ctx.getBean("KrowdSearchDAO");
		userDAO = (TableUserDAO) ctx.getBean("TableUserDAO");
		krowdTypeDao = (TableKrowdTypeDAO) ctx.getBean("TableKrowdTypeDAO");
		joinKrowdDao = (TableJoinKrowdDAO) ctx.getBean("TableJoinKrowdDAO");
	}

	public void parseReqParam() {
		reqLat = parseFloat("lat");
		reqLng = parseFloat("lng");
		reqOffset = parseInt("offset");
		log.info(String.format("lat=%f, lng=%f, offset=%d", reqLat, reqLng,
				reqOffset));

	}

	public void processBusiness() {
		json = new JSONObject();

		TableLeague home, away;
		Date now = new Date();

		try {
			List<Object[]> kk = krowdSearchDao.findWithinTimeAndDistance(now,
					reqLat, reqLng, reqOffset);

			List<HashMap> krowdsList = new LinkedList();
			for (Object[] k : kk) {
				TableKrowd krowd = (TableKrowd) k[0];
				TableLocation location = (TableLocation) k[1];
				HashMap krowdMap = new HashMap();
				krowdMap.put("krowdId", krowd.getKrowdId());
				krowdMap.put("startTime", DateFormatter.getInstance().format(
						krowd.getStartTime()));
				krowdMap.put("createTime", DateFormatter.getInstance().format(
						location.getCreateTime()));
				krowdMap.put("lat", location.getLatitude());
				krowdMap.put("lng", location.getLongitude());
				krowdMap.put("locationName", location.getLocationName());

				krowdMap.put("krowdTypeId", krowd.getKrowdTypeId());
				krowdMap.put("krowdTypeName", krowdTypeDao.findById(
						krowd.getKrowdTypeId()).getKrowdTypeName());

				krowdMap.put("creatorId", krowd.getCreatorId());
				krowdMap.put("creatorName", userDAO.findById(
						krowd.getCreatorId()).getUserName());

				home = leagueDao.findById(Long.parseLong(krowd.getHome()));
				krowdMap.put("homeTeamId", home.getTeamId());
				krowdMap.put("homeTeamName", home.getTeamName());
				krowdMap.put("homeTeamLogo", home.getLogo());
				krowdMap.put("homeSupporterCount", joinKrowdDao
						.countByKrowdIdAndSupportingTeamId(krowd.getKrowdId(),
								home.getTeamId()));

				away = leagueDao.findById(Long.parseLong(krowd.getAway()));
				krowdMap.put("awayTeamId", away.getTeamId());
				krowdMap.put("awayTeamName", away.getTeamName());
				krowdMap.put("awayTeamLogo", away.getLogo());
				krowdMap.put("awaySupporterCount", joinKrowdDao
						.countByKrowdIdAndSupportingTeamId(krowd.getKrowdId(),
								away.getTeamId()));

				krowdsList.add(krowdMap);
				// TODO temp
				// OnlineUserManager.getInstance(getServletContext()).removeUser(1);
			}
			json.put("lat", reqLat);
			json.put("lng", reqLng);
			json.put("serverTime", DateFormatter.getInstance().format(now));
			json.put("krowds", krowdsList);
			json.put("error", EC.SUCCESS);

		} catch (IndexOutOfBoundsException e) {
			json.put("error", EC.NOT_FOUND);
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

	public TableKrowdDAO getKrowdDao() {
		return krowdDao;
	}

	public void setKrowdDao(TableKrowdDAO krowdDao) {
		this.krowdDao = krowdDao;
	}

	public TableLocationDAO getLocationDao() {
		return locationDao;
	}

	public void setLocationDao(TableLocationDAO locationDao) {
		this.locationDao = locationDao;
	}

	public TableLeagueDAO getLeagueDao() {
		return leagueDao;
	}

	public void setLeagueDao(TableLeagueDAO leagueDao) {
		this.leagueDao = leagueDao;
	}

	public int getReqOffset() {
		return reqOffset;
	}

	public void setReqOffset(int reqOffset) {
		this.reqOffset = reqOffset;
	}

	public KrowdSearchDAO getKrowdSearchDao() {
		return krowdSearchDao;
	}

	public void setKrowdSearchDao(KrowdSearchDAO krowdSearchDao) {
		this.krowdSearchDao = krowdSearchDao;
	}

	public TableUserDAO getUserDAO() {
		return userDAO;
	}

	public void setUserDAO(TableUserDAO userDAO) {
		this.userDAO = userDAO;
	}

	public TableKrowdTypeDAO getKrowdTypeDao() {
		return krowdTypeDao;
	}

	public void setKrowdTypeDao(TableKrowdTypeDAO krowdTypeDao) {
		this.krowdTypeDao = krowdTypeDao;
	}

	public TableJoinKrowdDAO getJoinKrowdDao() {
		return joinKrowdDao;
	}

	public void setJoinKrowdDao(TableJoinKrowdDAO joinKrowdDao) {
		this.joinKrowdDao = joinKrowdDao;
	}

}
