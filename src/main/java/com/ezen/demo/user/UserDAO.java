package com.ezen.demo.user;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.stereotype.Repository;

@Repository
public class UserDAO {
	
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	public boolean login(UserVO user) {
		getConn();
		String sql = "SELECT * FROM emp2 ename=? AND empno=?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user.getUserid().toUpperCase());
			pstmt.setInt(2, user.getUserPwd());
			rs = pstmt.executeQuery();
			return rs.next();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeAll();
		}
		return false;
	}
	
	private Connection getConn()
	{
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			conn = DriverManager.getConnection(
	                "jdbc:oracle:thin:@localhost:1521:xe", "SCOTT", "TIGER");
			return conn;
		}catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	private void closeAll()
	{
		try {
			if(rs!=null) 			rs.close();
			else if(pstmt!=null) 	pstmt.close();
			else if(conn!=null) 	conn.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
}
