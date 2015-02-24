package com.tadosoft.krowdit.po;

import java.util.List;
import org.hibernate.LockMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 	* A data access object (DAO) providing persistence and search support for TableLocationType entities.
 			* Transaction control of the save(), update() and delete() operations 
		can directly support Spring container-managed transactions or they can be augmented	to handle user-managed Spring transactions. 
		Each of these methods provides additional information for how to configure it for the desired type of transaction control. 	
	 * @see com.tadosoft.krowdit.po.TableLocationType
  * @author MyEclipse Persistence Tools 
 */

public class TableLocationTypeDAO extends HibernateDaoSupport  {
	     private static final Logger log = LoggerFactory.getLogger(TableLocationTypeDAO.class);
		//property constants
	public static final String LOCATION_TYPE_NAME = "locationTypeName";



	protected void initDao() {
		//do nothing
	}
    
    public void save(TableLocationType transientInstance) {
        log.debug("saving TableLocationType instance");
        try {
            getHibernateTemplate().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }
    
	public void delete(TableLocationType persistentInstance) {
        log.debug("deleting TableLocationType instance");
        try {
            getHibernateTemplate().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public TableLocationType findById( Long id) {
        log.debug("getting TableLocationType instance with id: " + id);
        try {
            TableLocationType instance = (TableLocationType) getHibernateTemplate()
                    .get("com.tadosoft.krowdit.po.TableLocationType", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
    
    
    public List findByExample(TableLocationType instance) {
        log.debug("finding TableLocationType instance by example");
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
      log.debug("finding TableLocationType instance with property: " + propertyName
            + ", value: " + value);
      try {
         String queryString = "from TableLocationType as model where model." 
         						+ propertyName + "= ?";
		 return getHibernateTemplate().find(queryString, value);
      } catch (RuntimeException re) {
         log.error("find by property name failed", re);
         throw re;
      }
	}

	public List findByLocationTypeName(Object locationTypeName
	) {
		return findByProperty(LOCATION_TYPE_NAME, locationTypeName
		);
	}
	

	public List findAll() {
		log.debug("finding all TableLocationType instances");
		try {
			String queryString = "from TableLocationType";
		 	return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
    public TableLocationType merge(TableLocationType detachedInstance) {
        log.debug("merging TableLocationType instance");
        try {
            TableLocationType result = (TableLocationType) getHibernateTemplate()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(TableLocationType instance) {
        log.debug("attaching dirty TableLocationType instance");
        try {
            getHibernateTemplate().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void attachClean(TableLocationType instance) {
        log.debug("attaching clean TableLocationType instance");
        try {
            getHibernateTemplate().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

	public static TableLocationTypeDAO getFromApplicationContext(ApplicationContext ctx) {
    	return (TableLocationTypeDAO) ctx.getBean("TableLocationTypeDAO");
	}
}