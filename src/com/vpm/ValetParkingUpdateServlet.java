package com.vpm;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

public class ValetParkingUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = -902991268328472258L;
	
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// Create DAOFactoryMysql using DAOFactory
		DAOFactory mysqlFactory = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
		// Create ValetParkingDAO using DAOFactoryMysql
		ValetParkingDAO vpDAO = mysqlFactory.getValetParkingDAO();
		
		int id = Integer.parseInt(req.getParameter("id"));
		String name = req.getParameter("name");
		String registration = req.getParameter("registration");
		LocalDate arrivalDate = LocalDate.parse((req.getParameter("arrival_date")));
		
		ValetParking item = new ValetParking(id, name, registration, arrivalDate);
		
		int i = vpDAO.update(item);
		req.setAttribute("updated", i);
		// Select all data
		// Get data from Model (from database)
		List<ValetParking> dataItems = vpDAO.readAll();
		req.setAttribute("dataItems", dataItems);

		RequestDispatcher dispatcher = req.getRequestDispatcher("valet-parking.jsp");
		dispatcher.forward(req, res);
	}
}
