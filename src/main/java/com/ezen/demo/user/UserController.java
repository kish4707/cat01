package com.ezen.demo.user;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.support.SessionStatus;

import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService svc;
	
	// 회원 로그인
	@GetMapping("/login")
	public String loginForm() {
		return "user/login";
	}
		
	@PostMapping("/login")
	@ResponseBody
	public Map<String, Boolean> login(UserVO user, Model m){
		boolean ok = svc.login(user);
		if(ok) {
			m.addAttribute("userid", user.getUserid());
			log.info("로그인 성공");
		}
		Map<String, Boolean> map = new HashMap<>();
		map.put("login", ok);
		return map;
	}
	
	@PostMapping("/logout")
	@ResponseBody
	public Map<String, Boolean> logout(SessionStatus status){
		status.setComplete();
		boolean logout = status.isComplete();
		Map<String, Boolean> map = new HashMap<>();
		map.put("logout", logout);
		return map;
	}
	
	/*
	@PostMapping("/test")
	@ResponseBody
	public Map<String, Boolean> isLogged(
		@SessionAttribute(name="userid", required=false) String userid) {
		Map<String, Boolean> map = new HashMap<>();
		map.put("userid", userid);
		return map;
	}
	*/
	
}