<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-F3w7mX95PdgyTmZZMECAngseQB83DfGTowi0iMjiWaeVhAn4FJkqJByhZMI3AhiU" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-/bQdsTh/da6pkI1MST/rWKFNjaCP5gBSY4sEBT38Q/9RBh9AH40zEOg7Hlq2THRZ" crossorigin="anonymous"></script>

<!-- 1. JQuery 입력받을 수 있도록 처리 -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<style>
	#modiDiv {
		width:	300px;
		height: 100px;
		background-color: green;
		position:	absolute;
		top:	50%;
		left:	50%;
		margin-top: -50px;
		margin-right: -150px;
		z-index: 1;
	}
</style>

</head>
<body>
	
	<h1>상세 페이지</h1>
	번호 : ${vo.bno}<br>
	제목 : ${vo.title}<br>
	글쓴이 : ${vo.writer}<br>
	본문 : ${vo.content}<br>
	날짜 : ${vo.regdate}<br>
	수정날짜 : ${vo.updatedate}<br>
	
	<%--pageNum, searchType, keyword 여부 디버깅
	EL의 ${param.파라미터명}을 이용해 확인 가능
	get 파라미터에 SearchCriteria를 선언해 처리해도 되지만
	pageNum, searchType, keyword가 DB와 연계된 작업을 하지 않으므로
	아래와 같이 활용하는게 효율적이다.
	--%>
	
	페이지번호 : ${param.pageNum}<br>
	검색조건: ${param.searchType }<br>
	키워드 : ${param.keyword}<br>
	
	<a href="/board/list?pageNum=${param.pageNum}&searchType=${param.searchType}&keyword=${param.keyword}"><button class="btn btn-primary">목록</button></a><br>
	<!-- 글 삭제 버튼으로 삭제되면, list 페이지로 넘어간다.
		삭제로 넘어가는 경우 alert()창을 띄워서 "글이 삭제되었습니다"가
		출력되도록 작성 -->
	<form action="/board/remove" method="post">
		<input type="hidden" name="bno" value="${vo.bno }">
		<input type="submit" class="btn btn-danger" value="삭제">
	</form>
	
	<form action="/board/boardmodify" method="post">
		<input type="hidden" name="bno" value="${vo.bno }">
		<input type="hidden" name="pageNum" value="${param.pageNum}">
		<input type="hidden" name="searchType" value="${param.searchType}">
		<input type="hidden" name="keyword" value="${param.keyword}">
		<input type="submit" class= "btn btn-warning" value="수정">
	</form>

	<hr>
	<h2>댓글 영역</h2>
	<!-- 글쓰기 창 -->
	<div>
		<div>
			REPLYER <input type="text" name="replyer" id="newReplyWriter">
		</div>
		<div>
			REPLY TEXT <input type="text" name="reply" id="newReplyText">
		</div>
		<button id="replyAddBtn">ADD REPLY</button>
	</div>
	<hr>
	<div class="row">
		<h3 class="text-primary">댓글</h3>
		<div id="replies">
		</div>
	</div>
	
<!-- 3. 모달창, 기타 ajax 호출 로직을 가져와 작동 -->
	<div id="modiDiv" style="display:none;">
		<div class="modal-title"></div>
		<div>
			<input type="text" id="replytext">
		</div>
		<div>
			<button type="button" id="replyModBtn">Modify</button>
			<button type="button" id="replyDelBtn">Delete</button>
			<button type="button" id="closeBtn">close</button>
		</div>
	</div>
	
<!-- 2. body태그 하단에 <script>태그 작성 후 var bno = ${vo.bno}로 글번호를 받은 다음
function getAllList()를 test.jsp에서 복붙하여 게시물별 페이지에서 작동하도록 확인 -->	
	<script type="text/javascript">
		var bno = ${vo.bno};
		
		// 댓글 출력
		function getAllList() {
			$.getJSON("/replies/all/" + bno, function(data){
				// data 변수가 바로 얻어온 JSON데이터의 집합
				// console.log(data);
				
				// str 변수 내부에 문자 형태로 html코드를 작성
				var str = "";
				
				$(data).each(function() {
					// $(data).each()는 향상된 for문처럼 내부 데이터를 하나하나 반복
					// 내부 this는 댓글 하나하나
					
					// 시간 처리
					var timestamp = this.updatedate;
					var date = new Date(timestamp);
					// date만으로 보기 힘든 형식을 알아보기 쉬운 형태로 수정
					var formattedTime = "게시일 : " + date.getFullYear() // 년도 추출
					+ "/" + (date.getMonth() +1)	// 월 추출(0월부터 시작)
					+ "/" + date.getDate()			// 날짜 추출
					+ "/" + date.getHours()			// 시간 추출
					+ ":" + date.getMinutes()		// 분 추출
					+ ":" + date.getSeconds()		// 초 추출
					
					str += "<div class='replyLi' data-rno='" + this.rno + "'> <strong> @" 
					+ this.replyer + "</strong> - " + formattedTime + "<br>"
					+ "<div class='reply'>" + this.reply + "</div>"
					+ " <button type='button' class='btn btn-info'>수정/삭제</button></div><br>";
					/* str += "<li data-rno='" + this.rno + "' class='replyLi'>" 
					+ this.reply + " - <span>" + this.replyer + "</span>"
					+ " <button>수정/삭제</button></li>"; */
				});
				$("#replies").html(str);
			});
		}
	getAllList();
	
	// 모달창 보이기
	$("#replies").on("click", ".replyLi button", function() {
		var replyLi = $(this).parent();
		
		var rno = replyLi.attr("data-rno");
		var reply= replyLi.children(".reply").text();	// li태그 내부 글씨 얻기
		
		console.log(replyLi);
		console.log(rno);
		console.log(reply);
		$(".modal-title").html(rno);	// 모달 상단에 rno 넣기
		$("#replytext").val(reply);		// 모달 수정창에 reply 넣기
		$("#modiDiv").show("slow");		// 창에 애니메이션 효과 넣기
	});
	
	
	// 모달창 닫기_closeBtn 버튼을 눌렀을 때 #modDiv가 hide되도록 설정
	$("#closeBtn").on("click", function() {
		$("#modiDiv").hide("slow");
	})
	
	
	
	// 댓글 생성
	$("#replyAddBtn").on("click", function() {

		// 각 input태그에 들어있던 rmfTmsdl, 본문의 value값을 변수에 저장
		var replyer = $("#newReplyWriter").val();
		var reply = $("#newReplyText").val();

		$.ajax({
			type : 'post',
			url : '/replies',
			headers : {
				"Content-Type" : "application/json",
				"X-HTTP-Method-Override" : "POST"
			},
			dataType : 'text',
			data : JSON.stringify({
				bno : bno,
				replyer : replyer,
				reply : reply
			}),
			success : function(result) {
				if (result == 'SUCCESS') {
					alert("등록 되었습니다.");
					// 댓글 생성 후 input창 비우기
					$("#newReplyWriter").val("");
					$("#newReplyText").val("");
					getAllList();
				}
			}
		});
	});
	

	getAllList()

	// 삭제버튼 작동
	$("#replyDelBtn").on("click", function() {
		var rno = $(".modal-title").html();
		console.log("삭제버튼 클릭");
		$.ajax({
			type : 'delete',
			url : '/replies/' + rno,
			success : function(result) {
				console.log("result : " + result);
				if (result === 'SUCCESS') {
					alert(rno + "번 댓글이 삭제 되었습니다.");
					$("#modiDiv").hide("slow");
					getAllList();
				}
			}
		});
	});

	// 수정버튼 작동
	$("#replyModBtn").on("click", function() {
		var rno = $(".modal-title").html();
		var reply = $("#replytext").val();
		console.log("수정버튼 클릭");

		$.ajax({
			type : 'put',
			url : '/replies/' + rno,
			headers : {
				"Content-Type" : "application/json",
				"X-HTTP-Method-Override" : "PUT"
			},
			data : JSON.stringify({
				reply : reply
			}),
			dataType : 'text',
			success : function(result) {
				console.log("result : " + result);
				if (result == 'SUCCESS') {
					alert(rno + "수정 되었습니다.");
					// 댓글 수정 후 모달창 닫고 새 댓글 목록 갱신
					$("#modiDiv").hide("slow");
					getAllList();
				}
			}
		});
	});
	</script>
</body>
</html>