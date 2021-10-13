<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
</head>
<body>
	<h1>Upload with Ajax</h1>
	<div class="uploadDiv">
		<input type="file" name="uploadFile" multiple>
	</div>
	<button id="uploadBtn">Upload</button>
	<script>
		$(document).ready(function(){
			$("#uploadBtn").on("click",function(e){
				
				// ajax는 제출 버튼을 눌렀을 때 목적지가 없기 때문에
				// 빈 폼을 만들고 거기에 정보를 채워 나간다.
				let formData = new FormData();
				let inputFile = $("input[name='uploadFile']");
				console.log(inputFile);
				let files = inputFile[0].files;
				console.log(files);
				
			});
		});
	</script>
</body>
</html>