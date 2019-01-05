package com.pugpro.DAO;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

import com.pugpro.beans.User;

/**
 * Defines a data access object used for interactions with User objects.
 * @author Benjamin Hermus
 * @since 2019-04-01
 */
public class UserDAO {
	/**
	 * Creates a new User in the USERS table using the passed parameters.
	 * @param username The username of the User.
	 * @param email The email address of the User.
	 * @param password The password of the User.
	 * @return Whether or not the User was successfully created.
	 */
	public boolean createUser(String username, String email, String password) {
		OracleConnection oc = new OracleConnection();
		Connection conn = null;
		PreparedStatement p = null;
		UUID userID = null;
		
		try {
			conn = oc.getConnection();
			
			userID = UUID.randomUUID();
			
			p = conn.prepareStatement("INSERT INTO users VALUES (?, ?, ?, ?)");
			p.setString(1, userID.toString());
			p.setString(2, username);
			p.setString(3, email);
			p.setString(4,  password);
			p.executeUpdate();
			
			conn.close();
		} catch (ClassNotFoundException | IOException | SQLException e) {
			System.out.println(e.getMessage());
			return false;
		}
		return true;
	}
	
	/**
	 * Validates a given User by confirming whether their password equals the given password.
	 * @param password The password of the User.
	 * @param user The User whose password is being validated.
	 * @return Whether or not the password is correct.
	 */
	public boolean validateUser(String password, User user) {
		return password.equals(user.getPassword());
	}
	
	/**
	 * @param id The ID of the User.
	 * @return The User object corresponding to the given User ID.
	 */
	public User getUserByID(String id) {
		OracleConnection oc = new OracleConnection();
		Connection conn = null;
		PreparedStatement p = null;
		ResultSet result = null;
		User user = null; //the User to be returned
		
		try {
			conn = oc.getConnection();
			
			p = conn.prepareStatement("SELECT * FROM users WHERE userID=?");
			p.setString(1, id);
			result = p.executeQuery();
			
			result.next();
			user = new User(result.getString(1), result.getString(2), result.getString(3), result.getString(4)); //create User object from query results
			
			conn.close();
		} catch (ClassNotFoundException | IOException | SQLException e) {
			return null; //if there is an error, return null
		}
		
		return user; //return created User object
	}
	
	/**
	 * @param email The email address of the User.
	 * @return The User object corresponding to the given email.
	 */
	public User getUserByEmail(String email) {
		OracleConnection oc = new OracleConnection();
		Connection conn = null;
		PreparedStatement p = null;
		ResultSet result = null;
		User user = null;
		
		try {
			conn = oc.getConnection();
			
			p = conn.prepareStatement("SELECT * FROM users WHERE email=?");
			p.setString(1, email);
			result = p.executeQuery();
			
			result.next();
			user = new User(result.getString(1), result.getString(2), result.getString(3), result.getString(4)); //create User object from query results
			
			conn.close();
		} catch (ClassNotFoundException | IOException | SQLException e) {
			return null; //if there is an error, return null
		}
		
		return user; //return created User object
	}
	
	/**
	 * @param username The username of the User.
	 * @return The User object corresponding to the given username.
	 */
	public User getUserByUsername(String username) {
		OracleConnection oc = new OracleConnection();
		Connection conn = null;
		PreparedStatement p = null;
		ResultSet result = null;
		User user = null;
		
		try {
			conn = oc.getConnection();
			
			p = conn.prepareStatement("SELECT * FROM users WHERE username=?");
			p.setString(1, username);
			result = p.executeQuery();
			
			result.next();
			user = new User(result.getString(1), result.getString(2), result.getString(3), result.getString(4)); //create User object from query results
			
			conn.close();
		} catch (ClassNotFoundException | IOException | SQLException e) {
			return null; //if there is an error, return null
		}
		
		return user; //return created User object
	}
	
}
