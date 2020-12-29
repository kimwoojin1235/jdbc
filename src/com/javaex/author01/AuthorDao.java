package com.javaex.author01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AuthorDao {

	// 필드
	private String driver = "oracle.jdbc.driver.OracleDriver";
	private String url1 = "jdbc:oracle:thin:@localhost:1521:xe";
	private String id = "webdb";
	private String pw = "webdb";
	//db의 정보를 모아서 한번에 관리를 하면 정보가 수정이되면 수정을 하기 편리하다.
	// 생성자
	// 디폴트 생성자 생략 (다른 생성자 없음)

	// 메소드 g/s

	// 메소드 일반
	// 작가 수정하기
	public int authorUpdate(AuthorVo authorVo) {
		// 0. import java.sql.*;
		Connection conn = null;
		PreparedStatement pstmt = null;
		int count = 0;
		try {
			// 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName(driver);
			// 2. Connection 얻어오기
			String url = url1;
			conn = DriverManager.getConnection(url, id, pw);
			// 3. SQL문 준비 / 바인딩 / 실행
			/*
			 * UPDATE author set author_desc = '서울시 서초구 하이미디어 별관' WHERE author_id = 21;
			 */
			String query = "";
			query += " UPDATE author";
			query += " set author_name = ?,";
			query += "     author_desc = ?";
			query += " WHERE author_id = ?";
			// 쿼리문을 여러줄로 만들었다. 그리고 만들때는 오타가 나지 않게 신중이 작성한다.
			// 아래 쿼리문들은 한칸씩 뛰어줘야지 오류가 나지 않는다. 아니면 전부 한칸씩 뛰어주는 방법도 있다.
			pstmt = conn.prepareStatement(query); // 쿼리로 만들기
			pstmt.setString(1, authorVo.authorName);
			pstmt.setString(2, authorVo.authorDesc);
			pstmt.setInt(3, authorVo.authorId);
			// 업데이트에 경우에는 쿼리문을 만들때 줄을 늘린것 빼고는 바뀌지 않음
			count = pstmt.executeUpdate();
			// 수정이 된 개숫를 알려줌
			// 4.결과처리
			System.out.println("[Dao]" + count + "건이 수정되었습니다.");
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
		return count;
	}

	// 작가 삭제하기
	public int authorDelete(int authorid) {
		// 0. import java.sql.*;
		Connection conn = null;
		PreparedStatement pstmt = null;
		int count = 0;
		try {
			// 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName(driver);
			// 2. Connection 얻어오기
			String url = url1;
			conn = DriverManager.getConnection(url, id, pw);

			// 3. SQL문 준비 / 바인딩 / 실행
			/*
			 * DELETE FROM author WHERE author_id = 21;
			 */
			String query = "";
			query += " DELETE FROM author";
			query += " WHERE author_id = ?";
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, authorid);
			count = pstmt.executeUpdate();
			// 4.결과처리
			System.out.println("[Dao]" + count + "건이 삭제되었습니다.");
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
		return count;
	}

	// 작가 리스트 가져오기
	public List<AuthorVo> getAuthorList() {

		List<AuthorVo> authorList = new ArrayList<AuthorVo>();
		// 0. import java.sql.*;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			// 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName(driver);
			// 2. Connection 얻어오기
			String url = url1;
			conn = DriverManager.getConnection(url, id, pw);

			// 3. SQL문 준비 / 바인딩 / 실행
			String query = "";
			query += " SELECT  author_id, ";
			query += "         author_name, ";
			query += " 		   author_desc ";
			query += " FROM author ";
			System.out.println(query);
			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();
			// 4.결과처리
			// ts에 있는 데이터를 List<AuthorVo>로 구성해야 한다.
			while (rs.next()) {
				int authorid = rs.getInt("author_id");// 숫자가 오니까 int로 받는다.
				String authorname = rs.getString("author_name");
				String authordesc = rs.getString("author_desc");
				// 컬럼의 이름순으로 찾기 때문에 순서는 상관이 없다.
				AuthorVo vo = new AuthorVo(authorid, authorname, authordesc);
				authorList.add(vo);
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

		return authorList;
	}

	// 작가 저장 기능
	public int authorInsert(AuthorVo authorVo) {
		// 기존에는 코드를 괄호 안에 넣었지만 지금은 vo에 있는 데이터를 사용하여서 기능을 수정했다.
		// 0. import java.sql.*;
		Connection conn = null;
		PreparedStatement pstmt = null;
		int count = 0;// 실패하면 0을 리턴
		try {
			// 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName(driver);
			// 2. Connection 얻어오기
			String url = url1;
			conn = DriverManager.getConnection(url, id, pw);
			// 3. SQL문 준비 / 바인딩 / 실행
			String query = "";
			query += " INSERT into author ";
			query += " VALUES(seq_author_id.nextval,?,?)";
			System.out.println(query);
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, authorVo.authorName);
			pstmt.setString(2, authorVo.authorDesc);
			count = pstmt.executeUpdate();// 성공시 1을 내보낸다
			// 4.결과처리
			System.out.println("[dao]" + count + "건 저장");
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

		return count;
	}
}
