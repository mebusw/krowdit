package com.tadosoft.krowdit.test.dao;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

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
public class TestKrowdSearchDAO {

	private static final Logger log = LoggerFactory
			.getLogger(TestKrowdSearchDAO.class);

	/** the Object Under Test */
	@Autowired
	private KrowdSearchDAO krowdSearchDao;
	@Autowired
	private TableKrowdDAO tableKrowdDao;
	@Autowired
	private TableLocationDAO tableLocationDao;

	// test helper
	private Date now;
	private Date future;
	private float x;
	private float y;
	private Calendar cal;

	public TestKrowdSearchDAO() {
		log.info(this.getClass().toString());
		now = new Date();
		cal = Calendar.getInstance();
		cal.set(2050, 12, 2);
		future = cal.getTime();
		x = 39.15f;
		y = 117.20f;
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
	public void testFindWithinTimeAndDistance_locationOrder() {
		/** 0.01 degree ~= 1km */
		// closest location
		TableLocation location1 = new TableLocation("testTianjin", 1L,
				x - 0.02f, y + 0.01f, now, 1L);
		tableLocationDao.save(location1);
		// 2nd closest location
		TableLocation location2 = new TableLocation("testBeijing", 1L,
				x + 0.02f, y + 0.05f, now, 1L);
		tableLocationDao.save(location2);
		// a far away location
		TableLocation location3 = new TableLocation("testShanghai", 1L, x + 1f,
				y + 1f, now, 1L);
		tableLocationDao.save(location3);

		TableKrowd k1 = new TableKrowd("testk1", 1, "1", "2", now, location1
				.getLocationId(), 1L, now, now, now, now);
		tableKrowdDao.save(k1);
		TableKrowd k2 = new TableKrowd("testk2", 1, "1", "2", now, location2
				.getLocationId(), 1L, now, now, now, now);
		tableKrowdDao.save(k2);
		// k3 & k4 either too far away or not within the time window
		TableKrowd k3 = new TableKrowd("testk3", 1, "1", "2", now, location3
				.getLocationId(), 1L, now, now, now, now);
		tableKrowdDao.save(k3);
		TableKrowd k4 = new TableKrowd("testk4", 1, "1", "2", now, location1
				.getLocationId(), 1L, future, now, now, now);
		tableKrowdDao.save(k4);

		List<TableKrowd> result = krowdSearchDao.findWithinTimeAndDistance(now,
				x, y, 0);

		assertEquals(2, result.size());
//		assertEquals(location1.getLocationId(), result.get(0).getLocationId());
//		assertEquals(location2.getLocationId(), result.get(1).getLocationId());

	}
	


}
