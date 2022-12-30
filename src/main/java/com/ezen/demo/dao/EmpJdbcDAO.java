package com.ezen.demo.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.ezen.demo.vo.Emp;

@Repository
public class EmpJdbcDAO 
{
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;

	public boolean add(Emp emp) 
	{
		getConn();
		String sql = "INSERT INTO emp2 (empno, ename, deptno, sal, hiredate) ";
				sql +="VALUES(?,?,?,?,?)";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, emp.getEmpno());
			pstmt.setString(2, emp.getEname());
			pstmt.setInt(3, emp.getDeptno());
			pstmt.setFloat(4, emp.getSal());
			pstmt.setDate(5, emp.getHiredate());
			int rows = pstmt.executeUpdate();
			return rows>0;
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			closeAll();
		}
		return false;
	}
	
	public List<Emp> list()
	{
		getConn();
		String sql = "SELECT * FROM emp2";
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			List<Emp> list = new ArrayList<>();
			while(rs.next()) {
				Emp emp = new Emp();
				emp.setEmpno(rs.getInt("EMPNO"));
				emp.setEname(rs.getString("ENAME"));
				emp.setDeptno(rs.getInt("DEPTNO"));
				emp.setSal(rs.getFloat("SAL"));
				emp.setHiredate(rs.getDate("HIREDATE"));
				list.add(emp);
			}
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			closeAll();
		}
		return null;
	}
	

	public Emp getEmp(Emp key) 
	{
		getConn();
		String sql = "SELECT * FROM emp2 WHERE empno=?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, key.getEmpno());
			rs = pstmt.executeQuery();
			List<Emp> list = new ArrayList<>();
			if(rs.next()) {
				Emp emp = new Emp();
				emp.setEmpno(rs.getInt("EMPNO"));
				emp.setEname(rs.getString("ENAME"));
				emp.setDeptno(rs.getInt("DEPTNO"));
				emp.setSal(rs.getFloat("SAL"));
				emp.setHiredate(rs.getDate("HIREDATE"));
				return emp;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			closeAll();
		}
		return null;
	}
	

	public boolean update(Emp emp) 
	{
		getConn();
		String sql = "UPdATE emp2 SET deptno=?, sal=? WHERE empno=?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, emp.getDeptno());
			pstmt.setFloat(2, emp.getSal());
			pstmt.setInt(3, emp.getEmpno());
			
			int rows = pstmt.executeUpdate();
			return rows > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			closeAll();
		}
		return false;
	}
	
	public boolean delete(int empno) 
	{
		getConn();
		String sql = "DELETE FROM emp2 WHERE empno=?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, empno);
			int rows = pstmt.executeUpdate();
			return rows > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
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
