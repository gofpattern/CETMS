
<!DOCTYPE html>
<html lang="en">
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="./fragments/staticFiles.jsp" />

<%@page session="true"%>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Admin Login Page </title>
</head>
<body>
	<h2>Employee Login webapp</h2>
	<c:url value="/j_spring_security_check" var="login" />
	<input type="hidden" name="${_csrf.parameterName}"
		value="${_csrf.token}" />
	<form:form class="form-horizontal" modelAttribute="user"
		action="${login}">
		<div class="form-group">
			<label for="inputEmail3" class="col-sm-2 control-label">UserName</label>
			<div class="col-sm-10">
				<form:input class="form-control" path="username" type='text' name='username' />
			</div>
		</div>
		<div class="form-group">
			<label for="inputPassword3" class="col-sm-2 control-label">Password</label>
			<div class="col-sm-10">
				<form:input type="password" class="form-control" id="inputPassword3"
					path="password" name='password' placeholder="Password" />
			</div>
		</div>
		<div>

			<select>
				<option>Admin</option>
				<option>Employee</option>
			</select>

		</div>
		<div class="form-group">
			<div class="col-sm-offset-2 col-sm-10">
				<div class="checkbox">
					<label> <input type="checkbox"> Remember me
					</label>
				</div>
			</div>
		</div>
		<div class="form-group">
			<div class="col-sm-offset-2 col-sm-10">
				<button type="submit" class="btn btn-default">Sign in</button>
			</div>
		</div>
	</form:form>
</body>
</html>