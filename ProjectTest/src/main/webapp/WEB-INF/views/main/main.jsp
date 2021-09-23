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
	<h1>main 페이지</h1>
	${userInfo }<br>
	
	<a href="#"><button>DAMOYO LIST</button></a>
	<a href="#"><button>My Page</button></a><br>
	<c:forEach var="interest" items="${interest }">
		<a href="#"><button>${interest.i_cate_name }</button></a>
	</c:forEach>
	<p>필터링 목록<br>
	<p>모임리스트<br>
	<a href="#"><button>개설</button></a><br>
</body>
</html>