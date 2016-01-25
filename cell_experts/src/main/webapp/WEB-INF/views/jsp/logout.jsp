<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page session="true"%>
<html>
<body>
	<h1>Title : ${title}</h1>
	<h1>Message:</h1>
<sec:authorize access="hasRole('ROLE_USER')">
	<c:url value="/j_spring_security_logout" var="logoutUrl" />
	<form action="${logoutUrl}" method='POST' id="logoutForm">
		<input type="hidden" name="${_csrf.parameterName}"
			value="${_csrf.token}" />
	</form>
	<script>
		function formSubmit() {
			document.getElementById("logoutForm").submit();
		}
	</script>

	<c:if test="${pageContext.request.userPrincipal.name != null}">
		Welcome : ${pageContext.request.userPrincipal.name} |<h2><a href="javascript:formSubmit()"> Logout</a></h2>
	</c:if>
	<c:if test="${pageContext.request.userPrincipal.name == null}">
		<h2>You are not logged in. Please Login</a></h2>
	</c:if>
</sec:authorize>
</body>
</html>