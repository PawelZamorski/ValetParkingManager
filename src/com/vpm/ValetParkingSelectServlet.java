package com.vpm;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class ValetParkingSelectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final String DATASOURCE_PARAMETERS = "C:/Users/MoioM/eclipseJEE-workspace/ValetParkingManager/WebContent/WEB-INF/data-source-properties.xml";
	
	public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		// Create DAOFactoryMysql using DAOFactory
		DAOFactory mysqlFactory = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
		// Specify DataSource
		try(DataSource ds = new DataSourceImpMysql(this.DATASOURCE_PARAMETERS)) {
			// Create ValetParkingDAO using DAOFactoryMysql
			ValetParkingDAO vpDAO = mysqlFactory.getValetParkingDAO(ds);
			// Get data from Model (from database)
			List<ValetParking> dataItems = vpDAO.readAll();
			req.setAttribute("dataItems", dataItems);
		} catch (ClassNotFoundException | SQLException e) {
			throw new ServletException(e);
		}
		// Dispatch to View (JSP page)
		RequestDispatcher dispatcher = req.getRequestDispatcher("valet-parking.jsp");
		dispatcher.forward(req, res);
	}
}
