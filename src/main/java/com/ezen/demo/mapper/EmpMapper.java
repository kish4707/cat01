package com.ezen.demo.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ezen.demo.vo.Emp;

@Mapper
public interface EmpMapper
{
	public List<Emp> list();
	
	public List<Emp> listByDeptno(int deptno);
	
	public int deleteByEmpno(Emp emp);
	
	public int updateByDeptno(Map map);
}

