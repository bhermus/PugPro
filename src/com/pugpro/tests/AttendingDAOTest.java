/**
 * 
 */
package com.pugpro.tests;

import static org.junit.Assert.*;

import java.sql.Connection;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.pugpro.DAO.AttendingDAO;
import com.pugpro.DAO.OracleConnection;

/**
 * @author Ben
 *
 */
public class AttendingDAOTest {

	static AttendingDAO dao;
	static OracleConnection oc;
	static Connection conn;
	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		dao = new AttendingDAO();
		oc = new OracleConnection();
		conn = oc.getConnection();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		conn.close();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test method for {@link com.pugpro.DAO.AttendingDAO#signUp(java.lang.String, java.lang.String)}.
	 */
	@Test
	public void testSignUp() {
		
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.pugpro.DAO.AttendingDAO#getUserEvents(java.lang.String)}.
	 */
	@Test
	public void testGetUserEvents() {
		fail("Not yet implemented");
	}

}
