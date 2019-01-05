package com.pugpro.DAO;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.pugpro.beans.Instance;


/**
 * Defines a data access object used for interactions with Instance objects.
 * @author Benjamin Hermus
 * @since 2019-04-01
 */
public class InstanceDAO {

	/**
	 * Returns a List of all the Instances in the INSTANCES table.
	 * @return A List of all Instances in the INSTANCES table.
	 */
	public List<Instance> getAllInstances(){
		OracleConnection oc = new OracleConnection();
		Connection conn = null;
		ResultSet result = null;
		List<Instance> instances = new ArrayList<Instance>(); //the List to be returned
		
		try {
			conn = oc.getConnection();
			
			result = conn.prepareStatement("SELECT * FROM instances").executeQuery();
			while(result.next()) {
				instances.add(new Instance(result.getString(1), result.getString(2) ) ); //create new Instance from result and add it to List
			}
			
			conn.close();
		} catch (ClassNotFoundException | IOException | SQLException e) {
			System.out.println(e.getMessage());
		}		
		
		return instances;
	}
	
	/**
	 * Returns the Instance object of a given name.
	 * @param name The name of the Instance.
	 * @return The Instance object corresponding to the given name.
	 */
	public Instance getInstanceByName(String name) {
		OracleConnection oc = new OracleConnection();
		Connection conn = null;
		PreparedStatement p = null;
		ResultSet result = null;
		Instance instance = null; //Instance to be returned
		
		try {
			conn = oc.getConnection();
			
			p = conn.prepareStatement("SELECT * FROM instances WHERE instanceName=?");
			p.setString(1, name);
			result = p.executeQuery();
			if (result.next()) {
				instance = new Instance(result.getString(1), result.getString(2));
			}
			
			conn.close();
		} catch (ClassNotFoundException | IOException | SQLException e) {
			System.out.println(e.getMessage());
		}
		
		return instance;
	}
	
	/**
	 * Returns the Instance object of a given Instance ID.
	 * @param id The ID of the Instance.
	 * @return The Instance object corresponding to the given Instance ID.
	 */
	public Instance getInstanceByID(String id) {
		OracleConnection oc = new OracleConnection();
		Connection conn = null;
		PreparedStatement p = null;
		ResultSet result = null;
		Instance instance = null; //Instance to be returned
		
		try {
			conn = oc.getConnection();
			
			p = conn.prepareStatement("SELECT * FROM instances WHERE instanceID=?");
			p.setString(1, id);
			result = p.executeQuery();
			if (result.next()) {
				instance = new Instance(result.getString(1), result.getString(2));
			}
			
			conn.close();
		} catch (ClassNotFoundException | IOException | SQLException e) {
			System.out.println(e.getMessage());
		}
		
		return instance;
	}
}
