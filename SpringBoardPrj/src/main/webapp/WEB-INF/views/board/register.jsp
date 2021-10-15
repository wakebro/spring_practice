<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
	<!DOCTYPE html>
	<html>

	<head>
		<meta charset="UTF-8">
		<title>Insert title here</title>
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
		<style>
			.uploadResult {
				width: 100%;
				background-color: gray;
			}

			.uploadResult ul {
				display: flex;
				flex-flow: row;
				justify-content: center;
				align-items: center;
			}

			.uploadResult ul li {
				list-style: none;
				padding: 10px;
			}

			.uploadResult ul li img {
				width: 20px;
			}

			.uploadResult span:hover {
				cursor: pointer;
			}
		</style>
	</head>

	<body>
		<h1>register 페이지</h1>
		<form action="/board/register" method="post">
			제 목 : <input type="text" name="title"><br>
			글쓴이 : <input type="text" name="writer"><br>
			본 문 : <br>
			<textarea rows="10" cols="50" name="content"></textarea><br>
			<input type="reset" value="초기화">
			<input type="submit" value="작성" id="submitBtn">
		</form>
		<hr>
		<!-- 파일 업로드_register.jsp가 form역할 -->
		<h2>첨부파일</h2>
		<div class="uploadDiv">
			<input type="file" name="uploadFile" multiple>
		</div>
		<div class="uploadResult">
			<ul>
				<!-- 업로드된 파일이 들어갈 자리 -->
			</ul>
		</div>
		<button id="uploadBtn">Upload</button><br>


		<a href="/board/list"><button>목록</button></a>
		<!-- 파일 업로드 -->
		<script>
			$(document).ready(function () {

				// 정규표현식으로 파일 확장자, 용량 거르기
				let regex = new RegExp("(.*?)\.(exe|sh|zip|alz)$");
				let maxSize = 5242880; // 5MB
				function checkExtension(fileName, fileSize) {
					if (fileSize > maxSize) {
						console.log(fileSize);
						alert("파일 사이즈 초과!");
						return false;
					}

					// 위에 만든 형식에 파일 이름이 해당하는지 검사
					if (regex.test(fileName)) {
						alert("해당 종류의 파일은 업로드할 수 없습니다.");
						return false;
					}
					return true;
				}

				// 업로드시 파일 선택을 초기화시키기
				let clonObj = $(".uploadDiv").clone();


				$("#uploadBtn").on("click", function (e) {

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
						if (!checkExtension(files[i].name, files[i].size)) {
							return false;
						}

						// 검사가 끝난 파일 데이터를 formData에 집어넣기
						formData.append("uploadFile", files[i])
					}

					$.ajax({
						url: '/uploadAjaxAction',
						processData: false,
						contentType: false,
						data: formData,
						type: 'POST',
						dataType: 'json',
						success: function (result) {
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
					$(uploadResultArr).each(function (i, obj) {
						// console.log("------------------------------");
						// console.log(i);
						// console.log(obj);

						if (!obj.image) {
							let fileCallPath = encodeURIComponent(
								obj.uploadpath + "/"
								+ obj.uuid + "_" + obj.fileName);
							// 그림이 아니면 썸네일 대신 resources폴더 내 이미지를 대체로 설정
							str += "<li "
								+ " data-path='" + obj.uploadpath + "' data-uuid='" + obj.uuid
								+ "' data-filename='" + obj.fileName + "' data-type='" + obj.image
								+ "'><a href='/download?fileName=" + fileCallPath 
								+ "'>" + "<img src='/resources/image/attachment.png'>"
								+ obj.fileName + "</a>"
								+ "<span data-file=\'" + fileCallPath + "\' data-type='file'> X </span></li>";
						} else {
							// str += "<li>" + obj.fileName + "</li>";

							// 파일 이름 + 썸네일을 보여주기 위해 썸네일 주소 요청하게 만들기
							let fileCallPath = encodeURIComponent(obj.uploadpath + "/s_"
								+ obj.uuid + "_" + obj.fileName);

							let fileCallPath2 = encodeURIComponent(obj.uploadpath + "/"
								+ obj.uuid + "_" + obj.fileName);

							// fileCallPath를 조립
							str += "<li "
								+ " data-path='" + obj.uploadpath + "' data-uuid='" + obj.uuid
								+ "' data-filename='" + obj.fileName + "' data-type='" + obj.image
								+ "'><a href='/download?fileName=" + fileCallPath2
								+ "'><img src='/display?fileName=" + fileCallPath + "'>'" + obj.fileName + "</a>"
								+ "<span data-file=\'" + fileCallPath + "\' data-type='file'> X </span></li>";
						}
					});
					uploadResult.append(str);
				}// End showUploadedFile

				$(".uploadResult").on("click", "span", function (e) {
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
						url: '/deleteFile',
						data: { fileName: targetFile, type: type },
						dataType: 'text',
						type: 'POST',
						success: function (result) {
							alert(result);
							targetLi.remove();
						}
					});
				})// End uploadResult

				$("#submitBtn").on("click", function (e) {
					// 클릭된 요소의 동작 중지(제출버튼의 경우 제출이 되지 않음)
					// 글쓰기를 했을 때, 그림이 몇 장 추가될지는 글을 써봐야 알 수 있는 상태
					// 제출을 바로 하지 못하도록 막음
					e.preventDefault();
					console.log("전송버튼클릭")

					// 위의 form에 업로드된 그림요소들에 대한 정보를 추가
					// 1. form 태그 정보 얻어오기
					// 상단 form태그에 이미지 관련 정보를 hidden으로 추가하기 위해 얻어온다
					let formObj = $("form");

					// 2. 추가 내용을 먼저 받기 위해 빈 문자열 생성
					let str = "";

					// 3. .uploadResult 내부에 들어간 그림정보를 얻어와서
					// formObj내부에 넣어준다.
					// .uploadResult 내부 ul 내부의 li가 그림정보를 담고 있으므로
					// 반복문으로 처리
					$(".uploadResult ul li").each(function (i, obj) {

						let imgInfo = $(obj);
						// BoardVO 내부의 List<BoardAttachVO>를 처리하기 위해 변수명 attachList로 전달
						str += "<input type='hidden' name='attachList[" + i + "].fileName'"
							+ " value='" + imgInfo.data("filename") + "'>"
							+ "<input type='hidden' name='attachList[" + i + "].uuid'"
							+ " value='" + imgInfo.data("uuid") + "'>"
							+ "<input type='hidden' name='attachList[" + i + "].uploadpath'"
							+ " value='" + imgInfo.data("path") + "'>"
							+ "<input type='hidden' name='attachList[" + i + "].image'"
							+ " value='" + imgInfo.data("type") + "'>"
							
						});
					// 반복이 끝나면, formObj에 위 태그를 추가한 후 제출한다.
					// 그림 정보가 잘 추가되는지 디버깅
					formObj.append(str);
					console.log("formObj");
					console.log($(formObj));
					formObj.submit();
				})


			});
		</script>
	</body>

	</html>