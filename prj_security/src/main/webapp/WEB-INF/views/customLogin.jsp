<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>사용자 생성 로그인 폼</h1>
	<h2><c:out value="${error }"></c:out></h2>
	<h2><c:out value="${logout }"></c:out></h2>
	
	<form action="/login" method="post">
		아이디 : <input type="text" name="username" value="admin"><br>
		비밀번호 : <input type="text" name="password" value="admin"><br>
		자동로그인 : <input type="checkbox" name="remember-me"><br>
		<input type="submit" value="로그인">
		<input type="hidden" name="${_csrf.parameterName }" value="${_csrf.token }">
	</form>
	<a href="/secu/join"><button>회원가입</button></a>
</body>
</html>