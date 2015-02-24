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

import com.tadosoft.krowdit.servlet.ListKrowdTypes;
import com.tadosoft.krowdit.type.EC;

@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class TestListKrowdTypesServlet {

	private static final Logger log = LoggerFactory
			.getLogger(TestListKrowdTypesServlet.class);

	@Autowired
	private TableKrowdTypeDAO krowdTypeDao;

	/** the Object Under Test */
	private ListKrowdTypes servlet;

	// test helper

	public TestListKrowdTypesServlet() {
		servlet = new ListKrowdTypes();
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
		servlet.setKrowdTypeDao(krowdTypeDao);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	@Transactional
	public void testprocessBusiness_SUCCESS() {
		int sizeBefore = krowdTypeDao.findAll().size();
		TableKrowdType kt1 = new TableKrowdType("typeA", true);
		krowdTypeDao.save(kt1);

		servlet.processBusiness();

		JSONObject json = servlet.getJson();
		assertEquals(EC.SUCCESS, json.get("error"));
		assertEquals(sizeBefore + 1, krowdTypeDao.findAll().size());
	}

}
