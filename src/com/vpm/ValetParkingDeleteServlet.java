package com.vpm;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class ValetParkingDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final String DATASOURCE_PARAMETERS = "C:/Users/MoioM/eclipseJEE-workspace/ValetParkingManager/WebContent/WEB-INF/data-source-properties.xml";
	
	public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// Create DAOFactoryMysql using DAOFactory
		DAOFactory mysqlFactory = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
		// Specify DataSource
		try(DataSource ds = new DataSourceImpMysql(this.DATASOURCE_PARAMETERS)) {
// NOTE: DAO object uses the same Connection both to delete() method and readAll() method
// NOTE: creating Connection is an expensive operation
			// Create ValetParkingDAO using DAOFactoryMysql
			ValetParkingDAO vpDAO = mysqlFactory.getValetParkingDAO(ds);
			
			int id = Integer.parseInt(req.getParameter("id"));
			int i = vpDAO.delete(id);

			req.setAttribute("noOfItemsDeleted", i);

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
