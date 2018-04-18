<%-- 
    Document   : index
    Created on : 29/03/2018, 06:15:22 PM
    Author     : Sammy Guergachi <sguergachi at gmail.com>
--%>

<%@page import="Modelo.RolMod"%>
<%@page import="Modelo.AsignaPerMod"%>
<%@page import="Modelo.UsuarioMod"%>
<%@page import="Modelo.PermisoMod"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
    String jsp = "usuario.jsp";
    HttpSession Ses = request.getSession(true);
    Ses.setAttribute("jsp", jsp);

//Confirmar sesion del usuario
    if (Ses.getAttribute("log") != null) {
        UsuarioMod uSes = (UsuarioMod) Ses.getAttribute("log");
//cargar permisos y confirmar accesos
        List<AsignaPerMod> ap = (List<AsignaPerMod>) Ses.getAttribute("ApSes");
        AsignaPerMod acc = null;
        for (AsignaPerMod a : ap) {
            if (a.getTabla().equalsIgnoreCase("Usuario")) {

                acc = a;

            }
        }
        if (acc.isLeer()) {
//confirmar contenido del la lista de la tabla usuario
            if (Ses.getAttribute("arrU") != null) {
                List<UsuarioMod> ul = (List<UsuarioMod>) Ses.getAttribute("arrU");
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
    <body <%if (msj != null && msj != "") {%> onload="Mensaje()" <%}%>>

        <nav class="white" role="navigation">
            <%@include file="menu.jsp" %>

        </nav>

        <div class="container">
            <%                if (ul != null && ul.size() > 0) {

                    /*            out.println("Tabla: " + acc.getTabla());
                    if (acc.isModificar()) {
                        out.println("Modificar: Activo");
                    } else {
                        out.println("Modificar: Inactivo");
                    }
                    if (acc.isLeer()) {
                        out.println("Leer: Activo");
                    } else {
                        out.println("Leer: Inactivo");
                    }
                    if (acc.isEliminar()) {
                        out.println("Eliminar: Activo");
                    } else {
                        out.println("Eliminar: Inactivo");
                    }
                    if (acc.isNuevo()) {
                        out.println("Nuevo: Activo");
                    } else {
                        out.println("Nuevo: Inactivo");
                    }*/
            %>
            <table class="striped responsive-table centered">
                <thead>
                    <tr>
                        <th>Cedula</th>
                        <th>Nombre</th>
                        <th>Rol</th>
                            <%if (acc.isModificar()) { %>
                        <th>Editar</th>
                            <%}%>
                            <%if (acc.isEliminar()) { %>
                        <th>Eliminar</th>
                            <%}%>
                    </tr>
                </thead>
                <tbody>
                    <%                    for (UsuarioMod uS : ul) {
                            if (uS.getRol_id() > uSes.getRol_id()) {
                    %>

                <td><%=uS.getCedula()%></td>
                <td><%=uS.getNombre()%></td>
                <td><%=uS.getnRol()%></td>
                <%if (acc.isModificar()) {%>
                <td>
                    <form action="usuarios.do" method="post">
                        <input type="text" name="accion" value="Obtener" hidden />
                        <input type="text" name="Cedula" value="<%=uS.getCedula()%>" hidden />
                        <a><input type="submit" class="waves-effect waves-blue material-icons" value="edit"></a>
                    </form>
                </td>
                <%}%>
                <%if (acc.isEliminar()) {%>
                <td><a><input type="submit"  class="waves-effect waves-blue material-icons" onclick="msj(<%=uS.getCedula()%>)" value="delete"></a></td>
                        <%}%>

                </tr>
                <%}
                        }
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

                    <h4>Nuevo usuario</h4>
                    <p>Registre la informacion del nuevo usuario</p>
                    <form action="usuarios.do" method="get">

                        <div class="input-field col s6 centered">
                            <input placeholder="Cedula" name="Cedula" type="number"  class="validate">
                        </div>
                        <div class="input-field col s6 centered">
                            <input placeholder="Nombre" name="Nombre" type="text" class="validate">
                        </div>
                        <div class="input-field col s6 centered">
                            <%
                                if (rl != null && rl.size() > 0) {
                            %>
                            <select name="Rol">
                                <%
                                    for (RolMod r : rl) {
                                %>
                                <option value="<%=r.getId()%>" ><%=r.getNombre()%></option>
                                <%}%>
                            </select>
                            <%}%>
                            <label>Rol </label>


                        </div>

                        <div class="modal-footer">
                            <input name="accion" value="Registrar" type="submit" class="modal-action modal-close waves-effect waves-green btn-flat">
                        </div>
                    </form>
                </div>
            </div>

            <%
                }
                if (Ses.getAttribute("Usu") != null) {
                    UsuarioMod uM = (UsuarioMod) Ses.getAttribute("Usu");

            %>

            <div id="modModificar" class="modal">

                <div class="modal-content">

                    <h4><%=uM.getNombre()%></h4>
                    <p>Modifique la informacion del usuario</p>
                    <form action="usuarios.do" method="get">

                        <div class="input-field col s6 centered">
                            <input placeholder="Cedula" name="Cedula" value="<%=uM.getCedula()%>" type="number"  class="validate">
                        </div>
                        <div class="input-field col s6 centered">
                            <input placeholder="Nombre" name="Nombre" value="<%=uM.getNombre()%>" type="text" class="validate">
                        </div>

                        <div class="input-field col s6 centered">
                            <input placeholder="Password" name="Password" value="<%=uM.getPass()%>" type="password" class="validate" hidden>
                        </div>
                        <div class="input-field col s6 centered">
                            <%
                                if (rl != null && rl.size() > 0) {
                            %>
                            <select name="Rol">
                                <%
                                    for (RolMod r : rl) {
                                %>
                                <option <%if (r.getId() == uM.getRol_id()) {%>selected <%}%>value="<%=r.getId()%>" ><%=r.getNombre()%></option>
                                <%}%>
                            </select>
                            <%}%>
                            <label>Rol </label>


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
            window.location = 'usuarios.do?accion=Eliminar&Id='+id;
            } 
            });
            };


        </script>

    </body>
</html>

<%
                    Ses.setAttribute("arrU", null);
                    Ses.setAttribute("msj", null);
                    Ses.setAttribute("Usu", null);
                    ;
                } else {
                    response.sendRedirect("rols.do?accion=Listar");
                }
            } else {
                response.sendRedirect("usuarios.do?accion=Listar");
            }
        } else {
            response.sendRedirect("main.jsp?msj=Tu no tienes permisos para ingresar a parametros de Usuario");
        }
    } else {

        response.sendRedirect("main.jsp");
    }%>

