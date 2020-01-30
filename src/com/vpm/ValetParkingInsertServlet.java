package com.vpm;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ValetParkingInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	// Throw exceptions to be deal by servlet container. Set up web.xml. 
	public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// Create DAOFactoryMysql using DAOFactory
		DAOFactory mysqlFactory = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
		// Create ValetParkingDAO using DAOFactoryMysql
		ValetParkingDAO vpDAO = mysqlFactory.getValetParkingDAO();

		// Get data from request
		int id = Integer.parseInt(req.getParameter("id"));
		String name = req.getParameter("name");
		String registration = req.getParameter("registration");
		// TODO - validate date: '', wrong format
		LocalDate arrivalDate = LocalDate.parse(req.getParameter("arrival_date"));
		
		ValetParking vp = new ValetParking(id, name, registration, arrivalDate);
		
		// Get data from Model (from database)
		// Test if PrimaryKey is not repeated
		if(vpDAO.read(vp.getId()) == null) {
			int i = vpDAO.create(vp);				
			req.setAttribute("noOfInsertedItems", i);			
		}else {
			req.setAttribute("noOfInsertedItems", 0);			
		}

		// Select all data
		// Get data from Model (from database)
		List<ValetParking> dataItems = vpDAO.readAll();
		req.setAttribute("dataItems", dataItems);
		
		// Dispatch to View (JSP page)
		RequestDispatcher dispatcher = req.getRequestDispatcher("valet-parking.jsp");
		dispatcher.forward(req, res);
	}
}
