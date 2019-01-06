/**
 * 
 */
package com.pugpro.tests;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

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

	static AttendingDAO dao = null; //object used for testing
	static Connection conn = null; //used to get and utilize the connection from the OracleConnection object
	static ResultSet result = null; //used to store results returned from database
	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		dao = new AttendingDAO();
		conn = new OracleConnection().getConnection();
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
		//insert test data
		conn.prepareStatement("INSERT INTO users VALUES('testUserID','testUserUsername','testUserEmail','testUserPassword')").executeUpdate(); //test User
		conn.prepareStatement("INSERT INTO instances VALUES ('testInstanceID', 'testInstanceName')").executeUpdate(); //test Instance
		conn.prepareStatement("INSERT INTO events VALUES('testEventID1', 'testUserID', 'testInstanceID', 'testEventTitle', '-1', 'testEventDescription', SYSTIMESTAMP)").executeUpdate(); //test Event for testSignUp()
		conn.prepareStatement("INSERT INTO events VALUES('testEventID2', 'testUserID', 'testInstanceID', 'testEventTitle', '-1', 'testEventDescription', SYSTIMESTAMP)").executeUpdate(); //test Event for testGetUserEvents()
		conn.prepareStatement("INSERT INTO attending VALUES('testEventID2', 'testUserID')").executeUpdate(); //test User is attending second test Event
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {	
		//delete test data
		conn.prepareStatement("DELETE FROM attending WHERE userid LIKE 'test%'").executeUpdate();
		conn.prepareStatement("DELETE FROM events WHERE ownerid LIKE 'test%'").executeUpdate();
		conn.prepareStatement("DELETE FROM instances WHERE instanceid LIKE 'test%'").executeUpdate();
		conn.prepareStatement("DELETE FROM users WHERE userid LIKE 'test%'").executeUpdate();
	}

	/**
	 * Test method for {@link com.pugpro.DAO.AttendingDAO#signUp(java.lang.String, java.lang.String)}.
	 */
	@Test
	public void testSignUp() {
		try {
			if (!dao.signUp("testEventID1", "testUserID")) { //call signUp() method. if it returns false (i.e. User was not signed up),
				fail("User could not be signed up."); //fail the test
			}
			
			//confirm that a new Attending object was created for the test User and test Event
			result = conn.prepareStatement("SELECT * FROM attending WHERE userid='testUserID'").executeQuery();
			result.next();
			assertEquals("testEventID1",result.getString(1));
			assertEquals("testUserID",result.getString(2));
			
		} catch (SQLException e) { //fail the test if an exception is thrown
			e.printStackTrace();
			fail("An exception was thrown.");
		}
		
		
	}

	/**
	 * Test method for {@link com.pugpro.DAO.AttendingDAO#getUserEvents(java.lang.String)}.
	 */
	@Test
	public void testGetUserEvents() {
		List<String> events = null;
		events = dao.getUserEvents("testUserID"); //call getUserEvents() method
		assertTrue(events.contains("testEventID2")); //returned List must contain the Event added in setUp()
		assertEquals(1, events.size()); //returned List should contain no other Events
	}

}
