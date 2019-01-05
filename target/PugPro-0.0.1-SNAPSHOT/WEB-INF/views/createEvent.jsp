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
<title>New Event</title>
</head>
</head>
<script type="text/javascript">
function isNumberKey(evt){
    var charCode = (evt.which) ? evt.which : event.keyCode
    if (charCode > 31 && (charCode < 48 || charCode > 57))
        return false;
    return true;
}
</script>
<body>
<img src="resources/PugPro.png" class="logo">
<div class="content">
<form:form action="createEvent" modelAttribute="event" >
	<table class="centered">
		<tr>
			<td><label for="instances">Instance:</label></td>
			<td><form:select name="instances" id="instances" path="${ instance.instanceName }" >
				<c:forEach items="${ sessionScope.instances }" var="instance">
					<form:option value="${ instance.instanceName }" />
				</c:forEach>
			</form:select></td>
		<tr>
			<td><label for="title">Title: </label></td>
			<td><form:input type="text" name="title" id="title" path="title" /></td>
			<td><form:errors path="title" cssClass="error" /></td>
		</tr>
		<tr>
			<td><label for="minilvl">Minimum item level:</label></td>
			<td><form:input type="text" name="minilvl" id="minilvl" maxlength="3" size="1" onkeypress="return isNumberKey(event)" path="minilvl" /></td>
			<td><form:errors path="minilvl" cssClass="error" /></td>
		</tr>
		<tr>
			<td valign="top"><label for="description" class="description">Description:</label></td>
			<td><form:textarea name="description" id="description" cols="30" rows="8" maxlength="1000" path="description" /></td>
		</tr>
		<tr>
			<td><label for="eventDate">Event date:</label></td>
			<td><form:input type="date" name="eventDate" id="eventDate" path="eventDate" /></td>
			<td><form:errors path="eventDate" cssClass="error" /></td>
		</tr>
		<tr>
			<td><label for="eventTime">Event time:</label></td>
			<td><form:input type="time" name="eventTime" id="eventTime" path="eventTime"  /></td>
			<td><form:errors path="eventTime" cssClass="error" /></td>
		</tr>	
		<tr>
			<td><input type="submit" name="submit" id="submit" value="Create" /></td>
		</tr>
	</table>
	
</form:form>
<div style="text-align:center"><a href="home">&lt; Back</a></div>
</div>
</body>
</html>