<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<html>
<head>
<meta charset="ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" type="text/css" href="resources/standard.css"/>
<title>Login</title>
</head>
<body>
<img src="resources/PugPro.png" class="logo">
<div class="centered content">
<form action="login" method="post">
	<table>
		<tr>
			<td><label for="email">Email:</label></td>
			<td><input type="text" name="email" id="email" /></td>
		</tr>
		<tr>
			<td><label for="password">Password:</label></td>
			<td><input type="password" name="password" id="password" /></td>
		</tr>
		<tr>
			<td><input type="submit" name="submit" id="submit" value="Login" /></td>
		</tr>
	</table>
</form>
<p>First time? <a href="register">Create new account.</a></p>
<p class="error">${ message }</p>
<a href="viewEvents"><button>View Events</button></a>
</div>
</body>
</html>

