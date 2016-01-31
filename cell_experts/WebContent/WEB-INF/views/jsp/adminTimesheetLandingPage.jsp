<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="./fragments/staticFiles.jsp" />
<jsp:include page="./fragments/bodyHeader.jsp" />
<jsp:include page="./fragments/topNavBar.jsp" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<script type="text/javascript">
	function setDate() {
		var d = new Date();
		document.getElementById("todayDate").innerHTML = d;
	}

	function setPickedDate() {

		var date = document.getElementById("datepicker").value;
		$('#dropdown a').each(
				function() {

					var id = $(this).attr('id');
					var url = $(this).attr("href",'adminSelectEmployee?id=' + id + '&dateselected='+ date);
					//alert($(this).attr("href"));

				})
	}
</script>
<body onload="setDate()">
	<div class="container">
		<div class="page-header">
			<h1>
				<small>Employees Time Sheets</small>
			</h1>
		</div>
		<p>User Logged: ${username}</p>
		<p id="todayDate"></p>
		<!------------------------
	  Daily Timesheet Entry Form
		-------------------------->
		<form:form class="form-horizontal" modelAttribute="timesheetbean" method="POST" action="/cell_experts/adminSaveTime">
			<div class="row">
				<div class="col-xs-6">
					<div class="row">
						<div class="col-md-4">
							<form:label path="pickedDate">Pick Timesheet Date:</form:label>
						</div>
						<div class="col-md-4">
							<form:input type="date" path="pickedDate" onchange="setPickedDate()" id="datepicker"></form:input>
						</div>
					</div>
				</div>
				<div class="col-xs-6">
					<div id="dropdown" class="dropdown">
						<button class="btn btn-primary dropdown-toggle" type="button" data-toggle="dropdown">
							Select Employee <span class="caret"></span>
						</button>
						<ul class="dropdown-menu">
							<c:forEach items="${empList}" var="emp">
								<li><a id="${emp.employeeId}" href="">${emp.firstName}</a></li>
							</c:forEach>
						</ul>
					</div>
				</div>
			</div>

			<table class="table">
				<caption>Selected Employee: ${employee.firstName} ${employee.lastName}</caption>
				<caption>Timesheet Date: ${timesheetbean.pickedDate}</caption>
				<form:hidden path="employeeId" />
				<form:hidden path="lastuser" />
				<form:hidden path="firstname" />
				<form:hidden path="lastname" />
				<form:hidden path="pickedDate" />

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
									<form:input type="text" class="form-control" path="day" id="day" />
								</div>
							</div>
						</td>
						<td>
							<div class="form-group">
								<div class="col-sm-10">
									<form:input type="text" class="form-control" path="hours" id="hours" />
								</div>
							</div>
						</td>
						<td>
							<div class="form-group">
								<div class="col-sm-10">
									<form:input type="text" class="form-control" path="minutes" id="minutes" />
								</div>
							</div>
						</td>
						<td>
							<div class="form-group">
								<div class="col-sm-10">
									<form:input type="text" class="form-control" path="cash" id="cash" />
								</div>
							</div>
						</td>
						<td>
							<div class="form-group">
								<div class="col-sm-15">
									<form:input type="text" class="form-control" path="notes" id="notes" />
								</div>
							</div>
						</td>

					</tr>

				</tbody>
			</table>
			<div>
				<div>
					<input type="submit" value="submit Time" />
				</div>
			</div>
		</form:form>
	</div>
</body>
</html>