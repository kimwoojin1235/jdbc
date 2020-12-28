package com.javaex.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AuthorSelect {

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
			String query="";
		    query += " SELECT  author_id, ";
		    query += "         author_name, ";
		    query += " 		   author_desc ";
		    query += " FROM author ";
		    System.out.println(query);
		    pstmt=conn.prepareStatement(query);
		    
		    /*
		    SELECT  author_id,
	        		author_name,
	        		author_desc
		   	FROM author;
		    */
		    rs = pstmt.executeQuery();
		    //sql에 있는 author의 결과물을 가져와서 담는다.
		    // 4.결과처리
		    while (rs.next()) {//next라는 문을 사용하여서 꺼내온다.
		    	  int authorid=rs.getInt("author_id");//숫자가 오니까 int로 받는다.
				  String authorname = rs.getString("author_name");
				  String authordesc = rs.getString("author_desc");
				  //컬럼의 이름순으로 찾기 때문에 순서는 상관이 없다.
				  System.out.println(authorid+","+authorname+","+authordesc);
			}	    
		  
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
