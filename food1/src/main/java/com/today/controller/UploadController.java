package com.today.controller;

import java.io.File;
import java.io.IOException;

/*
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
*/
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/files")
public class UploadController 
{
   @Autowired
   ResourceLoader resourceLoader;

   @GetMapping("/upload")
   public String getForm() 
   {
      return "Files/upload_form";
   }
  
   
   @PostMapping("/upload")
   @ResponseBody
   public String upload(@RequestParam("files")MultipartFile[] mfiles,
                     HttpServletRequest request,
                     @RequestParam("author") String author) 
   {
      ServletContext context = request.getServletContext();
      String savePath = context.getRealPath("/WEB-INF/files");

 
   try {
         for(int i=0;i<mfiles.length;i++) {
            mfiles[i].transferTo(
              new File(savePath+"/"+mfiles[i].getOriginalFilename()));
    
         }

         String msg = String.format("파일(%d)개 저장성공(작성자:%s)", mfiles.length,author);
         return msg;
      } catch (Exception e) {
         e.printStackTrace();
         return "파일 저장 실패:";
      }
   }
  
   @GetMapping("/download/{filename}")	//		/files/download/파일명
   public ResponseEntity<Resource> download(
                              HttpServletRequest request,
                              @PathVariable String filename)
   {
      Resource resource = resourceLoader.getResource("WEB-INF/files/"+filename);
      System.out.println("파일명:"+resource.getFilename());
        String contentType = null;
        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        }
 
        if(contentType == null) {
            contentType = "application/octet-stream";
        }
 
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
   }
}