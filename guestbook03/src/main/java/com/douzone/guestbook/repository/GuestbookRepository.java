package com.douzone.guestbook.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.douzone.guestbook.vo.GuestbookVO;

@Repository
public class GuestbookRepository {
	
	//Conn 메소드
	private Connection getConnection() throws SQLException {
		Connection conn = null;
		try {
			// 1. JDBC Driver 로딩
			Class.forName("org.mariadb.jdbc.Driver");

			// 2. 연결하기
			String url = "jdbc:mysql://127.0.0.1:3306/webdb?charset=utf8";
			conn = DriverManager.getConnection(url, "webdb", "webdb");
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패:" + e);
		}

		return conn;
	}
	
	//select - list를 다 꺼내온다.
	public List<GuestbookVO> findAll() {
		List<GuestbookVO> list = new ArrayList<GuestbookVO>();
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = getConnection();
			
			//3. SQL 준비
			String sql = 
					"select no, name,date_format(reg_date, '%Y%m%d %H:%i:%s'), message"+ 
				    " from guestbook" +
				    " order by reg_date desc";
			pstmt = conn.prepareStatement(sql);
			
			//4. 바인딩(binding)
			
			//5. SQL 실행
			rs = pstmt.executeQuery();
			
			while(rs.next()) {                           // 여기는 password가 없으니깐 password는 적어주면안된다.
				Long no = rs.getLong(1);                 // rs가 안대혁 한줄이된다.  
				String name = rs.getString(2);           // 더 이상 줄이 없을때 까지 반복되고 결과로 그 list가 나온다.
				String regDate = rs.getString(3);
				String message = rs.getString(4);
				
				GuestbookVO vo = new GuestbookVO();
				 vo.setNo(no);
				 vo.setName(name);
				 vo.setMessage(message);
				 vo.setRegDate(regDate);
			
				list.add(vo);
			}

		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			// clean up
			try {
				if(rs != null) {
					rs.close();
				}
				if(pstmt != null) {
					pstmt.close();
				}
				if(conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return list;
	}
		
	
	//insert
	public boolean insert(GuestbookVO vo) {
		boolean result = false;
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = getConnection();
			
			//3. SQL 준비
			String sql = 
				" insert into guestbook values (null, ?, ?, ?, now())";
			pstmt = conn.prepareStatement(sql);
			
			//4. 바인딩(binding)
			pstmt.setString(1, vo.getName());
			pstmt.setString(2, vo.getPassword());
			pstmt.setString(3, vo.getMessage());
			
			//5. SQL 실행
			int count = pstmt.executeUpdate();
			
			result = count == 1;
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			// clean up
			try {
				if(pstmt != null) {
					pstmt.close();
				}
				if(conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return result;
	}
	
	// delete
	public void delete(GuestbookVO vo) {
		Connection conn = null;
		
		try {
			// 1. 연결
			conn = getConnection(); // 연결해주고
			
			//2. sql 준비
			String sql = "delete from guestbook where no =? and password = ?"; //구문찾고
			
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			//3. 바인딩
			pstmt.setLong(1, vo.getNo());					// 항상달라지는 ?값
			pstmt.setString(2, vo.getPassword());
			
			pstmt.executeUpdate();  // 위에꺼 실행된다
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
	}
	
}
