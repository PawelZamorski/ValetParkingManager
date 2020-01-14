package com.vpm;

import java.util.List;

import java.util.ArrayList;
// Step 1 import java.sql.* package
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;

public class ValetParkingDAO {

	public static List<ValetParking> getValetParkingList() {
		List<ValetParking> items = new ArrayList<ValetParking>();
		
		// Connection data: url, user, password
		String url = "jdbc:mysql://localhost:3306/valet_parking?serverTimezone=UTC";
		String user = "root";
		String password = "";
		
		// Step 2 Load and register the Driver
		
/*
 *  Download driver class: Connector/J from dev.mysql.com
 *  Add library to the Java Build Path: Project/Properties/Java Build Path/Library/External Jar
 *  Build Path is only available during compile time (development process)
 *  Add library to the WebContent/WEB-INF/lib
 *  Add library to the Deployment Assembly: Project/Properties/Deployment Assembly
 *  Now the library will be available during run-time
 */
		try {
/*
 *  Class.forName("className") method registers/loads the class
 *  A static block of class is executed while the class is loaded
 *  Driver class has the static block
 *  The static block is executed while the Driver class is loaded
 */

/* source code of com.mysql.cj.jdbc.Driver()
	static {
		try {
    			java.sql.DriverManager.registerDriver(new Driver());
		} catch (SQLException E) {
    		throw new RuntimeException("Can't register driver!");
		}
	}
 */
			// DriverManager registers Driver: it creates new instance of Driver class
			Class.forName("com.mysql.cj.jdbc.Driver");

			// The below code is the same as the above code:
			/*
			 * DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
			*/
			
			// Step 3 Create Connection
			Connection con = DriverManager.getConnection(url, user, password);
			
			// Step 4 Create Statement
			Statement stm = con.createStatement();
			
			// Step 5 Execute the query
			String sql = "SELECT * FROM car_list";
			stm.executeQuery(sql);
			
			// Step 6 Process ResultSet
			ResultSet rs = stm.getResultSet();
			
			while(rs.next()) {
				ValetParking vp = new ValetParking();
				vp.setId(rs.getInt(1));
				vp.setName(rs.getString(2));
				vp.setRegistration(rs.getString(3));
				vp.setArrivalDate(rs.getDate(4).toLocalDate());
				items.add(vp);
			}
			
			// Step 7 Close connection
			stm.close();
			con.close();
		} catch (ClassNotFoundException cnfEx) {
			cnfEx.printStackTrace();
		} catch (SQLException sqlEx) {
			sqlEx.printStackTrace();
		}
		return items;
	}
}
