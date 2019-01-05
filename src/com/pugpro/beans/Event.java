package com.pugpro.beans;

import javax.validation.constraints.NotEmpty;

import com.pugpro.customAnnotations.DateFormat;
import com.pugpro.customAnnotations.TimeFormat;

/**
 * A model of the EVENTS table in the RDS.
 * An Event object represents a single row in the table, containing all of the data regarding that Event.
 * @author Benjamin Hermus
 * @since 2019-04-01
 */
public class Event {
	private String eventID;
	private String ownerID;
	private String instanceID;
	@NotEmpty(message="Your event must have a title.")
	private String title;
	private String minilvl; 
	private String description;
	@DateFormat
	private String eventDate;
	@TimeFormat
	private String eventTime;
	
	/**
	 * Default constructor that sets fields to null.
	 */
	public Event() {
		eventID = null;    
		ownerID = null;    
		instanceID = null; 
		title = null;      
		minilvl = null;   
		description = null;
		eventDate = null;
		eventTime = null;  
	}

	/**
	 * Initializer constructor that sets fields to passed parameters.
	 * @param eventID The ID of the Event.
	 * @param ownerID The ID of the User who created the event.
	 * @param instanceID The ID of the Instance in which the event will take place.
	 * @param title The title of the Event.
	 * @param minilvl The minimum item level required for a User to attend the Event.
	 * @param description The description of the Event.
	 * @param eventDate The date (yyyy-mm-dd) of the Event.
	 * @param eventTime The time (hh-mi) of the Event.
	 */
	public Event(String eventID, String ownerID, String instanceID, String title, String minilvl, String description,
			String eventDate, String eventTime) {
		this.eventID = eventID;
		this.ownerID = ownerID;
		this.instanceID = instanceID;
		this.title = title;
		this.minilvl = minilvl;
		this.description = description;
		this.eventDate = eventDate;
		this.eventTime = eventTime;
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
	 * @return The ID of the User who created the event.
	 */
	public String getOwnerID() {
		return ownerID;
	}

	/**
	 * @param ownerID The ID of the User who created the event.
	 */
	public void setOwnerID(String ownerID) {
		this.ownerID = ownerID;
	}

	/**
	 * @return The ID of the Instance in which the event will take place.
	 */
	public String getInstanceID() {
		return instanceID;
	}

	/**
	 * @param instanceID The ID of the Instance in which the event will take place.
	 */
	public void setInstanceID(String instanceID) {
		this.instanceID = instanceID;
	}

	/**
	 * @return The title of the Event.
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title The title of the Event.
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return The minimum item level required for a User to attend the Event.
	 */
	public String getMinilvl() {
		return minilvl;
	}

	/**
	 * @param minilvl The minimum item level required for a User to attend the Event.
	 */
	public void setMinilvl(String minilvl) {
		this.minilvl = minilvl;
	}

	/**
	 * @return The description of the Event.
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description The description of the Event.
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	
	/**
	 * @return The date (yyyy-mm-dd) of the Event.
	 */
	public String getEventDate() {
		return eventDate;
	}
	
	/**
	 * @param eventDate The date (yyyy-mm-dd) of the Event.
	 */
	public void setEventDate(String eventDate) {
		this.eventDate = eventDate;
	}
	
	/**
	 * @return The time (hh-mm) of the Event.
	 */
	public String getEventTime() {
		return eventTime;
	}

	/**
	 * @param eventTime The time (hh-mm) of the Event.
	 */
	public void setEventTime(String eventTime) {
		this.eventTime = eventTime;
	}
	
}
