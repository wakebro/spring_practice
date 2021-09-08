<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>섭씨 온도를 적으세요</h1>
	<form action="ctof" method="POST">
		<input type="text" name="cel" required="required">
		<input type="submit" value="변환">
	</form>
</body>
</html>