<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="com.vpm.ValetParking" %>
<%@ page import="java.util.List" %>

<%-- Get data from request --%>
<%
  List<ValetParking> data = null;
  Object object = request.getAttribute("dataItems");
  if(object instanceof List){
	  if(((List<?>)object).size() > 0 && (((List<?>)object).get(0) instanceof ValetParking)) {
		  data = (List<ValetParking>)object;
	  }
  }
%>

<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>Valet Parking</title>
  <meta name = "author" content = "Pawel Zamorski" /> 
  <meta name="viewport" content="width=device-width, initial-scale=1.0" />
  <link rel="stylesheet" href="styles.css" />
</head>
<body>
  <h1>Valet parking - all data</h1>  
  <table>
    <caption>Valet parking</caption>
    <thead>
      <tr>
        <th>ID</th>
        <th>Name</th>
        <th>Registration</th>
        <th>Date of Arrival</th>
      </tr>
    </thead>
    <tbody>
      <%
      for (ValetParking vp : data) {
      %>
      <tr>
        <td><% out.println(vp.getId()); %></td>
        <td><% out.println(vp.getName()); %></td>
        <td><% out.println(vp.getRegistration()); %></td>
        <td><% out.println(vp.getArrivalDate().toString()); %></td>
      </tr>
      <%
      }
      %>
    </tbody>
  </table>  
</body>
</html>