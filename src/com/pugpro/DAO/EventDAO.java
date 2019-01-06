package com.pugpro.DAO;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.pugpro.beans.Event;

/**
 * Defines a data access object used for interactions with Event objects.
 * @author Benjamin Hermus
 * @since 2019-04-01
 */
public class EventDAO {
	
	/**
	 * Creates a new Event in the EVENTS table using the passed parameters.
	 * @param ownerID The ID of the User who created the event.
	 * @param instanceID The ID of the Instance in which the event will take place.
	 * @param title The title of the Event.
	 * @param minilvl The minimum item level required for a User to attend the Event.
	 * @param description The description of the Event.
	 * @param eventTime The time <b>and date</b> (yyyy-mm-dd hh-mi) of the Event.
	 * @return The ID of the created Event, or null if it could not be created.
	 */
	public String createEvent(String ownerID, String instanceID, String title, String minilvl, String description,
			String eventTime) {
		OracleConnection oc = new OracleConnection();
		Connection conn = null;
		PreparedStatement p = null;
		UUID eventID = null;
		
		try {
			conn = oc.getConnection();
			
			eventID = UUID.randomUUID();
			
			p = conn.prepareStatement("INSERT INTO events VALUES(?,?,?,?,?,?, TO_TIMESTAMP(?, 'YYYY-MM-DD HH24:MI:SS'))");
			p.setString(1, eventID.toString());
			p.setString(2, ownerID);
			p.setString(3, instanceID);
			p.setString(4, title);
			p.setString(5, minilvl);
			p.setString(6, description);
			p.setString(7, eventTime);
			p.executeUpdate();
			
			conn.close();
		} catch (ClassNotFoundException | IOException | SQLException e) {
			System.out.println(e.getMessage());
			return null;
		}
		return eventID.toString();
	}
	
	/**
	 * Returns a List of all the Events in the EVENTS table.
	 * @return A List of all Events in the EVENTS table.
	 */
	public List<Event> getAllEvents() {
		OracleConnection oc = new OracleConnection();
		Connection conn = null;
		ResultSet result = null;
		List<Event> events = new ArrayList<Event>(); //the List to be returned
		
		try {
			conn = oc.getConnection();
			
			//create and execute query, storing results in result
			result = conn.prepareStatement("SELECT eventID, ownerID, events.instanceID, title, minilvl, description, "
					+ "to_char(cast(eventTime as date),'DD-MM-YYYY'),to_char(cast(eventTime as date),'hh24:mi'), instanceName "
					+ "FROM events INNER JOIN instances ON instances.instanceid = events.instanceid ORDER BY instanceName").executeQuery();
			
			//add results from result to the List
			while (result.next()) { //while there are still items in result,
				events.add(new Event(result.getString(1), result.getString(2), result.getString(3), result.getString(4), //create a new Event object from result and add i to the List
						result.getString(5), result.getString(6), result.getString(7), result.getString(8)));
			}
			
			conn.close();
		} catch (ClassNotFoundException | IOException | SQLException e) {
			System.out.println("ERROR: " + e.getMessage());
			e.printStackTrace();
		}
		
		return events;
	}

	/**
	 * Returns the Event object of a given Event ID.
	 * @param id The ID of the Event.
	 * @return The Event object corresponding to the given Event ID.
	 */
	public Event getEventByID(String id) {
		OracleConnection oc = new OracleConnection();
		Connection conn = null;
		PreparedStatement p = null;
		ResultSet result = null;
		Event event = null; //the Event object to be returned
		
		try {
			conn = oc.getConnection();
			
			p = conn.prepareStatement("SELECT eventID, ownerID, instanceID, title, minilvl, description, " 
										+ "to_char(cast(eventTime as date),'DD-MM-YYYY'),to_char(cast(eventTime as date),'hh24:mi') FROM events WHERE eventID=?");
			p.setString(1, id);
			result = p.executeQuery();
			
			if (result.next()) { //if Event with given ID is found,
				event = new Event(result.getString(1), result.getString(2), result.getString(3), result.getString(4), //Assign to Event object new Event from result
						result.getString(5), result.getString(6), result.getString(7), result.getString(8));
			}
			
			conn.close();
		} catch (ClassNotFoundException | IOException | SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		
		return event; 
	}
	
	/**
	 * Returns a List of the User IDs of the Users attending the given Event.
	 * @param id The ID of the Event.
	 * @return A List of User IDs.
	 */
	public List<String> getEventAttendees(String id){
		OracleConnection oc = new OracleConnection();
		Connection conn = null;
		PreparedStatement p = null;
		ResultSet result = null;
		List<String> users = new ArrayList<String>();
		
		try {
			conn = oc.getConnection();
			
			p = conn.prepareStatement("SELECT userid FROM attending WHERE eventid = ?");
			p.setString(1, id);
			result = p.executeQuery();
			
			while(result.next()) {
				users.add(result.getString(1));
			}
			
			conn.close();
		} catch (ClassNotFoundException | IOException | SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		
		return users;
	}
}
