package com.tadosoft.krowdit.servlet;

import java.util.Date;
import java.util.List;

import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.tadosoft.krowdit.po.TableJoinKrowd;
import com.tadosoft.krowdit.po.TableJoinKrowdDAO;
import com.tadosoft.krowdit.po.TableKrowd;
import com.tadosoft.krowdit.po.TableKrowdDAO;
import com.tadosoft.krowdit.type.EC;

public class JoinKrowd extends JSONServlet implements ServletTemplate {

	private static final Logger log = LoggerFactory.getLogger(JoinKrowd.class);

	private TableJoinKrowdDAO joinKrowdDao;
	private TableKrowdDAO krowdDao;
	private long reqUid;
	private long reqKrowdId;
	private long reqTeamId;

	public void injectDAO() {
		ApplicationContext ctx = WebApplicationContextUtils
				.getWebApplicationContext(request.getSession()
						.getServletContext());
		joinKrowdDao = (TableJoinKrowdDAO) ctx.getBean("TableJoinKrowdDAO");
		krowdDao = (TableKrowdDAO) ctx.getBean("TableKrowdDAO");
	}

	public void parseReqParam() {
		reqUid = parseInt("uid");
		reqKrowdId = parseInt("krowdId");
		reqTeamId = parseInt("teamId");
		log.info(String.format("uid=%d, krowdId=%d, teamId=%d", reqUid,
				reqKrowdId, reqTeamId));
	}

	public void processBusiness() {
		json = new JSONObject();
		try {
			// 1. check if user has joined that krowd
			List<TableJoinKrowd> result = joinKrowdDao.findByUid(reqUid);
			if (result.size() > 0) {
				json.put("error", EC.EXISTED);
				TableKrowd krowd = krowdDao
						.findById(result.get(0).getKrowdId());
				json.put("krowdName", krowd.getKrowdName());
				json.put("krowdId", krowd.getKrowdId());
			} else {
				// 2. check if the krowd is closed.
				if (krowdDao.findById(reqKrowdId).getCloseTime().before(
						new Date())) {
					json.put("error", EC.KROWD_CLOSED);
				} else {
					// 3. now user has not joined any krowd and krowd is not
					// closed,
					// add a new record
					TableJoinKrowd joinKrowd = new TableJoinKrowd(reqKrowdId,
							reqUid, new Date(), reqTeamId);

					joinKrowdDao.save(joinKrowd);
					json.put("error", EC.SUCCESS);

				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			json.put("error", EC.DAO_FAILED);

		}
	}

	public TableJoinKrowdDAO getJoinKrowdDao() {
		return joinKrowdDao;
	}

	public void setJoinKrowdDao(TableJoinKrowdDAO joinKrowdDao) {
		this.joinKrowdDao = joinKrowdDao;
	}

	public TableKrowdDAO getKrowdDao() {
		return krowdDao;
	}

	public void setKrowdDao(TableKrowdDAO krowdDao) {
		this.krowdDao = krowdDao;
	}

	public long getReqUid() {
		return reqUid;
	}

	public void setReqUid(long reqUid) {
		this.reqUid = reqUid;
	}

	public long getReqKrowdId() {
		return reqKrowdId;
	}

	public void setReqKrowdId(long reqKrowdId) {
		this.reqKrowdId = reqKrowdId;
	}

	public long getReqTeamId() {
		return reqTeamId;
	}

	public void setReqTeamId(long reqTeamId) {
		this.reqTeamId = reqTeamId;
	}

}
