package kr.co.imh.utils;

public class Criteria {
//	페이징
	//현재 페이지 번호
	private int num;
	//한 페이지에 출력할 회원 명수
	private int postNum = 5;
	//하단 페이징 시작번호
	private int displayPost;
//	검색
	//검색 키워드
	private String keyword;
	
	
	public Criteria() {}
	public Criteria(int num, int postNum, int displayPost, String keyword) {
		super();
		this.num = num;
		this.postNum = postNum;
		this.displayPost = displayPost;
		this.keyword = keyword;
	}
//	public Criteria(int num, int postNum, int displayPost) {
//		super();
//		this.num = num;
//		this.postNum = postNum;
//		this.displayPost = displayPost;
//	}

	//getter, setter
	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public int getPostNum() {
		return postNum;
	}

	public void setPostNum(int postNum) {
		this.postNum = postNum;
	}

	public int getDisplayPost() {
		displayPost = (num - 1) * postNum;
		if(displayPost <= 0) {
			displayPost = 0;
		}
		return displayPost;
	}

	public void setDisplayPost(int displayPost) {
		this.displayPost = displayPost;
	}
	
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	
	@Override
	public String toString() {
		return "Criteria [num=" + num + ", postNum=" + postNum + ", displayPost=" + displayPost + ", keyword=" + keyword
				+ "]";
	}
}
