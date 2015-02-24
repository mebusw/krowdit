package com.tadosoft.krowdit.servlet;


import javax.servlet.ServletException;

import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.tadosoft.krowdit.loop.EmailSendingQueue;
import com.tadosoft.krowdit.loop.NotifyEmail;
import com.tadosoft.krowdit.po.TableUser;
import com.tadosoft.krowdit.po.TableUserDAO;
import com.tadosoft.krowdit.type.EC;

public class RecoverPassword extends JSONServlet implements ServletTemplate {

	private static final Logger log = LoggerFactory
			.getLogger(RecoverPassword.class);

	private EmailSendingQueue emailSendingQueue;
	private TableUserDAO userDao;
	private String reqEmail;

	public void init() throws ServletException {
		log.info("init");
		emailSendingQueue = EmailSendingQueue.getInstance();

	}

	public void injectDAO() {
		ApplicationContext ctx = WebApplicationContextUtils
				.getWebApplicationContext(request.getSession()
						.getServletContext());
		userDao = (TableUserDAO) ctx.getBean("TableUserDAO");
	}

	public void parseReqParam() {
		reqEmail = parseStr("email");
		log.info(String.format("email=%s", reqEmail));
	}

	public void processBusiness() {
		TableUser u = null;
		json = new JSONObject();
		

		try {
			u = (TableUser) userDao.findByEmail(reqEmail).get(0);
			NotifyEmail pwdRecoverEmail = new NotifyEmail(u.getEmail(), u
					.getPwd(), u.getUserName());
			emailSendingQueue.addEmailToQueue(pwdRecoverEmail);
			json.put("error", EC.SUCCESS);
		} catch (IndexOutOfBoundsException e) {
			json.put("error", EC.NOT_FOUND);
		} catch (Exception e) {
			json.put("error", EC.DAO_FAILED);
		}
	}

	public EmailSendingQueue getEmailSendingQueue() {
		return emailSendingQueue;
	}

	public void setEmailSendingQueue(EmailSendingQueue emailSendingQueue) {
		this.emailSendingQueue = emailSendingQueue;
	}

	public TableUserDAO getUserDao() {
		return userDao;
	}

	public void setUserDao(TableUserDAO userDao) {
		this.userDao = userDao;
	}

	public String getReqEmail() {
		return reqEmail;
	}

	public void setReqEmail(String reqEmail) {
		this.reqEmail = reqEmail;
	}
	
	
}
