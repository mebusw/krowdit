package com.tadosoft.krowdit.po;

import java.util.List;
import org.hibernate.LockMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 	* A data access object (DAO) providing persistence and search support for TableRole entities.
 			* Transaction control of the save(), update() and delete() operations 
		can directly support Spring container-managed transactions or they can be augmented	to handle user-managed Spring transactions. 
		Each of these methods provides additional information for how to configure it for the desired type of transaction control. 	
	 * @see com.tadosoft.krowdit.po.TableRole
  * @author MyEclipse Persistence Tools 
 */

public class TableRoleDAO extends HibernateDaoSupport  {
	     private static final Logger log = LoggerFactory.getLogger(TableRoleDAO.class);
		//property constants
	public static final String ROLE_NAME = "roleName";



	protected void initDao() {
		//do nothing
	}
    
    public void save(TableRole transientInstance) {
        log.debug("saving TableRole instance");
        try {
            getHibernateTemplate().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }
    
	public void delete(TableRole persistentInstance) {
        log.debug("deleting TableRole instance");
        try {
            getHibernateTemplate().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public TableRole findById( Long id) {
        log.debug("getting TableRole instance with id: " + id);
        try {
            TableRole instance = (TableRole) getHibernateTemplate()
                    .get("com.tadosoft.krowdit.po.TableRole", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
    
    
    public List findByExample(TableRole instance) {
        log.debug("finding TableRole instance by example");
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
      log.debug("finding TableRole instance with property: " + propertyName
            + ", value: " + value);
      try {
         String queryString = "from TableRole as model where model." 
         						+ propertyName + "= ?";
		 return getHibernateTemplate().find(queryString, value);
      } catch (RuntimeException re) {
         log.error("find by property name failed", re);
         throw re;
      }
	}

	public List findByRoleName(Object roleName
	) {
		return findByProperty(ROLE_NAME, roleName
		);
	}
	

	public List findAll() {
		log.debug("finding all TableRole instances");
		try {
			String queryString = "from TableRole";
		 	return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
    public TableRole merge(TableRole detachedInstance) {
        log.debug("merging TableRole instance");
        try {
            TableRole result = (TableRole) getHibernateTemplate()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(TableRole instance) {
        log.debug("attaching dirty TableRole instance");
        try {
            getHibernateTemplate().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void attachClean(TableRole instance) {
        log.debug("attaching clean TableRole instance");
        try {
            getHibernateTemplate().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

	public static TableRoleDAO getFromApplicationContext(ApplicationContext ctx) {
    	return (TableRoleDAO) ctx.getBean("TableRoleDAO");
	}
}