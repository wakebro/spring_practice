<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
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
	<h2>Ajax 테스트</h2>
	
	<div>
		<div>
			REPLYER <input type="text" name="replyer" id="newReplyWriter">
		</div>
		<div>
			REPLY TEXT <input type="text" name="reply" id="newReplyText">
		</div>
		<button id="replyAddBtn">ADD REPLY</button>
	</div>
	
	<ul id="replies">
	</ul>
	
	<!-- 모달 요소는 안 보이기 때문에 어디에 넣어도 되지만 보통 html요소들 끼리 놨을 때,
	제일 아래쪽에 작성하는 경우가 많다.-->
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
	
	<!-- jquery -->
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
	<script type="text/javascript">
		var bno = 1111;
		
		function getAllList() {
			$.getJSON("/replies/all/" + bno, function(data){
				// data 변수가 바로 얻어온 JSON데이터의 집합
		//		console.log(data);
				
				// str 변수 내부에 문자 형태로 html 코드를 작성
				var str = "";
				// str = "<li>123</li>" + 
				// "<li>456</li>"+"<button>123</button>"+"<a>a</a>";
				
				// #replies인 ul태그 내부에 str을 끼워넣어
				// ul 내부에 <li>123</li>를 추가하는 구문
				// $("#replies").html(str); */
				
				
				// .each : 향상된 foreach문
				// data를 하나씩 떼어내어 진행
				// 내부 this는 댓글 하나하나를 뜻함
				$(data).each(
		//			console.log(this.rno);
		//			console.log("------------------");
					function() {
						str += "<li data-rno='" + this.rno + "' class='replyLi'>" 
						+ this.rno + " : " + this.replyer + " - " + this.reply 
						+ " <button>수정/삭제</button></li>";
					}		
				);
				$("#replies").html(str);
			});
		}
		getAllList()
		
		$("#replyAddBtn").on("click", function() {
			
			// 각 input태그에 들어있던 rmfTmsdl, 본문의 value값을 변수에 저장
			var replyer = $("#newReplyWriter").val();
			var reply = $("#newReplyText").val();
			
			
			$.ajax({
				type : 'post',
				url : '/replies',
				headers: {
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
					if(result == 'SUCCESS'){
						alert("등록 되었습니다.");
						// 댓글을 쓰고 나서 다시 새롭게 갱신된 목록을
						// 넣어주도록 전체 댓글 다시 조회
						getAllList();
					}
				}
			});
		});
		
		getAllList()
		
		
		// 이벤트 위임
		// 현재 이벤트를 걸려는 집단(button)을 포함하면서 범위가 제일 좁은
		// #replies로 시작조건 설정
		// .on("click", "목적지 태그까지의 요소들", function(){실행문})
		// 과 같이 위임시에는 파라미터가 3개 들어간다.
		$("#replies").on("click", ".replyLi button", function() {
			// this는 최하위 태그인 button이며, button의 부모는 결국 .replyLi다.
			var replyLi = $(this).parent();
			
			// .attr("속성명")을 하면 해당 속성의 값을 얻는다.
			var rno = replyLi.attr("data-rno");
			var reply= replyLi.text();	// li태그 내부 글씨 얻기
			//console.log(replyLi);
			//console.log(rno);
			//console.log(reply);
			
			// 클락한 버튼에 해당하는 댓글번호 + 본문이 얻어지는지 디버깅
			//alert(rno + " : " + replytext);
			
			// 모달 열리도록 수정
			$(".modal-title").html(rno);	// 모달 상단에 rno 넣기
			$("#replytext").val(reply);		// 모달 수정창에 reply 넣기
			$("#modiDiv").show("slow");		// 창에 애니메이션 효과 넣기
		});
		
		
		// 삭제버튼 작동
		$("#replyDelBtn").on("click", function() {
			// 삭제에 필요한 댓글번호를 모달 타이틀에서 얻기
			var rno = $(".modal-title").html();
			console.log("삭제버튼 클릭");
			$.ajax({
				type : 'delete',
				url : '/replies/' + rno,
				// 삭제로직은 rno만 전달
				// 호출타입 DELETE, URL 정보 이외엔 처리할게 없음
				
				// JSON데이터를 이용하여 움직이는 로직이 아니기 때문에 필요 없다
				/* headers : {
					"Content-Type" : "application/json",
					"X-HTTP-Method-Override" : "DELETE"
				},
				dataType : 'text', */
				
				success : function(result) {
					console.log("result : " + result);
					if (result === 'SUCCESS') {
						alert(rno + "번 댓글이 삭제 되었습니다.");
						// 댓글 삭제 후 모달창 닫고 새 댓글목록을 갱신
						$("#modiDiv").hide("slow");
						getAllList();
					}
				}
			});
		});
		
		// 수정버튼 작동
		$("#replyModBtn").on("click", function(){
			var rno = $(".modal-title").html();
			var reply = $("#replytext").val();
			console.log("수정버튼 클릭");

			$.ajax({
				type : 'patch',
				url : '/replies/' + rno,
				headers : {
					"Content-Type" : "application/json",
					"X-HTTP-Method-Override" : "PATCH"
				},
				contentType : 'application/jason',
				data : JSON.stringify({reply : reply}),
				dataType : 'text',
				success : function(result){
					console.log("result : " + result);
					if(result == 'SUCCESS'){
						alert("수정 되었습니다.");
						$("#modiDiv").hide("slow");
						getAllList();
					}
				}
			});
		});
	</script>
</body>
</html>