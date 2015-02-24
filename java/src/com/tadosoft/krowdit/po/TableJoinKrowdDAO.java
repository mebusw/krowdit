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
 * TableJoinKrowd entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.tadosoft.krowdit.po.TableJoinKrowd
 * @author MyEclipse Persistence Tools
 */

public class TableJoinKrowdDAO extends HibernateDaoSupport {
	private static final Logger log = LoggerFactory
			.getLogger(TableJoinKrowdDAO.class);
	// property constants
	public static final String KROWD_ID = "krowdId";
	public static final String UID = "uid";
	public static final String SUPPORTING_TEAM_ID = "supportingTeamId";

	protected void initDao() {
		// do nothing
	}

	public void save(TableJoinKrowd transientInstance) {
		log.debug("saving TableJoinKrowd instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(TableJoinKrowd persistentInstance) {
		log.debug("deleting TableJoinKrowd instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public TableJoinKrowd findById(Long id) {
		log.debug("getting TableJoinKrowd instance with id: " + id);
		try {
			TableJoinKrowd instance = (TableJoinKrowd) getHibernateTemplate()
					.get("com.tadosoft.krowdit.po.TableJoinKrowd", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(TableJoinKrowd instance) {
		log.debug("finding TableJoinKrowd instance by example");
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
		log.debug("finding TableJoinKrowd instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from TableJoinKrowd as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByKrowdId(Object krowdId) {
		return findByProperty(KROWD_ID, krowdId);
	}

	public List findByUid(Object uid) {
		return findByProperty(UID, uid);
	}

	public List findBySupportingTeamId(Object supportingTeamId) {
		return findByProperty(SUPPORTING_TEAM_ID, supportingTeamId);
	}

	public List findAll() {
		log.debug("finding all TableJoinKrowd instances");
		try {
			String queryString = "from TableJoinKrowd";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public TableJoinKrowd merge(TableJoinKrowd detachedInstance) {
		log.debug("merging TableJoinKrowd instance");
		try {
			TableJoinKrowd result = (TableJoinKrowd) getHibernateTemplate()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(TableJoinKrowd instance) {
		log.debug("attaching dirty TableJoinKrowd instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(TableJoinKrowd instance) {
		log.debug("attaching clean TableJoinKrowd instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static TableJoinKrowdDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (TableJoinKrowdDAO) ctx.getBean("TableJoinKrowdDAO");
	}

	/**
	 * Delete all records which match the given uid.
	 * 
	 * @author jacky
	 * @param uid
	 */
	public void deleteByUid(Object uid) {
		log.debug("deleting TableJoinKrowd by uid");
		try {
			List result = getHibernateTemplate().find(
					"from TableJoinKrowd where uid=?", new Object[] { uid });
			getHibernateTemplate().deleteAll(result);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	/**
	 * count by krowdId and teamId
	 * 
	 * @param kid
	 *            krowdId
	 * @param tid
	 *            teamId
	 * @return amount of records
	 */
	public long countByKrowdIdAndSupportingTeamId(long kid, long tid) {
		log.debug("count TableJoinKrowd by krowdId & teamId");
		Long count = 0L;
		try {
			count = (Long) getHibernateTemplate()
					.find(
							"select count(joinId) from TableJoinKrowd where krowdId=? and supportingTeamId=?",
							new Object[] { kid, tid }).get(0);
			log.debug("count successful");
		} catch (RuntimeException re) {
			log.error("count failed", re);
			throw re;
		}
		return count;

	}
}