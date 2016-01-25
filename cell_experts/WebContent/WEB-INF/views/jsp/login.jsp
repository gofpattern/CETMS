
<!DOCTYPE html>
<html lang="en">
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="./fragments/staticFiles.jsp" />
<jsp:include page="./fragments/bodyHeader.jsp" />
<%@page session="true"%>
<div class="container">
<body>
	<h2>Employee Login</h2>
	<c:url value="/j_spring_security_check" var="login" />
	<!-- <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" /> -->
	<c:if test="${not empty error}">
		<div class="error">${error}</div>
	</c:if>
	<c:if test="${not empty msg}">
		<div class="msg">${msg}</div>
	</c:if>
	<!-- ?${_csrf.parameterName}=${_csrf.token}" enctype="multipart/form-data" -->
	<form name="loginForm" class="form-horizontal" action="${login}" method ='POST' >
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
		<div class="form-group">
			<label for="username" class="col-sm-2 control-label">UserName</label>
			<div class="col-sm-10">
				<input class="form-control" type='text' name='username' id="username"/>
			</div>
		</div>
		<div class="form-group">
			<label for="password" class="col-sm-2 control-label">Password</label>
			<div class="col-sm-10">
				<input type="password" class="form-control" id="inputPassword3"
					name='password' placeholder="Password" />
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
	</form>
</body>
</div>
</html>
