package com.pugpro.beans;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.pugpro.customAnnotations.EmailFormat;
import com.pugpro.customAnnotations.PasswordFormat;
import com.pugpro.customAnnotations.UniqueEmail;
import com.pugpro.customAnnotations.UniqueUsername;

/**
 * A model of the USERS table in the RDS.
 * A User object represents a single row in the table, containing all of the data regarding that User.
 * @author Benjamin Hermus
 * @since 2019-04-01
 */
public class User {
	private String userID;
	
	@UniqueUsername
	@NotEmpty(message="You must provide a username.")
	private String username;
	
	@EmailFormat 
	@UniqueEmail
	private String email;
	
	@Size(min=8, max=128, message="Password must be between 8 and 128 characters.") 
	@PasswordFormat
	private String password;
	
	/**
	 * Default constructor that sets fields to null.
	 */
	public User() {
		this.userID = null;
		this.username = null;
		this.email = null;
		this.password = null;
	}
	
	/**
	 * Initializer constructor that sets fields to passed parameters.
	 * @param userID The ID of the User.
	 * @param username The username of the User.
	 * @param email The email address of the User.
	 * @param password The password of the User.
	 */
	public User(String userID, String username, String email, String password) {
		this.userID = userID;
		this.username = username;
		this.email = email;
		this.password = password;
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
	
	/**
	 * @return The username of the User.
	 */
	public String getUsername() {
		return username;
	}
	
	/**
	 * @param username The username of the User.
	 */
	public void setUsername(String username) {
		this.username = username;
	}
	
	/**
	 * @return The email address of the User.
	 */
	public String getEmail() {
		return email;
	}
	
	/**
	 * @param email The email address of the User.
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	
	/**
	 * @return The password of the User.
	 */
	public String getPassword() {
		return password;
	}
	
	/**
	 * @param password The password of the User.
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
