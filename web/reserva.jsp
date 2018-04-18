<%-- 
    Document   : index
    Created on : 29/03/2018, 06:15:22 PM
    Author     : Sammy Guergachi <sguergachi at gmail.com>
--%>

<%@page import="Modelo.AsignaPerMod"%>
<%@page import="Modelo.ReservaMod"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
    String jsp = "reserva.jsp";
    HttpSession Ses = request.getSession(true);
    Ses.setAttribute("jsp", jsp);

//Confirmar sesion del usuario
    if (Ses.getAttribute("log") != null) {
//cargar permisos y confirmar accesos
        List<AsignaPerMod> ap = (List<AsignaPerMod>) Ses.getAttribute("ApSes");
        AsignaPerMod acc = null;
        for (AsignaPerMod a : ap) {
            if (a.getTabla().equals("Reserva")) {
                acc = a;
            }
        }
//confirmar contenido del la lista de la tabla rol
        if (Ses.getAttribute("arrR") != null) {
            List<ReservaMod> rl = (List<ReservaMod>) Ses.getAttribute("arrR");

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
            <%                if (rl != null && rl.size() > 0) {

            %>
            <table class="striped centered">
                <thead>
                    <tr>
                        <th>Codigo</th>
                        <th>Habitacion</th>
                        <th>Fecha de ingreso</th>
                        <th>Fecha de Egreso</th>
                        <th>Cedula</th>
                        <th>Nombre</th>
                        <th># Personas</th>
                        <th>Editar</th>
                        <th>Eliminar</th>
                    </tr>
                </thead>
                <tbody>
                    <%                    for (ReservaMod r : rl) {
                    %>

                <td><%=r.getCodigo()%></td>
                <td><%=r.getHabitacion()%></td>
                <td><%=r.getFecha_ingreso()%></td>
                <td><%=r.getFecha_egreso()%></td>
                <td><%=r.getCedula()%></td>
                <td><%=r.getNombre()%></td>
                <td><%=r.getNum_personas()%></td>
                <td><a href=""><i class="material-icons">edit</i></a></td>
                <td><a href=""><i class="material-icons">delete</i></a></td>

                </tr>
                <%}

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
            response.sendRedirect("reservas.do?accion=Listar");
        }
    } else {
        response.sendRedirect("index.jsp");
    }%>

