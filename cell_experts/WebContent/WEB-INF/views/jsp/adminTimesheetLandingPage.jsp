<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
 <script>
  $(function() {
    $( "#datepicker" ).datepicker();
  });
  </script>
</head>
<div class="container">
	<div class="page-header">
		<h1>
			<small>Employees Time Sheets</small>
		</h1>
	</div>
	<small>User Logged: ${username}</small>
	<body>
		<!------------------------
	  Form
	-------------------------->
		<form:form class="form-horizontal" modelAttribute="employee" method="POST" action="/cell_experts/adminSelectEmployee">
			<div class="dropdown">
				<button class="btn btn-primary dropdown-toggle" type="button"
					data-toggle="dropdown">
					Select Employee <span class="caret"></span>
				</button>
				<ul class="dropdown-menu">
					<c:forEach items="${empList}" var="emp">
						<li><a
							href="adminSelectEmployee?id=${emp.employeeId}">${emp.firstName}</a></li>
					</c:forEach>
				</ul>
			</div>
		</form:form>
		<!------------------------
	  Daily Timesheet Entry Form
	-------------------------->
		<form:form class="form-horizontal" modelAttribute="timesheetbean" method="POST" action="/cell_experts/adminSaveTime">
			<%-- <jsp:include page="./dailyTimesheetEntry.jsp" /> --%>
			Pick Date: <form:input type="date" path="pickedDate" id="datepicker"/>
			<table class="table">
				<caption>Employee Name: ${employee.firstName} ${employee.lastName}</caption>
				<form:hidden path="employeeId" />
				<form:hidden path="lastuser" />
				<thead>
					<tr>
						<th>#</th>
						<th>Day</th>
						<th>Hours</th>
						<th>Minutes</th>
						<th>Cash</th>
						<th>Notes</th>
					</tr>

				</thead>
				<tbody>
					<tr>
						<th scope="row">#</th>
						<td>
							<div class="form-group">
								<div class="col-sm-10">
									<form:input type="text" class="form-control" path="day" id="day"/>
								</div>
							</div>
						</td>
						<td>
							<div class="form-group">
								<div class="col-sm-10">
									<form:input type="text" class="form-control" path="hours" id="hours"/>
								</div>
							</div>
						</td>
						<td>
							<div class="form-group">
								<div class="col-sm-10">
									<form:input type="text" class="form-control" path="minutes" id="minutes"/>
								</div>
							</div>
						</td>
						<td>
							<div class="form-group">
								<div class="col-sm-10">
									<form:input type="text" class="form-control" path="cash" id="cash"/>
								</div>
							</div>
						</td>
						<td>
							<div class="form-group">
								<div class="col-sm-15">
									<form:input type="text" class="form-control" path="notes" id="notes"/>
								</div>
							</div>
						</td>

					</tr>

				</tbody>
			</table>
			<div>
				<div>
					<input type="submit" value="submit Time"/>
				</div>
			</div>
		</form:form>






	</body>
</div>
</html>