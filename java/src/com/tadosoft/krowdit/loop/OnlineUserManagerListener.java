package com.tadosoft.krowdit.loop;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class OnlineUserManagerListener implements ServletContextListener {
	private static final Logger log = LoggerFactory
			.getLogger(OnlineUserManagerListener.class);
	private OnlineUserManager onlineUserManager;

	public void contextDestroyed(ServletContextEvent arg0) {
		log.info("will stop OnlineUserManager");
		onlineUserManager.setRunning(false);
		onlineUserManager = null;
	}

	public void contextInitialized(ServletContextEvent arg0) {
//		onlineUserManager = OnlineUserManager.getInstance(arg0
//				.getServletContext());
		onlineUserManager = OnlineUserManager.getInstance();
		onlineUserManager.setDaemon(true);
		log.info("will start OnlineUserManager");
		onlineUserManager.start();

	}
}
