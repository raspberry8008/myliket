package com.myliket.controller;

import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.servlet.ModelAndView;

import com.myliket.domain.LoginVO;
import com.myliket.service.LoginService;
import com.myliket.utils.CommUtils;

@Controller
public class LoginController {
	private static final Logger Logger = LoggerFactory.getLogger(LoginController.class);
	
	
	@Inject
	private LoginService loginService;
	
	/**
	 * loginView : 로그인 화면 조회
	 * @return "login/login"
	 */
	@RequestMapping(value="login", method = RequestMethod.GET)
	public String loginView() {
		Logger.debug("user/login/user");
		return "login/login";
	}
	
	/**
	 * login : 회원 로그인(회원 아이디, 비밀번호, 계정상태 확인)
	 * @param userId, userPwd
	 * loginCheck : userId(회원아이디), userPwd(회원 비밀번호), userState(계정상태:Y)
	 * @return 성공 LoginVO=getLogin(userId), "login/login" , 실패 : (msg : 아이디와 비밀번호를 다시 확인해주세요.) 
	 */
	@RequestMapping(value="login/user", method= RequestMethod.POST)
	public String login(HttpServletRequest request, HttpSession session) throws Exception{
		Logger.debug("login/user");
		
		if (session.getAttribute("LoginVO") != null) {
			session.removeAttribute("LoginVO");			
		}
		
		Map<String, Object> inOutMap = CommUtils.getFormParam(request);
		
		inOutMap = loginService.loginCheck(inOutMap);

		if (inOutMap.get("fail") != null) {
			Logger.debug("login/user/fail");
			session.setAttribute( "fail", inOutMap.get("fail").toString());

			return "redirect:/login";

		} else {
			Logger.debug("login/user/success");
			session.removeAttribute("fail");
			String userId = inOutMap.get("userId").toString();
			LoginVO resultVO  = loginService.getLogin(userId);

			session.setAttribute("LoginVO", resultVO);
			session.setAttribute("userId", resultVO.getUserId());

			return "redirect:/home";
			
		}
	}

	/**
	 * 로그아웃
	 * 
	 * @param LoginVO - 로그인 정보
	 * @param request
	 * @return 세션초기화
	 * @throws Exception
	 */

	@RequestMapping(value = "login/logout")
	public String logout(HttpServletRequest request, ModelMap model) throws Exception {
		RequestContextHolder.getRequestAttributes().removeAttribute("LoginVO", RequestAttributes.SCOPE_SESSION);
		request.getSession().invalidate();
		return "login/login";
	}

}






