<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<jsp:include page="../fragments/staticFiles.jsp" />
<jsp:include page="../fragments/bodyHeader.jsp" />
<jsp:include page="../fragments/topNavBar.jsp" />

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<div class="container">
	<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
	</head>
	<body>
	<c:if test="${not empty username}">
		<div class="msg">Logged in: ${username}</div>
	</c:if>
		<h3>${title}</h3>
		${message}	
	</div>

	</body>
</div>
</html>
