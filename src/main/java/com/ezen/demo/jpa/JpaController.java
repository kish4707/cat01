package com.ezen.demo.jpa;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import org.apache.jasper.tagplugins.jstl.core.Remove;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/jpa")
public class JpaController {
	
	@Autowired
	private EmpRepository repo;
	
	@GetMapping("/")
	@ResponseBody
	public String index() {
		
		return "JPA Controller Working...";
	}
	
	@GetMapping("/add")
	@ResponseBody
	public String add() {
		
		Date hiredate = Date.valueOf("2022-12-10");
		Emp emp = new Emp(11, "smith", 20, 3200, hiredate);
		Emp saved = repo.save(emp);
		log.info("추가 성공");
		
		return saved.toString();
	}
	
	@GetMapping("/list")
	@ResponseBody
	public String list() {
		
		List<Emp> list = repo.findAll();
		return list.toString();
	}

	@GetMapping("/emp/{empno}")
	@ResponseBody
	public String getEmp(@PathVariable int empno) {
		Optional<Emp> op = repo.findById(empno);
		if(op.isEmpty()) {
			return empno + "번호로 검색 실패";
		}
		return op.get().toString();
	}
	
	@GetMapping("/name/{name}")
	@ResponseBody
	public String getEmpByName(@PathVariable String name) 
	{
		return repo.findByName(name).toString();
	}
	
	 @GetMapping("/deptno/{deptno}")
	 @ResponseBody
	 public String getEmpByDeptno(@PathVariable int deptno)
	 {
	    return repo.findByDeptno(deptno).toString();
	 }
	
	 // 특정부서에 근무하는 이름을 검색(WHERE deptno=? AND name=?)
	 // repo.findByDeptnoAndName(20, "smith")
	 
	 @GetMapping("/dept_name/{deptno}/{name}")
	 @ResponseBody
	 public String getEmpByDeptnoAndName(@PathVariable("deptno") int deptno, @PathVariable("name") String name)
	 {
		 List<Emp> found = repo.findByDeptnoAndName(deptno, name);
	    return found.toString();
	 }
	 
	 // 수정 : Entity 객체를 생성하여 save() 하면 해당 PK인 곳을 찾아 수정해줌.
	 // save()는 수정 후 수정된 객체를 리턴하므로 수정 여부를 판단하는 데 사용가능.
	 @GetMapping("/update/{empno}/{deptno}/{sal}")
	 @ResponseBody
	 public String updateByEmpno(@PathVariable int empno,
			 					 @PathVariable int deptno,
			 					 @PathVariable int sal)
	 {
		 Emp e = repo.findById(empno).get();
		 e.setDeptno(deptno);
		 e.setSal(sal);
		 return repo.save(e).toString();
	 }
	 // repo.deleteById(empno)
	 
	@GetMapping("/delete")
	@ResponseBody
	public String delete() {

		// Emp delete = Remove("emp4", name);
		
	return null;
	}
	 
}
