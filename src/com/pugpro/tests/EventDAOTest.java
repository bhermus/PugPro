package com.pugpro.tests;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.pugpro.DAO.EventDAO;
import com.pugpro.DAO.OracleConnection;
import com.pugpro.beans.Event;

public class EventDAOTest {

	static EventDAO dao = null; //object used for testing
	static Connection conn = null; //used to get and utilize the connection from the OracleConnection object
	static PreparedStatement p = null; //used to interact with the database
	static ResultSet result = null; //used to store results returned from database
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		dao = new EventDAO();
		conn = new OracleConnection().getConnection();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		conn.close();
	}

	@Before
	public void setUp() throws Exception {
		//insert test data
		conn.prepareStatement("INSERT INTO users VALUES('testUserID','testUserUsername','testUserEmail','testUserPassword')").executeUpdate(); //test User
		conn.prepareStatement("INSERT INTO instances VALUES ('testInstanceID', 'testInstanceName')").executeUpdate(); //test Instance
		conn.prepareStatement("INSERT INTO events VALUES('testEventID1', 'testUserID', 'testInstanceID', 'testEventTitle', '-1', 'testEventDescription', TO_TIMESTAMP('1111-11-11 11:11:11', 'YYYY-MM-DD HH24:MI:SS'))").executeUpdate(); //test Event 
		conn.prepareStatement("INSERT INTO events VALUES('testEventID2', 'testUserID', 'testInstanceID', 'testEventTitle', '-1', 'testEventDescription', TO_TIMESTAMP('1111-11-11 11:11:11', 'YYYY-MM-DD HH24:MI:SS'))").executeUpdate(); //test Event 
	}

	@After
	public void tearDown() throws Exception {
		//delete test data
		conn.prepareStatement("DELETE FROM events WHERE ownerid LIKE 'test%'").executeUpdate();
		conn.prepareStatement("DELETE FROM instances WHERE instanceid LIKE 'test%'").executeUpdate();
		conn.prepareStatement("DELETE FROM users WHERE userid LIKE 'test%'").executeUpdate();
	}

	@Test
	public void testCreateEvent() {
			
		try {
			String newEventID = dao.createEvent("testUserID", "testInstanceID", "testEventTitle", //call createEvent() method
					"-1", "testEventDescription", "1111-11-11 11:11:11");
			if(newEventID == null) { //if Event was not created,
				fail("Event could not be created.");
			} else { 
				//query for the newly created Event
				p = conn.prepareStatement("SELECT * FROM events WHERE eventid = ?");
				p.setString(1, newEventID);
				result = p.executeQuery();
				
				if (result.next()) { //if a result was returned,
					//confirm that it is the newly created Event
					assertEquals(newEventID, result.getString(1));
					assertEquals("testUserID", result.getString(2));
					assertEquals("testInstanceID", result.getString(3));
					assertEquals("testEventTitle", result.getString(4));
					assertEquals("-1", result.getString(5));
					assertEquals("testEventDescription", result.getString(6));
					assertEquals("1111-11-11 11:11:11", result.getString(7));
				} else { //if no result was returned,
					fail("Failed to SELECT new Event from table.");
				}
			}
		} catch (SQLException e) {
	
			e.printStackTrace();
			fail("Exception was thrown");
		}
		
	}

	@Test
	public void testGetAllEvents() {
		List<Event> events = dao.getAllEvents(); //call getAllEvent() method
		boolean containsEvent1 = false, containsEvent2 = false; //variables will be set to true if Event is in List
		for (Event e : events) { //check each Event in List
			if (e.getEventID().equals("testEventID1")) {   //if it is the first test Event,
				//it should have the values assigned in setUp()
				assertEquals(e.getOwnerID(), "testUserID");
				assertEquals(e.getInstanceID(), "testInstanceID");
				assertEquals(e.getTitle(), "testEventTitle");
				assertEquals(e.getMinilvl(),"-1");
				assertEquals(e.getDescription(), "testEventDescription");
				assertEquals(e.getEventDate(), "11-11-1111");
				assertEquals(e.getEventTime(), "11:11");
				containsEvent1 = true; //test Event 1 is in List, so set to true
			}
			if (e.getEventID().equals("testEventID2")) {  //if it is the second test Event,
				//it should have the values assigned in setUp()
				assertEquals(e.getOwnerID(), "testUserID");
				assertEquals(e.getInstanceID(), "testInstanceID");
				assertEquals(e.getTitle(), "testEventTitle");
				assertEquals(e.getMinilvl(),"-1");
				assertEquals(e.getDescription(), "testEventDescription");
				assertEquals(e.getEventDate(), "11-11-1111");
				assertEquals(e.getEventTime(), "11:11");
				containsEvent2 = true; //test Event 2 is in List, so set to true
			}
		}
		assertTrue(containsEvent1); //List should have contained test Event 1
		assertTrue(containsEvent2); //List should have contained test Event 2
	}

	@Test
	public void testGetEventByID() {
		Event event = dao.getEventByID("testEventID1"); //call getEventByID() method
		//all values should be the ones assigned in setUp()
		assertEquals(event.getEventID(), "testEventID1");
		assertEquals(event.getOwnerID(), "testUserID");
		assertEquals(event.getInstanceID(), "testInstanceID");
		assertEquals(event.getTitle(), "testEventTitle");
		assertEquals(event.getMinilvl(),"-1");
		assertEquals(event.getDescription(), "testEventDescription");
		assertEquals(event.getEventDate(), "11-11-1111");
		assertEquals(event.getEventTime(), "11:11");
	}

}
