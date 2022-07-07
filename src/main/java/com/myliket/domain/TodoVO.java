package com.myliket.domain;

public class TodoVO {
	
	// 할일 상세정보
	private String todoId; // 할일 상세코드
	private String cateCode; // 카테고리 코드
	private String cateName; // 카테고리 이름
	private String todoTitle; // 할일 제목
	private String todoContent; // 할일 내용
	private String originalTododay; // 수정전 할일 예정일
	private String tododay; // 할일 예정일
	private String todotime; // 할일 시간
	private String todoState; // 할일 상태코드
	private String todoStateKor; // 할일 상태코드 한글명
	private String todoEnddate; // 할일 완료일
	private String userId; // 회원아이디
	
	public String getTodoId() {
		return todoId;
	}
	public void setTodoId(String todoId) {
		this.todoId = todoId;
	}
	public String getCateCode() {
		return cateCode;
	}
	public void setCateCode(String cateCode) {
		this.cateCode = cateCode;
	}
	public String getCateName() {
		return cateName;
	}
	public void setCateName(String cateName) {
		this.cateName = cateName;
	}
	public String getTodoTitle() {
		return todoTitle;
	}
	public void setTodoTitle(String todoTitle) {
		this.todoTitle = todoTitle;
	}
	public String getTodoContent() {
		return todoContent;
	}
	public void setTodoContent(String todoContent) {
		this.todoContent = todoContent;
	}
	public String getOriginalTododay() {
		return originalTododay;
	}
	public void setOriginalTododay(String originalTododay) {
		this.originalTododay = originalTododay;
	}
	public String getTododay() {
		return tododay;
	}
	public void setTododay(String tododay) {
		this.tododay = tododay;
	}
	public String getTodotime() {
		return todotime;
	}
	public void setTodotime(String todotime) {
		this.todotime = todotime;
	}
	public String getTodoState() {
		return todoState;
	}
	public void setTodoState(String todoState) {
		this.todoState = todoState;
	}
	public String getTodoStateKor() {
		return todoStateKor;
	}
	public void setTodoStateKor(String todoStateKor) {
		this.todoStateKor = todoStateKor;
	}
	public String getTodoEnddate() {
		return todoEnddate;
	}
	public void setTodoEnddate(String todoEnddate) {
		this.todoEnddate = todoEnddate;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	@Override
	public String toString() {
		return "TodoVO [todoId=" + todoId + ", cateCode=" + cateCode + ", cateName=" + cateName + ", todoTitle="
				+ todoTitle + ", todoContent=" + todoContent + ", originalTododay=" + originalTododay + ", tododay="
				+ tododay + ", todotime=" + todotime + ", todoState=" + todoState + ", todoStateKor=" + todoStateKor
				+ ", todoEnddate=" + todoEnddate + ", userId=" + userId + "]";
	}

}
