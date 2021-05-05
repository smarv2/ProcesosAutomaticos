<%-- 
    Document   : verLogin
    Created on : 4/05/2021, 08:51:18 PM
    Author     : smarv
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Ver datos Login</title>
</head>
<body>
	<h1>Ver Login</h1>
	<%="Usuario: " + request.getParameter("usuario")%>
	<br>
	<%="Constraseña: " + request.getParameter("contrasenia")%>
	<br>
	<a href=?parametro=login>Ir al login</a>
</body>
</html>
