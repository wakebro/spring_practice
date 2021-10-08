package org.ict.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import lombok.extern.log4j.Log4j;

@Log4j
public class CustomLoginSuccessHander implements AuthenticationSuccessHandler {

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		
		// 로그인 성공시 어떤 권한인지 체크하기 위해 부여받은 권한을 불러오기
		// ROLE_ADMIN의 경우는 ROLE_MEMBER가 함께 부여되기 때문에 경우에 따라
		// 권한은 여럿이 될 수도 있음
		log.warn("로그인 성공");
		List<String> roleList = new ArrayList<String>();
		
		for(GrantedAuthority role : authentication.getAuthorities()) {
			roleList.add(role.getAuthority());
		}
		
		// roleList에 포함된 권한을 통해 로그인 계정의 권한에 따라 처리
		log.warn("부여받은 권한들 " + roleList);
		if(roleList.contains("ROLE_ADMIN")) {
			response.sendRedirect("/secu/admin");
			return;
		}
		if(roleList.contains("ROLE_MEMBER")) {
			response.sendRedirect("/secu/member");
			return;
		}
		
		response.sendRedirect("/");
			
		
	}

}
