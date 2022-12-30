<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>INDEX PAGE</title>
</head>
<body>
<%
	String msg = "Hello, World!";
	pageContext.setAttribute("msg", msg);
%>
${msg}
</body>
</html>