package com.vpm;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;

import java.io.PrintWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

public class ValetParkingUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = -902991268328472258L;
	
	public void doPost(HttpServletRequest req, HttpServletResponse res) {
		PrintWriter out = null;
		
		int id = Integer.parseInt(req.getParameter("id"));
		String name = req.getParameter("name");
		String registration = req.getParameter("registration");
		LocalDate arrivalDate = LocalDate.parse((req.getParameter("arrival_date")));
		
		ValetParking item = new ValetParking(id, name, registration, arrivalDate);
		
		int i = ValetParkingDAO.update(item);
		req.setAttribute("updated", i);
		// Select all data
		// Get data from Model (from database)
		List<ValetParking> dataItems = ValetParkingDAO.selectAll();
		req.setAttribute("dataItems", dataItems);

		RequestDispatcher dispatcher = req.getRequestDispatcher("valet-parking.jsp");
		try {
			dispatcher.forward(req, res);
		}catch (ServletException e1) {
			try {
				out = res.getWriter();
				out.println("Servlet not found");
			}catch(IOException ioEx) {
				ioEx.printStackTrace();
			}catch(Exception e) {
				e.printStackTrace();
			}finally {
				if(out != null) {
					out.flush();
				}
			}
			e1.printStackTrace();
		}catch (IOException e1) {
			e1.printStackTrace();
			try {
				out = res.getWriter();
				out.println("Something went wrong");
			}catch(IOException ioEx) {
				ioEx.printStackTrace();
			}catch(Exception e) {
				e.printStackTrace();
			}finally {
				if(out != null) {
					out.flush();
				}
			}
		}
	}
}
