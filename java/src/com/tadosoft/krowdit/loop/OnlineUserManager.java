package com.tadosoft.krowdit.loop;

import java.util.*;

import javax.servlet.ServletContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//import org.springframework.context.ApplicationContext;
//import org.springframework.web.context.support.WebApplicationContextUtils;
//import com.tadosoft.krowdit.po.TableJoinKrowdDAO;
//import com.tadosoft.krowdit.po.TableUserDAO;

public class OnlineUserManager extends Thread {
	private static final Logger log = LoggerFactory
			.getLogger(OnlineUserManager.class);
	private static OnlineUserManager instance;

	protected static final int HEARTBEAT_TIMEOUT = 3; // minute
	protected boolean running = true;

	/** key = uid, value = lastRefreshDate of that uid */
	protected TreeMap<Long, Date> users;
//	private TableUserDAO userDao;
//	private TableJoinKrowdDAO joinKrowdDao;

	public boolean isRunning() {
		return running;
	}

	public void setRunning(boolean running) {
		this.running = running;
	}

	/**
	 * Singleton
	 * 
	 * @param servletContext
	 * @return
	 */
	public synchronized static OnlineUserManager getInstance() {
		if (instance == null) {
			instance = new OnlineUserManager();
			// ApplicationContext ctx = WebApplicationContextUtils
			// .getWebApplicationContext(servletContext);
			// instance.userDao = (TableUserDAO) ctx.getBean("TableUserDAO");
			// instance.joinKrowdDao = (TableJoinKrowdDAO) ctx
			// .getBean("TableJoinKrowdDAO");

		}
		return instance;
	}

	
	/**
	 * Constructor
	 */
	public OnlineUserManager() {
		super();
		users = new TreeMap();
	}

	/**
	 * add a user to online user queue.
	 * 
	 * @param uid
	 */
	public void addUser(long uid) {
		synchronized (users) {
			users.put(uid, new Date());
			log.info("added #" + uid + ", now size=" + users.size());
			log.info("users will be notified");
			users.notify();
		}
	}

	/**
	 * remove a user from online user queue.
	 * 
	 * @param uid
	 */
	public void removeUser(long uid) {
		synchronized (users) {
			try {
				users.remove(uid);
				log.info("removed #" + uid + ", size=" + users.size());
			} catch (Exception e) {
				log.info("can't remove it, size=" + users.size());
			}
		}
	}

	/**
	 * refresh the lastRefreshDate of the uid
	 * 
	 * @param uid
	 */
	public void refreshUser(long uid) {
		synchronized (users) {
			users.put(uid, new Date());
			log.info("refreshed #" + uid + ", size=" + users.size());
		}
	}

	/**
	 * count the online users.
	 * @return size of users
	 */
	public int onlineCount() {
		return users.size();
		
	}

	/**
	 * main loop
	 */
	public void run() {
		
		while (running) {
			waitWhenEmpty();

			scanInactiveUser();
			try {
				// log.info("#users:" + users);
				Thread.sleep(3000);
			} catch (Exception e) {
				log.info("Exeption sending message", e);
			}
		}
		users = null;
	}

	private void waitWhenEmpty() {
		if (users.size() == 0) {
			try {
				synchronized (users) {
					log.info("users is empty, thread will wait");
					users.wait();
				}
			} catch (InterruptedException e) {
				// Ignore
			}
		}
	}

	private void scanInactiveUser() {
		synchronized (users) {
			Calendar calendar = Calendar.getInstance();
			Date now = new Date();
			long uid = -1;
			Iterator iter = users.entrySet().iterator();
			while (iter.hasNext()) {
				Map.Entry e = (Map.Entry<Long, Date>) iter.next();
				uid = (Long) e.getKey();
				calendar.setTime((Date) e.getValue());
				calendar.add(Calendar.MINUTE, HEARTBEAT_TIMEOUT);

				if (now.after(calendar.getTime())) {
					this.removeUser(uid);
				}

			}
		}
	}
}
