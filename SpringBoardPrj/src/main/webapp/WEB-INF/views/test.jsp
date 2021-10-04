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
		margin-top: -50%;
		margin-left: -150%;
		padding: 10px;
		z-index: 1000;
	}
</style>
</head>

<body>
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
		
		$("#replies").on("click", ".replyLi button", function() {
			var reply = $(this).parent();
			document.write(reply);
			
			var rno = reply.attr("data-rno");
			var replytext = reply.text();
			
			$(".modal-title").html(rno);
			$("#replytext").val(replytext);
			$("#modiDiv").show("slow");
		});
		
	</script>
</body>
</html>