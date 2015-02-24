package com.tadosoft.krowdit.po;

import java.util.Date;
import java.util.List;
import org.hibernate.LockMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 	* A data access object (DAO) providing persistence and search support for TableProfile entities.
 			* Transaction control of the save(), update() and delete() operations 
		can directly support Spring container-managed transactions or they can be augmented	to handle user-managed Spring transactions. 
		Each of these methods provides additional information for how to configure it for the desired type of transaction control. 	
	 * @see com.tadosoft.krowdit.po.TableProfile
  * @author MyEclipse Persistence Tools 
 */

public class TableProfileDAO extends HibernateDaoSupport  {
	     private static final Logger log = LoggerFactory.getLogger(TableProfileDAO.class);
		//property constants
	public static final String PIC_ID = "picId";
	public static final String TOTAL_KROWDS_JOINED = "totalKrowdsJoined";
	public static final String KROWDS_OF25MEMBERS = "krowdsOf25members";
	public static final String NUMBER_OF_TEXT_POSTS = "numberOfTextPosts";
	public static final String NUMBER_OF_PIC_POSTS = "numberOfPicPosts";
	public static final String LARGEST_KROWD_ID = "largestKrowdId";
	public static final String FAVORITE_KROWD_TYPE_ID = "favoriteKrowdTypeId";
	public static final String FAVORITE_LOCATION_ID = "favoriteLocationId";



	protected void initDao() {
		//do nothing
	}
    
    public void save(TableProfile transientInstance) {
        log.debug("saving TableProfile instance");
        try {
            getHibernateTemplate().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }
    
	public void delete(TableProfile persistentInstance) {
        log.debug("deleting TableProfile instance");
        try {
            getHibernateTemplate().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public TableProfile findById( Long id) {
        log.debug("getting TableProfile instance with id: " + id);
        try {
            TableProfile instance = (TableProfile) getHibernateTemplate()
                    .get("com.tadosoft.krowdit.po.TableProfile", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
    
    
    public List findByExample(TableProfile instance) {
        log.debug("finding TableProfile instance by example");
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
      log.debug("finding TableProfile instance with property: " + propertyName
            + ", value: " + value);
      try {
         String queryString = "from TableProfile as model where model." 
         						+ propertyName + "= ?";
		 return getHibernateTemplate().find(queryString, value);
      } catch (RuntimeException re) {
         log.error("find by property name failed", re);
         throw re;
      }
	}

	public List findByPicId(Object picId
	) {
		return findByProperty(PIC_ID, picId
		);
	}
	
	public List findByTotalKrowdsJoined(Object totalKrowdsJoined
	) {
		return findByProperty(TOTAL_KROWDS_JOINED, totalKrowdsJoined
		);
	}
	
	public List findByKrowdsOf25members(Object krowdsOf25members
	) {
		return findByProperty(KROWDS_OF25MEMBERS, krowdsOf25members
		);
	}
	
	public List findByNumberOfTextPosts(Object numberOfTextPosts
	) {
		return findByProperty(NUMBER_OF_TEXT_POSTS, numberOfTextPosts
		);
	}
	
	public List findByNumberOfPicPosts(Object numberOfPicPosts
	) {
		return findByProperty(NUMBER_OF_PIC_POSTS, numberOfPicPosts
		);
	}
	
	public List findByLargestKrowdId(Object largestKrowdId
	) {
		return findByProperty(LARGEST_KROWD_ID, largestKrowdId
		);
	}
	
	public List findByFavoriteKrowdTypeId(Object favoriteKrowdTypeId
	) {
		return findByProperty(FAVORITE_KROWD_TYPE_ID, favoriteKrowdTypeId
		);
	}
	
	public List findByFavoriteLocationId(Object favoriteLocationId
	) {
		return findByProperty(FAVORITE_LOCATION_ID, favoriteLocationId
		);
	}
	

	public List findAll() {
		log.debug("finding all TableProfile instances");
		try {
			String queryString = "from TableProfile";
		 	return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
    public TableProfile merge(TableProfile detachedInstance) {
        log.debug("merging TableProfile instance");
        try {
            TableProfile result = (TableProfile) getHibernateTemplate()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(TableProfile instance) {
        log.debug("attaching dirty TableProfile instance");
        try {
            getHibernateTemplate().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void attachClean(TableProfile instance) {
        log.debug("attaching clean TableProfile instance");
        try {
            getHibernateTemplate().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

	public static TableProfileDAO getFromApplicationContext(ApplicationContext ctx) {
    	return (TableProfileDAO) ctx.getBean("TableProfileDAO");
	}
}