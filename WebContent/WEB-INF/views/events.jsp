<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<html>
<head>
<meta charset="ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" type="text/css" href="resources/standard.css"/>
<title>Events</title>
<style>
td{
	padding:10px;
}
</style>
</head>
<body>
<img src="resources/PugPro.png" class="logo">
<div class="centered content">
<table>
	<tr style="text-align:center">
		<th>Title</th>
		<th>Instance</th>
		<th>Date</th>
		<th>Time</th>
	</tr>
	<c:forEach items="${ events }" var="event" >
		<tr>
			<td><a href="eventPage/${ event.eventID }" >${ event.title }</a></td>
			<td>${ instances[event.instanceID] }</td> 
			<td>${ event.eventDate }</td>
			<td>${ event.eventTime }</td>
		</tr>
	</c:forEach>
</table>
<div style="text-align:center"><a href="home">&lt; Back</a></div>
</div>
</body>
</html>