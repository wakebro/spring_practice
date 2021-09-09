package org.ict.dao;

import static org.junit.Assert.fail;

import java.sql.*;

import org.junit.Test;

import lombok.extern.log4j.Log4j;

@Log4j
public class MySQLConnectionTest {
	
	// 커넥션 설정 완료
	static {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 테스트 코드를 실행하면 @Test가 붙은 메서드만 실행
	@Test
	public void testConnection() {
		try {
			Connection con = DriverManager.getConnection(
					"jdbc:mysql://127.0.0.1:3306/mysql?useSSL=false&serverTimezone=UTC", // MySQL자체에 접근
					"root",
					"mysql");
			log.info(con);
			log.info("MySQL 연결 완료");
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
	
	@Test
	public void testConnection2() {
		log.info("실행 불가");
	}
}
