package com.ezen.demo.mapper;

import java.util.ArrayList;
import java.util.List;

public class Board {
	private int num;
	private String title;
	private String contents;
	private String author;
	// private List<Attach> attList = new ArrayList<>();
	
	public Board(int num, String title, String contents, String author) {
		super();
		this.num = num;
		this.title = title;
		this.contents = contents;
		this.author = author;
		// this.attList = attList;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	/*
	public List<Attach> getAttList() {
		return attList;
	}

	public void setAttList(List<Attach> attList) {
		this.attList = attList;
	}
	*/
	
}
