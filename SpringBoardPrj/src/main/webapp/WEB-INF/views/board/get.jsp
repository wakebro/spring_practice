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
	.uploadResult{
		width:100%;
		background-color: gray;
	}
	.uploadResult ul{
		display: flex;
		flex-flow: row;
		justify-content: center;
		align-items: center;
	}
	.uploadResult ul li{
		list-style: none;
		padding: 10px;
	}
	.uploadResult ul li img{
		width: 20px;
	}
	.uploadResult span:hover{
		cursor: pointer;
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
	
	<!-- 파일 업로드 -->
	<div class="uploadDiv">
		<input type="file" name="uploadFile" multiple>
	</div>
	<div class="uploadResult">
		<ul>
			<!-- 업로드된 파일이 들어갈 자리 -->
		</ul>
	</div>
	<button id="uploadBtn">Upload</button>
	<hr>
	<h2>댓글 영역</h2>
	<!-- 댓글 창 -->
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
		
		console.log(bno)
		console.log(replyer)
		console.log(reply)
		

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


<!-- 파일 업로드 -->
	<script>
		$(document).ready(function(){

			// 정규표현식으로 파일 확장자, 용량 거르기
			let regex = new RegExp("(.*?)\.(exe|sh|zip|alz)$");
			let maxSize = 5242880; // 5MB
			function checkExtension(fileName, fileSize){
				if(fileSize > maxSize){
					console.log(fileSize);
					alert("파일 사이즈 초과!");
					return false;
				}
				
				// 위에 만든 형식에 파일 이름이 해당하는지 검사
				if(regex.test(fileName)){
					alert("해당 종류의 파일은 업로드할 수 없습니다.");
					return false;
				}
				return true;
			}

			// 업로드시 파일 선택을 초기화시키기
			let clonObj = $(".uploadDiv").clone();
			

			$("#uploadBtn").on("click",function(e){
				
				// ajax는 제출 버튼을 눌렀을 때 목적지가 없기 때문에
				// 빈 폼을 만들고 거기에 정보를 채워 나간다.
				let formData = new FormData();
				let inputFile = $("input[name='uploadFile']");
				console.log(inputFile);
				let files = inputFile[0].files;
				console.log("파일 : ");
				console.log(files);
				for (var i = 0; i < files.length; i++) {
					// formData에 넣기 전에 확장사, 용량 검사
					if(!checkExtension(files[i].name, files[i].size)){
						return false;
					}
					
					// 검사가 끝난 파일 데이터를 formData에 집어넣기
					formData.append("uploadFile", files[i])
				}
				
				$.ajax({
					url : '/uploadAjaxAction',
					processData : false,
					contentType : false,
					data : formData,
					type : 'POST',
					dataType: 'json',
					success : function(result){
						//alert("Uploaded");
						
						// 내가 업로드한 파일 디버깅
						console.log(result);
						
						showUploadedFile(result);
						
						// 세팅되어있던 파일이 업로드되면서 목록에서 사라지게 처리
						$(".uploadDiv").html(clonObj.html());
					}
				}); // ajax
			}); // #uploadBtn

			let uploadResult = $(".uploadResult ul");
			
			function showUploadedFile(uploadResultArr) {
				let str = "";

				console.log(uploadResultArr)

				// i는 인덱스 번호(0, 1, 2, ...) obj 그림파일 정보가 담긴 json
				$(uploadResultArr).each(function(i, obj) {
					// console.log("------------------------------");
					// console.log(i);
					// console.log(obj);

					if (!obj.image) {
						let fileCallPath = encodeURIComponent(obj.uploadPath + "/"
							+ obj.uuid + "_" + obj.fileName);
						// 그림이 아니면 썸네일 대신 resources폴더 내 이미지를 대체로 설정
						str += "<li><a href='/download?fileName=" + fileCallPath + "'>" +
							"<img src='/resources/image/attachment.png'>" + obj.fileName + "</a>"
							+ "<span data-file=\'" + fileCallPath + "\' data-type='file'> X </span></li>";
					} else {
						// str += "<li>" + obj.fileName + "</li>";

						// 파일 이름 + 썸네일을 보여주기 위해 썸네일 주소 요청하게 만들기
						let fileCallPath = encodeURIComponent(obj.uploadPath + "/s_"
							+ obj.uuid + "_" + obj.fileName);

						let fileCallPath2 = encodeURIComponent(obj.uploadPath + "/"
							+ obj.uuid + "_" + obj.fileName);

						// fileCallPath를 조립
						str += "<li><a href='/download?fileName=" + fileCallPath2
							+ "'><img src='/display?fileName=" + fileCallPath + "'>'" + obj.fileName + "</a>"
							+ "<span data-file=\'" + fileCallPath + "\'data-type='image'> X </span>" + "</li>";
					}
				});
				uploadResult.append(str);
			}// End showUploadedFile

			$(".uploadResult").on("click", "span", function(e){
				console.log(e);
				let targetFile = $(this).data("file");
				let type = $(this).data("type");
				console.log("타켓파일");
				console.log(targetFile);
				console.log("타입");
				console.log(type);

				let targetLi = $(this).closest("li");
				console.log(targetLi);

				$.ajax({
					url : '/deleteFile',
					data : {fileName: targetFile, type:type},
					dataType : 'text',
					type : 'POST',
					success : function(result){
						alert(result);
						targetLi.remove();						
					}
				});
				
			})
		});
	</script>
</body>
</html>