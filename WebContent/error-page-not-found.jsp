<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isErrorPage="true" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>Error Page Not Found</title>
  <meta name = "author" content = "Pawel Zamorski" /> 
  <meta name="viewport" content="width=device-width, initial-scale=1.0" />
  <link rel="stylesheet" href="styles.css" />
</head>
<body>
  <h1>This is Error Page Not Found - 404</h1>
  <p><%=response.getStatus() %></p>
  <p><%=exception %></p>
  
  <a href="index.html">Go to Home Page</a>
</body>
</html>