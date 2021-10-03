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
	
	<ul id="replies">
	</ul>
	
	<div>
		<div>
			REPLYER <input type="text" name="replyer" id="newReplyWriter">
		</div>
		<div>
			REPLY TEXT <input type="text" name="replytext" id="newReplyText">
		</div>
		<button id="replyAddBtn">ADD REPLY</button>
	</div>
	
	<!-- jquery -->
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
	<script type="text/javascript">
		var bno = 81921;
		function getAllList() {
			$.getJSON("/replies/all/" + bno, function(data){
				var str = "";
				console.log(data.length);
				
				$(data).each(
					function() {
						str += "<li data-rno='" + this.rno + "' class='replyLi'>" 
						+ this.rno + ":" + this.reply 
						+ "<button>수정/삭제</button></li>";
					}		
				);
				$("#replies").html(str);
			});
		}
		
		
		
		$("#replyAddBtn").on("click", function() {
			var bno = 81921;
			var replyer = $("#newReplyWriter").val();
			var replytext = $("#newReplyText").val();
			
			
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
					reply : replytext
				}),
				success : function(result) {
					if(result == 'SUCCESS'){
						alert("등록 되었습니다.");
						getAllList();
					}
				}
			});
		});
		
		
		
	</script>
	<script type="text/javascript">
	$("#replies").on("click", ".replyLi button", function() {
		var reply = $(this).parent();
		document.write(reply);
		
		var rno = reply.attr("data-rno");
		var replytext = reply.text();
		
		$(".modal-title").html(rno);
		${"#replytext"}.val(replytext);
		$("#modiDiv").show("slow");
	});
	</script>
		
	
</body>
</html>