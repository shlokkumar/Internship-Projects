<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%@ page session="true"%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>options</title>
</head>
<body>
	<%
		String s = (String) session.getAttribute("nm");
		out.println("<h1>Welcome" + s + "</h1>");
	%>


	<form action=/SimpleServletProject/show>
		1. <input type="submit" value="Show Your Record" />
	</form>
	<br>
	<form action=/SimpleServletProject/del>
		2.<input type="submit" value="Delete Your Record" />
	</form>
	<br>
	<form action=/SimpleServletProject/showal>
		3.<input type="submit" value="Show All Record" />
	</form>
	<br>
	<form action="Update.html">
		4.<input type="submit" value="update your record" />
	</form>
	<br>
	<form action="Logon.html">
		5.<input type="submit" value="Switch User" />
	</form>

</body>
</html>