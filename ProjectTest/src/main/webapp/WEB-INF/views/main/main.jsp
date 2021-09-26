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
	
	<table border="1">
		<c:forEach var="m" items="${meet }">
			<tr>
				<input type="hidden" name="m_num" value="${m.m_num }">
				<td rowspan="3"><a href="/meet/info?num=${m.m_num }">${m.m_profile }이미지${m.m_num }</a></td>
				<td>${m.m_area }</td>
			</tr>
			<tr>
				<td>${m.m_name }</td>
			</tr>
			<tr>
				<td>${m.m_people_cnt }</td>
			</tr>
		</c:forEach>
	</table>
	<a href="/main/register"><button>개설</button></a><br>
</body>
</html>