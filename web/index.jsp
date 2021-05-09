<%-- 
    Document   : index
    Created on : 4/05/2021, 08:16:25 PM
    Author     : smarv
--%>
<%@ page contentType="text/html; charset=iso-8859-1" language="java" import="com.mx.digital.stone.pa.EnviaCorreoHilo" errorPage="" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>.:Proces Automaticos:.</title>
    </head>
    <body>
         <form action='ControlServicios' method='post'>
            <input name='IDProcess' id='IDProcess' type='hidden'>
            <input name='Action' id='Action' type='hidden'>
            <table>
                <tr>
                    <td>Envío de Reportes</td>
                    
                    <% if (EnviaCorreoHilo.getEstatus()) { %>
                        <td>Estatus: <b> <font color="GREEN">Iniciado</font></b></td>
                    <%} else {%>
                        <td>Estatus: <b> <font color="RED">Detenido</font></b></td>
                    <%}%> 
                </tr>
                <tr>
                    <% if (EnviaCorreoHilo.getEstatus()) { %>
                        <td><input onClick='document.all.IDProcess.value = 1; document.all.Action.value = 0' type='submit' value='Detener'></input></td>
                    <%} else {%>
                        <td><input onClick='document.all.IDProcess.value = 1; document.all.Action.value = 1' type='submit' value='Iniciar'></input></td>
                    <%}%> 
                </tr>
            </table>
        </form>     
    </body>
</html>
