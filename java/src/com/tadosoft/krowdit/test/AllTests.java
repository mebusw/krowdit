package com.tadosoft.krowdit.test;

import com.tadosoft.krowdit.test.dao.*;

import junit.framework.JUnit4TestAdapter;
import junit.framework.Test;
import junit.framework.TestSuite;

public class AllTests {

	public static Test suite() {
		TestSuite suite = new TestSuite("Test for com.tadosoft.krowdit.test");
		//$JUnit-BEGIN$
		suite.addTest(new JUnit4TestAdapter(TestLoginServlet.class));
		suite.addTest(new JUnit4TestAdapter(TestSignupServlet.class));
		suite.addTest(new JUnit4TestAdapter(TestRecoverPasswordServlet.class));
		suite.addTest(new JUnit4TestAdapter(TestListKrowdsServlet.class));
		suite.addTest(new JUnit4TestAdapter(TestJoinKrowdServlet.class));
		suite.addTest(new JUnit4TestAdapter(TestListLocationsServlet.class));
		suite.addTest(new JUnit4TestAdapter(TestListKrowdTypesServlet.class));
		suite.addTest(new JUnit4TestAdapter(TestListTeamsServlet.class));
		suite.addTest(new JUnit4TestAdapter(TestCreateLocationServlet.class));
		suite.addTest(new JUnit4TestAdapter(TestCreateKrowdServlet.class));
		suite.addTest(new JUnit4TestAdapter(TestLogoutServlet.class));
		
		
		
		suite.addTest(new JUnit4TestAdapter(TestTableKrowdDAO.class));
		suite.addTest(new JUnit4TestAdapter(TestTableUserDAO.class));
		suite.addTest(new JUnit4TestAdapter(TestTableJoinKrowdDAO.class));
		suite.addTest(new JUnit4TestAdapter(TestKrowdSearchDAO.class));
		//$JUnit-END$
		return suite;
	}

}
