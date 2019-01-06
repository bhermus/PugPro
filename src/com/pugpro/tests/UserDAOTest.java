package com.pugpro.tests;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.pugpro.DAO.OracleConnection;
import com.pugpro.DAO.UserDAO;
import com.pugpro.beans.User;

public class UserDAOTest {

	static UserDAO dao = null; //object used for testing
	static Connection conn = null; //used to get and utilize the connection from the OracleConnection object
	static PreparedStatement p = null; //used to interact with the database
	static ResultSet result = null; //used to store results returned from database
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		dao = new UserDAO();
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
	}

	@After
	public void tearDown() throws Exception {
		//delete test data
		conn.prepareStatement("DELETE FROM users WHERE userid LIKE 'test%'").executeUpdate();
	}

	@Test
	public void testCreateUser() {
		try {
			String uuid = UUID.randomUUID().toString();
			String newUserID = dao.createUser(uuid, "testUserEmail" + uuid, "testUserPassword"); //call createUser() method
			 if (newUserID == null) { //if User was not created,
				 fail("Could not create User");
			 } else {
				//query for the newly created User
				p = conn.prepareStatement("SELECT * FROM users WHERE userid = ?");
				p.setString(1, newUserID);
				result = p.executeQuery();
					
				if (result.next()) { //if a result was returned,
					//confirm that it is the newly created User
					assertEquals(newUserID, result.getString(1));
					assertEquals(uuid, result.getString(2));
					assertEquals("testUserEmail" + uuid, result.getString(3));
					assertEquals("testUserPassword", result.getString(4));
				} else { //if no result was returned,
					fail("Failed to SELECT new User from table.");
				}
				
				//delete the newly created User
				p = conn.prepareStatement("DELETE FROM users WHERE userid = ?");
				p.setString(1, newUserID);
				p.executeUpdate();
			 }
			 
			
		} catch (SQLException e) {
			e.printStackTrace();
			fail("Exception was thrown.");
		}
				
	}

	@Test
	public void testValidateUser() {
		//method should return false with incorrect password
		assertFalse(dao.validateUser("This is not the password.", new User("testUserID","testUserUsername","testUserEmail","testUserPassword")));
		//method should return true with correct password
		assertTrue(dao.validateUser("testUserPassword", new User("testUserID","testUserUsername","testUserEmail","testUserPassword")));
	}

	@Test
	public void testGetUserByID() {
		User user = dao.getUserByID("testUserID"); //call getUserByID() method
		//all values should be the ones assigned in setUp()
		assertEquals(user.getUserID(), "testUserID");
		assertEquals(user.getUsername(), "testUserUsername");
		assertEquals(user.getEmail(), "testUserEmail");
		assertEquals(user.getPassword(), "testUserPassword");
	}

	@Test
	public void testGetUserByEmail() {
		User user = dao.getUserByEmail("testUserEmail"); //call getUserByEmail() method
		//all values should be the ones assigned in setUp()
		assertEquals(user.getUserID(), "testUserID");
		assertEquals(user.getUsername(), "testUserUsername");
		assertEquals(user.getEmail(), "testUserEmail");
		assertEquals(user.getPassword(), "testUserPassword");
	}

	@Test
	public void testGetUserByUsername() {
		User user = dao.getUserByUsername("testUserUsername"); //call getUserByUsername() method
		//all values should be the ones assigned in setUp()
		assertEquals(user.getUserID(), "testUserID");
		assertEquals(user.getUsername(), "testUserUsername");
		assertEquals(user.getEmail(), "testUserEmail");
		assertEquals(user.getPassword(), "testUserPassword");
	}

}
