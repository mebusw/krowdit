package com.tadosoft.krowdit.servlet;


import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.tadosoft.krowdit.loop.OnlineUserManager;
import com.tadosoft.krowdit.po.TableUser;
import com.tadosoft.krowdit.po.TableUserDAO;
import com.tadosoft.krowdit.type.EC;


public class Logout extends JSONServlet implements ServletTemplate {

	private static final Logger log = LoggerFactory.getLogger(Logout.class);


	private TableUserDAO userDao;
	private long reqUid;

	public void processBusiness() {

		TableUser u = null;
		json = new JSONObject();
		try {
			u = (TableUser) userDao.findById(reqUid);
			json.put("error", EC.SUCCESS);
			
			// TODO temp, the removeUser() to onlineUserList should not be here
			OnlineUserManager.getInstance().removeUser(u.getUid());
		} catch (Exception e) {
			e.printStackTrace();
			json.put("error", EC.DAO_FAILED);
		}

	}

	public void parseReqParam() {
		reqUid = parseLong("uid");
		log.info(String.format("uid=%d", reqUid));
	}

	public void injectDAO() {
		ApplicationContext ctx = WebApplicationContextUtils
				.getWebApplicationContext(request.getSession()
						.getServletContext());
		userDao = (TableUserDAO) ctx.getBean("TableUserDAO");
	}

	public TableUserDAO getUserDao() {
		return userDao;
	}

	public void setUserDao(TableUserDAO userDao) {
		this.userDao = userDao;
	}

	public long getReqUid() {
		return reqUid;
	}

	public void setReqUid(long reqUid) {
		this.reqUid = reqUid;
	}

}
