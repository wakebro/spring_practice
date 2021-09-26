<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>모임방</h1>
	<a href="/meet/info?num=${detail.m_num }"><button>정보</button></a>
	<a href="#"><button>게시판</button></a>
	<a href="#"><button>갤러리</button></a>
	<a href="#"><button>채팅</button></a>
	<br>
	<table border="1">
		<tr><td colspan="2">${detail.m_profile }</td></tr>
		<tr>
			<td>${detail.i_cate_num }</td>
			<td>${detail.m_name }</td>
		</tr>
		<tr><td colspan="2">${detail.m_content }</td></tr>
	</table>
	
	<h2>모임 정모</h2>
	
	${detail }
	
	
	<br>
	<a href="/main/main"><button>메인화면</button></a>
</body>
</html>