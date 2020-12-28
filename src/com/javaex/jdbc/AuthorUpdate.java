package com.javaex.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AuthorUpdate {

	public static void main(String[] args) {
		// 0. import java.sql.*;
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
		    // 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");
		    // 2. Connection 얻어오기
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(url, "webdb", "webdb");
		    // 3. SQL문 준비 / 바인딩 / 실행
			/*
			UPDATE author
			set author_desc = '서울시 서초구 하이미디어 별관'
			WHERE author_id = 21;
			*/
			String query ="";
			query += " UPDATE author";
			query += " set author_desc = ?";
			query += " WHERE author_id = ?";
			//쿼리문을 여러줄로 만들었다. 그리고 만들때는 오타가 나지 않게 신중이 작성한다.
			//아래 쿼리문들은 한칸씩 뛰어줘야지 오류가 나지 않는다. 아니면 전부 한칸씩 뛰어주는 방법도 있다.			
			pstmt=conn.prepareStatement(query); //쿼리문 만들기
			pstmt.setString(1, "서울시 서초구 하이미디어 별관");
			pstmt.setInt(2, 21);
			//업데이트에 경우에는 쿼리문을 만들때 줄을 늘린것 빼고는 바뀌지 않음
			int count=pstmt.executeUpdate();
			//수정이 된 개숫를 알려줌
		    // 4.결과처리
			System.out.println(count+"건이 수정되었습니다.");
		} catch (ClassNotFoundException e) {
		    System.out.println("error: 드라이버 로딩 실패 - " + e);
		} catch (SQLException e) {
		    System.out.println("error:" + e);
		} finally {
		   
		    // 5. 자원정리
		    try {        
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
