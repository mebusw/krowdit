package com.tadosoft.krowdit.po;

import java.util.Date;
import java.util.List;
import org.hibernate.LockMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 	* A data access object (DAO) providing persistence and search support for TableAchievement entities.
 			* Transaction control of the save(), update() and delete() operations 
		can directly support Spring container-managed transactions or they can be augmented	to handle user-managed Spring transactions. 
		Each of these methods provides additional information for how to configure it for the desired type of transaction control. 	
	 * @see com.tadosoft.krowdit.po.TableAchievement
  * @author MyEclipse Persistence Tools 
 */

public class TableAchievementDAO extends HibernateDaoSupport  {
	     private static final Logger log = LoggerFactory.getLogger(TableAchievementDAO.class);
		//property constants
	public static final String ACHIEVEMENT_NAME = "achievementName";
	public static final String ACHIEVEMENT_DETAIL = "achievementDetail";
	public static final String CREATOR_ID = "creatorId";



	protected void initDao() {
		//do nothing
	}
    
    public void save(TableAchievement transientInstance) {
        log.debug("saving TableAchievement instance");
        try {
            getHibernateTemplate().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }
    
	public void delete(TableAchievement persistentInstance) {
        log.debug("deleting TableAchievement instance");
        try {
            getHibernateTemplate().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public TableAchievement findById( Long id) {
        log.debug("getting TableAchievement instance with id: " + id);
        try {
            TableAchievement instance = (TableAchievement) getHibernateTemplate()
                    .get("com.tadosoft.krowdit.po.TableAchievement", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
    
    
    public List findByExample(TableAchievement instance) {
        log.debug("finding TableAchievement instance by example");
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
      log.debug("finding TableAchievement instance with property: " + propertyName
            + ", value: " + value);
      try {
         String queryString = "from TableAchievement as model where model." 
         						+ propertyName + "= ?";
		 return getHibernateTemplate().find(queryString, value);
      } catch (RuntimeException re) {
         log.error("find by property name failed", re);
         throw re;
      }
	}

	public List findByAchievementName(Object achievementName
	) {
		return findByProperty(ACHIEVEMENT_NAME, achievementName
		);
	}
	
	public List findByAchievementDetail(Object achievementDetail
	) {
		return findByProperty(ACHIEVEMENT_DETAIL, achievementDetail
		);
	}
	
	public List findByCreatorId(Object creatorId
	) {
		return findByProperty(CREATOR_ID, creatorId
		);
	}
	

	public List findAll() {
		log.debug("finding all TableAchievement instances");
		try {
			String queryString = "from TableAchievement";
		 	return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
    public TableAchievement merge(TableAchievement detachedInstance) {
        log.debug("merging TableAchievement instance");
        try {
            TableAchievement result = (TableAchievement) getHibernateTemplate()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(TableAchievement instance) {
        log.debug("attaching dirty TableAchievement instance");
        try {
            getHibernateTemplate().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void attachClean(TableAchievement instance) {
        log.debug("attaching clean TableAchievement instance");
        try {
            getHibernateTemplate().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

	public static TableAchievementDAO getFromApplicationContext(ApplicationContext ctx) {
    	return (TableAchievementDAO) ctx.getBean("TableAchievementDAO");
	}
}