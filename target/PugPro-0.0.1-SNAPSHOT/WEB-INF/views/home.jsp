<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<html>
<head>
<meta charset="ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link href="<c:url value="/resources/standard.css" />" rel="stylesheet">
<title>Home</title>
</head>

<body>
<img src="resources/PugPro.png" class="logo">
<div class="centered content">
<h3>Welcome, ${ user.username }!</h3>
<c:choose>
	<c:when test="${ not empty events }" >
		<p>Events you are currently signed up to:</p>
		<c:forEach items="${ events }" var="event" >
			<p><a href="eventPage/${event.eventID }">${ event.title }</a><p>
		</c:forEach>
	</c:when>
	<c:otherwise>
		<p>You are not currently signed up to any events.</p>
	</c:otherwise>
</c:choose>
<a href="viewEvents"><button>View Events</button></a>
<a href="createEvent"><button>New Event</button></a>
<br>
<a href="logout">logout</a>
</div>
</body>
</html>