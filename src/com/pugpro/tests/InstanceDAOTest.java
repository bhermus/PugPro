package com.pugpro.tests;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.pugpro.DAO.InstanceDAO;
import com.pugpro.DAO.OracleConnection;
import com.pugpro.beans.Instance;

public class InstanceDAOTest {

	static InstanceDAO dao = null; //object used for testing
	static Connection conn = null; //used to get and utilize the connection from the OracleConnection object
	static ResultSet result = null; //used to store results returned from database
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		dao = new InstanceDAO();
		conn = new OracleConnection().getConnection();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		conn.close();
	}

	@Before
	public void setUp() throws Exception {
		conn.prepareStatement("INSERT INTO instances VALUES ('testInstanceID1', 'testInstanceName1')").executeUpdate(); //test Instance 1
		conn.prepareStatement("INSERT INTO instances VALUES ('testInstanceID2', 'testInstanceName2')").executeUpdate(); //test Instance 2
	}

	@After
	public void tearDown() throws Exception {
		conn.prepareStatement("DELETE FROM instances WHERE instanceid LIKE 'test%'").executeUpdate();
	}

	@Test
	public void testGetAllInstances() {
		List<Instance> instances = dao.getAllInstances(); //call getAllInstances() method
		boolean containsInstance1 = false, containsInstance2 = false; //variables will be set to true if Instance is in List
		for (Instance i : instances) { //check each Instance in List
			if (i.getInstanceID().equals("testInstanceID1")) {   //if it is the first test Instance,
				//it should have the values assigned in setUp()
				assertEquals(i.getInstanceName(), "testInstanceName1");
				containsInstance1 = true; //test Instance 1 is in List, so set to true
			}
			if (i.getInstanceID().equals("testInstanceID2")) {   //if it is the second test Instance,
				//it should have the values assigned in setUp()
				assertEquals(i.getInstanceName(), "testInstanceName2");
				containsInstance2 = true; //test Instance 1 is in List, so set to true
			}
		}
		assertTrue(containsInstance1); //List should have contained test Instance 1
		assertTrue(containsInstance2); //List should have contained test Instance 2
	}

	@Test
	public void testGetInstanceByName() {
		Instance instance = dao.getInstanceByName("testInstanceName1"); //call getInstanceByName() method
		//all values should be the ones assigned in setUp()
		assertEquals(instance.getInstanceID(), "testInstanceID1");
	}

	@Test
	public void testGetInstanceByID() {
		Instance instance = dao.getInstanceByID("testInstanceID1"); //call getInstanceByID() method
		//all values should be the ones assigned in setUp()
		assertEquals(instance.getInstanceName(), "testInstanceName1");
	}

}
