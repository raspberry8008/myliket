package com.myliket.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.myliket.domain.TodoTotalVO;
import com.myliket.domain.TodocateVO;
import com.myliket.service.TodoService;
import com.myliket.service.TodocateService;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	

	private static final Logger Logger = LoggerFactory.getLogger(HomeController.class);
	
	@Inject
	private TodocateService todocateService;
	
	@Inject
	private TodoService todoService;
	
	/**
	 * main :  / 입력 시 로그인 여부에 따른 페이지 이동
	 * 로그인 정보 있을 시 : "redirect:/home"
	 * 로그인 정보 없을 시 : "login/login"
	 */
	@RequestMapping(value = "/", method = {RequestMethod.GET})
	public String mainView(Locale locale, Model model, HttpSession session, HttpServletRequest request, HttpServletResponse response) throws Exception {
		Logger.debug("Welcome myliket!");
		
		if (session.getAttribute("LoginVO")== null) {
			return "login/login";
		} else {
			return "redirect:/home";
		}
	}
	
	/**
	 * 회원 로그인 후 home(dashBoard) 화면 조회
	 * @param LoginVO (로그인 회원 정보)
	 * @return TodoTotalVO(할일 일별 집계), List<TodocateVO>
	 * @throws Exception 
	 */
	@RequestMapping(value = "home", method = RequestMethod.GET)
	public ModelAndView dashBoardView(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {
		Logger.debug("home/dashBoardView");
		
		Map<String, Object> inMap = new HashMap <String, Object> ();
		inMap.put("userId",session.getAttribute("userId").toString());

		ModelAndView mav = new ModelAndView();
		
		// todo 통계 조회
		TodoTotalVO resultVO = todoService.todoDailytotal(inMap);

		// 요청한 회원 아이디와 조회된 통계의 회원 아이디가 같은지 확인
		if (inMap.get("userId").equals(resultVO.getTotalId())) {
			
			// 카테고리 목록 조회
			List<TodocateVO> list= todocateService.todocateList(inMap);
			

			if (list.size() < 1) {
				mav.addObject("msg", "아직 등록된 카테고리가 없습니다.");
			} else {
				inMap.put("todocateId",session.getAttribute("userId").toString());

				// 카테고리코드 별 할일 갯수 
				for (int i= 0; i <list.size(); i++) {
					inMap.put("todocateCode", list.get(i).getTodocateCode());
					list.get(i).setTodoCount(todoService.todoListCount(inMap));
				}	
				mav.addObject("list", list);
			}

			mav.addObject("TodoTotalVO", resultVO);			
			mav.setViewName("home");

		}
		return mav;
	}
	
	
}







