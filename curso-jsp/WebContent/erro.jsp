<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Ops erro *_*</title>
</head>
<body>
<h1>ERRO DETECTADO , Por favor entre em contato com o suporte do sistema</h1>
<%
out.println(request.getAttribute("msg"));
%>
</body>
</html>