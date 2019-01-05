package com.pugpro.DAO;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Defines a data access object used for interactions with Attending objects.
 * @author Benjamin Hermus
 * @since 2019-04-01
 */
public class AttendingDAO {
	
	/**
	 * Signs a User up to an Event by inserting their respective IDs into the ATTENDING table.
	 * @param eventID The ID of the Event being signed up to by the User.
	 * @param userID The ID of the User signing up to the Event.
	 * @return Whether or not the User was successfully signed up to the Event.
	 */
	public boolean signUp(String eventID, String userID) {
		OracleConnection oc = new OracleConnection();
		Connection conn = null;
		PreparedStatement p = null;
		
		try {
			conn = oc.getConnection();
			
			p = conn.prepareStatement("INSERT INTO attending VALUES (?, ?)");
			p.setString(1, eventID);
			p.setString(2, userID);
			p.executeUpdate();
			
			conn.close();
		} catch (ClassNotFoundException | IOException | SQLException e) {
			return false;
		}
		
		return true;
	}
	
	/**
	 * Returns a List of the Event IDs for the Events being attended by a given User.
	 * @param userID The ID of the User whose Events are being returned.
	 * @return A List of the Event IDs for the Events being attended by the User.
	 */
	public List<String> getUserEvents(String userID){
		OracleConnection oc = new OracleConnection();
		Connection conn = null;
		PreparedStatement p = null;
		ResultSet result = null;
		List<String> attending = new ArrayList<String>(); //the List to be returned
		
		try {
			conn = oc.getConnection();
			
			p = conn.prepareStatement("SELECT eventID FROM attending WHERE userID = ?");
			p.setString(1, userID);
			result = p.executeQuery();
			while(result.next()) {
				attending.add(result.getString(1));
			}
			
			conn.close();
		} catch (ClassNotFoundException | IOException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return attending;
	}
	
}
