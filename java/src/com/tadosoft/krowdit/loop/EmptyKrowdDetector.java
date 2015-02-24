package com.tadosoft.krowdit.loop;

import java.util.*;

import javax.servlet.ServletContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.tadosoft.krowdit.po.TableJoinKrowdDAO;
import com.tadosoft.krowdit.po.TableKrowd;
import com.tadosoft.krowdit.po.TableKrowdDAO;

/**
 * This class is triggered by timer to detect whether krowds need to be closed
 * and close them. Firstly, scan all the krowds which still not closed. Ignore
 * the krowds just started within 8 hrs and those had been scanned within 3 hrs.
 * Secondly, check if krowd has any user in it, if none, close it. whatever,
 * next scan will be 3 hrs later.
 * 
 * @author TadoSoft
 * 
 */
public class EmptyKrowdDetector extends TimerTask {
	private static final Logger log = LoggerFactory
			.getLogger(EmptyKrowdDetector.class);
	static EmptyKrowdDetector emptyKrowdDetector;

	public List<TableKrowd> openKrowds; // store all the open krowds
	public TableKrowdDAO krowdDao;
	public TableJoinKrowdDAO joinKrowdDao;

	public synchronized static EmptyKrowdDetector getInstance(
			ServletContext servletContext) {
		if (emptyKrowdDetector == null) {
			emptyKrowdDetector = new EmptyKrowdDetector();
			ApplicationContext ctx = WebApplicationContextUtils
					.getWebApplicationContext(servletContext);
			emptyKrowdDetector.krowdDao = (TableKrowdDAO) ctx
					.getBean("TableKrowdDAO");
			emptyKrowdDetector.joinKrowdDao = (TableJoinKrowdDAO) ctx
					.getBean("TableJoinKrowdDAO");
		}
		return emptyKrowdDetector;
	}

	public void run() {
		Date now = new Date();
		Date startTimePlus8;
		Date lastScanTimePlus3;

		Calendar calendar = Calendar.getInstance();

		// 1. scan all the krowds which still not closed
		openKrowds = krowdDao.findByCloseTimeAfter(now);
		Iterator<TableKrowd> iterator = openKrowds.iterator();

		//log.info("I'm detecting >> " + openKrowds);
		while (iterator.hasNext()) {
			TableKrowd krowd = iterator.next();

			// 2. Ignore the krowds just started within 8 hrs and those had been
			// scanned within 3 hrs
			//TODO it's better to filter out those in DAO, not here
			calendar.setTime(krowd.getStartTime());
			calendar.add(Calendar.HOUR_OF_DAY, 8);
			startTimePlus8 = calendar.getTime();

			calendar.setTime(krowd.getLastScanTime());
			calendar.add(Calendar.HOUR_OF_DAY, 3);
			lastScanTimePlus3 = calendar.getTime();

			if (now.before(startTimePlus8) || now.before(lastScanTimePlus3))
				continue;

			// 3.check if they have any user left

			long remainUserCount = joinKrowdDao.findByKrowdId(
					krowd.getKrowdId()).size();

			if (remainUserCount <= 0) {
				// 4. close the empty krowd
				log.info("krowd will be closed. #" + krowd.getKrowdId());
				krowd.setCloseTime(now);

			}
			// 5. next scan will be 3 hrs later
			log.info("update lastScanTime for krowd #" + krowd.getKrowdId());
			krowd.setLastScanTime(now);

			krowdDao.attachDirty(krowd);

		}

	}
}
