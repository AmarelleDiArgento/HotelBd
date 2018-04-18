<%-- 
    Document   : index
    Created on : 29/03/2018, 06:15:22 PM
    Author     : Sammy Guergachi <sguergachi at gmail.com>
--%>

<%@page import="Modelo.AsignaPerMod"%>
<%@page import="Modelo.PermisoMod"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
// nombrar jsp de estancia
    String jsp = "permiso.jsp";
    HttpSession Ses = request.getSession(true);
    Ses.setAttribute("jsp", jsp);

//Confirmar sesion del usuario
    if (Ses.getAttribute("log") != null) {
//cargar permisos y confirmar accesos
        List<AsignaPerMod> ap = (List<AsignaPerMod>) Ses.getAttribute("ApSes");
        AsignaPerMod acc = null;
        for (AsignaPerMod a : ap) {
            if (a.getTabla().equals("Permiso")) {
                acc = a;
            }
        }
//confirmar contenido del la lista de la tabla permisos
        if (Ses.getAttribute("arrP") != null) {
            List<PermisoMod> pl = (List<PermisoMod>) Ses.getAttribute("arrP");


%>
<html lang="es">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="viewport" content="width=device-width, initial-scale=1"/>
        <title>Hotel - Permisos</title>

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

            <%                if (pl != null && pl.size() > 0) {

            %>
            <table class="striped centered">
                <thead>
                    <tr>
                        <th>id</th>
                        <th>Modulo</th>
                        <th>Tabla</th>
                        <th>Url</th>
                        <th>Editar</th>
                        <th>Eliminar</th>
                    </tr>
                </thead>
                <tbody>
                    <%                    for (PermisoMod p : pl) {

                    %>
                    <tr>
                        <td><%=p.getId()%></td>
                        <td><%=p.getModulo()%></td>
                        <td><%=p.getTabla()%></td>
                        <td><%=p.getUrl()%></td>
                        <td>
                            <form action="permisos.do" method="get">
                                <input type="text" name="accion" value="Obtener" hidden />
                                <input type="text" name="Id" value="<%=p.getId()%>" hidden />
                                <a><input type="submit" class="waves-effect waves-blue material-icons" value="edit"></a>
                            </form>
                        </td>
                        <td>
                            <a><input type="submit"  class="waves-effect waves-blue material-icons" onclick="msj(<%=p.getId()%>)" value="delete"></a>
                        </td>
                    </tr>


                    <%}
                        }
                    %>

                </tbody>
            </table>





            <div class="fixed-action-btn horizontal">
                <a class="btn-floating btn-large red">
                    <i class="large material-icons modal-trigger" href="#modRegistrar">add</i>

                </a>

            </div>
            <div id="modRegistrar" class="modal">

                <div class="modal-content">

                    <h4>Nuevo permiso</h4>
                    <p>Registre la informacion del nuevo permiso</p>
                    <form action="permisos.do" method="get">

                        <div class="input-field col s6 centered">
                            <input placeholder="Modulo" name="Modulo" type="text" class="validate">
                        </div>
                        <div class="input-field col s6 centered">
                            <input placeholder="Tabla" name="Tabla" type="text" class="validate">
                        </div>
                        <div class="input-field col s6 centered">
                            <input placeholder="Url" name="Url" type="text" class="validate">
                        </div>

                        <div class="modal-footer">
                            <input name="accion" value="Registrar" type="submit" class="modal-action modal-close waves-effect waves-green btn-flat">
                        </div>
                    </form>
                </div>
            </div>
            <%
                if (Ses.getAttribute("arrP") != null) {
                    PermisoMod p = (PermisoMod) Ses.getAttribute("per");


            %>

            <div id="modModificar" class="modal">

                <div class="modal-content">

                    <h4><%=p.getTabla()%></h4>
                    <p>Registre la informacion del nuevo permiso</p>
                    <form action="permisos.do" method="get">
                        <input name="Id" type="text" value="<%=p.getId()%>" class="validate" hidden>
                        <div class="input-field col s6 centered">
                            <input placeholder="Modulo" name="Modulo" type="text" value="<%=p.getModulo()%>" class="validate">
                        </div>
                        <div class="input-field col s6 centered">
                            <input placeholder="Tabla" name="Tabla" type="text" value="<%=p.getTabla()%>" class="validate">
                        </div>
                        <div class="input-field col s6 centered">
                            <input placeholder="Url" name="Url" type="text" value="<%=p.getUrl()%>" class="validate">
                        </div>

                        <div class="modal-footer">
                            <input name="accion" value="Modificar" type="submit" class="modal-action modal-close waves-effect waves-green btn-flat">
                        </div>
                    </form>
                </div>
            </div>
            <%}%>
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
            title: "Are you sure?",
            text: "Once deleted, you will not be able to recover this imaginary file!",
            icon: "warning",
            buttons: true,
            dangerMode: true,
            })
            .then((willDelete) => {
            if (willDelete) {
            window.location = 'permisos.do?accion=Eliminar&Id='+id;
            } 
            });
            };


        </script>


    </body>
</html>
<%
            Ses.setAttribute("arrP", null);
            Ses.setAttribute("msj", null);
            Ses.setAttribute("Per", null);
        } else {
            response.sendRedirect("permisos.do?accion=Listar");
        }
    } else {
        response.sendRedirect("index.jsp");
    }%>

