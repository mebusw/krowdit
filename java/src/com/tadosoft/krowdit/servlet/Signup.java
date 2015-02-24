package com.tadosoft.krowdit.servlet;

import java.util.Date;

import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import com.tadosoft.krowdit.po.TableUser;
import com.tadosoft.krowdit.po.TableUserDAO;
import com.tadosoft.krowdit.type.EC;
import com.tadosoft.krowdit.type.UserType;

public class Signup extends JSONServlet implements ServletTemplate {

	private static final Logger log = LoggerFactory.getLogger(Signup.class);

	
	private TableUserDAO userDao;
	private String reqUserName;
	private String reqPwd;
	private String reqEmail;

	public void injectDAO() {
		ApplicationContext ctx = WebApplicationContextUtils
				.getWebApplicationContext(request.getSession()
						.getServletContext());
		userDao = (TableUserDAO) ctx.getBean("TableUserDAO");

	}

	public void parseReqParam() {
		reqUserName = parseStr("userName");
		reqPwd = parseStr("pwd");
		reqEmail = parseStr("email");
		log.info(String.format("userName=%s, pwd=%s, email=%s", reqUserName,
				reqPwd, reqEmail));

	}

	public void processBusiness() {
		TableUser u = null;
		json = new JSONObject();
		try {
			if (userDao.findByUserName(reqEmail).size() > 0
					|| userDao.findByEmail(reqEmail).size() > 0) {
				json.put("error", EC.EXISTED);
			} else {

				u = new TableUser(reqUserName, reqPwd, reqEmail, new Date(),
						UserType.KROWDIT_USER);
				userDao.save(u);
				json.put("error", EC.SUCCESS);
				json.put("uid", u.getUid());
			}
		} catch (Exception e) {
			e.printStackTrace();
			json.put("error", EC.DAO_FAILED);
		}

	}

	public TableUserDAO getUserDao() {
		return userDao;
	}

	public void setUserDao(TableUserDAO userDao) {
		this.userDao = userDao;
	}

	public String getReqUserName() {
		return reqUserName;
	}

	public void setReqUserName(String reqUserName) {
		this.reqUserName = reqUserName;
	}

	public String getReqPwd() {
		return reqPwd;
	}

	public void setReqPwd(String reqPwd) {
		this.reqPwd = reqPwd;
	}

	public String getReqEmail() {
		return reqEmail;
	}

	public void setReqEmail(String reqEmail) {
		this.reqEmail = reqEmail;
	}

	
	
}
