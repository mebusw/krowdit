package com.tadosoft.krowdit.po;

import java.util.Date;
import java.util.List;
import org.hibernate.LockMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 	* A data access object (DAO) providing persistence and search support for TableDeal entities.
 			* Transaction control of the save(), update() and delete() operations 
		can directly support Spring container-managed transactions or they can be augmented	to handle user-managed Spring transactions. 
		Each of these methods provides additional information for how to configure it for the desired type of transaction control. 	
	 * @see com.tadosoft.krowdit.po.TableDeal
  * @author MyEclipse Persistence Tools 
 */

public class TableDealDAO extends HibernateDaoSupport  {
	     private static final Logger log = LoggerFactory.getLogger(TableDealDAO.class);
		//property constants
	public static final String DEAL_NAME = "dealName";
	public static final String CONTENT = "content";
	public static final String SHOP_ID = "shopId";



	protected void initDao() {
		//do nothing
	}
    
    public void save(TableDeal transientInstance) {
        log.debug("saving TableDeal instance");
        try {
            getHibernateTemplate().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }
    
	public void delete(TableDeal persistentInstance) {
        log.debug("deleting TableDeal instance");
        try {
            getHibernateTemplate().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public TableDeal findById( Long id) {
        log.debug("getting TableDeal instance with id: " + id);
        try {
            TableDeal instance = (TableDeal) getHibernateTemplate()
                    .get("com.tadosoft.krowdit.po.TableDeal", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
    
    
    public List findByExample(TableDeal instance) {
        log.debug("finding TableDeal instance by example");
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
      log.debug("finding TableDeal instance with property: " + propertyName
            + ", value: " + value);
      try {
         String queryString = "from TableDeal as model where model." 
         						+ propertyName + "= ?";
		 return getHibernateTemplate().find(queryString, value);
      } catch (RuntimeException re) {
         log.error("find by property name failed", re);
         throw re;
      }
	}

	public List findByDealName(Object dealName
	) {
		return findByProperty(DEAL_NAME, dealName
		);
	}
	
	public List findByContent(Object content
	) {
		return findByProperty(CONTENT, content
		);
	}
	
	public List findByShopId(Object shopId
	) {
		return findByProperty(SHOP_ID, shopId
		);
	}
	

	public List findAll() {
		log.debug("finding all TableDeal instances");
		try {
			String queryString = "from TableDeal";
		 	return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
    public TableDeal merge(TableDeal detachedInstance) {
        log.debug("merging TableDeal instance");
        try {
            TableDeal result = (TableDeal) getHibernateTemplate()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(TableDeal instance) {
        log.debug("attaching dirty TableDeal instance");
        try {
            getHibernateTemplate().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void attachClean(TableDeal instance) {
        log.debug("attaching clean TableDeal instance");
        try {
            getHibernateTemplate().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

	public static TableDealDAO getFromApplicationContext(ApplicationContext ctx) {
    	return (TableDealDAO) ctx.getBean("TableDealDAO");
	}
}