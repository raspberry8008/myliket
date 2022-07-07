package com.myliket.service;

import java.util.List;
import java.util.Map;

import com.myliket.domain.TodoTotalVO;
import com.myliket.domain.TodoVO;

public interface TodoService {
	
	/**
	 * todoInsert : 할일 등록 시 할일 아이디 생성 및 등록
	 * @param todocateId=cateCode(카테고리 코드),userId (회원 아이디)
	 * @return Map<String, Object> 
	 */
	public Map<String, Object> todoIdInsert (Map<String, Object> inMap) throws Exception ;
	
	/**
	 * todoInsertUpdate : 할일 등록 완료 (사용자등록정보로 수정)
	 * @param todoVO (등록할 할일 정보)
	 * @return Map<String, Object> 
	 */
	public Map<String, Object>  todoInsertUpdate (TodoVO todoVO) throws Exception ;
	
	/**
	 * todoListCount : 단일 카테고리의 오늘 및 예정 할일 전체 갯수
	 * @param todocateCode=cateCode(카테고리 코드), todocateId=userId (회원 아이디)
	 * @return int
	 */
	public int todoListCount ( Map<String, Object> inMap) throws Exception ;
	
	/**
	 * todoReadyList : 단일 카테고리의 예정 할일 전체 목록 조회
	 * @param todocateCode=cateCode(카테고리 코드), todocateId=userId (회원 아이디)
	 * @return List<TodoVO>
	 */
	public List<TodoVO> todoReadyList (Map<String, Object> inMap) throws Exception ;
	
	/**
	 * todayReadyList : 단일 카테고리의 오늘 할일 전체 목록 조회
	 * @param todocateCode=cateCode(카테고리 코드), todocateId=userId (회원 아이디)
	 * @return List<TodoVO>
	 */
	public List<TodoVO> todayReadyList ( Map<String, Object> inMap) throws Exception ;
	
	/**
	 * todoPastList : 카테고리 별 지난 할일 전체 목록 조회
	 * @param todocateCode=cateCode(카테고리 코드), todocateId=userId (회원 아이디)
	 * @return List<TodoVO>
	 */
	public List<TodoVO> todoPastList (Map<String, Object> inMap) throws Exception;
	
	
	/**
	 * beforeReadyList : 카테고리 별 오늘 이후 할일 전체 목록 조회
	 * @param todocateCode=cateCode(카테고리 코드), todocateId=userId (회원 아이디)
	 * @return List<TodoVO>
	 */
	public List<TodoVO> beforeReadyList (Map<String, Object> inMap) throws Exception ;
	
	
	/**
	 * getTodoDetail : 할일 단일조회 
	 * @param todoId (조회할 할일아이디), userId (회원아이디)
	 * @return TodoVO
	 */
	public TodoVO getTodoDetail (Map<String, Object> inMap) throws Exception;
	
	/**
	 * todoUpdating : 할일 수정 상태로 변경
	 * @param todoId (조회할 할일아이디), userId (회원아이디)
	 * @return 성공 : !0 , 실패 : 0
	 */
	public int todoUpdating(Map<String, Object> inMap) throws Exception;
	
	/**
	 * todoUpdate : 할일 수정 기능
	 * @param todoVO (수정할 todo 정보)
	 * @return Map<String, Object>
	 */
	public Map<String, Object> todoUpdate (TodoVO todoVO) throws Exception;
	
	/**
	 * todoComplete : 할일 완료로 상태 수정기능
	 * @param todoId (할일 아이디), userId(회원 아이디)
	 * @return 성공 : totoTotal 카운트 , 실패 : 0
	 */
	public int todoComplete (Map<String, Object> inMap) throws Exception;
	
	
	/**
	 * todoDelete : todo 삭제(todoState=TD)
	 * @param todoId (할일 아이디), userId(회원 아이디)
	 * @return Map<String, Object>
	 */
	public Map<String, Object> todoDelete (Map<String, Object> inMap) throws Exception ;
	
	/**
	 * todoDailytotal : 회원아이디 할일 통계 조회
	 * @param userId(회원 아이디)
	 * @return TodoTotalVO
	 */
	public TodoTotalVO todoDailytotal (Map<String, Object> inMap) throws Exception ;

}