package com.vpm;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class ValetParkingUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = -902991268328472258L;
	private final String DATASOURCE_PARAMETERS = "C:/Users/MoioM/eclipseJEE-workspace/ValetParkingManager/WebContent/WEB-INF/data-source-properties.xml";
	
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// Create DAOFactoryMysql using DAOFactory
		DAOFactory mysqlFactory = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
		// Specify DataSource
		try(DataSource ds = new DataSourceImpMysql(this.DATASOURCE_PARAMETERS)) {
			// Create ValetParkingDAO using DAOFactoryMysql
			ValetParkingDAO vpDAO = mysqlFactory.getValetParkingDAO(ds);
			
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
		} catch (ClassNotFoundException | SQLException e) {
			throw new ServletException(e);
		}
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("valet-parking.jsp");
		dispatcher.forward(req, res);
	}
}
