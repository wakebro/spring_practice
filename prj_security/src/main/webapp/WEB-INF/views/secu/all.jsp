<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>All 주소</h1>
	<sec:authorize access="isAnonymous()">
	<!-- 로그인 안한(익명) 사용자의 경우 -->
		<a href="/customLogin">로그인</a>
	</sec:authorize>	
	<sec:authorize access="isAuthenticated()">
	<!-- 로그인 한(인증된) 사용자인 경우 -->
		<a href="/customLogout">로그아웃</a>
	</sec:authorize>
</body>
</html>