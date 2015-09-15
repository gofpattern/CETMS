<!DOCTYPE html>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>



<html lang="en">

<jsp:include page="./fragments/staticFiles.jsp" />

<body bgcolor="#f46c04">
	<div class="container">
		<div id=header>
			<jsp:include page="./fragments/bodyHeader.jsp" />
		</div>
		<h2>Employee Login</h2>
		<form:form method="POST" action="./Timesheet" modelAttribute="employee">
			<table>
				<tr>

					<td>User Name: <form:input path="user" /></td>
				</tr>
				<tr>
					<td>Password: <form:input path="pwd" /></td>
				</tr>

				<tr>
					<td colspan="2"><input type="submit" value="Submit" /></td>
				</tr>
			</table>
		</form:form>

	</div>
	<div id=footer>
		<jsp:include page="./fragments/footer.jsp" />
	</div>
</body>

</html>
