<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="./fragments/staticFiles.jsp" />
<jsp:include page="./fragments/bodyHeader.jsp" />
<jsp:include page="./fragments/topNavBar.jsp" />
<%@page session="true"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<div class="container">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Employees</title>
</head>
<body>
	<div class="page-header">
	<c:if test="${not empty username}">
		<div class="msg">Logged in: ${username}</div>
	</c:if>
		<h1>
			<small>Employees at Cell Expert</small>
		</h1>
	</div>
	<table class="table table-striped">
		<c:if test="${not empty msg}">
			<div class="msg">${msg}</div>
		</c:if>
		<c:forEach items="${empList}" var="emp">
			<tr>

				<td>${emp.firstName}</td>
				<td>${emp.lastName}</td>
				<td>${emp.email}</td>

			</tr>
		</c:forEach>
	</table>
</body>
<div class="container">
</html>
