package com.vpm;

import java.io.FileInputStream;
import java.util.Arrays;
import java.util.List;

import org.dbunit.DBTestCase;
import org.dbunit.PropertiesBasedJdbcDatabaseTester;
import org.dbunit.database.DatabaseConfig;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.ext.mysql.MySqlDataTypeFactory;
import org.dbunit.ext.mysql.MySqlMetadataHandler;
import org.dbunit.operation.DatabaseOperation;
import org.junit.Assert;
import org.junit.Test;

public class ValetParkingDAOMysqlTestStub extends DBTestCase {
	DataSource ds;
	ValetParkingDAOMysql dao;
	
	// Constructor using the stub object of DataSource
	public ValetParkingDAOMysqlTestStub(String name) {
		super(name);
		
		// TODO - move to @Before
		// Create a stub object
		try {
			ds = new DataSourceImpStub();
			dao = new ValetParkingDAOMysql(ds);
		}catch(Exception e) {

		}		
		
		System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_DRIVER_CLASS,"com.mysql.cj.jdbc.Driver");
		System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_CONNECTION_URL,"jdbc:mysql://localhost:3306/VALET_PARKING?serverTimezone=UTC");
		System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_USERNAME,"vpuser");
		System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_PASSWORD,"password");
		System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_SCHEMA,"valet_parking");
		}
	
	@Test
	public void testCar_ListSelectAll() throws Exception
    {
		// Expected
		List<ValetParking> expectedList = Arrays.asList(
				new ValetParking(1, "ALA", "123xc123", java.sql.Date.valueOf("2020-02-18").toLocalDate()),
				new ValetParking(2, "John", "222xc123", java.sql.Date.valueOf("2020-02-17").toLocalDate()),
				new ValetParking(3, "Ted", "3c123", java.sql.Date.valueOf("2020-02-16").toLocalDate()));

		// Actual
		List<ValetParking> actualList = dao.readAll();
		
        Assert.assertEquals(expectedList, actualList);		
    }
	
	@Override
	protected IDataSet getDataSet() throws Exception {
		return new FlatXmlDataSetBuilder().build(new FileInputStream("user.xml"));
	}
	
	// This method will setup database to initial position before testing any method.
	// SOURCE: http://dbunit.sourceforge.net/components.html
    protected DatabaseOperation getSetUpOperation() throws Exception {
        return DatabaseOperation.CLEAN_INSERT;
    }
 
    // This method will clear all the entries after test case run.
    protected DatabaseOperation getTearDownOperation() throws Exception {
        return DatabaseOperation.DELETE_ALL;
    }
    
	/**
     * Override method to set custom properties/features
     */
	@Override	
    protected void setUpDatabaseConfig(DatabaseConfig config) {
		config.setProperty(DatabaseConfig.PROPERTY_DATATYPE_FACTORY, new MySqlDataTypeFactory());
		config.setProperty(DatabaseConfig.PROPERTY_METADATA_HANDLER, new MySqlMetadataHandler());
    }
}
