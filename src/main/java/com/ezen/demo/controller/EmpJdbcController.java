package com.ezen.demo.controller;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ezen.demo.vo.Emp;
import com.ezen.demo.service.EmpJdbcSvc;

import lombok.extern.slf4j.Slf4j;

//@RestController
@Controller
@RequestMapping("/jdbc/emp")
@Slf4j
public class EmpJdbcController 
{
	@Autowired
	private EmpJdbcSvc svc;
	
	@GetMapping("/add")
	public String addForm()
	{
		return "jdbc/empAdd";
	}
	
	@PostMapping("/add")
	@ResponseBody
	public Map<String, Boolean> addEmp(Emp emp)
	{
		boolean added = svc.add(emp);
		Map<String, Boolean> map = new HashMap<>();
		map.put("added", added);
		return map;
	}
	
	@GetMapping("/list")
	public String list(Model m)
	{
		m.addAttribute("list", svc.list());
		return "jdbc/empList";
	}
	
	@GetMapping("/detail/{empno}")
	public String detail(@PathVariable int empno, Model m)
	{
		m.addAttribute("emp", svc.getEmp(empno));
		return "jdbc/empDetail";
	}
	
	@GetMapping("/edit/{empno}")
	public String edit(@PathVariable int empno, Model m)
	{
		m.addAttribute("emp", svc.getEmp(empno));
		return "jdbc/empEdit";
	}
	
	@PostMapping("/update")
	@ResponseBody
	public Map<String, Boolean> update(Emp emp)
	{
		boolean updated = svc.update(emp);
		Map<String, Boolean> map = new HashMap<>();
		map.put("updated", updated);
		return map;
	}
	
	@PostMapping("/delete/{empno}")
	@ResponseBody
	public Map<String, Boolean> delete(@PathVariable int empno)
	{
		boolean deleted = svc.delete(empno);
		Map<String, Boolean> map = new HashMap<>();
		map.put("deleted", deleted);
		return map;
	}
}
