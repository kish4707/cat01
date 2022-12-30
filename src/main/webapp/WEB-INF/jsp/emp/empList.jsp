<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title></title>
</head>
<body> 

<table>
<tr><td>사번</td><td>이름</td><td>부서</td><td>급여</td><td>입사일</td></tr>
<c:forEach var='list' items='${list}'>
<tr>
<td>${list.empno}</td> 
<td><a href='/emp/detail/${list.empno}'>${list.ename}</a></td>
<td>${list.deptno}</td>
<td>${list.sal}</td>
<td>${list.hiredate}</td>
</tr>
</c:forEach>
</table>
<a href='/emp/add'><button>추가</button></a>
</body>
</html>