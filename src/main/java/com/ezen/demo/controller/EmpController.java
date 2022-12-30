package com.ezen.demo.controller;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ezen.demo.vo.Emp;
import com.ezen.demo.service.EmpService;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/emp")
@Slf4j
public class EmpController {
   /*
      - List<Emp> -> Serialize ->파일에 저장
      - 출력(메모리 -> 파일) : ObjectOutputStream, FileOutputStream
      - 입력(파일 -> 메모리) : ObjectInputStream, FileInputStream
   */
   private Connection conn;
   private Statement stmt; 
   private ResultSet rs;
   
   @Autowired
   private EmpService svc;
   
   @GetMapping("/add") 
   public String showAddForm() {
      log.info("폼 요청됨");
      /*
       log.info("폼 요청됨");
       log.debug(null);
       log.warn();
       log.error(null);
      */
      return "/emp/empAdd"; //사번, 이름, 부서, 급여, 입사일
   }
   
   @PostMapping("/add")
   @ResponseBody
   public Map<String, Boolean> addEmp(Emp emp) {
      boolean added = svc.addEmp(emp);
      Map<String, Boolean> map = new HashMap<>();
      map.put("added", added);
      return map;
   }
   
   @GetMapping("/list") 
   public String empList(Model m) {
      m.addAttribute("list", svc.showlist());
      return "/emp/empList"; //사번, 이름, 부서, 급여, 입사일
   }

   @GetMapping("/detail/{empno}")
   public String empDetail(Emp emp, Model m) {
      Emp detail = svc.getEmp(emp);
      m.addAttribute("emp", detail); //request.setAttribute("emp",detail)
      return "/emp/empDetail";
   }
   
   @GetMapping("/edit/{empno}")
   public String empEdit(Emp emp, Model m) {
      Emp detail = svc.getEmp(emp);
      m.addAttribute("emp", detail);
      return "/emp/empEdit";
   }
   
   @PostMapping("/update")
   @ResponseBody
   public Map<String, Object> updateEmp(Emp emp) {
      boolean updated = svc.updateEmp(emp);
      Map<String, Object> map = new HashMap<>();
      map.put("updated", updated);
      map.put("empno", emp.getEmpno());
      return map;
   }
   
   @PostMapping("/delete")
   @ResponseBody
   public Map<String, Boolean> deleteEmp(Emp emp) {
      boolean deleted = svc.deleteEmp(emp);
      Map<String, Boolean> map = new HashMap<>();
      map.put("deleted", deleted);
      return map;
   }
}