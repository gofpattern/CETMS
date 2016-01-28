<%-- <%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<jsp:include page="./fragments/staticFiles.jsp" />
<%@ taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Test Page</title>
</head>
<body>
	<div class="container">
		<jsp:include page="./dailyTimesheetEntry.jsp" />
	</div>
	<!--Content -->
	<table>
  <c:forEach items="${empList}" var="emp">
   <tr>
     <td>${emp.email}</td>
     <td>${emp.firstName}</td>
   </tr>
  </c:forEach>
</table>
	<!--Content  -->

	
</body>
</html> --%>

<!doctype html>
<html>
<head>
<title>Wifi router</title>

   <meta charset="utf-8" />
   <meta http-equiv="Content-type" content="text/html; charset=utf-8" />
   <meta name="viewport" content="width=device-width, initial-scale=1" />
       
</head>
<h1>Hello World</h1>
<h2>Few thing you must know about wi-fi</h2>
<img src="https://upload.wikimedia.org/wikipedia/commons/thumb/3/34/Linksys-Wireless-G-Router.jpg/220px-Linksys-Wireless-G-Router.jpg"/>

<body>
<iframe width="560" height="315" align="right" src="https://www.youtube.com/embed/_mQvPzJOQEI" frameborder="0" allowfullscreen></iframe>

<p>A wireless router is a device that performs the functions of a router and also includes the functions of a wireless access point. It is used to provide access to the Internet or a private computer network. It can function in a wired LAN (local area network), in a wireless-only LAN (WLAN), or in a mixed wired/wireless network, depending on the manufacturer and model..</p>
<hr />

<h3>About the Router</h3>
<ul>
<li>It Has a antenna</li>
<li>Run on <strong>DC</strong>  power</li>
<li>It require a phone line to get internet access</li>

</ul>

<h3>Operating Requirements</h3>
<ol>
<li>110-250volts,50/60Hz</li>
<li>Temprature 80&#8451;</li>


</ol>
<hr />
<table>
<tr><th>Port</th><th>Led,s</th><th>Switches</th></tr>
<tr><td>4</td><td>5</td><td>1</td></tr>
<table/>
<br /><br />

<p><strong><em>If you want to know more?</em></strong></p>

<form action="https://en.wikipedia.org/wiki/Wireless_router" />
<input type="submit" value="Click me" />

</form>








</body>
</html>