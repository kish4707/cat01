package com.ezen.demo.mapper;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.ui.Model;

public class Board_upload {

	public String list(Model model)
	   {
	      // Map한개에 포함된 게시글 정보, 첨부파일 정보를 Board, Attach 에 저장한다
	      // List<Map<String, Object>> mlist = boardMapper.boardList();
	      List<Board> list = new ArrayList<>();
	      
	      /*
	      for(int i=0;i<mlist.size();i++) {
	         Map<String,Object> m = mlist.get(i);  // 한 행
	         
	         BigDecimal big = (java.math.BigDecimal)m.get("NUM");
	         Board board = new Board(big.intValue());
	         boolean found = false;
	         if(list.contains(board)) {
	            board = list.get(list.indexOf(board));
	            found = true;
	         }
	         board.setTitle((String)m.get("TITLE"));
	         board.setAuthor((String)m.get("AUTHOR"));
	         
	         Object objFname = m.get("FNAME");
	         if(objFname==null) continue;
	         
	         Attach att = new Attach();
	         att.setFname((String) objFname);
	         big = (BigDecimal)m.get("FNUM");
	         att.setNum(big.intValue());
	         big = (BigDecimal)m.get("FSIZE");
	         att.setFsize(big.intValue());

	         board.getAttList().add(att);  // Board에 Attach 연결
	         if(!found) list.add(board);
	      }
	      */
	      
	      //return list2.toString();
	      model.addAttribute("list", list);
	      return "board/boardList";
	   }	
	
}
