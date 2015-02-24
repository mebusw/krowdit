package com.tadosoft.krowdit.test;

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

import com.tadosoft.krowdit.po.TableKrowdDAO;

import com.tadosoft.krowdit.servlet.CreateKrowd;
import com.tadosoft.krowdit.type.EC;

@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class TestCreateKrowdServlet {

	private static final Logger log = LoggerFactory
			.getLogger(TestCreateKrowdServlet.class);

	@Autowired
	private TableKrowdDAO krowdDao;

	/** the Object Under Test */
	private CreateKrowd servlet;

	// test helper
	private Date now;

	public TestCreateKrowdServlet() {
		servlet = new CreateKrowd();
		now = new Date();
		log.info("constructor");

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

	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	@Transactional
	public void testprocessBusiness_SUCCESS() {

		int sizeBefore = krowdDao.findAll().size();

		servlet.setReqLocationId(3);
		servlet.setReqCreatorId(1);
		servlet.setReqHomeTeamId("4");
		servlet.setReqAwayTeamId("3");
		servlet.setReqKrowdTypeId(1);
		servlet.setReqStartTime(now);

		servlet.processBusiness();

		JSONObject json = servlet.getJson();
		assertNotNull(json);
		assertEquals(EC.SUCCESS, json.get("error"));
		assertEquals(sizeBefore + 1, krowdDao.findAll().size());
	}

}
