package com.myliket.dao;

import java.util.List;
import java.util.Map;

import com.myliket.domain.TodoVO;

public interface TodoDAO {
	
	/**
	 * todoIdInsert : 사용자의 할일 아이디 insert
	 * @param todoVO (등록할 할일 정보)
	 * @return 성공 : !0 , 실패 : 0
	 */
	public int todoIdInsert (TodoVO todoVO) throws Exception ;
	
	/**
	 * todoUpdateCheck : 할일 수정완료건인지 확인
	 * @param todoId(할일 아이디),userId (회원 아이디)
	 * @return boolean
	 */
	public boolean todoUpdateCheck (Map<String, Object> inMap) throws Exception ;
	
	/**
	 * todoDeleteCheck : 할일 삭제완료건인지 확인
	 * @param todoId(할일 아이디),userId (회원 아이디)
	 * @return boolean
	 */
	public boolean todoDeleteCheck (Map<String, Object> inMap) throws Exception ;
	
	/**
	 * todoInsertUpdate : 사용자가 입력한 정보로 할일 수정
	 * @param todoVO (사용자가 입력한 todoVO 정보)
	 * @return 성공 : !0 , 실패 : 0
	 */
	public int todoInsertUpdate (TodoVO todoVO) throws Exception ;
	
	/**
	 * todoListCount : 단일 카테고리의 오늘 및 예정 할일 전체 갯수
	 * @param todocateCode=cateCode(카테고리 코드), todocateId=userId (회원 아이디)
	 * @return int
	 */
	public int todoListCount(Map<String, Object> inMap) throws Exception ;
	
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
	 * @param ttodocateCode=cateCode(카테고리 코드), todocateId=userId (회원 아이디)
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
	 * getTodoCheck : todo 단일조회 요청건 있는지 확인
	 * @param todoId (조회할 할일상세코드), userId (회원아이디)
	 * @return boolean
	 */	
	public boolean todoCheck(Map<String, Object> inMap) throws Exception;
	
	/**
	 * getTodoDetail : todo 단일조회
	 * @param todoId (조회할 할일상세코드), userId (회원아이디)
	 * @return TodoVO
	 */
	public TodoVO getTodoDetail(Map<String, Object> inMap) throws Exception;
	
	/**
	 * todoUpdating : 할일 수정중으로 상태 변경
	 * @param todoId (조회할 할일상세코드), userId (회원아이디)
	 * @return 성공 : !0 , 실패 : 0
	 */
	public int todoUpdating (Map<String, Object> inMap) throws Exception;		

	/**
	 * todoUpdate : 할일 수정 기능
	 * @param todoVO (수정할 todo 정보)
	 * @return 성공 : !0 , 실패 : 0
	 */
	public int todoUpdate (TodoVO todoVO) throws Exception;
	
	/**
	 * todoComplete : 할일 완료로 상태 변경
	 * @param todoId (할일 아이디 ), userId(회원 아이디)
	 * @return 성공 : !0 , 실패 : 0
	 */
	public int todoComplete (Map<String, Object> inMap) throws Exception;
	
	
	/**
	 * todoDelete : todo 삭제(todoState=TD)
	 * @param todoId (할일 아이디), userId(회원 아이디)
	 * @return 성공 : !0 , 실패 : 0
	 */
	public int todoDelete (Map<String, Object> inMap) throws Exception ;


}
