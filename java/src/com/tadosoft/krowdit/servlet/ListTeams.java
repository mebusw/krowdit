package com.tadosoft.krowdit.servlet;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;


import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.tadosoft.krowdit.po.*;
import com.tadosoft.krowdit.type.EC;

public class ListTeams extends JSONServlet implements ServletTemplate {

	private static final Logger log = LoggerFactory
			.getLogger(ListTeams.class);
	private TableLeagueDAO leagueDao;
	private long reqKrowdTypeId;

	public void injectDAO() {
		ApplicationContext ctx = WebApplicationContextUtils
				.getWebApplicationContext(request.getSession()
						.getServletContext());
		leagueDao = (TableLeagueDAO) ctx.getBean("TableLeagueDAO");
	}

	public void parseReqParam() {
		reqKrowdTypeId = parseLong("krowdTypeId");
		log.info(String.format("krowdTypeId=%d", reqKrowdTypeId));

	}

	public void processBusiness() {
		json = new JSONObject();
		try {
			List<HashMap> teamList = new LinkedList();
			List<TableLeague> teams = leagueDao.findByKrowdTypeId(reqKrowdTypeId);
			for (TableLeague team : teams) {
				HashMap teamMap = new HashMap();
				teamMap.put("teamId", team.getTeamId());
				teamMap.put("teamName", team.getTeamName());
				teamMap.put("logo", team.getLogo());
				teamList.add(teamMap);
			}

			json.put("error", EC.SUCCESS);
			json.put("teams", teamList);
		} catch (Exception e) {
			e.printStackTrace();
			json.put("error", EC.DAO_FAILED);
		}
	}

	public TableLeagueDAO getLeagueDao() {
		return leagueDao;
	}

	public void setLeagueDao(TableLeagueDAO leagueDao) {
		this.leagueDao = leagueDao;
	}

	public long getReqKrowdTypeId() {
		return reqKrowdTypeId;
	}

	public void setReqKrowdTypeId(long reqKrowdTypeId) {
		this.reqKrowdTypeId = reqKrowdTypeId;
	}

}
