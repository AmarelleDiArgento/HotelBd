<%-- 
    Document   : index
    Created on : 29/03/2018, 06:15:22 PM
    Author     : Sammy Guergachi <sguergachi at gmail.com>
--%>

<%@page import="Modelo.ConsumoMod"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
    HttpSession Ses = request.getSession(true);
    if (Ses.getAttribute("log") != null) {

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


        <nav class="white" role="navigation">
            <%@include file="menu.jsp" %>

        </nav>

        <div class="container">
            <%                if (Ses.getAttribute("arrC") != null) {
                    List<ConsumoMod> cl = (List<ConsumoMod>) Ses.getAttribute("arrC");
                    if (cl != null && cl.size() > 0) {

            %>
            <table class="striped responsive-table centered">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Habitacion</th>
                        <th>Reserva</th>
                        <th>Servicio</th>
                        <th>Tarifa</th>
                        <th>Fecha</th>
                        <th>Editar</th>
                        <th>Eliminar</th>
                    </tr>
                </thead>
                <tbody>
                    <%//
                        for (ConsumoMod c : cl) {
                    %>

                <td><%=c.getId_cons()%></td>
                <td><%=c.getNumero_hab()%></td>
                <td><%=c.getCodigo_res()%></td>
                <td><%=c.getServicio()%></td>
                <td><%=c.getTarifa()%></td>
                <td><%=c.getFecha_hora()%></td>
                <td><a href=""><i class="material-icons">edit</i></a></td>
                <td><a href=""><i class="material-icons">delete</i></a></td>

                </tr>
                <%}
                        }
                    }%>

                </tbody>
            </table>

            <div class="fixed-action-btn horizontal">
                <a class="btn-floating btn-large red">
                    <i class="large material-icons">mode_edit</i>
                </a>
            </div>

        </div>


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
<%} else {
        response.sendRedirect("main.jsp");
    }%>

