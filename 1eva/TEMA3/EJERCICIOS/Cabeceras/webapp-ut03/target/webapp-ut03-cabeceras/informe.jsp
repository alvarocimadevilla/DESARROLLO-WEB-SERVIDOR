<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="java.util.Map" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Cabeceras HTTP Request</title>
</head>
<body>
    <h1>Informe de cabeceras HTTP del request:</h1>
    <ul>
        <li>Método HTTP: <%=request.getAttribute("metodoHttp")%></li>
        <li>URI: <%=request.getAttribute("requestUri")%></li>
        <li>URL: <%=request.getAttribute("requestUrl")%></li>
        <li>resto...</li>

        <%
            // Recoger el map del request
            Map<String,String> headers = (Map<String,String>)request.getAttribute("headersMap");

            // Recorrerlo y pintarlo
            for(Map.Entry<String,String> header: headers.entrySet()){
                out.println("<li>"+header.getKey()+" : "+header.getValue()+"</li>");
            }
        %>

    </ul>
</body>
</html>
