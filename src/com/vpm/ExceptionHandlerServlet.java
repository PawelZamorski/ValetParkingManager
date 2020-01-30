package com.vpm;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;

public class ExceptionHandlerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void service(HttpServletRequest req, HttpServletResponse res) {
		// The request attributes are set with the followings:
	// SOURCE: https://tomcat.apache.org/tomcat-7.0-doc/servletapi/constant-values.html
		Integer statusCode = (Integer) req.getAttribute("javax.servlet.error.status_code");
	// The same code by using REquestDispatcher constants, but it is better to use constants:
	// Integer statusCode = (Integer) req.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);

		Class<?> exceptionType = (Class<?>) req.getAttribute(RequestDispatcher.ERROR_EXCEPTION_TYPE);
		String message = (String) req.getAttribute(RequestDispatcher.ERROR_MESSAGE);
		Throwable exception = (Throwable) req.getAttribute(RequestDispatcher.ERROR_EXCEPTION);
		String requestUri = (String) req.getAttribute(RequestDispatcher.ERROR_REQUEST_URI);
		String servletName = (String) req.getAttribute(RequestDispatcher.ERROR_SERVLET_NAME);
		
		System.out.println("Status Code: " + statusCode);
		System.out.println("Exception Type: " + exceptionType);
		System.out.println("Message: " + message);
		System.out.println("Exception: " + exception); // if it is null it will print 'null'
		System.out.println("Request "
				+ "URI: " + requestUri);
		System.out.println("Servlet Name: " + servletName);

		// Set requests' attributes (String type) to use in jsp file
		// Check for null. toString() method throws NullPointerException if the object is null
		if(statusCode != null) {
			req.setAttribute("error_status_code", statusCode.toString());
		}else {
			req.setAttribute("error_status_code", "unknown");
		}
		if(exceptionType != null) {
			req.setAttribute("error_exception_type", exceptionType.toString());
		}else {
			req.setAttribute("error_exception_type", "unknown");
		}
		if(message != null) {
			req.setAttribute("error_message", message);
		}else {
			req.setAttribute("error_message", "unknown");
		}
		if(exception != null) {
			req.setAttribute("error_exception", exception);
		}else {
			req.setAttribute("error_exception", "unknown");			
		}
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("error-page.jsp");
		try {
			dispatcher.forward(req, res);
		} catch (ServletException e) {
			System.out.println("ServletException in ExceptionHandlerServlet");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("IOException in ExceptionHandlerServlet");
			e.printStackTrace();
		}
	}
}
