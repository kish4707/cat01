package com.ezen.demo.vo;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Data
@ToString
@EqualsAndHashCode(exclude= {"ename","deptno","sal", "hiredate"})
@AllArgsConstructor
@NoArgsConstructor
public class Emp implements Serializable, Comparable<Emp>
{
	private int empno;
	private String ename;
	private int deptno;
	private float sal;
	private java.sql.Date hiredate;
	
	public Emp(int empno)
	{
		this.empno = empno;
	}

	@Override
	public boolean equals(Object obj) 
	{
		Emp other = (Emp) obj;
		return this.getEmpno()==other.getEmpno();
	}

	@Override
	public int compareTo(Emp other) {
		if(this.getEmpno()>other.getEmpno()) return 1;
		else if(this.getEmpno()<other.getEmpno()) return -1;
		else return 0;
	}

}
