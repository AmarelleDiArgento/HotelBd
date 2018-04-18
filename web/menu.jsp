<%-- 
    Document   : menu
    Created on : 30/03/2018, 10:37:13 AM
    Author     : Sammy Guergachi <sguergachi at gmail.com>
--%>

<%@page import="Modelo.UsuarioMod"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.ArrayList"%>
<%@page import="Modelo.PermisoMod"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%

    HttpSession SesM = request.getSession(true);
    List<PermisoMod> menu = (List<PermisoMod>) SesM.getAttribute("Menu");
    UsuarioMod u = (UsuarioMod) SesM.getAttribute("log");

    if (menu != null && menu.size() > 0) {

%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="viewport" content="width=device-width, initial-scale=1"/>
        <title>Menu</title>

        <!-- CSS  -->
        <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
        <link href="css/materialize.css" type="text/css" rel="stylesheet" media="screen,projection"/>
        <link href="css/materialize.min.css" type="text/css" rel="stylesheet" media="screen,projection"/>
        <link href="css/style.css" type="text/css" rel="stylesheet" media="screen,projection"/>

    </head>
    <body>

        <%            List<String> label = new ArrayList<String>();
            String temp = "";

            for (PermisoMod m : menu) {
                if (!temp.equalsIgnoreCase(m.getModulo())) {
                    label.add(m.getModulo());
                    temp = m.getModulo();
                }
            }

            Iterator l = label.iterator();

            l = label.iterator();

            while (l.hasNext()) {
                String el = (String) l.next();
        %>
        <ul id="<%=el%>" class="dropdown-content"><%

            for (PermisoMod m : menu) {
                if (!el.equals("")) {

                    if (el.equals(m.getModulo())) {
            %><li><a href="<%=m.getUrl()%>"> <%=m.getTabla()%> </a></li><%}
                    }
                }
                %>

        </ul>
        <%
            }
        %>




        <nav>
            <div class="nav-wrapper white">
                <a href="#!" class="brand-logo">Hotel</a>
                <a href="#" data-activates="mobile-demo" class="button-collapse"><i class="material-icons">menu</i></a>

                <ul class="right hide-on-med-and-down">

                    <!-- Dropdown Trigger -->
                    <%
                        l = label.iterator();

                        while (l.hasNext()) {
                            String el = (String) l.next();

                    %>
                    <li><a class="dropdown-trigger" href="#!" data-activates="<%=el%>"> <%=el%> <i class="material-icons right">arrow_drop_down</i></a></li>
                        <%}%>
                    <li><a href="sessionFinally.jsp"><i class="material-icons">exit_to_app</i></a></li>

                </ul>
                <ul class="side-nav" id="mobile-demo">
                    <li>
                        <div class="center-align">   
                            <span class="blue-grey-text name"><%=u.getNombre()%></span> <br>
                            <span class="grey-text email"><%=u.getnRol()%></span>
                        </div>
                    </li>

                    <li><div class="divider"></div></li>
                        <%
                            l = label.iterator();

                            while (l.hasNext()) {
                                String el = (String) l.next();

                        %>

                    <li><a class="subheader"><%=el%></a></li>
                        <%
                            for (PermisoMod m : menu) {
                                if (!el.equals("")) {

                                    if (el.equals(m.getModulo())) {
                        %>

                    <li><a href="<%=m.getUrl()%>"> <%=m.getTabla()%> </a></li><%}
                            }
                        }
                        %>


                    <li><div class="divider"></div></li>

                    <%}%>
                    <li><a href="sessionFinally.jsp"><i class="material-icons">exit_to_app</i></a></li>
                </ul>
            </div>
        </nav>
    </body>
</html>
<%        } else {
        response.sendRedirect("permisos.do?accion=menu");
    }

%>
