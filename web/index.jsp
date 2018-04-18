<%-- 
    Document   : index
    Created on : 29/03/2018, 06:15:22 PM
    Author     : Sammy Guergachi <sguergachi at gmail.com>
--%>

<%
    String jsp = "permiso.jsp";
    HttpSession Ses = request.getSession(true);
    Ses.setAttribute("jsp", jsp);
%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

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


        <!-- Modal Structure -->
        <div id="modal1" class="modal">

            <div class="modal-content">
                <ul id="tabs-swipe-demo" class="tabs">
                    <li class="tab col s3 center"><a href="#test-swipe-1"><h4>Iniciar sesion</h4></a></li>
                    <li class="tab col s3 center"><a href="#test-swipe-2"><h4>Registrarse</h4></a></li>
                </ul>
                <div id="test-swipe-1" class="col s6">
                    <form action="usuarios.do" method="get">
                        <div class="row">
                            <div class="input-field col s12">
                                <i class="material-icons prefix">account_circle</i>
                                <input id="icon_prefix" type="text" name="cedula" class="validate">
                                <label for="icon_prefix">Cedula</label>
                            </div>
                        </div>
                        <div class="row">
                            <div class="input-field col s12">
                                <i class="material-icons prefix">enhanced_encryption</i>
                                <input id="pass" type="password" name="password" class="validate">
                                <label for="pass">Password</label>
                            </div>
                        </div>

                        <div class="modal-footer">
                            <input name="accion" value="Ingresar" type="submit" class="modal-action modal-close waves-effect waves-green btn-flat">
                        </div>
                    </form>
                </div>
                <div id="test-swipe-2" class="col s12 ">
                    <form action="usuarios.do" method="post">
                        <div class="row">
                            <div class="input-field col s12">
                                <input placeholder="Cedula" name="usuario" type="text" class="validate">
                            </div>
                        </div>
                        <div class="row">
                            <div class="input-field col s12 centered">
                                <input placeholder="Nombre" name="Nombre" type="text" class="validate">
                            </div>
                        </div>
                        <div class="row">
                            <div class="input-field col s12">
                                <input placeholder="Password" name="password-1" type="password" class="validate">
                            </div>
                        </div> 
                        <div class="row">
                            <div class="input-field col s12">
                                <input placeholder="Confirmar Password" name="password-2" type="password" class="validate">
                            </div>
                        </div>

                        <div class="modal-footer">
                            <input name="accion" value="Registrar" type="submit" class="modal-action modal-close waves-effect waves-green btn-flat">
                        </div>
                    </form>
                </div>


            </div>


        </div>
    
    <div class="fixed-action-btn horizontal">
        <a class="btn-floating btn-large red">
            <i class="large material-icons modal-trigger" href="#modal1">person</i>

        </a>

    </div>

    <div class="container">

        <div class="slider fullscreen">
            <ul class="slides">
                <li>
                    <img src="img/Hotel1.jpg"> <!-- random image -->
                    <div class="caption center-align">
                        <h3>This is our big Tagline!</h3>
                        <h5 class="light grey-text text-lighten-3">Here's our small slogan.</h5>
                    </div>
                </li>
                <li>
                    <img src="img/Hotel2.jpg"> <!-- random image -->
                    <div class="caption left-align">
                        <h3>Left Aligned Caption</h3>
                        <h5 class="light grey-text text-lighten-3">Here's our small slogan.</h5>
                    </div>
                </li>
                <li>
                    <img src="img/Hotel3.jpg"> <!-- random image -->
                    <div class="caption right-align">
                        <h3>Right Aligned Caption</h3>
                        <h5 class="light grey-text text-lighten-3">Here's our small slogan.</h5>
                    </div>
                </li>
                <li>
                    <img src="img/Hotel3.jpg""> <!-- random image -->
                    <div class="caption center-align">
                        <h3>This is our big Tagline!</h3>
                        <h5 class="light grey-text text-lighten-3">Here's our small slogan.</h5>
                    </div>
                </li>
            </ul>
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
