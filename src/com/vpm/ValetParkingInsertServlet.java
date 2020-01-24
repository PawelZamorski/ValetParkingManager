package com.vpm;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ValetParkingInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public void service(HttpServletRequest req, HttpServletResponse res) {
		PrintWriter out = null;
		// Get data from request
		int id = Integer.parseInt(req.getParameter("id"));
		String name = req.getParameter("name");
		String registration = req.getParameter("registration");
		// TODO - validate date: '', wrong format
		LocalDate arrivalDate = LocalDate.parse(req.getParameter("arrival_date"));
		
		ValetParking vp = new ValetParking(id, name, registration, arrivalDate);
		
		// Get data from Model (from database)
		// Test if PrimaryKey is not repeated
		if(ValetParkingDAO.select(vp.getId()) == null) {
			int i = ValetParkingDAO.insert(vp);
			req.setAttribute("noOfInsertedItems", i);			
		}else {
			req.setAttribute("noOfInsertedItems", 0);			
		}

		// Select all data
		// Get data from Model (from database)
		List<ValetParking> dataItems = ValetParkingDAO.selectAll();
		req.setAttribute("dataItems", dataItems);
		
		// Dispatch to View (JSP page)
		RequestDispatcher dispatcher = req.getRequestDispatcher("valet-parking.jsp");
		
		try {
			dispatcher.forward(req, res);
		} catch (ServletException e1) {
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
		} catch (IOException e1) {
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
