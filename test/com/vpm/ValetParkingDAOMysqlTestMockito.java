package com.vpm;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.FileInputStream;
import java.util.Arrays;
import java.util.List;

import org.dbunit.Assertion;
import org.dbunit.DBTestCase;
import org.dbunit.PropertiesBasedJdbcDatabaseTester;
import org.dbunit.database.DatabaseConfig;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.ext.mysql.MySqlDataTypeFactory;
import org.dbunit.ext.mysql.MySqlMetadataHandler;
import org.dbunit.operation.DatabaseOperation;
import org.junit.Test;

/*
 * The following will make imports easier:
 * Add few classes to the FAVOURITES in Eclipse: Window -> Preferences -> Java -> Editor -> Content Assist -> Favourites
 * Add the following classes: Mockito, BDDMockito, org.hamcrest.Matchers, org.hamcrest.CoreMatchers
 */

public class ValetParkingDAOMysqlTestMockito extends DBTestCase {
	
	// Constructor. Set up DB properties.
	public ValetParkingDAOMysqlTestMockito(String name) {
		super(name);
		System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_DRIVER_CLASS, "com.mysql.cj.jdbc.Driver");
		System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_CONNECTION_URL, "jdbc:mysql://localhost:3306/VALET_PARKING?serverTimezone=UTC");
		System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_USERNAME, "vpuser");
		System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_PASSWORD, "password");
		System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_SCHEMA, "valet_parking");
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
		DataSource ds = mock(DataSource.class);
		when(ds.getConnection()).thenReturn(getConnection().getConnection());
		ValetParkingDAO dao = new ValetParkingDAOMysql(ds);
		
		List<ValetParking> actualList = dao.readAll();

		// assertEquals(Object, Object) from JUnit4/JUnit 5 or assertThat(actual, is(expected)) from Hamcrest 
		// will work only as both equals() and hashCode() are overridden.
        assertEquals(expectedList, actualList);		
    }
	
	@Override
	protected IDataSet getDataSet() throws Exception {
		return new FlatXmlDataSetBuilder().build(new FileInputStream("user.xml"));
	}
	
	// This method will setup database to initial position before testing any method.
	// SOURCE: http://dbunit.sourceforge.net/components.html
    protected DatabaseOperation getSetUpOperation() throws Exception {
        return DatabaseOperation.REFRESH;
    }
 
    // This method will clear all the entries after test case run.
    protected DatabaseOperation getTearDownOperation() throws Exception {
        return DatabaseOperation.NONE;
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
