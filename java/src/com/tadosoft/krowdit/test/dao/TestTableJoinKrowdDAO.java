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
public class TestTableJoinKrowdDAO {

	private static final Logger log = LoggerFactory
			.getLogger(TestTableJoinKrowdDAO.class);

	@Autowired
	private TableJoinKrowdDAO dao;

	// test helper
	private Date now;
	private TableJoinKrowd joinKrowd1;
	private TableJoinKrowd joinKrowd2;
	
	public TestTableJoinKrowdDAO() {
		log.info(this.getClass().toString());
		now = new Date();

		
		joinKrowd1 = new TableJoinKrowd(111, 111, now, 111);
		joinKrowd2 = new TableJoinKrowd(222, 222, now, 222);
	}

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	@Transactional
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	@Transactional
	public void testDeleteByUid() {
		long uid = 999;
		joinKrowd1.setUid(uid);
		joinKrowd2.setUid(uid);

		assertEquals(0, dao.findByUid(uid).size());

		dao.save(joinKrowd1);
		assertEquals(1, dao.findByUid(uid).size());
		
		dao.save(joinKrowd2);
		assertEquals(2, dao.findByUid(uid).size());
		
		dao.deleteByUid(uid);
		assertEquals(0, dao.findByUid(uid).size());
	}
	
	@Test
	@Transactional
	public void testCountByKrowdIdAndSupportingTeamId() {
		long kid = 999;
		long tid = 888;
		
		joinKrowd1.setKrowdId(kid);
		joinKrowd1.setSupportingTeamId(tid);
		joinKrowd2.setKrowdId(kid);
		joinKrowd2.setSupportingTeamId(tid);

		dao.save(joinKrowd1);
		dao.save(joinKrowd2);

		assertEquals(2, dao.countByKrowdIdAndSupportingTeamId(kid, tid));
		
	}



}
