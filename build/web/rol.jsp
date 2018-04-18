<%-- 
    Document   : index
    Created on : 29/03/2018, 06:15:22 PM
    Author     : Sammy Guergachi <sguergachi at gmail.com>
--%>

<%@page import="Modelo.AsignaPerMod"%>
<%@page import="Modelo.RolMod"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
    String jsp = "rol.jsp";
    HttpSession Ses = request.getSession(true);
    Ses.setAttribute("jsp", jsp);

//Confirmar sesion del usuario
    if (Ses.getAttribute("log") != null) {
//cargar permisos y confirmar accesos
        List<AsignaPerMod> ap = (List<AsignaPerMod>) Ses.getAttribute("ApSes");
        AsignaPerMod acc = null;
        for (AsignaPerMod a : ap) {
            if (a.getTabla().equals("Rol")) {
                acc = a;
            }
        }
//confirmar contenido del la lista de la tabla rol
        if (Ses.getAttribute("arrRol") != null) {
            List<RolMod> rl = (List<RolMod>) Ses.getAttribute("arrRol");

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
    <body <%if (msj != null && msj != "" ) {%> onload="Mensaje()" <%}%>>


        <nav class="white" role="navigation">
            <%@include file="menu.jsp" %>

        </nav>

        <div class="container">
            <%                if (rl != null && rl.size() > 0) {
            %>
            <table class="striped centered">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Nombre</th>
                        <th>Editar</th>
                        <th>Eliminar</th>
                    </tr>
                </thead>
                <tbody>
                    <%                    for (RolMod r : rl) {


                    %>

                <td><%=r.getId()%></td>
                <td><%=r.getNombre()%></td>
                <td>

                    <form action="asignapers.do" method="post">
                        <input type="text" name="rol" value="<%=r.getId()%>" hidden />
                        <input type="text" name="accion" value="Obtener" hidden />
                        <a><input type="submit" class="waves-effect waves-white material-icons" name="accion" value="edit"  ></a>
                    </form>
                </td>
                <td>

                    <form action="rols.do" method="post">
                        <input type="text" name="rol" value="<%=r.getId()%>" hidden />
                        <a> <input type="submit" class="waves-effect waves-blue material-icons" name="accion" value="delete"  ></a>
                    </form>
                </td>
                </tr>
                <%}

                    }%>

                </tbody>
            </table>

            <div class="fixed-action-btn">
                <a class="btn-floating btn-large red modal-trigger" href="#modal1">
                    <i class="large material-icons" >mode_edit</i>
                </a>
            </div>



        </div>
        <!-- Modal Structure -->
        <%

            if (Ses.getAttribute("Aprr") != null) {
                List<AsignaPerMod> apli = (List<AsignaPerMod>) Ses.getAttribute("Aprr");

                if (apli != null && apli.size() > 0) {

        %>
        <div id="modModificar" class="modal modal-fixed-footer">
            <form action="asignapers.do" method="get">
                <div class="modal-content">
                    <h4><%=apli.get(1).getRol()%></h4>
                    <table class="responsive-table striped centered">
                        <thead>
                            <tr>
                                <th colspan="2">Modulo</th>
                                <th>Leer</th>
                                <th>Nuevo</th>
                                <th>Modificar</th>
                                <th>Eliminar</th>
                            </tr>
                        </thead>
                        <tbody>
                            <%
                                for (AsignaPerMod apr : apli) {

                            %>
                            <tr>
                                <td colspan="2"><%=apr.getTabla()%> <input id="icon_prefix" type="text" name="Permisos" value="<%=apr.getPermisos_id()%>" hidden> </td>


                                <td>        
                                    <div class="switch">
                                        <label>No
                                            <input type="checkbox" <%if (apr.isLeer()) {%> checked <%}%>name="<%=apr.getTabla()%>" value="1">
                                            <span class="lever" >
                                            </span>Si
                                        </label >
                                    </div>
                                </td>
                                <td>        
                                    <div class="switch">
                                        <label>No
                                            <input type="checkbox" <%if (apr.isNuevo()) {%> checked <%}%>name="<%=apr.getTabla()%>" value="2">
                                            <span class="lever" >
                                            </span>Si
                                        </label >
                                    </div>
                                </td>
                                <td>        
                                    <div class="switch">
                                        <label>No
                                            <input type="checkbox" <%if (apr.isModificar()) {%> checked <%}%>name="<%=apr.getTabla()%>" value="3">
                                            <span class="lever" >
                                            </span>Si
                                        </label >
                                    </div>
                                </td>
                                <td>        
                                    <div class="switch">
                                        <label>No
                                            <input type="checkbox" <%if (apr.isEliminar()) {%> checked <%}%>name="<%=apr.getTabla()%>" value="4">
                                            <span class="lever" >
                                            </span>Si
                                        </label >
                                    </div>
                                </td>

                            </tr>
                            <%}%>
                        </tbody>
                    </table>

                </div>
                <div class="modal-footer">
                    <input name="accion" value="modificar" type="submit" class="modal-action modal-close waves-effect waves-green btn-flat">


                </div>
            </form>
        </div>
        <%
                }
            }
        %>
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
            response.sendRedirect("rols.do?accion=Listar");
        }
    } else {
        response.sendRedirect("index.jsp");
    }%>

