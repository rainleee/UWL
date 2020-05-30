package com.uwl.service.domain;

import java.sql.Date;

public class Challenge {
	
	//field
	//도전과제 시퀀스, 카테고리
	private int challNo;
	private String challCategory;
	
	//CRUD 등록시 - 썸머노트 사용
	private String challTitle;
	private String challContent;
	private Date challDate;
	//
	//도전과제 flag (1: 수행, 2: 미수행)
	private String challStatus;
	
	//Map을 이용한 도전과제 수행 시 위치
	private String firstUser;
	private int firstUserLocation;  //위도, 경도
	private String secondUser;
	private int secondUserLocation;  //위도, 경도
	
	//보상  , 플래그 (1: point 2: activityPoint) table에 컬럼으로 존재 X
	private int challReward;
	private String rewardCategory;
	
	//공개여부 flag
	private String viewStatus;
	
	
	
	//Constructor
	public Challenge() {
		// TODO Auto-generated constructor stub
	}



	//Getter, Setter
	public int getChallNo() {
		return challNo;
	}




	public void setChallNo(int challNo) {
		this.challNo = challNo;
	}




	public String getChallCategory() {
		return challCategory;
	}




	public void setChallCategory(String challCategory) {
		this.challCategory = challCategory;
	}




	public String getChallTitle() {
		return challTitle;
	}




	public void setChallTitle(String challTitle) {
		this.challTitle = challTitle;
	}




	public String getChallContent() {
		return challContent;
	}




	public void setChallContent(String challContent) {
		this.challContent = challContent;
	}




	public Date getChallDate() {
		return challDate;
	}




	public void setChallDate(Date challDate) {
		this.challDate = challDate;
	}




	public String getChallStatus() {
		return challStatus;
	}




	public void setChallStatus(String challStatus) {
		this.challStatus = challStatus;
	}




	public String getFirstUser() {
		return firstUser;
	}




	public void setFirstUser(String firstUser) {
		this.firstUser = firstUser;
	}




	public int getFirstUserLocation() {
		return firstUserLocation;
	}




	public void setFirstUserLocation(int firstUserLocation) {
		this.firstUserLocation = firstUserLocation;
	}




	public String getSecondUser() {
		return secondUser;
	}




	public void setSecondUser(String secondUser) {
		this.secondUser = secondUser;
	}




	public int getSecondUserLocation() {
		return secondUserLocation;
	}




	public void setSecondUserLocation(int secondUserLocation) {
		this.secondUserLocation = secondUserLocation;
	}




	public int getChallReward() {
		return challReward;
	}




	public void setChallReward(int challReward) {
		this.challReward = challReward;
	}




	public String getRewardCategory() {
		return rewardCategory;
	}




	public void setRewardCategory(String rewardCategory) {
		this.rewardCategory = rewardCategory;
	}



	public String getViewStatus() {
		return viewStatus;
	}



	public void setViewStatus(String viewStatus) {
		this.viewStatus = viewStatus;
	}



	@Override
	public String toString() {
		return "Challenge [challNo=" + challNo + ", challCategory=" + challCategory + ", challTitle=" + challTitle
				+ ", challContent=" + challContent + ", challDate=" + challDate + ", challStatus=" + challStatus
				+ ", firstUser=" + firstUser + ", firstUserLocation=" + firstUserLocation + ", secondUser=" + secondUser
				+ ", secondUserLocation=" + secondUserLocation + ", challReward=" + challReward + ", rewardCategory="
				+ rewardCategory + ", viewStatus=" + viewStatus + "]";
	}
	
	


}
