package com.vpm;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;

import java.io.IOException;
import java.util.List;

public class ValetParkingDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// Create DAOFactoryMysql using DAOFactory
		DAOFactory mysqlFactory = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
		// Create ValetParkingDAO using DAOFactoryMysql
		ValetParkingDAO vpDAO = mysqlFactory.getValetParkingDAO();
		
		int id = Integer.parseInt(req.getParameter("id"));
		int i = vpDAO.delete(id);

		req.setAttribute("noOfItemsDeleted", i);

		// Select all data
		// Get data from Model (from database)
		List<ValetParking> dataItems = vpDAO.readAll();
		req.setAttribute("dataItems", dataItems);
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("valet-parking.jsp");
		dispatcher.forward(req, res);
	}
}
