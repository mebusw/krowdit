package com.tadosoft.krowdit.test;

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

import com.tadosoft.krowdit.po.*;

import com.tadosoft.krowdit.servlet.ListKrowds;
import com.tadosoft.krowdit.type.EC;

@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class TestListKrowdsServlet {

	private static final Logger log = LoggerFactory
			.getLogger(TestListKrowdsServlet.class);

	@Autowired
	private TableKrowdDAO krowdDao;
	@Autowired
	private TableLocationDAO locationDao;
	@Autowired
	private TableLeagueDAO leagueDao;
	@Autowired
	private KrowdSearchDAO krowdSearchDao;
	@Autowired
	private TableUserDAO userDao;
	@Autowired
	private TableKrowdTypeDAO krowdTypeDao;
	@Autowired
	private TableJoinKrowdDAO joinKrowdDao;

	/** the Object Under Test */
	private ListKrowds servlet;

	// test helper

	public TestListKrowdsServlet() {
		servlet = new ListKrowds();
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
		servlet.setLeagueDao(leagueDao);
		servlet.setLocationDao(locationDao);
		servlet.setKrowdSearchDao(krowdSearchDao);
		servlet.setUserDAO(userDao);
		servlet.setKrowdTypeDao(krowdTypeDao);
		servlet.setJoinKrowdDao(joinKrowdDao);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	@Transactional
	public void testprocessBusiness_SUCCESS() {

		servlet.setReqLat(39.15f);
		servlet.setReqLng(117.20f);
		servlet.setReqOffset(0);

		servlet.processBusiness();

		JSONObject json = servlet.getJson();
		assertNotNull(json);
		assertEquals(EC.SUCCESS, json.get("error"));

	}

	@Test
	public void add() {
		int x = 1;
		int y = 2;
		assertEquals(3, x + y);
	}

}
