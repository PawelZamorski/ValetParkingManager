package com.vpm;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * The DataSourceImp class is an implementation of DataSource interface.
 * 
 * It has methods for creating a connection based on the xml file containing the following properties:
 * <entry key="driver"><//entry>
 * <entry key="dburl"><//entry>
 * <entry key="user"><//entry>
 * <entry key="password"><//entry>
 * 
 * An opened Connection should be closed with the close() method.
 * 
 * @author Pawel Zamorski
 *
 */
public class DataSourceImpMysql implements DataSource {
	private final String DRIVER_CLASS = "driver";
	private final String DBURL = "dburl";
	private final String USER = "user";
	private final String PASSWORD = "password";
	private Connection conn;
	
	/**
	 * The constructor
	 * 
	 * @param path path to the file containing properties
	 * @throws ClassNotFoundException 
	 * @throws SQLException 
	 * @throws IOException 
	 * 
	 */
	public DataSourceImpMysql(String path) throws ClassNotFoundException, SQLException, IOException {
		Properties properties = new Properties();

		try(FileInputStream fileInputStream = new FileInputStream(path)){

			properties.loadFromXML(fileInputStream);
		    String driver = properties.getProperty(this.DRIVER_CLASS);
		    String dburl = properties.getProperty(this.DBURL);
		    String user = properties.getProperty(this.USER);
		    String password = properties.getProperty(this.PASSWORD);		    
			// Set up Connection - use DriverManager class
			// Load and register the Driver
			// DriverManager registers Driver: it creates new instance of Driver class
			Class.forName(driver);
			conn = DriverManager.getConnection(dburl, user, password);
		}
	}
	
	/**
	 * Returns Connection to the data source. It may be a DB.
	 * 
	 * @return Connection to the data source
	 */
	public Connection getConnection() {
		return this.conn;
	}
	
	public void close() throws SQLException {
		this.conn.close();
	}

	
//	TODO - change to private, use in constructor
//	private void getProperties(String path) {
//	}
	
}
