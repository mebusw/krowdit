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
 * TableKrowd entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.tadosoft.krowdit.po.TableKrowd
 * @author MyEclipse Persistence Tools
 */

public class TableKrowdDAO extends HibernateDaoSupport {
	private static final Logger log = LoggerFactory
			.getLogger(TableKrowdDAO.class);
	// property constants
	public static final String KROWD_NAME = "krowdName";
	public static final String KROWD_TYPE_ID = "krowdTypeId";
	public static final String HOME = "home";
	public static final String AWAY = "away";
	public static final String LOCATION_ID = "locationId";
	public static final String CREATOR_ID = "creatorId";

	protected void initDao() {
		// do nothing
	}

	public void save(TableKrowd transientInstance) {
		log.debug("saving TableKrowd instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(TableKrowd persistentInstance) {
		log.debug("deleting TableKrowd instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public TableKrowd findById(Long id) {
		log.debug("getting TableKrowd instance with id: " + id);
		try {
			TableKrowd instance = (TableKrowd) getHibernateTemplate().get(
					"com.tadosoft.krowdit.po.TableKrowd", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(TableKrowd instance) {
		log.debug("finding TableKrowd instance by example");
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
		log.debug("finding TableKrowd instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from TableKrowd as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByKrowdName(Object krowdName) {
		return findByProperty(KROWD_NAME, krowdName);
	}

	public List findByKrowdTypeId(Object krowdTypeId) {
		return findByProperty(KROWD_TYPE_ID, krowdTypeId);
	}

	public List findByHome(Object home) {
		return findByProperty(HOME, home);
	}

	public List findByAway(Object away) {
		return findByProperty(AWAY, away);
	}

	public List findByLocationId(Object locationId) {
		return findByProperty(LOCATION_ID, locationId);
	}

	public List findByCreatorId(Object creatorId) {
		return findByProperty(CREATOR_ID, creatorId);
	}

	public List findAll() {
		log.debug("finding all TableKrowd instances");
		try {
			String queryString = "from TableKrowd";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public TableKrowd merge(TableKrowd detachedInstance) {
		log.debug("merging TableKrowd instance");
		try {
			TableKrowd result = (TableKrowd) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(TableKrowd instance) {
		log.debug("attaching dirty TableKrowd instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(TableKrowd instance) {
		log.debug("attaching clean TableKrowd instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static TableKrowdDAO getFromApplicationContext(ApplicationContext ctx) {
		return (TableKrowdDAO) ctx.getBean("TableKrowdDAO");
	}
	
	/**
	 * find by closeTime that is after given time.
	 * @param when
	 */
	public List findByCloseTimeAfter(Date when) {
		log.debug("finding TableKrowd instance by closeTime after given time");
		try {
			String queryString = "from TableKrowd as model where model.closeTime > ?";
		return getHibernateTemplate().find(queryString, when);
		} catch (RuntimeException re) {
			log.error("find by closeTime after given time failed", re);
			throw re;
		}
	}
}