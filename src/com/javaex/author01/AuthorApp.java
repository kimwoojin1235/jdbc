package com.javaex.author01;

import java.util.List;

public class AuthorApp {

	public static void main(String[] args) {
		AuthorDao authorDao = new AuthorDao();
		// 등록
		AuthorVo authorVo1 = new AuthorVo("이문열","경북 영양");
		AuthorVo authorVo2 = new AuthorVo("박경리", "경상남도 통영");
		AuthorVo authorVo3 = new AuthorVo("유시민", "17대 국회의원");
		//AuthorVo 에 생성자 2개 짜리가 없으면 오류가 난다.
		// int count= authorDao.authorInsert("이문열", "경북영양");//작가테이블에서 데이터 저장
		// System.out.println(count+"건이 등록되었습니다.");
		// authorDao.authorInsert("박경리","경상남도 통영");//작가테이블에서 데이터 저장
		authorDao.authorInsert(authorVo1); //작가테이블에 데이터 저장
		authorDao.authorInsert(authorVo2); //작가테이블에 데이터 저장
		authorDao.authorInsert(authorVo3); //작가테이블에 데이터 저장
		// 리스트
		List<AuthorVo> authorList = authorDao.getAuthorList();
		// 리스트 전체 출력
		System.out.println("=========작가 리스트========");
		for (int i = 0; i < authorList.size(); i++) {// for문을 통해 받아온다
			AuthorVo vo = authorList.get(i);// AuthorVo에서 리스트를 밭아온다.
			System.out.println(vo.getAuthorId() + "," + vo.getAuthorName() + "," + vo.getAuthorDesc());
			// 1.이문열,경북
		}
		// 작가 삭제
		
		authorDao.authorDelete(3);
		authorList = authorDao.getAuthorList();
		System.out.println("=========작가 리스트========");
		for (int i = 0; i < authorList.size(); i++) {// for문을 통해 받아온다
			AuthorVo vo = authorList.get(i);// AuthorVo에서 리스트를 밭아온다.
			System.out.println(vo.getAuthorId() + "," + vo.getAuthorName() + "," + vo.getAuthorDesc());
			// 1.이문열,경북
		}
		//작가수정
		AuthorVo authorVo4 = new AuthorVo(2,"김경리","제주도");
		//기존에는 업데이트 안에 수정정보를 넣었다면 지금은 따로 만들어서 관리를 한다.
		authorDao.authorUpdate(authorVo4);
		
		authorList = authorDao.getAuthorList();
		System.out.println("=========작가 리스트========");
		for (int i = 0; i < authorList.size(); i++) {// for문을 통해 받아온다
			AuthorVo vo = authorList.get(i);// AuthorVo에서 리스트를 밭아온다.
			System.out.println(vo.getAuthorId() + "," + vo.getAuthorName() + "," + vo.getAuthorDesc());
			// 1.이문열,경북
		}
	}

}
