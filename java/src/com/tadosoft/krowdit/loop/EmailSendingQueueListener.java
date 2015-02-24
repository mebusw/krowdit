package com.tadosoft.krowdit.loop;


import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EmailSendingQueueListener implements ServletContextListener {

	private static final Logger log = LoggerFactory
			.getLogger(EmailSendingQueueListener.class);


	private EmailSendingQueue emailSendingQueue;

	public void contextDestroyed(ServletContextEvent arg0) {
		log.info("will stop emailSendingQueueListener");
		emailSendingQueue.setRunning(false);
		emailSendingQueue = null;
	}

	public void contextInitialized(ServletContextEvent arg0) {
		emailSendingQueue = EmailSendingQueue.getInstance();
		emailSendingQueue.setDaemon(true);
		log.info("will start emailSendingQueueListener");
		emailSendingQueue.start();

	}

}
