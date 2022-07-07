package com.myliket.utils;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

import org.springframework.stereotype.Service;

@Service
public class TodoUtils {

	
	private int today = Integer.parseInt(LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"))); // 오늘
	private int pastday; // 과거 날짜 (YYYYmmDD)		
	private int beforeday; // 예정 날짜 (YYYYmmDD)	
	
	private int nowtime = Integer.parseInt(LocalTime.now().format(DateTimeFormatter.ofPattern("HHmmss"))); // 현재시간

	private int originalTododay; // 수정 전 할일 예정일자
	private int beforeTododay; // 수정 후 할일 예정일자
	

	public int getToday() {
		return today;
	}


	public void setToday(int today) {
		this.today =today;
	}


	public int getPastday() {
		return pastday;
	}


	public void setPastday(int pastday) {
		this.pastday = pastday;
	}


	public int getBeforeday() {
		return beforeday;
	}


	public void setBeforeday(int beforeday) {
		this.beforeday = beforeday;
	}


	public int getNowtime() {
		return nowtime;
	}


	public void setNowtime(int nowtime) {
		this.nowtime = nowtime;
	}


	public int getOriginalTododay() {
		return originalTododay;
	}


	public void setOriginalTododay(int originalTododay) {
		this.originalTododay = originalTododay;
	}


	public int getBeforeTododay() {
		return beforeTododay;
	}


	public void setBeforeTododay(int beforeTododay) {
		this.beforeTododay = beforeTododay;
	}




	/**
	 * tododay 의 과거일인지 판단한다.
	 * 
	 * @param Object object
	 * @return
	 */
	public boolean isPastDay(Object object) {

		return (today > Integer.parseInt(object.toString())) ? true : false;
	}
	
	/**
	 * tododay 의 오늘일자 인지 판단한다.
	 * 
	 * @param Object object
	 * @return
	 */
	public boolean isToDay(Object object) {
		return (today == Integer.parseInt(object.toString())) ? true : false;
	}
	
	/**
	 * tododay 의 예정일자 인지 판단한다.
	 * 
	 * @param Object object
	 * @return
	 */
	public boolean isBeforeDay(Object object) {
		return (today < Integer.parseInt(object.toString())) ? true : false;
	}
	
	/**
	 * todotime 의 현재 시간보다 이전인지 판단한다.
	 * 
	 * @param int todotime
	 * @return
	 */
	public boolean isPastTime(Object object) {
		return ( !("".equals(object)) && Integer.parseInt(object.toString()) < nowtime) ? true : false;
	}
	
	/**
	 * random uuid를 생성한다. 
	 * 
	 * @return string
	 */
	public String uuidMaking() {
		return UUID.randomUUID().toString().replace("-","");
	}


}
