<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<!-- 부트스트랩 CSS, js 파일 import -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-/bQdsTh/da6pkI1MST/rWKFNjaCP5gBSY4sEBT38Q/9RBh9AH40zEOg7Hlq2THRZ" crossorigin="anonymous"></script>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-F3w7mX95PdgyTmZZMECAngseQB83DfGTowi0iMjiWaeVhAn4FJkqJByhZMI3AhiU" crossorigin="anonymous">

<script defer="defer">
	window.onload = function(){
		// 컨트롤러에서 success라는 이름으로 날린 자료가 들어오는지 확인
		// 그냥 list페이지 접근시 success를 날려주지 않아서
		// 아무것도 들어오지 않고
		// remove로직의 결과로 넘어왔을 때는 데이터가 전달
		let result = "${result}";
		let bno = "${bno}";

		// 모달 사용을 위한 변수 선언
		// var myModal = new bootstrap.Modal(document.getElementById('myModal'));
		var myModal = new bootstrap.Modal(document.getElementById('myModal'),
				focus);
		// 이게 코드가 위에 있어서 코드 실행 이전에 myModal을 찾으려면 에러가 발생
		// 이걸 방지하기 위해 window.onload를 사용
		console.log(myModal);

		if (result === "remove") {
			alert(bno + "번 글이 삭제되었습니다.");
		} else if (result === "register") {
			// alert(bno + "번 글이 생성되었습니다.");

			// 공식 하단 문서의 .show()를 응용
			bno.get
			myModal.show();
		}
	}
</script>
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
					<td><a href="/board/get?bno=${n.bno }&pageNum=${btnMaker.cri.pageNum}&searchType=${btnMaker.cri.searchType }&keyword=${btnMaker.cri.keyword}">${n.title } 
						<c:if test="${n.replyCnt > 0 }">
							<span class="badge rounded-pill bg-primary">${n.replyCnt }</span>
						</c:if></a>
					</td>
					<td>${n.writer }</td>
					<td>${n.regdate }</td>
					<td>${n.updatedate }</td>
				</tr>
			</c:forEach>
		</tbody>
		<tfoot>
		</tfoot>
	</table>
	${btnMaker }<br>
	<nav aria-label="Page navigation example">
	  <ul class="pagination justify-content-center">
	  	<!-- prev버튼 -->
	  	<c:if test="${btnMaker.prev }">
	    	<li class="page-item">
	    		<a class="page-link" href="/board/list?pageNum=${btnMaker.startPage - 1}&amount=${btnMaker.btnNum}&searchType=${btnMaker.cri.searchType }&keyword=${btnMaker.cri.keyword}">Previous</a>
	    	</li>
	   	</c:if>
			
		<!-- 페이징 버튼  -->
		<c:forEach var="num" begin="${btnMaker.startPage }" end="${btnMaker.endPage }">
			<li class="page-item ${btnMaker.cri.pageNum eq num ? 'active' : '' }">
				<a class="page-link" 
					href="/board/list?pageNum=${num }&amount=${btnMaker.btnNum}&searchType=${btnMaker.cri.searchType }&keyword=${btnMaker.cri.keyword}">${num }
				</a>
			</li>
		</c:forEach>	    
	    
	    <!-- next버튼 -->
	    <c:if test="${btnMaker.next }">
	    	<li class="page-item">
	    		<a class="page-link" href="/board/list?pageNum=${btnMaker.startPage + 10}&amount=${btnMaker.btnNum}&searchType=${btnMaker.cri.searchType }&keyword=${btnMaker.cri.keyword}">Next</a>
	    	</li>
	    </c:if>
	  </ul>
	</nav>
	
	<!-- 검색창 -->
	<form action="/board/list" method="get">
		<select name="searchType">
			<option value="n" <c:out value="${btnMaker.cri.searchType == null ? 'selected' : '' }"/>>
			-
			</option>			
			<option value="t" <c:out value="${btnMaker.cri.searchType eq 't' ? 'selected' : '' }"/>>
			제목
			</option>			
			<option value="c" <c:out value="${btnMaker.cri.searchType eq 'c' ? 'selected' : '' }"/>>
			본문
			</option>			
			<option value="w" <c:out value="${btnMaker.cri.searchType eq 'w' ? 'selected' : '' }"/>>
			글쓴이
			</option>			
			<option value="tc" <c:out value="${btnMaker.cri.searchType eq 'tc' ? 'selected' : '' }"/>>
			제목+본문
			</option>			
			<option value="cw" <c:out value="${btnMaker.cri.searchType eq 'cw' ? 'selected' : '' }"/>>
			본문+글쓴이
			</option>			
			<option value="tcw" <c:out value="${btnMaker.cri.searchType eq 'tcw' ? 'selected' : '' }"/>>
			제목+본문+글쓴이
			</option>			
		</select>
		<input type="text" name="keyword" placeholder="검색어" value="${btnMaker.cri.keyword }">
		<input type="submit" value="검색">
	</form>
	<a href="/board/register"><button>글쓰기</button></a>
	
	
	<!-- 모달 코드는 작성이 안 되어있는게 아니라
		작성은 해두고, css의 display 옵션을 none으로 해두어
		특정 요건에 만족했을 때만 display를 허용하도록 설계
		아래에 넣어두고, 일반 화면에서는 보이지 않는다.
	 -->
	<div class="modal" id="myModal" tabindex="-1">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title">글 작성 완료</h5>
					<button type="button" class="btn-close" data-bs-dismiss="modal"
						aria-label="Close"></button>
				</div>
				<div class="modal-body">
					<p>${bno }번 글이 작성되었습니다.</p>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-primary"
						data-bs-dismiss="modal">확인</button>
				</div>
			</div>
		</div>
	</div>
</body>
</html>