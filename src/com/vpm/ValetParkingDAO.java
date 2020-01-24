package com.vpm;

import java.util.List;

import java.util.ArrayList;
// Step 1 import java.sql.* package
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.Statement;
import java.time.LocalDate;
import java.sql.ResultSet;

public class ValetParkingDAO {
	// Configuration data - connection to mysql DB
	private final static String URL = "jdbc:mysql://localhost:3306/valet_parking?serverTimezone=UTC";
	private final static String USER = "root";
	private final static String PASSWORD = "";

	/**
	 * Selects all data from the database
	 * 
	 * @return List of ValetParking objects
	 */
	public static List<ValetParking> selectAll() {
		List<ValetParking> items = new ArrayList<ValetParking>();
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
			Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
			
			// Step 4 Create Statement
			Statement stm = con.createStatement();
			
			// Step 5 Execute the query
			String sql = "SELECT id, name, registration, arrival_date FROM car_list";
			stm.executeQuery(sql);
			
			// Step 6 Process ResultSet
			ResultSet rs = stm.getResultSet();
			
			while(rs.next()) {
				int id = rs.getInt(1);
				String name = rs.getString(2);
				String registration = rs.getString(3);
				Date date = rs.getDate(4);
				LocalDate arrival_date = null;
				if(rs.wasNull()) {
					System.out.println("Arrival date value was not null, but it is working...");
				}else {
					arrival_date = date.toLocalDate();
				}
				ValetParking vp = new ValetParking(id, name, registration, arrival_date);
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

	/**
	 * Selects a single item from DB with the given id number (prime key)
	 * 
	 * @param idPK id of item, that is primary key
	 * @return ValetParking object
	 */
	public static ValetParking select(int idPK) {
		ValetParking vp = null;
		
		Connection con = null;
		PreparedStatement pstm = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			String sql = "SELECT id, name, registration, arrival_date FROM car_list WHERE id = ?";
			pstm = con.prepareStatement(sql);
			pstm.setInt(idPK, 1);
			ResultSet rs = pstm.executeQuery();
			while(rs.next()) {
				int id = rs.getInt(1);
				String name = rs.getString(2);
				String registration = rs.getString(3);
				Date date = rs.getDate(4);
				LocalDate arrival_date = null;
				if(rs.wasNull()) {
					System.out.println("Arrival date value was not null, but it is working...");
				}else {
					arrival_date = date.toLocalDate();
				}
				vp = new ValetParking(id, name, registration, arrival_date);
			}
		}catch(ClassNotFoundException cnfEx) {
			cnfEx.printStackTrace();
		}catch(SQLException sqlEx) {
			sqlEx.printStackTrace();
		}finally {
			if(pstm != null) {
				try {
					pstm.close();
				}catch(SQLException sqlEx) {
					sqlEx.printStackTrace();
				}
			}
			if(con != null) {
				try {
					con.close();
				}catch (SQLException sqlEx) {
					sqlEx.printStackTrace();
				}
			}
		}
		return vp;
	}
	
	/**
	 * Inserts ValetParking objects' fields to the DB.
	 * <br />
	 * This method doesn't check if the data with the same primary key already exists in the DB
	 * If the data with the same primary key already exists in the DB, it will throw SQLException.
	 * 
	 * @param item ValetParking object
	 * @return 0 if no data where inserted or 1 if the data where inserted to the DB
	 */
	public static int insert(ValetParking item) {
		int i = 0;
		Connection con = null;
		PreparedStatement pstm = null;

		// Step 2 Load and register the Driver
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			// Step 3 Create Connection
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			// Step 4 Create Statement
			String sql = "INSERT INTO car_list (id, name, registration, arrival_date) VALUES (?, ?, ?, ?)";
			pstm = con.prepareStatement(sql);
			// Step 5 Execute the query
			// Always use column name. Otherwise, if the table is changed in DB (more columns added) this method will throw an exception	
			pstm.setInt(1, item.getId());
			pstm.setString(2, item.getName());
			pstm.setString(3, item.getRegistration());
			pstm.setDate(4, Date.valueOf(item.getArrivalDate().toString()));
			i = pstm.executeUpdate();
		}catch(ClassNotFoundException cnfEx) {
			cnfEx.printStackTrace();
		}catch(SQLException sqlEx) {
			sqlEx.printStackTrace();
		}finally { // Step 6 Close connection
			try {
				if(pstm != null) pstm.close();				
			}catch(SQLException sqlEx) {
				sqlEx.printStackTrace();
			}
			
			try {
				if(con != null) con.close();
			}catch(SQLException sqlEx) {
				sqlEx.printStackTrace();
			}
		}
		return i;
	}
	
	/**
	 * Deletes item form the DB with the given primary key.
	 * 
	 * @param id id of item that is primary key
	 * @return 0 if no data were deleted or 1 if the data where deleted
	 */
	public static int delete(int id) {
		int i = 0;
		
		Connection con = null;
		PreparedStatement pstm = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			String sql = "DELETE FROM car_list WHERE id=?";
			pstm = con.prepareStatement(sql);
			pstm.setInt(1, id);
			i = pstm.executeUpdate();
		}catch (ClassNotFoundException cnfEx) {
			cnfEx.printStackTrace();
		}catch (SQLException sqlEx) {
			sqlEx.printStackTrace();
		}finally {
			if(pstm != null) {
				try {
					pstm.close();
				}catch (SQLException sqlEx) {
					sqlEx.printStackTrace();
				}
			}
			if(con != null) {
				try {
					con.close();
				}catch (SQLException sqlEx) {
					sqlEx.printStackTrace();
				}
			}
		}
		return i;
	}
	
	/**
	 * Updates data in the DB.
	 * 
	 * @param item ValetParking object
	 * @return 0 if no data were updated or 1 if data were updated
	 */
	public static int update(ValetParking item) {
		int i = 0;
		
		Connection con = null;
		PreparedStatement pstm = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			String sql = "UPDATE car_list SET name=?, registration=?, arrival_date=? WHERE id=?";
			pstm = con.prepareStatement(sql);
			pstm.setString(1, item.getName());
			pstm.setString(2, item.getRegistration());
			pstm.setDate(3, Date.valueOf(item.getArrivalDate().toString()));
			pstm.setInt(4, item.getId());
			i = pstm.executeUpdate();
		}catch(ClassNotFoundException cnfEx) {
			cnfEx.printStackTrace();
		}catch(SQLException sqlEx) {
			sqlEx.printStackTrace();
		}finally {
			if(con != null) {
				try {
					con.close();
				}catch(SQLException sqlEx) {
					sqlEx.printStackTrace();
				}
			}
		}
		return i;
	}
}
