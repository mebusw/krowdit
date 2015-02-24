package com.tadosoft.krowdit.po;

import java.util.Date;
import java.util.List;
import org.hibernate.LockMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 	* A data access object (DAO) providing persistence and search support for TableUserAchievement entities.
 			* Transaction control of the save(), update() and delete() operations 
		can directly support Spring container-managed transactions or they can be augmented	to handle user-managed Spring transactions. 
		Each of these methods provides additional information for how to configure it for the desired type of transaction control. 	
	 * @see com.tadosoft.krowdit.po.TableUserAchievement
  * @author MyEclipse Persistence Tools 
 */

public class TableUserAchievementDAO extends HibernateDaoSupport  {
	     private static final Logger log = LoggerFactory.getLogger(TableUserAchievementDAO.class);
		//property constants
	public static final String ACHIEVEMENT_ID = "achievementId";
	public static final String UID = "uid";



	protected void initDao() {
		//do nothing
	}
    
    public void save(TableUserAchievement transientInstance) {
        log.debug("saving TableUserAchievement instance");
        try {
            getHibernateTemplate().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }
    
	public void delete(TableUserAchievement persistentInstance) {
        log.debug("deleting TableUserAchievement instance");
        try {
            getHibernateTemplate().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public TableUserAchievement findById( Long id) {
        log.debug("getting TableUserAchievement instance with id: " + id);
        try {
            TableUserAchievement instance = (TableUserAchievement) getHibernateTemplate()
                    .get("com.tadosoft.krowdit.po.TableUserAchievement", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
    
    
    public List findByExample(TableUserAchievement instance) {
        log.debug("finding TableUserAchievement instance by example");
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
      log.debug("finding TableUserAchievement instance with property: " + propertyName
            + ", value: " + value);
      try {
         String queryString = "from TableUserAchievement as model where model." 
         						+ propertyName + "= ?";
		 return getHibernateTemplate().find(queryString, value);
      } catch (RuntimeException re) {
         log.error("find by property name failed", re);
         throw re;
      }
	}

	public List findByAchievementId(Object achievementId
	) {
		return findByProperty(ACHIEVEMENT_ID, achievementId
		);
	}
	
	public List findByUid(Object uid
	) {
		return findByProperty(UID, uid
		);
	}
	

	public List findAll() {
		log.debug("finding all TableUserAchievement instances");
		try {
			String queryString = "from TableUserAchievement";
		 	return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
    public TableUserAchievement merge(TableUserAchievement detachedInstance) {
        log.debug("merging TableUserAchievement instance");
        try {
            TableUserAchievement result = (TableUserAchievement) getHibernateTemplate()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(TableUserAchievement instance) {
        log.debug("attaching dirty TableUserAchievement instance");
        try {
            getHibernateTemplate().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void attachClean(TableUserAchievement instance) {
        log.debug("attaching clean TableUserAchievement instance");
        try {
            getHibernateTemplate().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

	public static TableUserAchievementDAO getFromApplicationContext(ApplicationContext ctx) {
    	return (TableUserAchievementDAO) ctx.getBean("TableUserAchievementDAO");
	}
}