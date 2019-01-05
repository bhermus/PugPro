<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<html>
<head>
<meta charset="ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" type="text/css" href="resources/standard.css"/>
<title>Register</title>
</head>
<body>
<img src="resources/PugPro.png" class="logo">
<div class="centered content">
<form:form action="register" modelAttribute="user" >
	<table>
		<tr>
			<td><label for="username">Username:</label></td>
			<td><form:input type="text" name="username" id="username" path="username"/></td>
		</tr>
		<tr>
			<td colspan="2"><form:errors path="username" cssClass="error" /></td>
		</tr>
		<tr>
			<td><label for="email">Email:</label></td>
			<td><form:input type="text" name="email" id="email" path="email"/></td>
		</tr>
		<tr>
			<td colspan="2"><form:errors path="email" cssClass="error" /></td>
		</tr>
		<tr>
			<td><label for="password">Password:</label></td>
			<td><form:input type="text" name="password" id="password" path="password" /></td>
		</tr>
		<tr>
			<td colspan="2"><form:errors path="password" cssClass="error" /></td>
		</tr>
		<tr>
			<td><label for="confirmPassword">Confirm password:</label>
			<td><input type="text" name="confirmPassword" id="confirmPassword" /></td>
		</tr>
		<tr>
			<td colspan="2"><form:errors path="" cssClass="error" /></td>
		</tr>
		<tr>
			<td><input type="submit" name="submit" id="submit" value="Register" /></td>
		</tr>
	</table>
	<a href="login">&lt; Back</a>
</form:form>
</div>
</body>
</html>