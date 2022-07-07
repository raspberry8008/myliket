package com.myliket.dao;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.myliket.domain.UserVO;

@Repository
public class UserDAO {
	
	@Inject
	private SqlSession sqlSeeion;
	
	private static final String nameSpace = "com.myliket.UserMapper";

	/**
	 * idCheck : 아이디 중복확인
	 * @param String userId(요청 아이디)
	 * @return 사용가능 : 0 , 불가 : 1
	 */
	public int idCheck(String userId) {
		return sqlSeeion.selectOne(nameSpace + ".idCheck", userId);
	}

	/**
	 * nameCheck : 닉네임 중복확인
	 * @param String userName(요청 닉네임)
	 * @return 사용가능 : 0 , 불가 : 1
	 */
	public int nameCheck(String userName) {
		return sqlSeeion.selectOne(nameSpace + ".nameCheck", userName);
	}

	/**
	 * userInsert : user 등록
	 * @param userVO (등록할 회원정보)
	 * @return 성공 : !0 , 실패 : 0
	 */
	public int userInsert(UserVO userVO) {
		return sqlSeeion.insert(nameSpace + ".userInsert" , userVO);
	}

}
