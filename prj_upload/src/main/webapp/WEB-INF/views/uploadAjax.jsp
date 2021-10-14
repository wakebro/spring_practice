<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<style>
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
</style>
</head>
<body>
	<h1>Upload with Ajax</h1>
	<div class="uploadDiv">
		<input type="file" name="uploadFile" multiple>
	</div>
	<div class="uploadResult">
		<ul>
			<!-- 업로드된 파일이 들어갈 자리 -->
		</ul>
	</div>
	<button id="uploadBtn">Upload</button>

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

					if(!obj.image){
						// 그림이 아니면 썸네일 대신 resources폴더 내 이미지를 대체로 설정
						str += "<li><img src='/resources/image/attachment.png'>"
							 + obj.fileName + "</li>";
					}else {
						// str += "<li>" + obj.fileName + "</li>";

						// 파일 이름 + 썸네일을 보여주기 위해 썸네일 주소 요청하게 만들기
						let fileCallPath = encodeURIComponent(obj.uploadPath + "/s_"
														+ obj.uuid + "_" + obj.fileName);

						// fileCallPath를 조립
						str += "<li><img src='/display?fileName=" + fileCallPath + "'>'"
							+ obj.fileName +"</li>";
					}
				});
				uploadResult.append(str);
			}// End showUploadedFile
		});
	</script>
</body>
</html>
