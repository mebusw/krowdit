package com.tadosoft.krowdit.po;

import java.util.Date;
import java.util.List;
import org.hibernate.LockMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * A data access object (DAO) providing persistence and search support for
 * TableLocation entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.tadosoft.krowdit.po.TableLocation
 * @author MyEclipse Persistence Tools
 */

public class TableLocationDAO extends HibernateDaoSupport {
	private static final Logger log = LoggerFactory
			.getLogger(TableLocationDAO.class);
	// property constants
	public static final String LOCATION_NAME = "locationName";
	public static final String LOCATION_TYPE_ID = "locationTypeId";
	public static final String LATITUDE = "latitude";
	public static final String LONGITUDE = "longitude";
	public static final String CREATOR_ID = "creatorId";

	protected void initDao() {
		// do nothing
	}

	public void save(TableLocation transientInstance) {
		log.debug("saving TableLocation instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(TableLocation persistentInstance) {
		log.debug("deleting TableLocation instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public TableLocation findById(Long id) {
		log.debug("getting TableLocation instance with id: " + id);
		try {
			TableLocation instance = (TableLocation) getHibernateTemplate()
					.get("com.tadosoft.krowdit.po.TableLocation", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(TableLocation instance) {
		log.debug("finding TableLocation instance by example");
		try {
			List results = getHibernateTemplate().findByExample(instance);
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding TableLocation instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from TableLocation as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByLocationName(Object locationName) {
		return findByProperty(LOCATION_NAME, locationName);
	}

	public List findByLocationTypeId(Object locationTypeId) {
		return findByProperty(LOCATION_TYPE_ID, locationTypeId);
	}

	public List findByLatitude(Object latitude) {
		return findByProperty(LATITUDE, latitude);
	}

	public List findByLongitude(Object longitude) {
		return findByProperty(LONGITUDE, longitude);
	}

	public List findByCreatorId(Object creatorId) {
		return findByProperty(CREATOR_ID, creatorId);
	}

	public List findAll() {
		log.debug("finding all TableLocation instances");
		try {
			String queryString = "from TableLocation";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public TableLocation merge(TableLocation detachedInstance) {
		log.debug("merging TableLocation instance");
		try {
			TableLocation result = (TableLocation) getHibernateTemplate()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(TableLocation instance) {
		log.debug("attaching dirty TableLocation instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(TableLocation instance) {
		log.debug("attaching clean TableLocation instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static TableLocationDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (TableLocationDAO) ctx.getBean("TableLocationDAO");
	}
}