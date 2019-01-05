package com.pugpro.beans;

/**
 * A model of the INSTANCES table in the RDS.
 * An Instance object represents a single row in the table, containing all of the data regarding that Instance.
 * @author Benjamin Hermus
 * @since 2019-04-01
 */
public class Instance {
	private String instanceID;
	private String instanceName;
	
	/**
	 * Default constructor that sets fields to null.
	 */
	public  Instance() {
		instanceID = null;
		instanceName = null;
	}

	/**
	 * Initializer constructor that sets fields to passed parameters.
	 * @param instanceID The ID of the Instance.
	 * @param instanceName The name of the Instance.
	 */
	public Instance(String instanceID, String instanceName) {
		this.instanceID = instanceID;
		this.instanceName = instanceName;
	}

	/**
	 * @return The ID of the Instance.
	 */
	public String getInstanceID() {
		return instanceID;
	}

	/**
	 * @param instanceID The ID of the Instance.
	 */
	public void setInstanceID(String instanceID) {
		this.instanceID = instanceID;
	}

	/**
	 * @return The name of the Instance.
	 */
	public String getInstanceName() {
		return instanceName;
	}

	/**
	 * @param instanceName The name of the Instance.
	 */
	public void setInstanceName(String instanceName) {
		this.instanceName = instanceName;
	}
	
}
