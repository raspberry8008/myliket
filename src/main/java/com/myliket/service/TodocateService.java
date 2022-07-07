package com.myliket.service;

import java.util.List;
import java.util.Map;

import com.myliket.domain.TodocateVO;

public interface TodocateService {
	
	/**
	 * todocateIdInsert : 카테고리 등록화면 조회 시 카테고리코드 삽입
	 * @param userId (로그인한 회원 아이디)
	 * @return Map<String, Object>
	 */
	public Map<String, Object>  todocateIdInsert (Map<String, Object> inOutMap) throws Exception ;
	
	/**
	 * todocateInsertUpdate : todocate 등록요청 시 사용자입력정보로 수정(등록완료)
	 * @param todocateVO (등록할 todo 카테고리 정보)
	 * @return Map<String, Object>
	 */
	public Map<String, Object> todocateInsertUpdate (TodocateVO todocateVO) throws Exception ;
	
	/**
	 * todocatelist : 사용중인 todocateList 목록
	 * @param todocateId (회원 아이디)
	 * @return List<TodolistVO>
	 */
	public List<TodocateVO> todocateList (Map<String, Object> inMap) throws Exception ;
	
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
	 * @return Map<String, Object>
	 */
	public Map<String, Object> todocateUpdate (TodocateVO todocateVO) throws Exception ;
	
	
	/**
	 * todocateDelete : todocate 삭제(카테고리 코드 상태 미사용으로(CN)으로 변경)
	 * @param todocateCode (조회할 카테고리코드), userId(회원 아이디)
	 * @return Map<String, Object> 
	 */
	public Map<String, Object>  todocateDelete (Map<String, Object> inMap) throws Exception ;
}