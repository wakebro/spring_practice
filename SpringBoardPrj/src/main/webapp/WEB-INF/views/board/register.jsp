<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>register 페이지</h1>
	<form action="/board/register" method="post">
		제 목 : <input type="text" name="title"><br>
		글쓴이 : <input type="text" name="writer"><br>
		본 문 : <br>
		<textarea rows="10" cols="50" name="content"></textarea><br>
		<input type="reset" value="초기화">
		<input type="submit" value="작성">
	</form>
	<a href="/board/list"><button>목록</button></a>
</body>
</html>