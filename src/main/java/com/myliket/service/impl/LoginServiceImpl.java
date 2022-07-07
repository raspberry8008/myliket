package com.myliket.service.impl;

import java.util.Map;
import java.util.regex.Pattern;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.myliket.dao.LoginDAO;
import com.myliket.domain.LoginVO;
import com.myliket.service.LoginService;

@Service
@Transactional
public class LoginServiceImpl implements LoginService {
	
	@Inject
	private LoginDAO loginDAO;

	@Override
	public Map<String, Object> loginCheck(Map<String, Object> inOutMap) throws Exception {
		
		Pattern patternEmail = Pattern.compile("^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$");		

		if (!("".equals(inOutMap.get("userId")) && "".equals(inOutMap.get("userPwd")))) {
			String userId = inOutMap.get("userId").toString().trim();

			if (patternEmail.matcher(userId).matches()) {

				if(!(loginDAO.loginCheck(inOutMap))) {
					inOutMap.clear();
					inOutMap.put("fail", "아이디와 비밀번호를 다시 확인해주세요.(일치하는 회원없음)");
					return inOutMap;
				}
				inOutMap.clear();
				inOutMap.put("userId", userId);
				return inOutMap;
			}
			inOutMap.clear();
			inOutMap.put("fail", "아이디와 비밀번호를 다시 확인해주세요.(이메일형식)");
			return inOutMap;
		}
		inOutMap.clear();
		inOutMap.put("fail", "아이디와 비밀번호를 다시 확인해주세요.(아이디, 비밀번호 입력값 없음)");
		return inOutMap;
	}
	
	@Override
	public LoginVO getLogin(String userId) throws Exception  {
		return loginDAO.getLogin(userId);
	}

	@Override
	public int idSearch( Map<String, Object> map) throws Exception  {
		return loginDAO.idSearch(map);
	}

	@Override
	public int pwdUpdate(String userId) throws Exception  {
		return loginDAO.pwdUpdate(userId);
	}



}
