package com.myliket.service;

import java.util.Map;

import com.myliket.domain.LoginVO;

public interface LoginService {
	
	/**
	 * loginCheck : user 로그인
	 * @param userID, userPwd (회원 아이디, 비밀번호) / 계정 사용여부 확인
	 * @return Map<String, Object> 실패 : fail 메세지
	 */
	public Map<String, Object> loginCheck(Map<String, Object> map)throws Exception ;
	
	/**
	 * getLogin : 로그인 정보 가져오기
	 * @param userID
	 * @return 성공 : LoginVO , 실패 : 0
	 */
	public LoginVO getLogin(String userId) throws Exception ;
	
	/**
	 * idSearch : user 아이디찾기
	 * @param userName(회원 닉네임), userBirth(회원 생년월일)
	 * @return 성공 : LoginVO , 실패 : 0
	 */
	public int idSearch(Map<String, Object> map) throws Exception ;
	
	/**
	 * pwdUpdate : 새로운 비빌번호로 변경
	 * @param userName(회원 닉네임), userBirth(회원 생년월일)
	 * @return 성공 : !0 , 실패 : 0
	 */
	public int pwdUpdate(String userId) throws Exception ;

}
