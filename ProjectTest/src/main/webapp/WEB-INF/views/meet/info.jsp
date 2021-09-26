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
	<h1>모임방</h1>
	${userInfo }
	<a href="/meet/info?num=${detail.m_num }"><button>정보</button></a>
	<a href="#"><button>게시판</button></a>
	<a href="#"><button>갤러리</button></a>
	<a href="#"><button>채팅</button></a>
	<br>
	<table border="1">
		<tr><td colspan="2">${detail.m_profile }</td></tr>
		<tr>
			<td>${detail.i_cate_name }</td>
			<td>${detail.m_name }</td>
		</tr>
		<tr><td colspan="2">${detail.m_content }</td></tr>
	</table>
	<form action="/meet/join" method="post">
		<input type="hidden" name="u_id" value="${userInfo.u_id }">
		<input type="hidden" name="m_num" value="${detail.m_num}">
		<input type="submit" value="가입">
	</form>
	
	<h2>모임 정모</h2>
	
	<h2>모임 멤버</h2>
	<%-- ${memberList } --%>
	<table border="1">
		<c:forEach var="member" items="${memberList }">
			<tr>
				<td rowspan="2">${member.u_profile }</td>
				<td>${member.u_name }</td>
				<td rowspan="2">${member.member_list_position }</td>
			</tr>
			<tr>
				<td>${member.u_intro }</td>
			</tr>
			
			
		</c:forEach>
	</table>
	
	<br>
	<a href="/main/main"><button>메인화면</button></a>
	<form action="/meet/update" method="post">
		<input type="hidden" name="num" value="${detail.m_num }">
		<input type="submit" value="수정">
	</form>
	<form action="/meet/remove" method="post">
		<input type="hidden" name="num" value="${detail.m_num }">
		<input type="submit" value="삭제">
	</form>
</body>
</html>