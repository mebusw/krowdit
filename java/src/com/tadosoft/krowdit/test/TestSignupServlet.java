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

import com.tadosoft.krowdit.po.TableUser;
import com.tadosoft.krowdit.po.TableUserDAO;
import com.tadosoft.krowdit.servlet.Signup;
import com.tadosoft.krowdit.type.EC;
import com.tadosoft.krowdit.type.UserType;

@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class TestSignupServlet {

	private static final Logger log = LoggerFactory
			.getLogger(TestSignupServlet.class);

	@Autowired
	private TableUserDAO userDao;

	/** the Object Under Test */
	private Signup servlet;

	// test helper
	private Date now;
	private String userName;
	private String pwd;
	private String email;
	private int utypeId;

	public TestSignupServlet() {
		servlet = new Signup();
		log.info("constructor");
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
		servlet.setUserDao(userDao);
		

	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	@Transactional
	public void testprocessBusiness_SUCCESS() {
		utypeId = UserType.KROWDIT_USER;

		assertEquals(0, userDao.findByUserNameAndPwdAndUserType(userName, pwd,
				utypeId).size());

		servlet.setReqUserName(userName);
		servlet.setReqPwd(pwd);
		servlet.setReqEmail(email);
		
		servlet.processBusiness();
		
		JSONObject json = servlet.getJson();
		assertEquals(EC.SUCCESS, json.get("error"));
		assertEquals(1, userDao.findByUserNameAndPwdAndUserType(userName, pwd,
				utypeId).size());
		
	}
	

	
	@Test
	@Transactional
	public void testprocessBusiness_EXISTED() {

		TableUser qa1 = new TableUser(userName, pwd, email, now, utypeId);
		userDao.save(qa1);
		assertEquals(1, userDao.findByUserNameAndPwdAndUserType(userName, pwd,
				utypeId).size());
		
		servlet.setReqUserName(userName);
		servlet.setReqPwd(pwd);
		servlet.setReqEmail(email);
		
		servlet.processBusiness();

		JSONObject json = servlet.getJson();
		assertEquals(EC.EXISTED, json.get("error"));

		
	}
	


}
