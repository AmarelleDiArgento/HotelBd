<%-- 
    Document   : index
    Created on : 29/03/2018, 06:15:22 PM
    Author     : Sammy Guergachi <sguergachi at gmail.com>
--%>

<%@page import="Modelo.ServicioMod"%>
<%@page import="Modelo.RolMod"%>
<%@page import="Modelo.AsignaPerMod"%>
<%@page import="Modelo.PermisoMod"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
    String jsp = "servicio.jsp";
    HttpSession Ses = request.getSession(true);
    Ses.setAttribute("jsp", jsp);

//Confirmar sesion del usuario
    if (Ses.getAttribute("log") != null) {
//cargar permisos y confirmar accesos
        List<AsignaPerMod> ap = (List<AsignaPerMod>) Ses.getAttribute("ApSes");
        AsignaPerMod acc = null;
        for (AsignaPerMod a : ap) {
            if (a.getTabla().equalsIgnoreCase("Servicio")) {

                acc = a;

            }
        }
        if (acc.isLeer()) {
//confirmar contenido del la lista de la tabla usuario
            if (Ses.getAttribute("arrS") != null) {
                List<ServicioMod> sl = (List<ServicioMod>) Ses.getAttribute("arrS");
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
            <%if (sl != null && sl.size() > 0) {%>
            <table class="striped centered">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Servicio</th>
                        <th>Tarifa</th>
                            <%if (acc.isModificar()) { %>
                        <th>Editar</th>
                            <%}%>
                            <%if (acc.isEliminar()) { %>
                        <th>Eliminar</th>
                            <%}%>
                    </tr>
                </thead>
                <tbody>
                    <%                    for (ServicioMod s : sl) {

                    %>

                <td><%=s.getId()%></td>
                <td><%=s.getNombre()%></td>
                <td><%=s.getTarifa()%></td>
                <%if (acc.isModificar()) {%>
                <td>
                    <form action="servicios.do" method="post">
                        <input type="text" name="accion" value="Obtener" hidden />
                        <input type="text" name="Id" value="<%=s.getId()%>" hidden />
                        <a><input type="submit" class="waves-effect waves-blue material-icons" value="edit"></a>
                    </form>
                </td>
                <%}%>
                <%if (acc.isEliminar()) {%>
                <td><a><input type="submit"  class="waves-effect waves-blue material-icons" onclick="msj(<%=s.getId()%>)" value="delete"></a></td>
                        <%}%>

                </tr>
                <%}
                    }
                %>

                </tbody>
            </table>
            <%if (acc.isNuevo()) { %>            
            <div class="fixed-action-btn horizontal">
                <a class="btn-floating btn-large red">
                    <i class="large material-icons modal-trigger" href="#modRegistrar">add</i>
                </a>
            </div>

            <div id="modRegistrar" class="modal">

                <div class="modal-content">

                    <h4>Nuevo servicio</h4>
                    <p>Registre la informacion del nuevo servicio</p>
                    <form action="servicios.do" method="get">

                        <div class="input-field col s6 centered">
                            <input placeholder="Nombre" name="Nombre" type="text"  class="validate">
                        </div>
                        <div class="input-field col s6 centered">
                            <input placeholder="Tarifa" name="Tarifa" type="number" class="validate">
                        </div>

                        <div class="modal-footer">
                            <input name="accion" value="Registrar" type="submit" class="modal-action modal-close waves-effect waves-green btn-flat">
                        </div>
                    </form>
                </div>
            </div>

            <%
                }
                if (Ses.getAttribute("Ser") != null) {
                    ServicioMod sM = (ServicioMod) Ses.getAttribute("Ser");

            %>

            <div id="modModificar" class="modal">

                <div class="modal-content">

                    <h4><%=sM.getNombre()%></h4>
                    <p>Modifique la informacion del servicio</p>
                    <form action="servicios.do" method="get">

                        <div class="input-field col s6 centered">
                            <input placeholder="ID" name="Id" value="<%=sM.getId()%>" type="number"  class="validate" hidden>
                        </div>
                        <div class="input-field col s6 centered">
                            <input placeholder="Nombre" name="Nombre" value="<%=sM.getNombre()%>" type="text" class="validate">
                        </div>

                        <div class="input-field col s6 centered">
                            <input placeholder="Tarifa" name="Tarifa" value="<%=sM.getTarifa()%>" type="number" class="validate" >
                        </div>


                        <div class="modal-footer">
                            <input name="accion" value="Modificar" type="submit" class="modal-action modal-close waves-effect waves-green btn-flat">
                        </div>
                    </form>
                </div>
            </div>
            <% }
            %>

        </div>


        <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
        <script src="https://code.jquery.com/jquery-3.2.1.min.js" ></script>
        <script type="text/javascript" src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
        <script type="text/javascript" src="js/materialize.min.js"></script>
        <script type="text/javascript" src="js/init.js"></script>

        <script language="JavaScript" type="text/JavaScript">

            var Mensaje = function () {
            var $toastContent = $('<span><%out.print(msj);%></span>').add($('<a href="#modModificar" class="modal-trigger btn-flat toast-action" >Ver</a>'));
            Materialize.toast($toastContent, 2500);
            };

            function msj(id){
            swal({
            title: "¿Eliminar?",
            text: "Seguro quieres eliminar el Id: " + id,
            icon: "warning",
            buttons: true,
            dangerMode: true,
            })
            .then((willDelete) => {
            if (willDelete) {
            window.location = 'servicios.do?accion=Eliminar&Id='+id;
            } 
            });
            };


        </script>

    </body>
</html>

<%
                Ses.setAttribute("arrS", null);
                Ses.setAttribute("msj", null);
                Ses.setAttribute("Ser", null);
                ;
            } else {
                response.sendRedirect("servicios.do?accion=Listar");
            }

        } else {
            response.sendRedirect("main.jsp?msj=Tu no tienes permisos para ingresar a parametros de Servicio");
        }
    } else {

        response.sendRedirect("main.jsp");
    }%>

