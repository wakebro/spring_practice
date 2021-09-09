package org.ict.dao;

import static org.junit.Assert.fail;

import java.sql.*;

import org.junit.Test;

import lombok.extern.log4j.Log4j;

// Log4j는 로깅 기능을 사용할 수 있도록 도와줌
// System.out.println();은 로깅 목적이 아니어서 
// 메모리 사용량이 높아 log를 남기려는 목적으로는 부적합
// 로깅만 할 수 있도록 Log4j 사용
// 참고로 Log4j2는 spring-boot에서 사용되고, 
// Log4j는 스프링에서 쓴다.
@Log4j
public class OjdbcConnectionTest {
	@Test
	public void testConnection() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String driver = "oracle.jdbc.driver.OracleDriver";
		String url = "jdbc:oracle:thin:@localhost:1521/XEPDB1";
		String db_id = "mytest";
		String db_pw = "mytest";
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, db_id, db_pw);
			log.info(con);
			log.info("정상적으로 연결");
		} catch (Exception e) {
			// 접속이 정상적이지 않을때 출력할 내용을 아래에 작성
			// fail 함수를 이용해 우칙 Failure Trace에 메세지를 출력
			fail(e.getMessage());
		}
	}
//	@Test
	public void testConnection2() {
		log.info("이 코드는 실행 불가");
	}
}
