package com.vpm;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/*
 * Stub implementation of DataSource interface
 */
public class DataSourceImpStub implements DataSource {
	private final String DRIVER_CLASS = "com.mysql.cj.jdbc.Driver";
	private final String DBURL = "jdbc:mysql://localhost:3306/VALET_PARKING?serverTimezone=UTC";
	private final String USER = "vpuser";
	private final String PASSWORD = "password";
	private Connection conn;

	public DataSourceImpStub() throws ClassNotFoundException, SQLException {
		Class.forName(DRIVER_CLASS);
		conn = DriverManager.getConnection(DBURL, USER, PASSWORD);
	}


	@Override
	public Connection getConnection() {
		// TODO Auto-generated method stub
		return conn;
	}

	@Override
	public void close() throws SQLException {
		// TODO Auto-generated method stub
		
	}

}
