<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<jsp:include page="../fragments/staticFiles.jsp" />
<jsp:include page="../fragments/bodyHeader.jsp" />
<jsp:include page="../fragments/topNavBar.jsp" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<script type="text/javascript">
	function setDate() {
		var d = new Date();
		document.getElementById("todayDate").innerHTML = d;
	}
</script>

<body onload="setDate()">
	<div class="container">
		<div class="page-header">
			<h1>
				<small>Daily Employees Time Sheets Report</small>
			</h1>
		</div>
		<p>User Logged: ${username}</p>
		<p id="todayDate"></p>
		<div>
			<display:table id="data" name="${employee}" requestURI="" class="table table-striped" pagesize="10" export="true">
				<display:column property="firstName" title="First Name" sortable="true" />
				<display:column property="lastName" title="Last Name" sortable="true">
				</display:column>
				<display:setProperty name="export.pdf" value="true" />
			</display:table>

			<!------------------------
	  Daily Timesheet Entry Form
		-------------------------->
		</div>
	</div>
</body>
</html>