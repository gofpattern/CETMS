<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="./fragments/staticFiles.jsp" />
<jsp:include page="./fragments/bodyHeader.jsp" />
<jsp:include page="./fragments/topNavBar.jsp" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
</head>
<div class="container">
	<div class="page-header">
		<h1>
			<small>Employees Time Sheets</small>
		</h1>
	</div>
	<body>
		<!------------------------
	  Form
	-------------------------->
		<form:form class="form-horizontal" modelAttribute="employee" method="POST" action="/cell_experts/adminTimesheetSearch">
			<div class="dropdown">
				<button class="btn btn-primary dropdown-toggle" type="button" data-toggle="dropdown">
					Select Employee <span class="caret"></span>
				</button>
				<ul class="dropdown-menu">
					<c:forEach items="${empList}" var="emp">
						<li><a href="<c:url value="/adminTimesheetSearch" var="actionURL"/>">${emp.firstName}</a></li>
					</c:forEach>
				</ul>
			</div>
		</form:form>
		<!------------------------
	  Daily Timesheet Entry Form
	-------------------------->
	<form:form class="form-horizontal" modelAttribute="timesheetbean" method="POST" action="/cell_experts/adminSaveTime">
		<jsp:include page="./dailyTimesheetEntry.jsp" />
	</form:form>






	</body>
</div>
</html>