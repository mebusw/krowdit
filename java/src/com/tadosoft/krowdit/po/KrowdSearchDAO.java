package com.tadosoft.krowdit.po;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * This DAO is for joint search for krowds and locations
 * 
 * @author Jacky
 * 
 */
public class KrowdSearchDAO extends HibernateDaoSupport {

	private static final Logger log = LoggerFactory
			.getLogger(KrowdSearchDAO.class);

	/** 0.01 degree ~= 1km */
	private static final double DEGREE_RANGE = 0.1;

	/** 6 hours */
	private static final int HOUR_RANGE = 6;

	/** 25 records per request */
	private static final int PAGE_SIZE = 25;

	public static KrowdSearchDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (KrowdSearchDAO) ctx.getBean("KrowdSearchDAO");
	}

	protected void initDao() {
		// do nothing
	}

	/**
	 * Find krowd whose startTime is within HOUR_RANGE of given time, and whose
	 * location is within DEGREE_RANGE of given GPS coordinates
	 * 
	 * @param when
	 *            the given time
	 * @param lat
	 *            latitude of given GPS coodinates
	 * @param lng
	 *            longitude of given GPS coodinates
	 * @param offset
	 *            the offset for page spliting
	 * 
	 * @return found records
	 */
	public List findWithinTimeAndDistance(Date when, float lat, float lng,
			final int offset) {
		log.debug("finding Krowds within time and distance: " + when + ", "
				+ lat + ", " + lng);
		try {
			/**
			 * the condition of the sub clause should contain e.g
			 * "latitude<?+DEGREE_RANGE" to benefit from table index
			 */
			Calendar cal = Calendar.getInstance();
			cal.setTime(when);
			cal.add(Calendar.HOUR_OF_DAY, HOUR_RANGE);
			final Date upperWhen = cal.getTime();
			cal.setTime(when);
			cal.add(Calendar.HOUR_OF_DAY, -HOUR_RANGE);
			final Date lowerWhen = cal.getTime();

			String queryString1 = "select locationId from TableLocation "
					+ "where latitude<?+? AND latitude>?-? AND longitude<?+? AND longitude>?-? "
					+ "AND power(latitude-?, 2)+power(longitude-?, 2) < power(?,2) "
					+ "ORDER BY power(latitude-?, 2)+power(longitude-?, 2) ";

			final List<Long> locations = getHibernateTemplate().find(
					queryString1, lat, DEGREE_RANGE, lat, DEGREE_RANGE, lng,
					DEGREE_RANGE, lng, DEGREE_RANGE, lat, lng, DEGREE_RANGE,
					lat, lng);
			log.info("size of locations nearby=" + locations.size());

			if (locations.size() > 0)
				return getHibernateTemplate().executeFind(
						new HibernateCallback() {
							public Object doInHibernate(Session s)
									throws HibernateException, SQLException {
								Query query = s
										.createQuery("from TableKrowd k, TableLocation l where k.locationId=l.locationId" +
												" AND k.startTime>? AND k.startTime<? "
												+ "AND k.locationId in (:loc) ORDER BY field(k.locationId, :loc)");
								query.setParameter(0, lowerWhen);
								query.setParameter(1, upperWhen);

								/**
								 * TODO be aware that size of parameter list
								 * should not larger than 2000 !!!
								 */
								query.setParameterList("loc", locations);

								query.setFirstResult(offset);
								query.setMaxResults(PAGE_SIZE);
								List list = query.list();
								return list;
							}

						});
			else
				//TODO should enlarge location searching range
				return new ArrayList();
		} catch (RuntimeException re) {
			log.error("find within time and distance failed", re);
			throw re;
		}
	}
}
