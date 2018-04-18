<%-- 
    Document   : sessionFinally
    Created on : 11/02/2018, 11:48:53 PM
    Author     : Freyd
--%>

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
        session.invalidate();
        response.sendRedirect("index.jsp?msj=Vuelve pronto"); 
        %> 
    </body>
</html>
