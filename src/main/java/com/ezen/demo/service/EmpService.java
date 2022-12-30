package com.ezen.demo.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ezen.demo.dao.EmpDAO;
import com.ezen.demo.vo.Emp;

@Service
public class EmpService {
	
	@Autowired
	private EmpDAO dao;
	
   private String fpath = "C:/JavaEx/test/emp/emp.ser";
   
   public EmpService() {}
   
   public boolean add(Emp emp) {
      if(emp!=null || emp.getEmpno()>0) {
         List<Emp> list = deserialize();
         list.add(emp);
         return serialize(list);
      }
      return false;
   }
   
   public boolean addEmp(Emp emp) {
	   /*
      if(emp!=null || emp.getEmpno()>0)
      {
         List<Emp> list = deserialize();
         list.add(emp);
         return serialize(list);
      }
      */
	   return dao.add(emp);
   }
   
   public boolean updateEmp(Emp emp) {
	   /*
      List<Emp> list = deserialize();
      if(list.contains(emp))
      {
         list.get(list.indexOf(emp)).setSal(emp.getSal());
         list.get(list.indexOf(emp)).setDeptno(emp.getDeptno());
        
         return serialize(list);
      }
      */
	   return dao.update(emp);
   }
   
   public boolean deleteEmp(Emp emp) {
	   return dao.delete(emp);
   }
   
   public Emp getEmp(Emp emp) {
	   /*
      List<Emp> list = deserialize();
      if(list.contains(emp))
         {
         emp = list.get(list.indexOf(emp));
         return emp;
         }
         */
	   return dao.getEmp(emp);
   }
   
   public List<Emp> showlist()
   {
      // return deserialize();
	   return dao.getList();
   }

   /*
   public boolean add(Emp emp)
   {
	   if(emp!=null || emp.getEmpno()>0) {
		   List<Emp> list = deserialize();
		   list.add(emp);
		   return serialize(list);
	   }
	   return false;
   }
   */
   
   private boolean serialize(List<Emp> list) {
      File f = new File(fpath);
      try {
         ObjectOutputStream out= new ObjectOutputStream(new FileOutputStream(f));
         out.writeObject(list);
         out.close();
         return true;
      }
      catch (Exception ex) {
         ex.printStackTrace();
      }
      return false;
   }
   
   private List<Emp> deserialize() {
      File f = new File(fpath);
      List<Emp> list = null;
      if(!f.exists()) {
         list = new ArrayList<Emp>();
      }
      else {
         try {
            ObjectInputStream oin = new ObjectInputStream(new FileInputStream(f));
            list = (List<Emp>) oin.readObject();
            oin.close();
         }
         catch (Exception ex) {
            ex.printStackTrace();
         }
      }
      return list;
   }

}
