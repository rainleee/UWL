package com.uwl.service.domain;

import java.sql.Date;

public class Notification {
	
	//Field
	private int notiNo;
	private String userId;
	private Date notiDate;
	private String notiOrigin;
//			1:게시글
//			2:댓글
	private String notiCode;
//			1:댓글
//			2:좋아요

	//변수명과 table명을 일치시킴. 
	
	public Notification() {
		// TODO Auto-generated constructor stub
	}

	public int getNotiNo() {
		return notiNo;
	}

	public void setNotiNo(int notiNo) {
		this.notiNo = notiNo;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Date getNotiDate() {
		return notiDate;
	}

	public void setNotiDate(Date notiDate) {
		this.notiDate = notiDate;
	}

	public String getNotiOrigin() {
		return notiOrigin;
	}

	public void setNotiOrigin(String notiOrigin) {
		this.notiOrigin = notiOrigin;
	}

	public String getNotiCode() {
		return notiCode;
	}

	public void setNotiCode(String notiCode) {
		this.notiCode = notiCode;
	}
	
}	
