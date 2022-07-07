package com.myliket.domain;

public class TodoTotalVO {
	
	private String totalId; // 회원 아이디
	private int todayReadyCnt; // 오늘 일정 건수
	private int pastReadyCnt; // 지난 일정 건수(미완료)
	private int beforeReadyCnt; // 예정 일정 총건수
	private int todaycomCnt; // 오늘 완료 건수
	private int totalcomCnt; // 총 완료 건수
	private String totaldate; // 등록일
	
	public String getTotalId() {
		return totalId;
	}
	public void setTotalId(String totalId) {
		this.totalId = totalId;
	}
	public int getTodayReadyCnt() {
		return todayReadyCnt;
	}
	public void setTodayReadyCnt(int todayReadyCnt) {
		this.todayReadyCnt = todayReadyCnt;
	}
	public int getPastReadyCnt() {
		return pastReadyCnt;
	}
	public void setPastReadyCnt(int pastReadyCnt) {
		this.pastReadyCnt = pastReadyCnt;
	}
	public int getBeforeReadyCnt() {
		return beforeReadyCnt;
	}
	public void setBeforeReadyCnt(int beforeReadyCnt) {
		this.beforeReadyCnt = beforeReadyCnt;
	}
	public int getTodaycomCnt() {
		return todaycomCnt;
	}
	public void setTodaycomCnt(int todaycomCnt) {
		this.todaycomCnt = todaycomCnt;
	}
	public int getTotalcomCnt() {
		return totalcomCnt;
	}
	public void setTotalcomCnt(int totalcomCnt) {
		this.totalcomCnt = totalcomCnt;
	}
	public String getTotaldate() {
		return totaldate;
	}
	public void setTotaldate(String totaldate) {
		this.totaldate = totaldate;
	}
	
	@Override
	public String toString() {
		return "TodoTotalVO [totalId=" + totalId + ", todayReadyCnt=" + todayReadyCnt + ", pastReadyCnt=" + pastReadyCnt
				+ ", beforeReadyCnt=" + beforeReadyCnt + ", todaycomCnt=" + todaycomCnt + ", totalcomCnt=" + totalcomCnt
				+ ", totaldate=" + totaldate + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + beforeReadyCnt;
		result = prime * result + pastReadyCnt;
		result = prime * result + todayReadyCnt;
		result = prime * result + todaycomCnt;
		result = prime * result + ((totalId == null) ? 0 : totalId.hashCode());
		result = prime * result + totalcomCnt;
		result = prime * result + ((totaldate == null) ? 0 : totaldate.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TodoTotalVO other = (TodoTotalVO) obj;
		if (beforeReadyCnt != other.beforeReadyCnt)
			return false;
		if (pastReadyCnt != other.pastReadyCnt)
			return false;
		if (todayReadyCnt != other.todayReadyCnt)
			return false;
		if (todaycomCnt != other.todaycomCnt)
			return false;
		if (totalId == null) {
			if (other.totalId != null)
				return false;
		} else if (!totalId.equals(other.totalId))
			return false;
		if (totalcomCnt != other.totalcomCnt)
			return false;
		if (totaldate == null) {
			if (other.totaldate != null)
				return false;
		} else if (!totaldate.equals(other.totaldate))
			return false;
		return true;
	}
	
	
	
	
}
