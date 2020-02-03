package com.vpm;

import java.sql.Connection;
import java.sql.SQLException;

public interface DataSource extends AutoCloseable {
	
	/**
	 * Returns Connection to the data source. It may be a DB.
	 * 
	 * @return Connection to the data source
	 */
	public Connection getConnection();
	
	/**
	 * Closes Connection
	 */
	public void close() throws SQLException;

}
