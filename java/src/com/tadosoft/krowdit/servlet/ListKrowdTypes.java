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

public class ListKrowdTypes extends JSONServlet implements ServletTemplate {
	private static final Logger log = LoggerFactory
			.getLogger(ListKrowdTypes.class);
	private TableKrowdTypeDAO krowdTypeDao;

	public void injectDAO() {
		ApplicationContext ctx = WebApplicationContextUtils
				.getWebApplicationContext(request.getSession()
						.getServletContext());
		krowdTypeDao = (TableKrowdTypeDAO) ctx.getBean("TableKrowdTypeDAO");
	}

	public void parseReqParam() {
		log.info(String.format("no param"));

	}

	public void processBusiness() {
		json = new JSONObject();
		try {
			List<HashMap> krowdTypeList = new LinkedList();
			List<TableKrowdType> krowdTypes = krowdTypeDao.findAll();
			for (TableKrowdType type : krowdTypes) {
				HashMap locationMap = new HashMap();
				locationMap.put("krowdTypeId", type.getKrowdTypeId());
				locationMap.put("krowdTypeName", type.getKrowdTypeName());
				locationMap.put("public", type.getPublic_());
				krowdTypeList.add(locationMap);
			}

			json.put("error", EC.SUCCESS);
			json.put("krowdTypes", krowdTypeList);
		} catch (Exception e) {
			e.printStackTrace();
			json.put("error", EC.DAO_FAILED);
		}
	}

	public TableKrowdTypeDAO getKrowdTypeDao() {
		return krowdTypeDao;
	}

	public void setKrowdTypeDao(TableKrowdTypeDAO krowdTypeDao) {
		this.krowdTypeDao = krowdTypeDao;
	}

}
