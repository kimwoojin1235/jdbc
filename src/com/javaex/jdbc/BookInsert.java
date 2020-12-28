package com.javaex.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BookInsert {

	public static void main(String[] args) {
		// 0. import java.sql.*;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			 // 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");
		    // 2. Connection 얻어오기
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(url, "webdb", "webdb");

		    // 3. SQL문 준비 / 바인딩 / 실행
			/*INSERT into book
			VALUES(seq_book_id.nextval,'우리들의 일그러진 영웅','다림','1998-02-22',1);*/
			String query ="";
			query+=" INSERT into book";
			query+=" VALUES(seq_book_id.nextval,?,?,?,?)";
			pstmt=conn.prepareStatement(query);
			pstmt.setString(1, "셜록 홈즈");
			pstmt.setString(2, "느낌이 있는 책");
			pstmt.setString(3, "2010-05-07");
			pstmt.setInt(4, 1);
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
		        if (rs != null) {
		            rs.close();
		        }                
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
