<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
</head>
<body>
	<h1>index.jsp</h1>
	<h1>${loginUser.name }님 환영합니다.</h1>
	
	<ul>
		<li><a href="<c:url value='/home/main' />">/home/main</a></li>
		<li><a href="<c:url value='/member/main' />">/member/main</a></li>
		<li><a href="<c:url value='/manager/main' />">/manager/main</a></li>
		<li><a href="<c:url value='/admin/main' />">/admin/main</a></li>
	</ul>
	
	<!-- spring security 3.xxx 이하만 가능   -->
	<!-- <a href="/security/spring_security_login">로그인</a> -->
	<a href="/security/commons/login">로그인</a>
	<!-- <a href="/security/j_spring_security_logout">로그아웃</a> -->
	<a href="/security/commons/logout">로그아웃</a>
	
</body>
</html>








