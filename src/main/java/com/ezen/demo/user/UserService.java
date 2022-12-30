package com.ezen.demo.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.ezen.demo.user.UserDAO;
import com.ezen.demo.user.UserVO;

import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserService 
{
   @Autowired
   private UserDAO dao;
   
   //@Autowired
   private HttpSession session;

   @Autowired
   public void setSession(HttpSession session)
   {
      this.session = session;
   }
   
   public boolean login(UserVO user)
   {
      boolean login = dao.login(user);
      return login;
   }
}