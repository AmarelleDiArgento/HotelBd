<%-- 
    Document   : index
    Created on : 29/03/2018, 06:15:22 PM
    Author     : Sammy Guergachi <sguergachi at gmail.com>
--%>

<%@page import="Modelo.PermisoMod"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
    HttpSession Ses = request.getSession(true);
    if (Ses.getAttribute("log") != null) {
        String jsp = "main.jsp";
        Ses.setAttribute("jsp", jsp);

%>
<html lang="es">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="viewport" content="width=device-width, initial-scale=1"/>
        <title>Hotel</title>

        <!-- CSS  -->
        <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
        <link href="css/materialize.css" type="text/css" rel="stylesheet" media="screen,projection"/>
        <link href="css/materialize.min.css" type="text/css" rel="stylesheet" media="screen,projection"/>
        <link href="css/style.css" type="text/css" rel="stylesheet" media="screen,projection"/>




    </head>
    <%String msj = (String) Ses.getAttribute("msj");%>
    <body <%if (msj != null && msj != "") {%> onload="Mensaje()" <%}%>>



        <%@include file="menu.jsp" %>






        <div class="container">





            <script type="text/javascript" src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
            <script type="text/javascript" src="js/materialize.min.js"></script>
            <script type="text/javascript" src="js/init.js"></script>

            <script language="JavaScript" type="text/JavaScript">

                var Mensaje = function () {
                var $toastContent = $('<span><%out.print(msj);%></span>').add($('<a href="#modModificar" class="modal-trigger btn-flat toast-action" >Ver</a>'));
                Materialize.toast($toastContent, 2500);
                };
            </script>

    </body>
</html>
<%

        Ses.setAttribute("msj", null);

    } else {
        response.sendRedirect("index.jsp");
    }%>

