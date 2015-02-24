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
import com.tadosoft.krowdit.type.*;

import static org.junit.Assert.*;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.junit.runner.RunWith;

@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class TestTableUserDAO {

	private static final Logger log = LoggerFactory
			.getLogger(TestTableUserDAO.class);

	/** the Object Under Test */
	@Autowired
	private TableUserDAO dao;

	// test helper
	private Date now;
	private String userName;
	private String pwd;
	private String email;
	private int utypeId;

	public TestTableUserDAO() {
		log.info(this.getClass().toString());
		now = new Date();
		userName = "sam";
		pwd = "testing";
		email = "sam@ccp.com";
		utypeId = UserType.KROWDIT_USER;
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
	public void testFindByUserNameAndPwdAndUserType() {

		assertEquals(0, dao.findByUserNameAndPwdAndUserType(userName, pwd,
				utypeId).size());

		TableUser qa1 = new TableUser(userName, pwd, email, now, utypeId);
		dao.save(qa1);
		assertEquals(1, dao.findByUserNameAndPwdAndUserType(userName, pwd,
				utypeId).size());
		assertEquals(0, dao.findByUserNameAndPwdAndUserType(userName, pwd,
				UserType.FACEBOOK_USER).size());

		TableUser qa2 = new TableUser(userName, pwd, email, now, utypeId);
		dao.save(qa2);
		assertEquals(2, dao.findByUserNameAndPwdAndUserType(userName, pwd,
				utypeId).size());
	}

	@Test
	@Transactional
	public void testFindByUserNameAndPwd() {

		assertEquals(0, dao.findByUserNameAndPwd(userName, pwd).size());

		TableUser qa1 = new TableUser(userName, pwd, email, now, utypeId);
		dao.save(qa1);
		assertEquals(1, dao.findByUserNameAndPwd(userName, pwd).size());

		TableUser qa2 = new TableUser(userName, pwd, email, now, utypeId);
		dao.save(qa2);
		assertEquals(2, dao.findByUserNameAndPwd(userName, pwd).size());
	}

}
