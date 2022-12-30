package com.ezen.demo.jpa;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.PathVariable;

public interface EmpRepository extends JpaRepository<Emp, Integer>{

	// List<Emp> findByName(String name);
	List<Emp> findByName(String name);
	List<Emp> findByDeptno(int deptno);
	List<Emp> findByDeptnoAndName(int deptno, String name);
	// SELECT * FROM emp4 WHERE deptno=?
}
