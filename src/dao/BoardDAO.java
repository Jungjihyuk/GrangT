package dao;

import java.sql.Connection; //인터페이스 connection 객체
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


import model.BoardBean;

public class BoardDAO {
	Connection con;
	PreparedStatement pstmt;
	ResultSet rs;
	
	public static BoardDAO instance;
	
	public static BoardDAO getInstance() {
		if(instance==null)instance=new BoardDAO();
		return instance;
	}
	
	public BoardDAO() {
		try {
			String dbURL="jdbc:mysql://localhost:3306/grangT?useSSL=false";
			String dbID="root";
			String dbPassword="root12345";
			Class.forName("com.mysql.jdbc.Driver");
			con=DriverManager.getConnection(dbURL,dbID,dbPassword);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void insertBoard(BoardBean boardbean) {
		String sql="insert into board values(?,?,?,?,?,?,now())";
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setNull(1, boardbean.getBoardNum());
			pstmt.setString(2, boardbean.getUserId());
			pstmt.setString(3, boardbean.getCategory());
			pstmt.setString(4, boardbean.getContent());
			pstmt.setString(5, boardbean.getPhotoName());
			pstmt.setString(6, boardbean.getPhotoRealName());
			pstmt.executeUpdate();
			con.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	public int selectOneBoardNum() {
		int boardNum = 0;
		try {
			String sql="select max(boardNum) from board";
			pstmt=con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				boardNum=rs.getInt(1);
			}		
			con.close();				
		}catch(Exception e) {
			e.printStackTrace();
		}
		return boardNum;
		
	}
	
	public boolean selectAllBoard() {
		try {
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return true;
	}
	
	public void updateBoard() {
		
	}
	
	public void deleteBoard() {
		
	}
	
	/*
	public void getBoard(int boardNum) throws SQLException {
		getCon();
		try {
			
		}catch(Exception e) {
			String sql="select *from viw_board where boardNum=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1,boardNum);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				
			}
		}
	}
	*/
}
