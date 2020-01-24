<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="com.vpm.ValetParking" %>
<%@ page import="java.util.List" %>
<%@ page import="java.time.LocalDate" %>

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
        <% LocalDate date = vp.getArrivalDate();
           if(date != null) { %>
             <td><% out.println(date); %></td>
         <%} else {%>
             <td><% out.println("null"); %></td>
         <%} %>
      </tr>
      <%
      }
      %>
    </tbody>
  </table>
  <br />
  <form action="valet-parking-insert" method="post" >
    <label for="id">Id:</label>
    <input type="number" id="id" placeholder="Insert id" name="id" required>
    <label for="name">Name:</label>
    <input type="text" id="name" placeholder="Insert name" name="name">
    <label for="registration">Registration:</label>
    <input type="text" id="registration" placeholder="Insert registration" name="registration">
    <label for="date">Arrival date:</label>
    <input type="date" id="date" placeholder="Insert arrival date" name="arrival_date">
    <input type="submit" value="insert">
  </form>
  <br />
  <form action="valet-parking-delete" method="post">
    <label for="id">Id:</label>
    <input type="number" id="id" placeholder="Insert id" name="id" required>
    <input type="submit" value="delete">
  </form>
  <br />
  <form action="valet-parking-update" method="post">
    <label for="id">Id: </label>
    <input type="number" id="id" placeholder="Insert id" name="id" required>
    <label for="name">Name: </label>
    <input type="text" id="name" placeholder="Insert name" name="name">
    <label for="registration">Registration: </label>
    <input type="text" id="registration" placeholder="Insert registration" name="registration">
    <label for="arrival_date">Arrival date: </label>
    <input type="date" id="arrival_date" placeholder="Insert arrival date" name="arrival_date">
    <input type="submit" value="update">
  </form>
</body>
</html>