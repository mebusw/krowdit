package com.tadosoft.krowdit.po;

import java.util.Date;
import java.util.List;
import org.hibernate.LockMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 	* A data access object (DAO) providing persistence and search support for TableAdministrator entities.
 			* Transaction control of the save(), update() and delete() operations 
		can directly support Spring container-managed transactions or they can be augmented	to handle user-managed Spring transactions. 
		Each of these methods provides additional information for how to configure it for the desired type of transaction control. 	
	 * @see com.tadosoft.krowdit.po.TableAdministrator
  * @author MyEclipse Persistence Tools 
 */

public class TableAdministratorDAO extends HibernateDaoSupport  {
	     private static final Logger log = LoggerFactory.getLogger(TableAdministratorDAO.class);
		//property constants
	public static final String USER_NAME = "userName";
	public static final String PWD = "pwd";
	public static final String EMAIL = "email";
	public static final String ROLE_ID = "roleId";



	protected void initDao() {
		//do nothing
	}
    
    public void save(TableAdministrator transientInstance) {
        log.debug("saving TableAdministrator instance");
        try {
            getHibernateTemplate().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }
    
	public void delete(TableAdministrator persistentInstance) {
        log.debug("deleting TableAdministrator instance");
        try {
            getHibernateTemplate().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public TableAdministrator findById( Long id) {
        log.debug("getting TableAdministrator instance with id: " + id);
        try {
            TableAdministrator instance = (TableAdministrator) getHibernateTemplate()
                    .get("com.tadosoft.krowdit.po.TableAdministrator", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
    
    
    public List findByExample(TableAdministrator instance) {
        log.debug("finding TableAdministrator instance by example");
        try {
            List results = getHibernateTemplate().findByExample(instance);
            log.debug("find by example successful, result size: " + results.size());
            return results;
        } catch (RuntimeException re) {
            log.error("find by example failed", re);
            throw re;
        }
    }    
    
    public List findByProperty(String propertyName, Object value) {
      log.debug("finding TableAdministrator instance with property: " + propertyName
            + ", value: " + value);
      try {
         String queryString = "from TableAdministrator as model where model." 
         						+ propertyName + "= ?";
		 return getHibernateTemplate().find(queryString, value);
      } catch (RuntimeException re) {
         log.error("find by property name failed", re);
         throw re;
      }
	}

	public List findByUserName(Object userName
	) {
		return findByProperty(USER_NAME, userName
		);
	}
	
	public List findByPwd(Object pwd
	) {
		return findByProperty(PWD, pwd
		);
	}
	
	public List findByEmail(Object email
	) {
		return findByProperty(EMAIL, email
		);
	}
	
	public List findByRoleId(Object roleId
	) {
		return findByProperty(ROLE_ID, roleId
		);
	}
	

	public List findAll() {
		log.debug("finding all TableAdministrator instances");
		try {
			String queryString = "from TableAdministrator";
		 	return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
    public TableAdministrator merge(TableAdministrator detachedInstance) {
        log.debug("merging TableAdministrator instance");
        try {
            TableAdministrator result = (TableAdministrator) getHibernateTemplate()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(TableAdministrator instance) {
        log.debug("attaching dirty TableAdministrator instance");
        try {
            getHibernateTemplate().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void attachClean(TableAdministrator instance) {
        log.debug("attaching clean TableAdministrator instance");
        try {
            getHibernateTemplate().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

	public static TableAdministratorDAO getFromApplicationContext(ApplicationContext ctx) {
    	return (TableAdministratorDAO) ctx.getBean("TableAdministratorDAO");
	}
}