package com.myliket.domain;

public class UserVO {
	
	// 회원 정보
	private String userId; // 회원 아이디
	private String userPwd; // 회원 비밀번호
	private String userName; // 회원 닉네임
	private String userBirth; // 회원 생년월일
	private String userImage; // 회원 이미지파일명
	private String joindate; // 가입일자
	private String lastdate; // 최근 수정일자
	private String enddate; // 탈퇴일자
	private String userState; // 회원 계정 상태
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserPwd() {
		return userPwd;
	}
	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserBirth() {
		return userBirth;
	}
	public void setUserBirth(String userBirth) {
		this.userBirth = userBirth;
	}
	public String getUserImage() {
		return userImage;
	}
	public void setUserImage(String userImage) {
		this.userImage = userImage;
	}
	public String getJoindate() {
		return joindate;
	}
	public void setJoindate(String joindate) {
		this.joindate = joindate;
	}
	public String getLastdate() {
		return lastdate;
	}
	public void setLastdate(String lastdate) {
		this.lastdate = lastdate;
	}
	public String getEnddate() {
		return enddate;
	}
	public void setEnddate(String enddate) {
		this.enddate = enddate;
	}
	public String getUserState() {
		return userState;
	}
	public void setUserState(String userState) {
		this.userState = userState;
	}
	
	@Override
	public String toString() {
		return "UserVO [userId=" + userId + ", userPwd=" + userPwd + ", userName=" + userName + ", userBirth="
				+ userBirth + ", userImage=" + userImage + ", joindate=" + joindate + ", lastdate=" + lastdate
				+ ", enddate=" + enddate + ", userState=" + userState + "]";
	}
	

}
