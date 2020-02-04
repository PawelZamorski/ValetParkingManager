package com.vpm;

import static org.junit.Assert.*;

import java.io.FileInputStream;

import org.dbunit.IDatabaseTester;
import org.dbunit.JdbcDatabaseTester;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.junit.Test;

import junit.framework.TestCase;

public class DAOValetParkingTest extends TestCase {
	
	// Constructor
	public DAOValetParkingTest(String name) {
		super(name);
	}

	IDatabaseTester databaseTester;
	 
	public void setUp() throws Exception{
	databaseTester = new JdbcDatabaseTester("com.mysql.jdbc.Driver","jdbc:mysql://localhost/test","root", "vampire");
	IDataSet dataSet = new FlatXmlDataSetBuilder().build(new FileInputStream("customer- init.xml"));
	databaseTester.setDataSet(dataSet); databaseTester.onSetup();                                                       }                                                                          
	 
	public void testInsert() {
	//your test method here
	}

	@Test
	public void test() {
		fail("Not yet implemented");
	}
	
	public void tearDown() throws Exception {
		databaseTester.onTearDown();
	}



}
