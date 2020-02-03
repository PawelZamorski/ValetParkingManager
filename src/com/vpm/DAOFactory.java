package com.vpm;

/**
 * Abstract class DAO Factory
 * - uses Factory Design Pattern
 * - supports creation of different factories: MySql, Oracle, File (TODO - CSV, maybe serializable object)
 * 
 * @author Pawel Zamorski
 * 
 * SOURCE: https://www.oracle.com/technetwork/java/dataaccessobject-138824.html
 *
 */
public abstract class DAOFactory {
	// List of DAO types supported by the factory
	public static final int MYSQL = 1;
	public static final int FILE = 2;
	// public static final int ORACLE = 3;
	
	/**
	 * Returns concrete implementation of ValetParkingDAO interface
	 * 
	 * @param DataSource 
	 * @return ValetParkingDOA
	 */
	public abstract ValetParkingDAO getValetParkingDAO(DataSource ds);
	
	/**
	 * Returns the concrete DAOFactory object based on the DAO types supported by the factory
	 * 
	 * @param whichFactory takes the following as an argument: MYSQL, FILE
	 * @return the concrete DAOFactory object
	 */
	public static DAOFactory getDAOFactory(int whichFactory) {
		System.out.println("Factory is working");
		
		switch(whichFactory) {
			case MYSQL:
				return new DAOFactoryMysql();
			case FILE:
				return new DAOFactoryFile();
			default:
				return null;
		}
	}
}
