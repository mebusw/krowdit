package com.tadosoft.krowdit.servlet;

import java.util.Date;

import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.tadosoft.krowdit.loop.OnlineUserManager;
import com.tadosoft.krowdit.po.TableUser;
import com.tadosoft.krowdit.po.TableUserDAO;
import com.tadosoft.krowdit.type.EC;
import com.tadosoft.krowdit.type.UserType;

public class Login extends JSONServlet implements ServletTemplate {

	private static final Logger log = LoggerFactory.getLogger(Login.class);

	private TableUserDAO userDao;
	private String reqUserName;
	private String reqPwd;
	private int reqUserType;

	public void processBusiness() {

		TableUser u = null;
		json = new JSONObject();
		try {

			if (UserType.FACEBOOK_USER == reqUserType)
				reqPwd = "fbPwd";
			u = (TableUser) userDao.findByUserNameAndPwdAndUserType(
					reqUserName, reqPwd, reqUserType).get(0);
			json.put("error", EC.SUCCESS);
			json.put("uid", u.getUid());

			// TODO temp, the addUser() to onlineUserList should not be here
			OnlineUserManager.getInstance().addUser(u.getUid());
		} catch (IndexOutOfBoundsException e) {
			if (UserType.FACEBOOK_USER == reqUserType) {
				u = new TableUser(reqUserName, "fbPwd", "fb@tadosoft.com",
						new Date(), reqUserType);
				userDao.save(u);
				json.put("error", EC.SUCCESS);
				json.put("uid", u.getUid());
				json.put("imageName", "garfield.jpg");
			} else
				json.put("error", EC.NOT_FOUND);
		} catch (Exception e) {
			e.printStackTrace();
			json.put("error", EC.DAO_FAILED);
		}

	}

	/**
	 * parse the parameters from the request URL
	 */
	public void parseReqParam() {
		reqUserName = parseStr("userName");
		reqPwd = parseStr("pwd");
		reqUserType = parseInt("userType");

		log.info(String.format("userName=%s, pwd=%s, userType=%d", reqUserName,
				reqPwd, reqUserType));
	}

	/**
	 * this method simply calls SPRING to inject a DAO instance. from unit test
	 * point of view, this method can be replaced by other injection method for
	 * a mock DAO.
	 */
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

	public int getReqUserType() {
		return reqUserType;
	}

	public void setReqUserType(int reqUserType) {
		this.reqUserType = reqUserType;
	}

}
