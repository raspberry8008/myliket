package com.myliket.domain;

public class TodocateVO {
	
	private String todocateCode; // 카테고리 코드
	private String todocateId; // 회원 아이디
	private String todocateName; // 카테고리 이름
	private String todocateState; // 카테고리 상태
	private String todocateStateKor; // 카테고리 상태 한글명
	private String todocateRegdate; // 최초 등록일자
	private String todocateLastdate; // 최근 수정일자
	private int todoCount; // 카테고리 내 할일 갯수 카운트
	public String getTodocateCode() {
		return todocateCode;
	}
	public void setTodocateCode(String todocateCode) {
		this.todocateCode = todocateCode;
	}
	public String getTodocateId() {
		return todocateId;
	}
	public void setTodocateId(String todocateId) {
		this.todocateId = todocateId;
	}
	public String getTodocateName() {
		return todocateName;
	}
	public void setTodocateName(String todocateName) {
		this.todocateName = todocateName;
	}
	public String getTodocateState() {
		return todocateState;
	}
	public void setTodocateState(String todocateState) {
		this.todocateState = todocateState;
	}
	public String getTodocateStateKor() {
		return todocateStateKor;
	}
	public void setTodocateStateKor(String todocateStateKor) {
		this.todocateStateKor = todocateStateKor;
	}
	public String getTodocateRegdate() {
		return todocateRegdate;
	}
	public void setTodocateRegdate(String todocateRegdate) {
		this.todocateRegdate = todocateRegdate;
	}
	public String getTodocateLastdate() {
		return todocateLastdate;
	}
	public void setTodocateLastdate(String todocateLastdate) {
		this.todocateLastdate = todocateLastdate;
	}
	public int getTodoCount() {
		return todoCount;
	}
	public void setTodoCount(int todoCount) {
		this.todoCount = todoCount;
	}
	@Override
	public String toString() {
		return "TodocateVO [todocateCode=" + todocateCode + ", todocateId=" + todocateId + ", todocateName="
				+ todocateName + ", todocateState=" + todocateState + ", todocateStateKor=" + todocateStateKor
				+ ", todocateRegdate=" + todocateRegdate + ", todocateLastdate=" + todocateLastdate + ", todoCount="
				+ todoCount + "]";
	}
	



}
