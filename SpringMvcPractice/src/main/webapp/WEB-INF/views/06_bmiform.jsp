<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>BMI 측정</h1>
	<form action="bmi" method="post">
		키 : <input type="text" name="height" required="required"><br/>
		체중 : <input type="text" name="weight" required="required"><br>
		<input type="submit" value="전송">
	</form>
</body>
</html>