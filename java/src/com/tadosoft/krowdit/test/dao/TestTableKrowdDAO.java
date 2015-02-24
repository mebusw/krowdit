package com.tadosoft.krowdit.test.dao;

import java.util.Date;


import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tadosoft.krowdit.po.*;

import static org.junit.Assert.*;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.junit.runner.RunWith;

@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
@RunWith(SpringJUnit4ClassRunner.class)

public class TestTableKrowdDAO {

	private static final Logger log = LoggerFactory
			.getLogger(TestTableKrowdDAO.class);

	@Autowired
	private TableKrowdDAO dao;
	
	private Date now;
	

	public TestTableKrowdDAO() {
		log.info(this.getClass().toString());
		now = new Date();
	}

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	@Transactional
	public void testfindByCloseTimeAfterNow() {
		assertEquals(2, dao.findByCloseTimeAfter(now).size());
		dao.delete(dao.findById(1L));
		assertEquals(1, dao.findByCloseTimeAfter(now).size());

	}

	@Test
	public void testfindByCloseTimeAfterNow1() {
		assertEquals(2, dao.findByCloseTimeAfter(now).size());
	}
}
