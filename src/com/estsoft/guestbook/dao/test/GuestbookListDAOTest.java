package com.estsoft.guestbook.dao.test;

import java.util.List;

import com.estsoft.db.MySQLWebDBConnection;
import com.estsoft.guestbook.dao.GuestbookListDAO;
import com.estsoft.guestbook.vo.GuestbookListVO;

public class GuestbookListDAOTest {

	public static void main(String[] args) {
		insertTest();
		getListTest();
	}

	public static void insertTest(){
		GuestbookListVO vo = new GuestbookListVO();
		vo.setName( "둘리" );
		vo.setReg_date("1999-1-1");
		vo.setMessage( "몰라" );
		vo.setPassword("gg");
		GuestbookListDAO dao = new GuestbookListDAO( new MySQLWebDBConnection() );
		dao.insert(vo);
	}	
	
	public static void getListTest(){
		GuestbookListDAO dao = new GuestbookListDAO( new MySQLWebDBConnection() );
		List<GuestbookListVO> list = dao.getList();
		for( GuestbookListVO vo : list ) {
			System.out.println( vo );
		}
	}
}
