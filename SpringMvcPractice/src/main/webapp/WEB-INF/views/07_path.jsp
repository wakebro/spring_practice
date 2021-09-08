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
	<h1>현재 보는 ${page} 페이지 조회중입니다.</h1>
	
	<!-- JSTL을 활용하여 page가 100 미만이면 하단에 h2태그를 활용하여 
	"초반부 입니다.", 100이상 200미만이면 "중반부 입니다.", 200 이상이면 "후반부 입니다."
	라는 문장을 추가로 출력-->
	<c:choose>
		<c:when test="${page >= 0 && page < 100}">
			<h2>초반부 입니다.</h2>
		</c:when>
		<c:when test="${page >= 100 && page < 200 }">
			<h2>중반부 입니다.</h2>
		</c:when>
		<c:when test="${page >= 200 }">
			<h2>후반부 입니다.</h2>
		</c:when>
		<c:otherwise>
			<h2>알 수 없는 페이지 입니다.</h2>
		</c:otherwise>
	</c:choose>
</body>
</html>