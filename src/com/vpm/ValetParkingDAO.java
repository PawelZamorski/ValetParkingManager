package com.vpm;

import java.util.List;
import javax.servlet.ServletException;

/**
 * CRUD interface - create, read, update, delete operation
 * It has also readAll method for reading all data from the table
 * 
 * @author MoioM
 *
 */
public interface ValetParkingDAO {

	public int create(ValetParking item) throws ServletException;
	
	public ValetParking read(int id) throws ServletException;
	
	public List<ValetParking> readAll() throws ServletException;
	
	public int update(ValetParking item) throws ServletException;
	
	public int delete(int id) throws ServletException;
}