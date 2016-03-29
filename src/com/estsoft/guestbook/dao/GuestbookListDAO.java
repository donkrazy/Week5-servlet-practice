package com.estsoft.guestbook.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.estsoft.db.DBConnection;
import com.estsoft.guestbook.vo.GuestbookListVO;

public class GuestbookListDAO {
	private DBConnection dbConnection;

	public GuestbookListDAO( DBConnection dbConnection ) {
		this.dbConnection = dbConnection;
	}
	
	public void insert( GuestbookListVO vo ) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try{
			conn = dbConnection.getConnection();
			String sql = "INSERT INTO guestbook VALUES ( null, ?, now(), ?, password(?) )";
			pstmt = conn.prepareStatement( sql );
			pstmt.setString( 1,  vo.getName() );
			pstmt.setString( 2, vo.getMessage() );
			pstmt.setString( 3, vo.getPassword() );
			pstmt.executeUpdate();
		} catch( SQLException ex ) {
			System.out.println( "error:" + ex );
		} finally {
			try{
				if( pstmt != null ) {
					pstmt.close();
				}
				if( conn != null ) {
					conn.close();
				}
			}catch( SQLException ex ) {
				ex.printStackTrace();
			}
		}
	}
	
	public List<GuestbookListVO> getList() {
		List<GuestbookListVO> list = new ArrayList<GuestbookListVO>();
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			conn = dbConnection.getConnection();
			stmt = conn.createStatement();
			String sql = "SELECT * FROM guestbook";
			rs = stmt.executeQuery( sql );
			while( rs.next() ) {
				int id = rs.getInt( 1 );
				String name = rs.getString( 2 );
				String reg_date = rs.getString( 3 );
				String message = rs.getString( 4 );
				String password = rs.getString( 4 );
				GuestbookListVO vo = new GuestbookListVO();
				vo.setId(id);
				vo.setName(name);
				vo.setReg_date(reg_date);
				vo.setMessage(message);
				vo.setPassword(password);
				list.add( vo );
			}
		} catch( SQLException ex ) {
			System.out.println( "error: " + ex);
		} finally {
			try{
				if( rs != null ) {
					rs.close();
				}
				if( stmt != null ) {
					stmt.close();
				}
				if( conn != null ) {
					conn.close();
				}
			}catch( SQLException ex ) {
				ex.printStackTrace();
			}
		}
			
		return list;
	}
	
	public boolean delete(String id, String password){
		Connection conn = null;
		PreparedStatement pstmt = null;
		try{
			conn = dbConnection.getConnection();
			String sql = "DELETE from guestbook where no=? and passwd=password(?)";
			pstmt = conn.prepareStatement( sql );
			pstmt.setString( 1,  id );
			pstmt.setString( 2, password );
			int resultCnt = pstmt.executeUpdate();
			return resultCnt >= 1;
		} catch( SQLException ex ) {
			System.out.println( "error:" + ex );
			return false;
		} finally {
			try{
				if( pstmt != null ) {
					pstmt.close();
				}
				if( conn != null ) {
					conn.close();
				}
			}catch( SQLException ex ) {
				ex.printStackTrace();
			}
		}
		
	}

}
