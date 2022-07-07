package com.myliket.domain;

public class LoginVO {
	
	// 로그인 회원정보
	private String userId; // 회원 아이디
	private String userName; // 회원 닉네임
	private String userImage; // 회원 이미지파일명
	private String userState; // 회원 계정 상태
	
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserImage() {
		return userImage;
	}
	public void setUserImage(String userImage) {
		this.userImage = userImage;
	}
	public String getUserState() {
		return userState;
	}
	public void setUserState(String userState) {
		this.userState = userState;
	}
	
	@Override
	public String toString() {
		return "LoginVO [userId=" + userId + ", userName=" + userName + ", userImage=" + userImage + ", userState="
				+ userState + "]";
	}

}
