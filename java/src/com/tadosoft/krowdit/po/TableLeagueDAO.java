package com.tadosoft.krowdit.po;

import java.util.List;
import org.hibernate.LockMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 	* A data access object (DAO) providing persistence and search support for TableLeague entities.
 			* Transaction control of the save(), update() and delete() operations 
		can directly support Spring container-managed transactions or they can be augmented	to handle user-managed Spring transactions. 
		Each of these methods provides additional information for how to configure it for the desired type of transaction control. 	
	 * @see com.tadosoft.krowdit.po.TableLeague
  * @author MyEclipse Persistence Tools 
 */

public class TableLeagueDAO extends HibernateDaoSupport  {
	     private static final Logger log = LoggerFactory.getLogger(TableLeagueDAO.class);
		//property constants
	public static final String KROWD_TYPE_ID = "krowdTypeId";
	public static final String TEAM_NAME = "teamName";
	public static final String LOGO = "logo";
	public static final String COLOR1 = "color1";
	public static final String COLOR2 = "color2";
	public static final String COLOR3 = "color3";
	public static final String HOME_LOCATION_ID = "homeLocationId";



	protected void initDao() {
		//do nothing
	}
    
    public void save(TableLeague transientInstance) {
        log.debug("saving TableLeague instance");
        try {
            getHibernateTemplate().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }
    
	public void delete(TableLeague persistentInstance) {
        log.debug("deleting TableLeague instance");
        try {
            getHibernateTemplate().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public TableLeague findById( Long id) {
        log.debug("getting TableLeague instance with id: " + id);
        try {
            TableLeague instance = (TableLeague) getHibernateTemplate()
                    .get("com.tadosoft.krowdit.po.TableLeague", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
    
    
    public List findByExample(TableLeague instance) {
        log.debug("finding TableLeague instance by example");
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
      log.debug("finding TableLeague instance with property: " + propertyName
            + ", value: " + value);
      try {
         String queryString = "from TableLeague as model where model." 
         						+ propertyName + "= ?";
		 return getHibernateTemplate().find(queryString, value);
      } catch (RuntimeException re) {
         log.error("find by property name failed", re);
         throw re;
      }
	}

	public List findByKrowdTypeId(Object krowdTypeId
	) {
		return findByProperty(KROWD_TYPE_ID, krowdTypeId
		);
	}
	
	public List findByTeamName(Object teamName
	) {
		return findByProperty(TEAM_NAME, teamName
		);
	}
	
	public List findByLogo(Object logo
	) {
		return findByProperty(LOGO, logo
		);
	}
	
	public List findByColor1(Object color1
	) {
		return findByProperty(COLOR1, color1
		);
	}
	
	public List findByColor2(Object color2
	) {
		return findByProperty(COLOR2, color2
		);
	}
	
	public List findByColor3(Object color3
	) {
		return findByProperty(COLOR3, color3
		);
	}
	
	public List findByHomeLocationId(Object homeLocationId
	) {
		return findByProperty(HOME_LOCATION_ID, homeLocationId
		);
	}
	

	public List findAll() {
		log.debug("finding all TableLeague instances");
		try {
			String queryString = "from TableLeague";
		 	return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
    public TableLeague merge(TableLeague detachedInstance) {
        log.debug("merging TableLeague instance");
        try {
            TableLeague result = (TableLeague) getHibernateTemplate()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(TableLeague instance) {
        log.debug("attaching dirty TableLeague instance");
        try {
            getHibernateTemplate().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void attachClean(TableLeague instance) {
        log.debug("attaching clean TableLeague instance");
        try {
            getHibernateTemplate().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

	public static TableLeagueDAO getFromApplicationContext(ApplicationContext ctx) {
    	return (TableLeagueDAO) ctx.getBean("TableLeagueDAO");
	}
}