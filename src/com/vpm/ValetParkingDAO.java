package com.vpm;

import java.util.List;

/**
 * CRUD interface - create, read, update, delete operation
 * It has also readAll method for reading all data from the table
 * 
 * @author MoioM
 *
 */
public interface ValetParkingDAO {

	public int create(ValetParking item);
	
	public ValetParking read(int id);
	
	public List<ValetParking> readAll();
	
	public int update(ValetParking item);
	
	public int delete(int id);
}