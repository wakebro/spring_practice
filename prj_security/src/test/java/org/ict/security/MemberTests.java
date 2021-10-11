package org.ict.security;

import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "file:src/main/webapp/WEB-INF/spring/root-context.xml", 
						"file:src/main/webapp/WEB-INF/spring/security-context.xml"})
@Log4j
public class MemberTests {
	
	@Autowired // 복호화 담당
	private PasswordEncoder pwen;
	@Autowired // DB접근 담당
	private DataSource ds;
	
	@Test
	public void testCryptDefaultDB() {
		String[] idList = {"user00", "member00", "admin00"};
		
		String sql = "UPDATE users SET password=? WHERE username=?";
		try {
			Connection con = ds.getConnection();
			
			for (String id : idList) {
				PreparedStatement pstmt = con.prepareStatement(sql);
				pstmt.setString(1, pwen.encode("pw00")); // pwen.encode("문자열") 시 자동복호화
				pstmt.setString(2, id);
				pstmt.executeUpdate();
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
