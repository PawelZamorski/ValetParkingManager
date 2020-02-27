package com.vpm;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.FileInputStream;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletException;

import org.dbunit.DBTestCase;
import org.dbunit.PropertiesBasedJdbcDatabaseTester;
import org.dbunit.database.DatabaseConfig;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.ext.mysql.MySqlDataTypeFactory;
import org.dbunit.ext.mysql.MySqlMetadataHandler;
import org.dbunit.operation.DatabaseOperation;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/*
 * The following will make imports easier:
 * Add few classes to the FAVOURITES in Eclipse: Window -> Preferences -> Java -> Editor -> Content Assist -> Favourites
 * Add the following classes: Mockito, BDDMockito, org.hamcrest.Matchers, org.hamcrest.CoreMatchers
 */

/*
 * Maven runs all test methods found from the test classes that fulfill these two conditions:
 * - The test classes must be found from the src/test/java directory.
 * - The name of the test class must start or end with the string: Test.
 * 
 * To change it the Surefire plugin may be used.
 */

/*
 * BDUnit extends junit.framework.TestCase
 * 
 * To use new features move to JUnit5 and Database Rider
 * 
 * source: https://database-rider.github.io/database-rider/1.2.7/documentation.html#_strong_introduction_strong
 */

// @RunWith(BlockJUnit4ClassRunner.class)
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

	@Rule
    public ExpectedException thrown = ExpectedException.none();
	
	@Test
	public void testReadAll() throws Exception {
		// Expected
		List<ValetParking> expectedList = Arrays.asList(
				new ValetParking(1, "Ala", "123xc123", java.sql.Date.valueOf("2020-02-18").toLocalDate()),
				new ValetParking(2, java.sql.Date.valueOf("2020-02-17").toLocalDate()),
				new ValetParking(3, null, null, java.sql.Date.valueOf("2020-02-16").toLocalDate()),
				new ValetParking(4, "John", null, java.sql.Date.valueOf("2020-02-15").toLocalDate()));
		
		// Actual
		DataSource ds = mock(DataSource.class);
		when(ds.getConnection()).thenReturn(getConnection().getConnection());
		ValetParkingDAO dao = new ValetParkingDAOMysql(ds);
		
		List<ValetParking> actualList = dao.readAll();

		// assertEquals(Object, Object) from JUnit4/JUnit 5 or assertThat(actual, is(expected)) from Hamcrest 
		// will work only as both equals() and hashCode() are overridden.
        assertEquals(expectedList, actualList);		
    }

	@Test
	public void testRead() throws Exception {
		// Expected
		ValetParking expected = new ValetParking(1, "Ala", "123xc123", java.sql.Date.valueOf("2020-02-18").toLocalDate());
		// Actual
		DataSource ds = mock(DataSource.class);
		when(ds.getConnection()).thenReturn(getConnection().getConnection());
		ValetParkingDAO dao = new ValetParkingDAOMysql(ds);
		ValetParking actual = dao.read(1);
		// Assertion
        assertEquals(expected, actual);		
    }

	@Test
	public void testRead_NoValueOfId() throws Exception {
		// Expected
		ValetParking expected = null;
		// Actual
		DataSource ds = mock(DataSource.class);
		when(ds.getConnection()).thenReturn(getConnection().getConnection());
		ValetParkingDAO dao = new ValetParkingDAOMysql(ds);
		ValetParking actual = dao.read(-1);
		// Assertion
        assertEquals(expected, actual);		
    }

	@Test
	public void testCreate() throws Exception {
		// Expected
		int expected = 1;
		// Actual
		DataSource ds = mock(DataSource.class);
		when(ds.getConnection()).thenReturn(getConnection().getConnection());
		ValetParkingDAO dao = new ValetParkingDAOMysql(ds);
		int actual = dao.create(new ValetParking(5, "Ala", "123xc123", java.sql.Date.valueOf("2020-02-18").toLocalDate()));
		// Assertion
        assertEquals(expected, actual);
    }

	// DBUnit extends junit.framework.TestCase. Use the old way to check exceptions
	@Test
	public void testCreate_Exception() throws Exception {
		DataSource ds = mock(DataSource.class);
		when(ds.getConnection()).thenReturn(getConnection().getConnection());
		ValetParkingDAO dao = new ValetParkingDAOMysql(ds);

		// Test - throw SQLException wrapped into ServletException - integrity constraint (repeated primary key)
		try { 
			dao.create(new ValetParking(1, "Ala", "123xc123", java.sql.Date.valueOf("2020-02-18").toLocalDate()));			
		}catch(ServletException e) {
			
		}
    }

	@Test
	public void testUpdate() throws Exception {
		// Expected
		int expected = 1;
		// Actual
		DataSource ds = mock(DataSource.class);
		when(ds.getConnection()).thenReturn(getConnection().getConnection());
		ValetParkingDAO dao = new ValetParkingDAOMysql(ds);
		int actual = dao.update(new ValetParking(1, "Ala", "123xc123", java.sql.Date.valueOf("2020-02-18").toLocalDate()));
		// Assertion
        assertEquals(expected, actual);
    }

	@Test
	public void testUpdate_NoValueOfId() throws Exception {
		// Expected
		int expected = 0;
		// Actual
		DataSource ds = mock(DataSource.class);
		when(ds.getConnection()).thenReturn(getConnection().getConnection());
		ValetParkingDAO dao = new ValetParkingDAOMysql(ds);
		int actual = dao.update(new ValetParking(-1, "Ala", "123xc123", java.sql.Date.valueOf("2020-02-18").toLocalDate()));
		// Assertion
        assertEquals(expected, actual);
    }
	
	@Test
	public void testDelete() throws Exception {
		// Expected
		int expected = 1;
		// Actual
		DataSource ds = mock(DataSource.class);
		when(ds.getConnection()).thenReturn(getConnection().getConnection());
		ValetParkingDAO dao = new ValetParkingDAOMysql(ds);
		int actual = dao.delete(1);
		// Assertion
        assertEquals(expected, actual);
    }

	@Test
	public void testDelete_NoValueOfId() throws Exception {
		// Expected
		int expected = 0;
		// Actual
		DataSource ds = mock(DataSource.class);
		when(ds.getConnection()).thenReturn(getConnection().getConnection());
		ValetParkingDAO dao = new ValetParkingDAOMysql(ds);
		int actual = dao.delete(-1);
		// Assertion
        assertEquals(expected, actual);
    }
	
	@Override
	protected IDataSet getDataSet() throws Exception {
		return new FlatXmlDataSetBuilder().build(new FileInputStream("car_list_data.xml"));
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
