<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>All 주소</h1>
	<h2>다양한 페이지 정보</h2>
	<!-- principal 내부에는 인증과 관련된 정보가 담겨있다. -->
	<p>principal : <sec:authentication property="principal"/></p>
	<!-- 스프링 시큐리티의 User를 상속해 만든 CustomUser에서 내부 멤버 변수로
	MemberVO member를 선언해놨고, getter를 만들어 놨기 때문에 principal.member를 표현식에 적으면
	.getMember()를 호출하는 효과 -->
	<p>MemberVO : <sec:authentication property="principal.member"/></p>
	<p>사용자의 이름 : <sec:authentication property="principal.member.userName"/></p>
	<p>사용자의 아이디 : <sec:authentication property="principal.member.userid"/></p>
	<p>사용자 권한 목록 : <sec:authentication property="principal.member.authList"/></p>
	
	<hr>
	<a href="/customLogout">로그아웃페이지 이동</a>
	<sec:authorize access="isAnonymous()">
		<!-- 로그인 안한(익명) 사용자의 경우 -->
		<a href="/customLogin">로그인</a>
	</sec:authorize>	
	<sec:authorize access="isAuthenticated()">
		<!-- 로그인 한(인증된) 사용자인 경우 -->
		<!-- xx님 환영합니다 인사말의 경우 -->
		<h2><sec:authentication property="principal.member.userName"/>님, 환영합니다.</h2>

		<!-- c태그 라이브러리를 사용하여 '운영자25'만 너무 잔갑습니다. 출력 
		아래와 같이 var 속성을 지정하면 property 정보가 var 변수명에 저장된다-->
		<sec:authentication property="principal" var="secInfo"/>
		<c:if test="${secInfo.member.userName eq '운영자25'}">
			<h3>너무 반갑습니다.</h3>
		</c:if>
		<p>${secInfo.member.userName }
		<a href="/customLogout">로그아웃</a>
	</sec:authorize>
</body>
</html>