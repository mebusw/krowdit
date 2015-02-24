package com.tadosoft.krowdit.po;

import java.util.Date;
import java.util.List;
import org.hibernate.LockMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 	* A data access object (DAO) providing persistence and search support for TableBeFans entities.
 			* Transaction control of the save(), update() and delete() operations 
		can directly support Spring container-managed transactions or they can be augmented	to handle user-managed Spring transactions. 
		Each of these methods provides additional information for how to configure it for the desired type of transaction control. 	
	 * @see com.tadosoft.krowdit.po.TableBeFans
  * @author MyEclipse Persistence Tools 
 */

public class TableBeFansDAO extends HibernateDaoSupport  {
	     private static final Logger log = LoggerFactory.getLogger(TableBeFansDAO.class);
		//property constants
	public static final String UID = "uid";
	public static final String FANS_ID = "fansId";



	protected void initDao() {
		//do nothing
	}
    
    public void save(TableBeFans transientInstance) {
        log.debug("saving TableBeFans instance");
        try {
            getHibernateTemplate().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }
    
	public void delete(TableBeFans persistentInstance) {
        log.debug("deleting TableBeFans instance");
        try {
            getHibernateTemplate().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public TableBeFans findById( Long id) {
        log.debug("getting TableBeFans instance with id: " + id);
        try {
            TableBeFans instance = (TableBeFans) getHibernateTemplate()
                    .get("com.tadosoft.krowdit.po.TableBeFans", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
    
    
    public List findByExample(TableBeFans instance) {
        log.debug("finding TableBeFans instance by example");
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
      log.debug("finding TableBeFans instance with property: " + propertyName
            + ", value: " + value);
      try {
         String queryString = "from TableBeFans as model where model." 
         						+ propertyName + "= ?";
		 return getHibernateTemplate().find(queryString, value);
      } catch (RuntimeException re) {
         log.error("find by property name failed", re);
         throw re;
      }
	}

	public List findByUid(Object uid
	) {
		return findByProperty(UID, uid
		);
	}
	
	public List findByFansId(Object fansId
	) {
		return findByProperty(FANS_ID, fansId
		);
	}
	

	public List findAll() {
		log.debug("finding all TableBeFans instances");
		try {
			String queryString = "from TableBeFans";
		 	return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
    public TableBeFans merge(TableBeFans detachedInstance) {
        log.debug("merging TableBeFans instance");
        try {
            TableBeFans result = (TableBeFans) getHibernateTemplate()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(TableBeFans instance) {
        log.debug("attaching dirty TableBeFans instance");
        try {
            getHibernateTemplate().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void attachClean(TableBeFans instance) {
        log.debug("attaching clean TableBeFans instance");
        try {
            getHibernateTemplate().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

	public static TableBeFansDAO getFromApplicationContext(ApplicationContext ctx) {
    	return (TableBeFansDAO) ctx.getBean("TableBeFansDAO");
	}
}