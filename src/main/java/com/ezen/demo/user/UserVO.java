package com.ezen.demo.user;

public class UserVO {
	private String userid;
	private int userPwd;

	public UserVO(String userid, int userPwd) {
		super();
		this.userid = userid;
		this.userPwd = userPwd;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public int getUserPwd() {
		return userPwd;
	}

	public void setUserPwd(int userPwd) {
		this.userPwd = userPwd;
	}
	
}
