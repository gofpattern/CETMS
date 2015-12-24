<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Test Page Results</title>
</head>
<body>
<table>
  <c:forEach items="${empList}" var="emp">
   <tr>
     <td>${emp.email}</td>
     <td>${emp.firstName}</td>
   </tr>
  </c:forEach>
</table>
</body>
</html>