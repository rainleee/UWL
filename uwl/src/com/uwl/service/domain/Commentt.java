package com.uwl.service.domain;

import java.sql.Date;

public class Commentt {
	
	//Field
	private int commentNo;	//댓글 번호
	private String userId;	//회원 아이디
	private int postNo;	//게시글 번호
	private String commentContent;	//댓글 내용
	private Date commentDate;	//댓글 등록 날짜
	private String commentStatus;	//댓글 상태
		//	1 : 공개
		//	2 : 비공개
	private int likeCount;	//댓글 좋아요 개수
	//Constructor
	public Commentt() {
		// TODO Auto-generated constructor stub
	}
	
	//Method
	public int getCommentNo() {
		return commentNo;
	}

	public void setCommentNo(int commentNo) {
		this.commentNo = commentNo;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public int getPostNo() {
		return postNo;
	}

	public void setPostNo(int postNo) {
		this.postNo = postNo;
	}

	public String getCommentContent() {
		return commentContent;
	}

	public void setCommentContent(String commentContent) {
		this.commentContent = commentContent;
	}

	public Date getCommentDate() {
		return commentDate;
	}

	public void setCommentDate(Date commentDate) {
		this.commentDate = commentDate;
	}

	public String getCommenbtStatus() {
		return commentStatus;
	}

	public void setCommenbtStatus(String commenbtStatus) {
		this.commentStatus = commenbtStatus;
	}

	public int getLikeCount() {
		return likeCount;
	}

	public void setLikeCount(int likeCount) {
		this.likeCount = likeCount;
	}

	@Override
	public String toString() {
		return "Commentt [commentNo=" + commentNo + ", userId=" + userId + ", postNo=" + postNo + ", commentContent="
				+ commentContent + ", commentDate=" + commentDate + ", commentStatus=" + commentStatus + ", likeCount="
				+ likeCount + "]";
	}
	
	

}
