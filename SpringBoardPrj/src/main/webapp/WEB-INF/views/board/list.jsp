<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-F3w7mX95PdgyTmZZMECAngseQB83DfGTowi0iMjiWaeVhAn4FJkqJByhZMI3AhiU" crossorigin="anonymous">
</head>
<body>
	<h1 class="text text-primary">스프링 list</h1>
	<table class="table table-striped table-hover">
		<thead>
			<tr>
				<th>글번호</th>
				<th>글제목</th>
				<th>글쓴이</th>
				<th>날짜</th>
				<th>최종수정일</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="n" items="${list }">
				<tr>
					<td>${n.bno }</td>
					<td><a href="/board/get?bno=${n.bno }">${n.title }</a></td>
					<td> ${n.writer }</td>
					<td>${n.regdate }</td>
					<td>${n.updatedate }</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</body>
</html>