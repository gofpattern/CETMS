<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<div class="container">
<body>

<nav class="navbar navbar-default" role="navigation">


	<div class="navbar-inner">
		<div class="navbar-header">
			<a class="navbar-brand" href="#">Admin DashBoard</a>
		</div>
		<ul class="nav navbar-nav">
			<li class="active"><a href="#">Stores</a></li>
			
			<li class="dropdown"><a href="#" class="dropdown-toggle"
				data-toggle="dropdown"> Employees <b class="caret"></b>
			</a>

				<ul class="dropdown-menu">
					<li><a href="adminRegisterEmployee">Register Employees</a></li>
					<li><a href="findEmployees">Find Employees</a></li>
					<li class="divider"></li>
					<li><a href="#">Separated link</a></li>

					<li class="divider"></li>
					<li><a href="#">One more separated link</a></li>
				</ul></li>

			<li class="dropdown"><a href="#" class="dropdown-toggle"
				data-toggle="dropdown"> Timesheet Reporting <b class="caret"></b>
			</a>

				<ul class="dropdown-menu">
					<li><a href="#">Weekly Timesheets</a></li>
					<li><a href="adminTimesheetLandingPage">Daily Timesheets</a></li>
					<li><a href="#">Monthly TimeSheet Reports</a></li>
					<li><a href="#">Annual TimeSheet Reports</a></li>
					<li class="divider"></li>
					<li><a href="#">Separated link</a></li>

					<li class="divider"></li>
					<li><a href="#">One more separated link</a></li>
				</ul></li>

		</ul>
	</div>

</nav>
</body>
</div>
</html>