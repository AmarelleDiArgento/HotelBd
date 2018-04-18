<%--
    Document   : test
    Created on : 3/04/2018, 11:30:06 PM
    Author     : Sammy Guergachi <sguergachi at gmail.com>
--%>

<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>

        <%

            String[] per = request.getParameterValues("conf");
            if (per != null && per.length > 0) {
                for (String p : per) {
                    out.println(p);
                }
            }
        %>


        <a  href = "asignapers.do?accion=obtener&probar=Llegolainfo" > serv </a>
    </body>
</html>
