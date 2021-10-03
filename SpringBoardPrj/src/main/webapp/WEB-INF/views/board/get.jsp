<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="/resources/js/reply.js"></script>
<script type="text/javascript">
	$(document).read(read(function(){
		console.log(replyService);
	}));
</script>
</head>
<body>
	
	<h1>상세 페이지</h1>
	번호 : ${vo.bno}<br>
	제목 : ${vo.title}<br>
	글쓴이 : ${vo.writer}<br>
	본문 : ${vo.content}<br>
	날짜 : ${vo.regdate}<br>
	수정날짜 : ${vo.updatedate}<br>
	<a href="/board/list?pageNum=${param.pageNum}&searchType=${param.searchType}&keyword=${param.keyword}"><button>목록</button></a><br>
	<%--pageNum, searchType, keyword 여부 디버깅
	EL의 ${param.파라미터명}을 이용해 확인 가능
	get 파라미터에 SearchCriteria를 선언해 처리해도 되지만
	pageNum, searchType, keyword가 DB와 연계된 작업을 하지 않으므로
	아래와 같이 활용하는게 효율적이다.
	--%>
	
	페이지번호 : ${param.pageNum}<br>
	검색조건: ${param.searchType }<br>
	키워드 : ${param.keyword}<br>
	
	<!-- 글 삭제 버튼으로 삭제되면, list 페이지로 넘어간다.
		삭제로 넘어가는 경우 alert()창을 띄워서 "글이 삭제되었습니다"가
		출력되도록 작성 -->
	<form action="/board/remove" method="post">
		<input type="hidden" name="bno" value="${vo.bno }">
		<input type="submit" value="삭제">
	</form>
	
	<form action="/board/boardmodify" method="post">
		<input type="hidden" name="bno" value="${vo.bno }">
		<input type="hidden" name="pageNum" value="${param.pageNum}">
		<input type="hidden" name="searchType" value="${param.searchType}">
		<input type="hidden" name="keyword" value="${param.keyword}">
		<input type="submit" value="수정">
	</form>
</body>
</html>