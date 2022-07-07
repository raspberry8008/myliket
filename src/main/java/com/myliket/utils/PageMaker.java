package kr.co.imh.utils;

public class PageMaker {
	//현재 페이지 번호
	//한 페이지에 출력할 회원 명수
	//출력할 회원리스트
	Criteria criteria;	//num(현재 페이지 번호), postNum(한 페이지에 출력할 회원 명수)
	//회원 총 수
	private int count;
	//하단 페이징 번호
	private int pageNum;
	//한번에 표시할 페이징 번호 개수
	private int pageNumCnt = 5;
	//마지막 페이지
	private int endPage;
	//시작 페이지
	private int startPage;
	//이전
	private boolean prev;
	//다음
	private boolean next;

	
	//getter, setter
	public Criteria getCriteria() {
		return criteria;
	}
	public void setCriteria(Criteria criteria) {
		this.criteria = criteria;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
		calcData();
	}
	public int getPageNum() {
		return pageNum;
	}
	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}
	public int getPageNumCnt() {
		return pageNumCnt;
	}
	public void setPageNumCnt(int pageNumCnt) {
		this.pageNumCnt = pageNumCnt;
	}
	public int getEndPage() {
		return endPage;
	}
	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}
	public int getStartPage() {
		return startPage;
	}
	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}
	public boolean getPrev() {
		return prev;
	}
	public void setPrev(boolean prev) {
		this.prev = prev;
	}
	public boolean getNext() {
		return next;
	}
	public void setNext(boolean next) {
		this.next = next;
	}
	//데이터 계산 메서드
	public void calcData() {
		//마지막 페이지
//		endPage = (int)(Math.ceil((double)num / (double)pageNumCnt) * pageNumCnt);
		endPage = (int)(Math.ceil((double)criteria.getNum() / (double)pageNumCnt) * pageNumCnt);
		//시작 페이지
		startPage = endPage - (pageNumCnt - 1);
		if(startPage <= 0) {
			startPage = 1;
		}
		//마지막 페이지 재계산
		int tempEndpage = (int)(Math.ceil((double)count / (double)pageNumCnt));
		if(endPage > tempEndpage) {
			endPage = tempEndpage;
		}
		//이전
		prev = startPage == 1 ? false : true;
		//다음
		next = endPage * pageNumCnt >= count ? false : true;
	}
	
	@Override
	public String toString() {
		return "PageMaker [criteria=" + criteria + ", count=" + count + ", pageNum=" + pageNum + ", pageNumCnt="
				+ pageNumCnt + ", endPage=" + endPage + ", startPage=" + startPage + ", prev=" + prev + ", next=" + next
				+ "]";
	}
}
