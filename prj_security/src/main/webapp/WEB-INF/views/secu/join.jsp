<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>회원가입</h1>
	
	<form action="/secu/join" method="post">
		아이디 : <input type="text" name="userid"><br>
		비밀번호 : <input type="password" name="userpw"><br>
		이름 : <input type="text" name="username"><br>
		권한 :
		<select name="auth">
			<option value="ROLE_USER">유저</option>
			<option value="ROLE_MEMBER">멤버</option>
			<option value="ROLE_ADMIN">관리자</option>
		</select><br>
		<input type="reset" value="초기화">
		<input type="submit" value="가입">
	</form>
</body>
</html>