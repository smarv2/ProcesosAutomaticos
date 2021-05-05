<%-- 
    Document   : login
    Created on : 4/05/2021, 08:48:19 PM
    Author     : smarv
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Login</title>
</head>
<body>
    <h1>Bienvenido a Login</h1>
    <table>
        <form method="get">
            <tr>
                <td>Usuario</td>
                <td><input type="text" name="usuario"></td>
            </tr>
            <tr>
                <td>Contraseña</td>
                <td><input type="password" name="contrasenia"></td>
            </tr>
            <tr>
                <td><input type="hidden" name="parametro" value="sesion"></td>
                <td><input type="submit" value="Iniciar Sesión"></td>
            </tr>
        </form>
    </table>
</body>
</html>
