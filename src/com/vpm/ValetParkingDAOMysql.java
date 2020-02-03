package com.vpm;

import java.util.List;

import javax.servlet.ServletException;

import java.util.ArrayList;
// NOTE: Step 1: import java.sql.* package
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.Statement;
import java.time.LocalDate;
import java.sql.ResultSet;

/**
 * Implementation of ValetParkingDAO to work with MySQL RDBMS.
 * 
 * CRUD class - create, retrieve/read, update, delete
 * 
 * @author Pawel Zamorski
 *
 */
public class ValetParkingDAOMysql implements ValetParkingDAO {
	private Connection con;
	
	public ValetParkingDAOMysql(DataSource ds) {
		this.con = ds.getConnection();
	}
	
	/**
	 * Creates new tuple in a DB based on ValetParking objects' fields.
	 * <br />
	 * This method doesn't check if the data with the same primary key already exists in the DB
	 * If the data with the same primary key already exists in the DB, it will throw SQLException.
	 * 
	 * @param item ValetParking object
	 * @return 0 if no data where inserted or 1 if the data where inserted to the DB
	 */
	public int create(ValetParking item) throws ServletException {
		int i = 0;
//		Connection con = null;
		PreparedStatement pstm = null;

		try {
// NOTE: step 2: Create Connection
//			con = DAOFactoryMysql.getConnection();
// NOTE: step 3: Create PreparedStatement/Statement
			String sql = "INSERT INTO car_list (id, name, registration, arrival_date) VALUES (?, ?, ?, ?)";
			pstm = this.con.prepareStatement(sql);
// NOTE: step 4: Execute the query
// NOTE: Always use column name. Otherwise, if the table is changed in DB (more columns added) this method will throw an exception	
			pstm.setInt(1, item.getId());
			pstm.setString(2, item.getName());
			pstm.setString(3, item.getRegistration());
			pstm.setDate(4, Date.valueOf(item.getArrivalDate().toString()));
			i = pstm.executeUpdate();
		}catch(SQLException e) {
			throw new ServletException(e);
		}finally {
// NOTE: step 5: Close PreparedStatement and Connection
			if(pstm != null) {
				try {
					pstm.close();
				}catch(SQLException e) {
					pstm = null;
					throw new ServletException(e);
				}
			}

			if(con != null) {
				try {
					con.close();					
				}catch(SQLException e) {
					con = null;
					throw new ServletException(e);
				}
			}
		}
		return i;
	}

	/**
	 * Returns a single tuple from DB with the given id number (prime key)
	 * 
	 * @param id id of item, that is primary key  
	 * @return ValetParking object
	 */

/* NOTE:
 * The try-with-resources Statement.(non-Javadoc)
 * Resource classes must implement AutoCloseable (both Connection and PreparedStatement do)
 * A try-with-resources statement can have catch and finally blocks just like an ordinary try statement.
 * In a try-with-resources statement, any catch or finally block is run after the resources declared have been closed.
 */
	public ValetParking read(int id) throws ServletException {
		ValetParking vp = null;
		String sql = "SELECT name, registration, arrival_date FROM car_list WHERE id = ?";
		
// NOTE: The close methods of resources are called in the opposite order of their creation.
		try(PreparedStatement pstm = this.con.prepareStatement(sql)) {
			pstm.setInt(1, id);
			ResultSet rs = pstm.executeQuery();
			while(rs.next()) {
				String name = rs.getString(1);
				String registration = rs.getString(2);
				Date date = rs.getDate(3);
				LocalDate arrival_date = null;
				// TODO - change the code
				if(rs.wasNull()) {
					System.out.println("Arrival date value was not null, but it is working...");
				}else {
					arrival_date = date.toLocalDate();
				}
				vp = new ValetParking(id, name, registration, arrival_date);
			}
		}catch(SQLException e) {
// NOTE: Wrap ClassNotFoundException and SQLException in ServletException
			throw new ServletException(e);
		}
		return vp;
	}

	/**
	 * Returns the list of all tuples from the database in a form of ValetParking object
	 * 
	 * @return List of ValetParking objects
	 */
	public List<ValetParking> readAll() throws ServletException {
		List<ValetParking> items = new ArrayList<ValetParking>();

		try(Statement stm = this.con.createStatement()) {
			String sql = "SELECT id, name, registration, arrival_date FROM car_list";
			stm.executeQuery(sql);
			ResultSet rs = stm.getResultSet();
			
			while(rs.next()) {
				int id = rs.getInt(1);
				String name = rs.getString(2);
				String registration = rs.getString(3);
				Date date = rs.getDate(4);
				LocalDate arrival_date = null;
				// TODO - change code
				if(rs.wasNull()) {
					System.out.println("Arrival date value was not null, but it is working...");
				}else {
					arrival_date = date.toLocalDate();
				}
				ValetParking vp = new ValetParking(id, name, registration, arrival_date);
				items.add(vp);
			}
		}catch (SQLException e) {
			throw new ServletException(e);
		}
		return items;
	}
	
	/**
	 * Updates a tuple in the DB.
	 * 
	 * @param item ValetParking object
	 * @return 0 if no data were updated or 1 if data were updated
	 */
	public int update(ValetParking item) throws ServletException {
		int i = 0;

		String sql = "UPDATE car_list SET name=?, registration=?, arrival_date=? WHERE id=?";
		try(PreparedStatement pstm = this.con.prepareStatement(sql)) {
			pstm.setString(1, item.getName());
			pstm.setString(2, item.getRegistration());
			pstm.setDate(3, Date.valueOf(item.getArrivalDate().toString()));
			pstm.setInt(4, item.getId());
			i = pstm.executeUpdate();
		}catch(SQLException e) {
			throw new ServletException(e);
		}
		return i;
	}
	
	/**
	 * Deletes a tuple form the DB with the given primary key.
	 * 
	 * @param id id of item that is primary key
	 * @return 0 if no data were deleted or 1 if the data where deleted
	 */
	public int delete(int id) throws ServletException {
		int i = 0;
		
		String sql = "DELETE FROM car_list WHERE id=?";
		try(PreparedStatement pstm = this.con.prepareStatement(sql)) {
			pstm.setInt(1, id);
			i = pstm.executeUpdate();
		}catch (SQLException e) {
			throw new ServletException(e);
		}
		return i;
	}
}
