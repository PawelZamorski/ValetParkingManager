package com.vpm;

//import java.sql.Connection;
//import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Implementation of DAOFactory abstract class for MySql RDBMS
 * 
 * It uses JDBC API for accessing and manipulation of data in persistent storage.
 * 
 * @author Pawel Zamorski
 *
 */
public class DAOFactoryMysql extends DAOFactory{
	// TODO - extract connection details to a separate class
	// TODO - connection pooling
	// Configuration data - connection to mysql DB
//	private static final String URL = "jdbc:mysql://localhost:3306/valet_parking?serverTimezone=UTC";
//	private static final String USER = "root";
//	private static final String PASSWORD = "";
//	private static final String DRIVER_CLASS = "com.mysql.cj.jdbc.Driver";

	/**
	 * Returns connection to the MySql RDBMS
	 * 
	 * @param driverClass
	 * @return Connection to the MySql RDBMS
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	// TODO - use connection pool
//	public static Connection getConnection() throws ClassNotFoundException, SQLException {
		// Step 2 Load and register the Driver
		
/*
 *  Download driver class: Connector/J from dev.mysql.com
 *  Add library to the Java Build Path: Project/Properties/Java Build Path/Library/External Jar
 *  Build Path is only available during compile time (development process)
 *  Add library to the WebContent/WEB-INF/lib
 *  Add library to the Deployment Assembly: Project/Properties/Deployment Assembly
 *  Now the library will be available during run-time
 */
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
//		Class.forName(DRIVER_CLASS);

// The below code is the same as the above code:
/*
 * DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
*/
		
//		Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
//		return con;
//	}
	
	@Override
	/**
	 * Returns concrete implementation of ValetParkingDAO interface
	 * 
	 * @return ValetParkingDOA
	 */
	public ValetParkingDAO getValetParkingDAO(DataSource ds) {
		return new ValetParkingDAOMysql(ds);
	}
}
