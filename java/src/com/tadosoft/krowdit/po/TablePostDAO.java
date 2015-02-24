package com.tadosoft.krowdit.po;

import java.util.Date;
import java.util.List;
import org.hibernate.LockMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 	* A data access object (DAO) providing persistence and search support for TablePost entities.
 			* Transaction control of the save(), update() and delete() operations 
		can directly support Spring container-managed transactions or they can be augmented	to handle user-managed Spring transactions. 
		Each of these methods provides additional information for how to configure it for the desired type of transaction control. 	
	 * @see com.tadosoft.krowdit.po.TablePost
  * @author MyEclipse Persistence Tools 
 */

public class TablePostDAO extends HibernateDaoSupport  {
	     private static final Logger log = LoggerFactory.getLogger(TablePostDAO.class);
		//property constants
	public static final String CREATOR_ID = "creatorId";
	public static final String POSITIVE = "positive";
	public static final String NEGATIVE = "negative";
	public static final String CONTENT = "content";
	public static final String PIC = "pic";
	public static final String KROWD_ID = "krowdId";
	public static final String CALL_AN_ACTION = "callAnAction";



	protected void initDao() {
		//do nothing
	}
    
    public void save(TablePost transientInstance) {
        log.debug("saving TablePost instance");
        try {
            getHibernateTemplate().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }
    
	public void delete(TablePost persistentInstance) {
        log.debug("deleting TablePost instance");
        try {
            getHibernateTemplate().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public TablePost findById( Long id) {
        log.debug("getting TablePost instance with id: " + id);
        try {
            TablePost instance = (TablePost) getHibernateTemplate()
                    .get("com.tadosoft.krowdit.po.TablePost", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
    
    
    public List findByExample(TablePost instance) {
        log.debug("finding TablePost instance by example");
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
      log.debug("finding TablePost instance with property: " + propertyName
            + ", value: " + value);
      try {
         String queryString = "from TablePost as model where model." 
         						+ propertyName + "= ?";
		 return getHibernateTemplate().find(queryString, value);
      } catch (RuntimeException re) {
         log.error("find by property name failed", re);
         throw re;
      }
	}

	public List findByCreatorId(Object creatorId
	) {
		return findByProperty(CREATOR_ID, creatorId
		);
	}
	
	public List findByPositive(Object positive
	) {
		return findByProperty(POSITIVE, positive
		);
	}
	
	public List findByNegative(Object negative
	) {
		return findByProperty(NEGATIVE, negative
		);
	}
	
	public List findByContent(Object content
	) {
		return findByProperty(CONTENT, content
		);
	}
	
	public List findByPic(Object pic
	) {
		return findByProperty(PIC, pic
		);
	}
	
	public List findByKrowdId(Object krowdId
	) {
		return findByProperty(KROWD_ID, krowdId
		);
	}
	
	public List findByCallAnAction(Object callAnAction
	) {
		return findByProperty(CALL_AN_ACTION, callAnAction
		);
	}
	

	public List findAll() {
		log.debug("finding all TablePost instances");
		try {
			String queryString = "from TablePost";
		 	return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
    public TablePost merge(TablePost detachedInstance) {
        log.debug("merging TablePost instance");
        try {
            TablePost result = (TablePost) getHibernateTemplate()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(TablePost instance) {
        log.debug("attaching dirty TablePost instance");
        try {
            getHibernateTemplate().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void attachClean(TablePost instance) {
        log.debug("attaching clean TablePost instance");
        try {
            getHibernateTemplate().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

	public static TablePostDAO getFromApplicationContext(ApplicationContext ctx) {
    	return (TablePostDAO) ctx.getBean("TablePostDAO");
	}
}