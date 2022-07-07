package com.myliket.dao;

import java.util.Map;

import com.myliket.domain.TodoTotalVO;

public interface TodoTotalDAO {
	
	
	/**
	 * todoTotalInsert : 금일 통계 등록
	 * @param TodoTotalVO
	 * @return 성공 : !0 , 실패 : 0
	 */
	public int todoTotalInsert (TodoTotalVO todoTotalVO) throws Exception ;
	
	/**
	 * todoDailytotal : 금일 통계 조회
	 * @param userId (회원 아이디)
	 * @return TodotatalVO
	 */
	public TodoTotalVO todoDailytotal (Map<String, Object> inMap) throws Exception ;
	
	/**
	 * todayReadyCheck : 오늘 할일 건수
	 * @param totalId(회원 아이디)
	 * @return int todayReadyCnt
	 */
	public int todayReadyCheck (Map<String, Object> inMap) throws Exception;
	
	/**
	 * pastReadyCheck : 지난 할일 건수
	 * @param totalId(회원 아이디)
	 * @return int pastReadyCheck
	 */
	public int pastReadyCheck (Map<String, Object> inMap) throws Exception;
	
	/**
	 * beforeReadyCheck : 오늘 이후 할일 건수
	 * @param totalId(회원 아이디)
	 * @return int beforeReadyCnt
	 */
	public int beforeReadyCheck (Map<String, Object> inMap) throws Exception;
	
	/**
	 * todayComCheck : 오늘 완료 건수
	 * @param totalId(회원 아이디)
	 * @return int todayComCheck
	 */
	public int todayComCheck (Map<String, Object> inMap) throws Exception;
	
	/**
	 * totalComCheck : 총 완료 건수
	 * @param totalId(회원 아이디)
	 * @return int totalcomCnt
	 */
	public int totalComCheck (Map<String, Object> inMap) throws Exception;
	
	
	/**
	 * totalCheck : 금일 통계 있는지 확인
	 * @param userId (회원 아이디)
	 * @return boolean
	 */
	public boolean totalCheck (Map<String, Object> inMap) throws Exception ;
	

	
	/**
	 * todayReadyUpdate : 오늘 할일 등록 카운트
	 * @param userId(회원 아이디)
	 * @return 성공 : !0 , 실패 : 0
	 */
	public int todayReadyUpdate (Map<String, Object> inMap) throws Exception ;
	
	/**
	 * beforeReadyUpdate : 오늘 이후 할일 등록 카운트
	 * @param userId(회원 아이디)
	 * @return 성공 : !0 , 실패 : 0
	 */
	public int beforeReadyUpdate (Map<String, Object> inMap) throws Exception ;
	
	/**
	 * todaycomUpdate : 오늘 할일 완료 카운트
	 * @param userId(회원 아이디)
	 * @return 성공 : !0 , 실패 : 0
	 */
	public int todaycomUpdate (Map<String, Object> inMap) throws Exception ;
	
	/**
	 * pastcomUpdate : 지난 할일 완료 카운트
	 * @param userId(회원 아이디)
	 * @return 성공 : !0 , 실패 : 0
	 */
	public int pastcomUpdate (Map<String, Object> inMap) throws Exception ;
	
	/**
	 * beforecomUpdate : 예정 할일 완료 카운트
	 * @param userId(회원 아이디)
	 * @return 성공 : !0 , 실패 : 0
	 */
	public int beforecomUpdate (Map<String, Object> inMap) throws Exception ;
	
	/**
	 * todaydeleteUpdate : 오늘 할일 삭제 카운트
	 * @param userId(회원 아이디)
	 * @return 성공 : !0 , 실패 : 0
	 */
	public int todaydeleteUpdate (Map<String, Object> inMap) throws Exception ;
	
	/**
	 * pastdeleteUpdate : 지난 할일 삭제 카운트
	 * @param userId(회원 아이디)
	 * @return 성공 : !0 , 실패 : 0
	 */
	public int pastdeleteUpdate (Map<String, Object> inMap) throws Exception ;
	
	/**
	 * beforedeleteUpdate : 예정 할일 삭제 카운트
	 * @param userId(회원 아이디)
	 * @return 성공 : !0 , 실패 : 0
	 */
	public int beforedeleteUpdate (Map<String, Object> inMap) throws Exception ;
	/**
	 * todoDailytotalUpdate : 통계 업데이트
	 * @param TodoTotalVO
	 * @return 성공 : !0 , 실패 : 0
	 */
	public int todoDailytotalUpdate (TodoTotalVO todoTotalVO) throws Exception ;
	

	

}
