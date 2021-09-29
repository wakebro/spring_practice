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
	페이지번호 : ${param.pageNum}<br>
	검색조건: ${param.searchType }<br>
	키워드 : ${param.keyword}<br>
	<!-- 수정을 하면서 동시에 페이지번호, 검색조건, 키워드를 넘긴다 
	수정 로직은 post이기 때문에 어쩔수 없이 컨트롤러를 경유해서 데이터를 전달한다.
	Controller 내부의 modify메서드에 의해 위 3가지 정보를 모두 처리할 수 있는
	SearchCriteria를 추가로 선언하여 받아준다.
	-->
	<form action="/board/modify" method="post">
		<input type="hidden" name="bno" value="${vo.bno }"><br>
		<input type="hidden" name="pageNum" value="${param.pageNum}">
		<input type="hidden" name="searchType" value="${param.searchType}">
		<input type="hidden" name="keyword" value="${param.keyword}">
		제목 : <input type="text" name="title" value=" ${vo.title}"><br>
		본문 : <br>
		<textarea name="content"  rows="10" cols="50">${vo.content}</textarea><br>
		글쓴이 : ${vo.writer } 
		<input type="hidden" name="writer" value="${vo.writer}"><br>
		날짜 : ${vo.regdate}<br>
		<input type="reset" value="초기화">
		<input type="submit" value="확인">
	</form>
	<a href="/board/list"><button>목록</button></a><br>

</body>
</html>