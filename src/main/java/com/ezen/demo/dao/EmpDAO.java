package com.ezen.demo.dao;

import java.net.http.HttpRequest;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.ezen.demo.vo.Emp;

@Component
public class EmpDAO 
{
	private Connection conn;
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	private HttpRequest request;
	
	private Connection getConn()
	{
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			conn = DriverManager.getConnection(
	                "jdbc:oracle:thin:@localhost:1521:xe", "SCOTT", "TIGER");
			this.conn = conn;
			return conn;
        } catch (Exception e) {
            e.printStackTrace();
        }
		return null;
	}

	public List<Emp> getList() 
	{
		getConn();
		try {
			String sql = "SELECT * FROM emp2";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			List<Emp> list = new ArrayList<>();
			while(rs.next())
			{
				Emp emp = new Emp();
				emp.setEmpno(rs.getInt("empno"));
				emp.setDeptno(rs.getInt("deptno"));
				emp.setEname(rs.getString("ename"));
				emp.setSal(rs.getFloat("sal"));
				emp.setHiredate(rs.getDate("hiredate"));
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
	public Map<String,Object> getListII(int page)
	{
		getConn();
		try {
			String sql = "SELECT * FROM get_board CROSS JOIN( SELECT CEIL(COUNT(*)/3) allpage FROM get_board)"+
					"WHERE page=?";
						
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1,page);
			rs = pstmt.executeQuery();
			List<Emp> list = new ArrayList<>();
			Map<String,Object> map = new HashMap<>();
			while(rs.next())
			{
				Emp emp = new Emp();
				list.add(emp);
				map.put("page", rs.getString("ALLPAGE"));
			}
			
			map.put("list",list);
			return map;
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			closeAll();
		}
		return null;
	}
	
	public List<Integer> getPage()
	{
		getConn();
		
		try {
			String sql = "SELECT DISTINCT page FROM get_board ORDER BY page";
			
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			List<Integer> list = new ArrayList<>();
			while(rs.next())
			{
				list.add(rs.getInt("page"));
			}
			return list;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			closeAll();
		}
		return null;
	}
	
	public boolean add(Emp emp) 
	{
		getConn();
		String sql = "INSERT INTO emp2 (empno, ename, deptno, sal, hiredate) " +
					 "VALUES(?,?,?,?,?)";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, emp.getEmpno());
			pstmt.setString(2, emp.getEname());
			pstmt.setInt(3, emp.getDeptno());
			pstmt.setFloat(4, emp.getSal());
			pstmt.setDate(5, emp.getHiredate());
			
			int rows = pstmt.executeUpdate();
			return rows > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	

	public Emp getEmp(Emp emp) 
	{
		getConn();
		try {
			String sql = "SELECT * FROM emp2 WHERE empno=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, emp.getEmpno());
			rs = pstmt.executeQuery();
			
			Emp detail = new Emp();
			if(rs.next())
			{  
				detail.setEmpno(rs.getInt("empno"));
				detail.setDeptno(rs.getInt("deptno"));
				detail.setEname(rs.getString("ename"));
				detail.setSal(rs.getFloat("sal"));
				detail.setHiredate(rs.getDate("hiredate"));
			}

			return detail;
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
		String sql = "UPDATE emp2 SET sal=?, deptno=? WHERE empno=?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setFloat(1, emp.getSal());
			pstmt.setInt(2, emp.getDeptno());
			pstmt.setInt(3, emp.getEmpno());
			int rows = pstmt.executeUpdate();
			
			return rows > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}


	public boolean delete(Emp emp) 
	{
		getConn();
		String sql = "DELETE FROM emp2 WHERE empno=?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, emp.getEmpno());
			int rows = pstmt.executeUpdate();
			return rows > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	private void closeAll()
	{
		try {
			if(rs!=null) rs.close();
			if(stmt!=null) stmt.close();
			if(pstmt!=null) pstmt.close();
			if(conn!=null) conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
