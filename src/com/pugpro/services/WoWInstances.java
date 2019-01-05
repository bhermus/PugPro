package com.pugpro.services;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.UUID;

import com.pugpro.DAO.OracleConnection;

public class WoWInstances {
	public boolean populateInstancesTable() {
		OracleConnection oc = new OracleConnection();
		Connection conn = null;
		PreparedStatement p = null;
		UUID instanceID = null;
		
		try {
			conn = oc.getConnection();
			p = conn.prepareStatement("INSERT INTO instances VALUES(?,?)");
			
//			instanceID = UUID.randomUUID();
//			p.setString(1, instanceID.toString());
//			p.setString(2, "Ragefire Chasm");
//			p.executeUpdate();
//			
//			instanceID = UUID.randomUUID();
//			p.setString(1, instanceID.toString());
//			p.setString(2, "The Deadmines");
//			p.executeUpdate();
//			
//			instanceID = UUID.randomUUID();
//			p.setString(1, instanceID.toString());
//			p.setString(2, "Wailing Caverns");
//			p.executeUpdate();
//			
//			instanceID = UUID.randomUUID();
//			p.setString(1, instanceID.toString());
//			p.setString(2, "Shadowfang Keep");
//			p.executeUpdate();
			
		} catch (ClassNotFoundException | IOException | SQLException e) {
			e.printStackTrace(System.out);
			return false;
		}
		return true;
	}
	public static void main(String[] args) {
		WoWInstances instances = new WoWInstances();
		System.out.println(instances.populateInstancesTable());
	}
}
