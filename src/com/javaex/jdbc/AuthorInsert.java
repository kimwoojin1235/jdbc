package com.javaex.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AuthorInsert {

	public static void main(String[] args) {
		
		
		// 0. import java.sql.*;
		Connection conn = null;
		PreparedStatement pstmt = null;
		//ResultSet rs = null;

		try {
		    // 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");
		    // 2. Connection 얻어오기
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(url, "webdb", "webdb");
		    // 3. SQL문 준비 / 바인딩 / 실행
			String query ="INSERT into author VALUES(seq_author_id.nextval,?,?)";
			//;를 붙이지 않아도 알아서 붙여준다.?은 나중에 넣어준다 이건 여기서 정한 문법이기에 따라야 한다.
			pstmt=conn.prepareStatement(query);//쿼리로 만든다.
			
			pstmt.setString(1,"김우진");
			pstmt.setString(2,"서울시 강북구");
			//값을 넣는 방법은 그냥 외워서 사용을 하자 쿼리문의 ?숫자와 숫자가 맞아야 한다.
			//INSERT into author VALUES(seq_author_id.nextval,"김우진","서울시 강북구")
			//쿼리문은 sql에서 테스트를 해보고 이클립스로 가지고 와서 가공한후 사용한다.
			int count=pstmt.executeUpdate();
		    // 4.결과처리
			System.out.println(count+ "건이 저장되었습니다.");
		} catch (ClassNotFoundException e) {
		    System.out.println("error: 드라이버 로딩 실패 - " + e);
		} catch (SQLException e) {
		    System.out.println("error:" + e);
		} finally {
		   
		    // 5. 자원정리
		    try {
		       /* if (rs != null) {
		            rs.close();
		        }  */              
		        if (pstmt != null) {
		            pstmt.close();
		        }
		        if (conn != null) {
		            conn.close();
		        }
		    } catch (SQLException e) {
		        System.out.println("error:" + e);
		    }

		}

	}

}
