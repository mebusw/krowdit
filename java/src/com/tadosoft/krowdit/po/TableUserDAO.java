package com.tadosoft.krowdit.po;

import java.util.List;
import org.hibernate.LockMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * A data access object (DAO) providing persistence and search support for
 * TableUser entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.tadosoft.krowdit.po.TableUser
 * @author MyEclipse Persistence Tools
 */

public class TableUserDAO extends HibernateDaoSupport {
	private static final Logger log = LoggerFactory
			.getLogger(TableUserDAO.class);
	// property constants
	public static final String USER_NAME = "userName";
	public static final String PWD = "pwd";
	public static final String EMAIL = "email";
	public static final String UTYPE_ID = "utypeId";

	protected void initDao() {
		// do nothing
	}

	public void save(TableUser transientInstance) {
		log.debug("saving TableUser instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(TableUser persistentInstance) {
		log.debug("deleting TableUser instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public TableUser findById(Long id) {
		log.debug("getting TableUser instance with id: " + id);
		try {
			TableUser instance = (TableUser) getHibernateTemplate().get(
					"com.tadosoft.krowdit.po.TableUser", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(TableUser instance) {
		log.debug("finding TableUser instance by example");
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
		log.debug("finding TableUser instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from TableUser as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByUserName(Object userName) {
		return findByProperty(USER_NAME, userName);
	}

	public List findByPwd(Object pwd) {
		return findByProperty(PWD, pwd);
	}

	public List findByEmail(Object email) {
		return findByProperty(EMAIL, email);
	}

	public List findByUtypeId(Object utypeId) {
		return findByProperty(UTYPE_ID, utypeId);
	}

	public List findAll() {
		log.debug("finding all TableUser instances");
		try {
			String queryString = "from TableUser";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public TableUser merge(TableUser detachedInstance) {
		log.debug("merging TableUser instance");
		try {
			TableUser result = (TableUser) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(TableUser instance) {
		log.debug("attaching dirty TableUser instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(TableUser instance) {
		log.debug("attaching clean TableUser instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static TableUserDAO getFromApplicationContext(ApplicationContext ctx) {
		return (TableUserDAO) ctx.getBean("TableUserDAO");
	}

	/**
	 * 
	 * @param userName
	 * @param pwd
	 * @return
	 */
	public List findByUserNameAndPwd(Object userName, Object pwd) {
		log.debug("finding TableUser instance with user name & pwd: "
				+ userName + ", pwd: " + pwd);
		try {
			String queryString = "from TableUser where userName=? and pwd=?";
			return getHibernateTemplate().find(queryString, userName, pwd);
		} catch (RuntimeException re) {
			log.error("find by  name & pwd failed", re);
			throw re;
		}
	}

	/**
	 * 
	 * @param userName
	 * @param pwd
	 * @param userType
	 * @return
	 */
	public List findByUserNameAndPwdAndUserType(Object userName, Object pwd,
			Object userType) {
		log.debug("finding TableUser instance with user name: " + userName
				+ ", pwd: " + pwd + ", userType: " + userType);
		try {
			String queryString = "from TableUser where userName=? and pwd=? and UTypeId=?";
			return getHibernateTemplate().find(queryString, userName, pwd,
					userType);
		} catch (RuntimeException re) {
			log.error("find by  name & pwd & userType failed", re);
			throw re;
		}
	}
}