<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%-- Get data from request --%>
<% 
  String status_code = (String) request.getAttribute("error_status_code");
  String exception_type = (String) request.getAttribute("error_exception_type");
  String message = (String) request.getAttribute("error_message");
  String exception = (String) request.getAttribute("error_exception");

%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>Error Page</title>
  <meta name = "author" content = "Pawel Zamorski" /> 
  <meta name="viewport" content="width=device-width, initial-scale=1.0" />
  <link rel="stylesheet" href="styles.css" />
</head>
<body>
  <h1>This is Error Page</h1>
  <a href="index.html">Go to Home Page</a>
  <p>Status Code: <% out.println(status_code); %></p>
  <p>Exception Type: <% out.println( exception_type); %></p>
  <p>Message: <% out.println(message); %></p>
  <p>Exception: <% out.println(exception); %></p>
</body>
</html>