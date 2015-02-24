package com.tadosoft.krowdit.loop;

import java.util.Timer;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class EmptyKrowdDetectorListener implements ServletContextListener {
	private static final Logger log = LoggerFactory.getLogger(EmptyKrowdDetectorListener.class);
	
	private static final int START_DELAY = 1000;
	private static final int INTERVAL = 3000;
	
	public void contextDestroyed(ServletContextEvent arg0) {
		log.info("destroy empty krowd detector");
		EmptyKrowdDetector.getInstance(arg0.getServletContext()).cancel();
		
	}

	public void contextInitialized(ServletContextEvent arg0) {
		log.info("init empty krowd detector");

		Timer timer = new Timer();
		timer.schedule(
				EmptyKrowdDetector.getInstance(arg0.getServletContext()), START_DELAY, INTERVAL);
        //timer.scheduleAtFixedRate(EmptyKrowdDetector.getInstance(), 2000, 3000);

		
	}

}
