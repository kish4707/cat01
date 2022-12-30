package com.ezen.demo.mapper;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ezen.demo.vo.Emp;

@Controller
@RequestMapping("/mybatis/emp")
public class MybatisEmpController {

	@Autowired
	private EmpMapper dao;
	
	@GetMapping("/list")
	@ResponseBody
	public List<Emp> list() {
		return dao.list();
	}
	
	@GetMapping("/listByDeptno")
	@ResponseBody
	public String listByDeptno() {
		return dao.listByDeptno(20).toString();
	}
	
	@GetMapping("/deleteByEmpno")
	@ResponseBody
	public int deleteByEmpno(Emp emp) {
		return dao.deleteByEmpno(emp);
	}
	
	@GetMapping("/updateByDeptno/{deptno}/{sal}")
	@ResponseBody
	public int updateByDeptno(@PathVariable Map<String, Integer> map) {
		return dao.updateByDeptno(map);
	}
	
}
