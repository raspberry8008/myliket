package com.myliket.dao;

import java.util.List;
import java.util.Map;

import com.myliket.domain.TodocateVO;

public interface TodocateDAO {
	
	/**
	 * todocateIdInsert : todocateCode 등록
	 * @param todocateVO (등록할 todo 카테고리 정보)
	 * @return 성공 : !0 , 실패 : 0
	 */
	public int todocateIdInsert (TodocateVO todocateVO) throws Exception ;
	
	/**
	 * todocateUpdateCheck : 카테고리코드 등록 메뉴에서 완료된 todocatecode인지 확인
	 * @param todocateCode (조회할 카테고리코드), userId(회원 아이디)
	 * @return boolean
	 */
	public boolean todocateUpdateCheck (Map<String, Object> inMap) throws Exception ;
	
	/**
	 * todocateInsertUpdate : todocate 등록요청 시 사용자입력정보로 수정(등록완료)
	 * @param todocateVO (등록할 todo 카테고리 정보)
	 * @return 성공 : !0 , 실패 : 0
	 */
	public int todocateInsertUpdate (TodocateVO todocateVO) throws Exception ;
	
	/**
	 * todocatelist : 사용중인 todocateList 목록
	 * @param todocateId (회원 아이디)
	 * @return List<TodolistVO>
	 */
	public List<TodocateVO> todocateList (Map<String, Object> inMap) throws Exception ;
	
	/**
	 * todocateCheck : todocate 있는지 확인
	 * @param todocateCode (조회할 카테고리코드), userId(회원 아이디)
	 * @return boolean
	 */
	public boolean todocateCheck (Map<String, Object> inMap) throws Exception ;
	
	/**
	 * getTodoCateDetail : todoCate 단일조회(수정화면)
	 * @param todocateCode (조회할 카테고리코드), userId(회원 아이디)
	 * @return todocateVO
	 */
	public TodocateVO getTodocateDetail (Map<String, Object> inMap) throws Exception ;
	
	/**
	 * todocateUpdating : todocate 수정중으로 상태 변경
	 * @param todocateCode (조회할 카테고리코드), userId(회원 아이디)
	 * @return 성공 : !0 , 실패 : 0
	 */
	public int todocateUpdating (Map<String, Object> inMap) throws Exception ;
	
	/**
	 * todocateUpdate : todocate 수정
	 * @param todocateVO (수정할 todo 카테고리 정보)
	 * @return 성공 : !0 , 실패 : 0
	 */
	public int todocateUpdate (TodocateVO todocateVO) throws Exception ;
	
	/**
	 * todocateDelete : todocate 삭제(카테고리 코드 상태 미사용으로(CN)으로 변경)
	 * @param todocateCode (삭제할 todo 카테고리 정보)
	 * @return 성공 : !0 , 실패 : 0
	 */
	public int todocateDelete (Map<String, Object> inMap) throws Exception ;


}
