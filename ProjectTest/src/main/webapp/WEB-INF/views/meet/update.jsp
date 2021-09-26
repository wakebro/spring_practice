<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>모임 수정창</h1>
	${vo }
	<form action="/meet/update/process" method="post">
		<input type="hidden" name="m_num" value="${vo.m_num }">
		모임 이름 : <input type="text" name="m_name" value="${vo.m_name }"><br>
		모임 인원 : <input type="text" name="m_people_cnt" value="${vo.m_people_cnt }"><br>
		모임 지역 : <input type="text" name="m_area" value="${vo.m_area }"><br>
		모임 사진 : <input type="text" name="m_profile" value="${vo.m_profile }"><br>
		모임 종류 : <input type="text" name="i_cate_num" value="${vo.i_cate_num }"><br>
		모임 방장 : <input type="text" name="u_id" readonly="readonly" value="${vo.u_id }"><br>
		모임 내용 :<br>
		<textarea name="m_content">${vo.m_content }</textarea><br>
		<input type="submit" value="수정">
	</form>
</body>
</html>