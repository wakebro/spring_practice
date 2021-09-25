<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>생성페이지</h1>
	<form action="/main/register" method="post">
		모임 이름 : <input type="text" name="m_name"><br>
		모임 인원 : <input type="text" name="m_people_cnt"><br>
		모임 지역 : <input type="text" name="m_area"><br>
		모임 사진 : <input type="text" name="m_profile"><br>
		모임 종류 : <input type="text" name="i_cate_num"><br>
		모임 방장 : <input type="text" name="u_id"><br>
		모임 내용 :<br>
		<textarea name="m_content"></textarea><br>
		<input type="submit" value="생성">
	</form>
</body>
</html>