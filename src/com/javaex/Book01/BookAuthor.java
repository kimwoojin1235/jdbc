package com.javaex.Book01;

import java.util.List;

import com.javaex.author01.AuthorDao;
import com.javaex.author01.AuthorVo;

public class BookAuthor {

	public static void main(String[] args) {

		// 작가 6명 등록
		// AuthorDao, AuthorVo 이용
		AuthorDao aDao = new AuthorDao();
		// 작가 6명 등록
		AuthorVo aisrtVo1 = new AuthorVo("이문열", "경상북도 영양");
		AuthorVo aisrtVo2 = new AuthorVo("박경리", "경상남도 통영");
		AuthorVo aisrtVo3 = new AuthorVo("유시민", "제17대 국회의원");
		AuthorVo aisrtVo4 = new AuthorVo("기안84", "기안동 거주 84년생");
		AuthorVo aisrtVo5 = new AuthorVo("강풀", "1세대 온라인만화가");
		AuthorVo aisrtVo6 = new AuthorVo("김영하", "알쓸신잡");

		aDao.authorInsert(aisrtVo1);
		aDao.authorInsert(aisrtVo2);
		aDao.authorInsert(aisrtVo3);
		aDao.authorInsert(aisrtVo4);
		aDao.authorInsert(aisrtVo5);
		aDao.authorInsert(aisrtVo6);

		// 책 8권 저장
		// BookDao, BookVo 이용
		BookDao bookDao = new BookDao();
		//책 리스트
		List<BookVo> booklist = bookDao.getBookList();

		// 책 8권 저장
		BookVo bisrtVo1 = new BookVo("우리들의 일그러진 영웅", "다림", "98/02/22", 1);
		BookVo bisrtVo2 = new BookVo("삼국지", "민음사", "02/03/01", 1);
		BookVo bisrtVo3 = new BookVo("토지", "마로니에북스", "12/08/15", 2);
		BookVo bisrtVo4 = new BookVo("유시민의 글쓰기 특강", "생각의 길", "15/04/01", 3);
		BookVo bisrtVo5 = new BookVo("패션왕", "중앙북스(books)", "12/02/22", 4);
		BookVo bisrtVo6 = new BookVo("순정만화", "재미주의", "11/08/03", 5);
		BookVo bisrtVo7 = new BookVo("오직 두 사람", "문학동네", "17/05/04", 6);
		BookVo bisrtVo8 = new BookVo("26년", "재미주의", "12/02/04", 5);

		bookDao.bookInsert(bisrtVo1);
		bookDao.bookInsert(bisrtVo2);
		bookDao.bookInsert(bisrtVo3);
		bookDao.bookInsert(bisrtVo4);
		bookDao.bookInsert(bisrtVo5);
		bookDao.bookInsert(bisrtVo6);
		bookDao.bookInsert(bisrtVo7);
		bookDao.bookInsert(bisrtVo8);

		// 책 정보 수정
		BookVo bupdVo = new BookVo(6, "홈즈");
		bookDao.bookUpdate(bupdVo);



		// 책 삭제
		bookDao.bookDelete(5);

		// 전체 출력(작가 ↔ 책 조인)
		booklist = bookDao.getBookListAll();
		System.out.println("------리스트-----");
		for (int i = 0; i < booklist.size(); i++) {
			BookVo books = booklist.get(i);
			System.out.println(books.book_id + ", " + books.title + ", " + books.pubs + ", " + books.pub_date + ", "
					+ books.author_id + ", " + books.authorName + ", " + books.authorDesc);
		}

	}

}
