package com.javaex.Book02;

import java.util.List;

import com.javaex.author01.AuthorDao;
import com.javaex.author01.AuthorVo;

public class BookAuthor {

	public static void main(String[] args) {
		List<AuthorVo> authorList;
		// 작가 6명 등록
		// AuthorDao, AuthorVo 이용
		AuthorDao authorDao = new AuthorDao();
		// 작가 6명 등록
		AuthorVo authorVo1 = new AuthorVo("이문열", "경상북도 영양");
		AuthorVo authorVo2 = new AuthorVo("박경리", "경상남도 통영");
		AuthorVo authorVo3 = new AuthorVo("유시민", "제17대 국회의원");
		AuthorVo authorVo4 = new AuthorVo("기안84", "기안동 거주 84년생");
		AuthorVo authorVo5 = new AuthorVo("강풀", "1세대 온라인만화가");
		AuthorVo authorVo6 = new AuthorVo("김영하", "알쓸신잡");

		authorDao.authorInsert(authorVo1);
		authorDao.authorInsert(authorVo2);
		authorDao.authorInsert(authorVo3);
		authorDao.authorInsert(authorVo4);
		authorDao.authorInsert(authorVo5);
		authorDao.authorInsert(authorVo6);
		
		AuthorVo authorVo7 = new AuthorVo(5,"코난도일","베이커리가");
		authorDao.authorUpdate(authorVo7);
		
		authorList = authorDao.getAuthorList();
		System.out.println("=========작가 리스트========");
		for (int i = 0; i < authorList.size(); i++) {// for문을 통해 받아온다
			AuthorVo vo = authorList.get(i);// AuthorVo에서 리스트를 밭아온다.
			System.out.println(vo.getAuthorId() + "," + vo.getAuthorName() + "," + vo.getAuthorDesc());
			// 1.이문열,경북
		}
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
		bookDao.bookDelete(3);

		// 전체 출력
		booklist = bookDao.getBookListAll();
		System.out.println("------리스트-----");
		for (int i = 0; i < booklist.size(); i++) {
			BookVo books = booklist.get(i);
			System.out.println(books.book_id + ", " + books.title + ", " + books.pubs + ", " + books.pub_date + ", "
			+ books.author_id + ", " + books.authorName + ", " + books.authorDesc);
		}

	}

}
