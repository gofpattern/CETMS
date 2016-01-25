<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<jsp:include page="./fragments/staticFiles.jsp" />
<%@ taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Test Page</title>
</head>
<body>
	<div class="container">
		<jsp:include page="./dailyTimesheetEntry.jsp" />
	</div>
	<!--Content -->
	<table>
  <c:forEach items="${empList}" var="emp">
   <tr>
     <td>${emp.email}</td>
     <td>${emp.firstName}</td>
   </tr>
  </c:forEach>
</table>
	<!--Content  -->

	
</body>
</html>