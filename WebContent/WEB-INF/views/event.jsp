<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<html>
<head>
<meta charset="ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" type="text/css" href="../resources/standard.css"/>
<title>${ event.title }</title>
</head>
<body>
<img src="../resources/PugPro.png" class="logo">
<div class="centered content">
<jsp:useBean id="userDao" class="com.pugpro.DAO.UserDAO"></jsp:useBean>
<jsp:useBean id="instanceDao" class="com.pugpro.DAO.InstanceDAO"></jsp:useBean>
<h3>${ event.title }</h3>
<p>${ instanceDao.getInstanceByID(event.instanceID).instanceName } - Event by ${ userDao.getUserByID(event.ownerID).username }</p>
<p>${ event.description }</p>
<p>${ event.eventDate } at ${ event.eventTime }</p>

<form action="${ event.eventID }" method="POST">
	<input type="submit" name="submit" id="submit" value="Sign Up" />
	<p style="color:red"> ${ message }</p>
</form>

<a href="../">Home</a>

<p>Users attending this event:</p>
<c:forEach items="${ attendees }" var="attendee" varStatus="loop">
	<p class=indent>${ loop.index+1}. ${ attendee }</p>
</c:forEach>
</div>
</body>
</html>