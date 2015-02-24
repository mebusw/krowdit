package com.tadosoft.krowdit.test;

import java.util.Calendar;
import java.util.Date;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

import org.json.simple.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.tadosoft.krowdit.po.TableJoinKrowd;
import com.tadosoft.krowdit.po.TableJoinKrowdDAO;
import com.tadosoft.krowdit.po.TableKrowd;
import com.tadosoft.krowdit.po.TableKrowdDAO;

import com.tadosoft.krowdit.servlet.JoinKrowd;
import com.tadosoft.krowdit.type.EC;


@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class TestJoinKrowdServlet {

	private static final Logger log = LoggerFactory
			.getLogger(TestJoinKrowdServlet.class);

	@Autowired
	private TableJoinKrowdDAO joinKrowdDao;
	@Autowired
	private TableKrowdDAO krowdDao;

	/** the Object Under Test */
	private JoinKrowd servlet;

	// test helper
	private Date now;
	private int uid;
	private int tid;
	private String krowdName;
	private String home;
	private String away;

	public TestJoinKrowdServlet() {
		servlet = new JoinKrowd();
		log.info("constructor");
		now = new Date();
		krowdName = "TestingCup";
		uid = 10;
		tid = 1;
		home = "1";
		away = "2";

	}

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		servlet.setKrowdDao(krowdDao);
		servlet.setJoinKrowdDao(joinKrowdDao);

	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	@Transactional
	public void testprocessBusiness_SUCCESS() {
		Calendar cal = Calendar.getInstance();
		cal.setTime(now);
		cal.add(Calendar.YEAR, 1);
		Date future = cal.getTime();
		TableKrowd k = new TableKrowd(krowdName, 1L, home, away, now, 1,
				1, now, now, future, now);
		krowdDao.save(k);

		servlet.setReqKrowdId(k.getKrowdId());
		servlet.setReqTeamId(tid);
		servlet.setReqUid(uid);

		servlet.processBusiness();

		JSONObject json = servlet.getJson();
		assertEquals(EC.SUCCESS, json.get("error"));

	}
	
	@Test
	@Transactional
	public void testprocessBusiness_KROWD_CLOSED() {

		TableKrowd k = new TableKrowd(krowdName, 1L, home, away, now, 1,
				1, now, now, now, now);
		krowdDao.save(k);

		servlet.setReqKrowdId(k.getKrowdId());
		servlet.setReqTeamId(tid);
		servlet.setReqUid(uid);

		servlet.processBusiness();

		JSONObject json = servlet.getJson();
		assertEquals(EC.KROWD_CLOSED, json.get("error"));

	}
	
	@Test
	@Transactional
	public void testprocessBusiness_EXISTED() {
		TableKrowd k = new TableKrowd(krowdName, 1L, home, away, now, 1,
				1, now, now, now, now);
		krowdDao.save(k);
		
		TableJoinKrowd jk = new TableJoinKrowd(k.getKrowdId(), uid, now, 1L);
		joinKrowdDao.save(jk);
	
		servlet.setReqKrowdId(k.getKrowdId());
		servlet.setReqTeamId(tid);
		servlet.setReqUid(uid);

		servlet.processBusiness();

		JSONObject json = servlet.getJson();
		assertEquals(EC.EXISTED, json.get("error"));

	}

}
