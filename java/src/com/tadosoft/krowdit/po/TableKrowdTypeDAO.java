package com.tadosoft.krowdit.po;

import java.util.List;
import org.hibernate.LockMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * A data access object (DAO) providing persistence and search support for
 * TableKrowdType entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.tadosoft.krowdit.po.TableKrowdType
 * @author MyEclipse Persistence Tools
 */

public class TableKrowdTypeDAO extends HibernateDaoSupport {
	private static final Logger log = LoggerFactory
			.getLogger(TableKrowdTypeDAO.class);
	// property constants
	public static final String KROWD_TYPE_NAME = "krowdTypeName";
	public static final String PUBLIC_ = "public_";

	protected void initDao() {
		// do nothing
	}

	public void save(TableKrowdType transientInstance) {
		log.debug("saving TableKrowdType instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(TableKrowdType persistentInstance) {
		log.debug("deleting TableKrowdType instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public TableKrowdType findById(Long id) {
		log.debug("getting TableKrowdType instance with id: " + id);
		try {
			TableKrowdType instance = (TableKrowdType) getHibernateTemplate()
					.get("com.tadosoft.krowdit.po.TableKrowdType", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(TableKrowdType instance) {
		log.debug("finding TableKrowdType instance by example");
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
		log.debug("finding TableKrowdType instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from TableKrowdType as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByKrowdTypeName(Object krowdTypeName) {
		return findByProperty(KROWD_TYPE_NAME, krowdTypeName);
	}

	public List findByPublic_(Object public_) {
		return findByProperty(PUBLIC_, public_);
	}

	public List findAll() {
		log.debug("finding all TableKrowdType instances");
		try {
			String queryString = "from TableKrowdType";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public TableKrowdType merge(TableKrowdType detachedInstance) {
		log.debug("merging TableKrowdType instance");
		try {
			TableKrowdType result = (TableKrowdType) getHibernateTemplate()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(TableKrowdType instance) {
		log.debug("attaching dirty TableKrowdType instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(TableKrowdType instance) {
		log.debug("attaching clean TableKrowdType instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static TableKrowdTypeDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (TableKrowdTypeDAO) ctx.getBean("TableKrowdTypeDAO");
	}
}