package com.pugpro.beans;

/**
 * A model of the ATTENDING table in the RDS. 
 * An Attending object represents a single pairing of a User to an Event using their respective IDs, 
 * indicating that the User of given ID will attend the Event of given ID.
 * @author Benjamin Hermus
 * @since 2019-01-04
 */
public class Attending {
	private String eventID;
	private String userID;
	
	/**
	 * Default constructor that sets fields to null.
	 */
	public Attending() {
		this.eventID = null;
		this.userID = null;
	}

	/**
	 * Initializer constructor that sets fields to passed parameters.
	 * @param eventID The ID of the Event.
	 * @param userID The ID of the User.
	 */
	public Attending(String eventID, String userID) {
		this.eventID = eventID;
		this.userID = userID;
	}

	/**
	 * @return The ID of the Event.
	 */
	public String getEventID() {
		return eventID;
	}

	/**
	 * @param eventID The ID of the Event.
	 */
	public void setEventID(String eventID) {
		this.eventID = eventID;
	}

	/**
	 * @return The ID of the User.
	 */
	public String getUserID() {
		return userID;
	}

	/**
	 * @param userID The ID of the User.
	 */
	public void setUserID(String userID) {
		this.userID = userID;
	}
	
}
