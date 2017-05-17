<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<!-- Login Test seite  -->
<div>
<center>
	<form action="LoginController" method="post">
		<label>Name</label>
		<br>
		<input type="text" name="name" placeholder="Username" required="">
		<br>
		<label>Passwort</label>
		<br>
		<input type="password" name="pwd" placeholder="Password" required="">
		<br>
		<input type="hidden" name="pageName" value="login">
		<button type="submit">Bestädigen</button>
	</form>
</center>
</div>

</body>
</html>