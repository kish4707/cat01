package com.ezen.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ezen.demo.dao.EmpJdbcDAO;
import com.ezen.demo.vo.Emp;

@Service
public class EmpJdbcSvc 
{
	@Autowired
	private EmpJdbcDAO dao;
	
	public boolean add(Emp emp) 
	{
		boolean added = dao.add(emp);
		return added;
	}
	
	public List<Emp> list()
	{
		return dao.list();
	}

	public Emp getEmp(int empno) 
	{
		return dao.getEmp(new Emp(empno));
	}

	public boolean update(Emp emp) 
	{
		boolean updated = dao.update(emp);
		return updated;
	}

	public boolean delete(int empno) 
	{
		boolean deleted = dao.delete(empno);
		return deleted;
	}

}
