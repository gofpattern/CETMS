<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Register Employee</title>
</head>
<body>
<div class="page-header">
  <h1><small>Register Employee</small></h1>
</div>
<form:form class="form-horizontal" method="POST" action="./timesheet"
			modelAttribute="employee">
		<div class="form-group">
			<label for="fname" class="col-sm-2 control-label">First Name</label>
			<div class="col-sm-10">
				<form:input type="text" class="form-control" id="fname" path="firstName"
					placeholder="First Name"/>
			</div>
		</div>
		<div class="form-group">
			<label for="lname" class="col-sm-2 control-label">Last Name</label>
			<div class="col-sm-10">
				<form:input type="text" class="form-control" id="lname" path="lastName"
					placeholder="Last Name"/>
			</div>
		</div>
		
		<div class="form-group">
			<label for="phone" class="col-sm-2 control-label">Phone</label>
			<div class="col-sm-10">
				<form:input type="text" class="form-control" id="phone" path="phone"/>
			</div>
		</div>
		
		<div class="form-group">
			<label for="email" class="col-sm-2 control-label">Email</label>
			<div class="col-sm-10">
				<form:input type="email" class="form-control" id="email" path="email"/>
			</div>
		</div>
		
		<div class="form-group">
			<label for="address" class="col-sm-2 control-label">Address</label>
			<div class="col-sm-10">
				<form:input type="text" class="form-control" id="address" path="address"/>
			</div>
		</div>
		<div class="form-group">
			<label for="empStartDt" class="col-sm-2 control-label">Employment Start Date</label>
			<div class="col-sm-10">
				<form:input type="date" class="form-control" id="empStartDt" path="startDt" />
			</div>
		</div>
		
		<div>
			<div>
				<input type="submit" value="Register">
			</div>
		</div>
		
	</form:form>
</body>
</html>