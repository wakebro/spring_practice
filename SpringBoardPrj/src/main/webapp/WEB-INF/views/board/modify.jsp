<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>${vo.bno }번 글 수정 페이지</h1>
	<form action="/board/modify" method="post">
		<input type="hidden" name="bno" value="${vo.bno }"><br>
		제목 : <input type="text" name="title" value=" ${vo.title}"><br>
		본문 : <br>
		<textarea name="content"  rows="10" cols="50">${vo.content}</textarea> <br>
		글쓴이 : ${vo.writer } 
		<input type="hidden" name="writer" value="${vo.writer}"><br>
		날짜 : ${vo.regdate}<br>
		<input type="reset" value="초기화">
		<input type="submit" value="확인">
	</form>
	<a href="/board/list">목록으로</a>
</body>
</html>