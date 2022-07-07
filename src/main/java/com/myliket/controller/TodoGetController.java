package com.myliket.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.myliket.domain.TodoVO;
import com.myliket.domain.TodocateVO;
import com.myliket.service.TodoService;
import com.myliket.service.TodocateService;
import com.myliket.utils.CommUtils;

@Controller
public class TodoGetController {
	private static final Logger LOGGER = LoggerFactory.getLogger(TodoGetController.class);
	
	@Inject
	private TodoService todoService;
	
	@Inject
	private TodocateService todocateService;
	
	/**
	 * todocateInsertView : String todocateCode 등록 후 카테고리 등록화면 조회
	 * @param userId(회원아이디)
	 * @return insert 성공한 String todocateCode(등록할 카테고리 코드), "todoCate/insert/register" 
	 * @throws Exception 
	 */
	@RequestMapping(value="todocate/insert/todocate", method= RequestMethod.GET)
	public ModelAndView todocateInsertView (HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {
		LOGGER.debug("todocateInsertView");
		
		Map<String, Object> inMap = new HashMap <String, Object> ();

		if (session.getAttribute("userId") == null) {
			LOGGER.debug("todoInsertView/fail");
			request.getSession().invalidate();
			return new ModelAndView("login/login", "fail", "로그인이 필요한 서비스 입니다.");
		} else {

			inMap.put("userId",session.getAttribute("userId").toString());
			
			Map<String, Object> outMap =todocateService.todocateIdInsert(inMap);

			if (inMap.get("userId").toString()!=outMap.get("todocateId").toString()) {
				LOGGER.debug("todoInsertView/fail");
				return new ModelAndView("login/login", "fail", "로그인이 필요한 서비스 입니다.");
			} else {
				LOGGER.debug("todoInsertView/success");				
				session.removeAttribute("fail");				
				return new ModelAndView("todocate/insert/register", "todocateCode", outMap.get("todocateCode").toString());
			}
		}

	}


	/**
	 * todocateUpdateView : 수정 요청한 카테고리의(todocateCode)의 상태를 수정(todocateState='CU')으로 변경 후 수정화면 조회 
	 * @param todocateCode (조회할 카테고리코드), userId(회원 아이디)
	 * @return "todocate/update/update"
	 */
	@RequestMapping(value="todocate/update/todocate", method= RequestMethod.GET)
	public ModelAndView todocateUpdateView( HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {
		LOGGER.debug("todocateUpdateView");
		
		if  (session.getAttribute("userId") == null) {
			LOGGER.debug("todocateUpdateView/fail");
			request.getSession().invalidate();
			return new ModelAndView("login/login", "fail", "로그인이 필요한 서비스 입니다.");
		}		
		
		Map<String, Object> inMap = CommUtils.getFormParam(request);
		 
		inMap.put("todocateId",session.getAttribute("userId").toString());
		
		TodocateVO resultVO = todocateService.getTodocateDetail(inMap);

		// 요청한 카테고리코드와 조회된 카테고리 코드 같은지 확인
		if (!(inMap.get("todocateCode").equals(resultVO.getTodocateCode()))) { 
				LOGGER.debug("todocateUpdateView/fail");
				return new ModelAndView("todocate/update/update", "fail", "TODO리킷이 없습니다.");
			} else {
				if(todocateService.todocateUpdating(inMap)==0) {
					LOGGER.debug("todocateUpdateView/fail");
					return new ModelAndView("todocate/update/update", "fail", "수정화면 조회에 실패했습니다.");
					
				} else {
					LOGGER.debug("todocateUpdateView/success");
					session.removeAttribute("fail");
					return new ModelAndView("todocate/update/update", "success", resultVO);
				}
				
			}	

	}


	/**
	 * todoInsertView : String todoId 등록 후 할일 등록화면 조회
	 * @param userId(회원아이디)
	 * @return insert 성공한 String todoId(등록할 할일아이디), "todo/insert/register"
	 * @throws Exception 
	 */
	@RequestMapping(value="todo/insert/todo", method= RequestMethod.GET)
	public ModelAndView todoInsertView (HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {
		LOGGER.debug("todoInsertView");
		
		if (session.getAttribute("userId") == null)  {
			LOGGER.debug("todoInsertView/fail");
			request.getSession().invalidate();
			return new ModelAndView("login/login", "fail", "로그인이 필요한 서비스 입니다.");
		} else {
			
			Map<String, Object> inMap = new HashMap <String, Object> ();
			inMap.put("userId",session.getAttribute("userId").toString());
			
			Map<String, Object> outMap =todoService.todoIdInsert(inMap);			
			
			// user의 카테고리 조회
			List<TodocateVO> list= todocateService.todocateList(inMap);

			if (list.size() < 1) {
				
				LOGGER.debug("todoInsertView/fail");
				return new ModelAndView("todo/insert/register", "fail", "아직 등록된 카테고리가 없습니다.");
				
			} else {
				LOGGER.debug("todoInsertView/success");
				
				session.removeAttribute("fail");
				
				String todoId = outMap.get("todoId").toString();
				ModelAndView mav =new ModelAndView ();
				mav.addObject("success", list);
				mav.addObject("todoId", todoId);
				mav.setViewName("todo/insert/register");
				
				return mav;
			}
		}
	}

	
	/**
	 * todoReadyList : 단일 카테고리의 예정 할일 전체 목록 조회
	 * 조회 조건 : 카테고리코드 상태 = 사용/수정중 (todocateState=CY, CU), 할일 상태 = 예정/수정중 (todoState=TR, TU)
	 * @param todocateCode=cateCode(카테고리 코드), todocateId=userId (회원 아이디)
	 * @return List<TodoVO>, "todo/list/readyList"
	 */
	@RequestMapping(value="todo/list/ready", method= RequestMethod.GET)
	public ModelAndView todoReadyList ( HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {
		LOGGER.debug("todo/list/ready");
		
		if (session.getAttribute("userId") == null) {
			LOGGER.debug("todo/list/ready/fail");
			request.getSession().invalidate();
			return new ModelAndView("login/login", "fail", "로그인이 필요한 서비스 입니다.");
		}	
		
		Map<String, Object> inMap = CommUtils.getFormParam(request);

		inMap.put("todocateId",session.getAttribute("userId").toString());

		TodocateVO resultVO= todocateService.getTodocateDetail(inMap);
		
		// 카테고리 코드(todocateId=cateCode)의 할일목록 조회(List<todoVO>) 조회
		List<TodoVO> list = todoService.todoReadyList(inMap);

		if (list.size() < 1) {
			
			LOGGER.debug("todo/list/ready/fail");
			
			return new ModelAndView ("todo/list/readyList", "fail", "등록된 할일이 없습니다.");
			
		} else {
			
			LOGGER.debug("todo/list/ready/success");
			
			session.removeAttribute("fail");
			
			ModelAndView mav = new ModelAndView();
			
			mav.addObject("TodocateVO", resultVO);
			mav.addObject("success", list);
			mav.setViewName("todo/list/readyList");
			
			return mav;
		}
	}
	
	/**
	 * todayReadyList : 카테고리 별 오늘 할일 전체 목록 조회
	 * 조회 조건 : 카테고리코드 상태 = 사용/수정중 (todocateState=CY, CU), 할일 상태 = 예정/수정중 (todoState=TR, TU)
	 * @param todocateCode=cateCode(카테고리 코드), todocateId=userId (회원 아이디)
	 * @return List<TodoVO>, "todo/list/todayList"
	 */
	@RequestMapping(value="todo/list/today", method= RequestMethod.GET)
	public ModelAndView todayReadyList ( HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {
		LOGGER.debug("todo/list/today");
		
		if (session.getAttribute("userId") == null) {
			LOGGER.debug("todo/list/today/fail");
			request.getSession().invalidate();
			return new ModelAndView("login/login", "fail", "로그인이 필요한 서비스 입니다.");
		}	
		
		Map<String, Object> inMap = CommUtils.getFormParam(request);
		
		inMap.put("userId",session.getAttribute("userId").toString());
		inMap.put("todocateId",session.getAttribute("userId").toString());
		
		// 카테고리 조회
		List<TodocateVO> category = todocateService.todocateList(inMap);
		
		if (category.size() < 1) { // 사용중인 카테고리가 있는지 확인
			
			LOGGER.debug("todo/list/today/fail");
			return new ModelAndView("todo/list/todayList", "fail", "오늘 일정이 없습니다.");
			
		} else {
			
			List<TodoVO> list = new ArrayList <TodoVO>();
			
			// 카테고리코드 별 오늘 할일 조회
			for (int i= 0; i <category.size(); i++) {
				inMap.put("todocateCode", category.get(i).getTodocateCode());
				list.addAll(todoService.todayReadyList(inMap));
			}		

			if (list.size() < 1) { // 카테고리코드의 오늘 할일 목록 있는지 확인
				LOGGER.debug("todo/list/today/fail");
				return new ModelAndView("todo/list/todayList", "fail", "오늘 일정이 없습니다.");
			} else {
				LOGGER.debug("todo/list/today/success");
				session.removeAttribute("fail");
				return new ModelAndView("todo/list/todayList", "success", list);
			}
		}
	}
	
	/**
	 * todoPastList : 카테고리 별 지난 할일 전체 목록 조회
	 * 조회 조건 : 카테고리코드 상태 = 사용/수정중 (todocateState=CY, CU), 할일 예정일 : 과거일자 (tododay < today), 할일 상태 = 예정/수정중 (todoState=TR, TU)
	 * @param todocateCode=cateCode(카테고리 코드), todocateId=userId (회원 아이디)
	 * @return List<TodoVO>, "todo/list/pastList"
	 */
	@RequestMapping(value="todo/list/past", method= RequestMethod.GET)
	public ModelAndView todoPastList ( HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {
		LOGGER.debug("todo/list/past");
		
		if (session.getAttribute("userId") == null) {
			LOGGER.debug("todo/list/past/fail");
			request.getSession().invalidate();
			return new ModelAndView("login/login", "fail", "로그인이 필요한 서비스 입니다.");
		}	
		
		Map<String, Object> inMap = CommUtils.getFormParam(request);
		
		
		inMap.put("userId",session.getAttribute("userId").toString());
		inMap.put("todocateId",session.getAttribute("userId").toString());

		List<TodocateVO> category = todocateService.todocateList(inMap);
		
		if (category.size() < 1) { // 사용중인 카테고리가 있는지 확인
			
			LOGGER.debug("todo/list/past/fail");
			return new ModelAndView("todo/list/pastList", "fail", "지난 일정이 없습니다.");
			
		} else {
			
			List<TodoVO> list = new ArrayList <TodoVO>();
			
			// 카테고리코드 별 지난 할일 조회
			for (int i= 0; i <category.size(); i++) {
				inMap.put("todocateCode", category.get(i).getTodocateCode());
				list.addAll(todoService.todoPastList(inMap));
			}		

			if (list.size() < 1) { // 카테고리코드의 지난 할일 목록 있는지 확인
				LOGGER.debug("todo/list/past/fail");
				return new ModelAndView("todo/list/pastList", "fail", "지난 일정이 없습니다.");
			} else {
				LOGGER.debug("todo/list/past/success");
				session.removeAttribute("fail");
				return new ModelAndView("todo/list/pastList", "success", list);
			}
		}

	}
	
	/**
	 * beforeReadyList : 카테고리 별 오늘 이후 할일 전체 목록 조회
	 * 조회 조건 : 카테고리코드 상태 = 사용/수정중 (todocateState=CY, CU), 할일 예정일 : 미래일자 (tododay > today), 할일 상태 = 예정/수정중 (todoState=TR, TU)
	 * @param todocateCode=cateCode(카테고리 코드), todocateId=userId (회원 아이디)
	 * @return List<TodoVO>, todo/list/beforeList
	 */
	@RequestMapping(value="todo/list/before", method= RequestMethod.GET)
	public ModelAndView beforeReadyList( HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {
		LOGGER.debug("todo/list/before");
		
		if (session.getAttribute("userId") == null) {
			LOGGER.debug("todo/list/before/fail");
			request.getSession().invalidate();
			return new ModelAndView("login/login", "fail", "로그인이 필요한 서비스 입니다.");
		}	
		
		Map<String, Object> inMap = CommUtils.getFormParam(request);
		
		inMap.put("userId",session.getAttribute("userId").toString());
		inMap.put("todocateId",session.getAttribute("userId").toString());
		
		List<TodocateVO> category = todocateService.todocateList(inMap);
		
		if (category.size() < 1) { // 사용중인 카테고리가 있는지 확인
			
			LOGGER.debug("todo/list/before/fail");
			return new ModelAndView("todo/list/beforeList", "fail", "예정 일정이 없습니다.");
			
		} else {
			
			List<TodoVO> list = new ArrayList <TodoVO>();
			
			// 카테고리코드 별 지난 할일 조회
			for (int i= 0; i <category.size(); i++) {
				inMap.put("todocateCode", category.get(i).getTodocateCode());
				list.addAll(todoService.beforeReadyList(inMap));
			}		

			if (list.size() < 1) { // 카테고리코드의 오늘 이후 할일 목록 있는지 확인
				LOGGER.debug("todo/list/before/fail");
				return new ModelAndView("todo/list/beforeList", "fail", "예정 일정이 없습니다.");
			} else {
				LOGGER.debug("todo/list/before/success");
				session.removeAttribute("fail");
				return new ModelAndView("todo/list/beforeList", "success", list);
			}
		}
	}
	
	
	/**
	 * getTodoDetailView : todoVO 단일조회 화면
	 * @param todoId (조회할 할일아이디), todocateId=userId (회원 아이디)
	 * @return TodoVO
	 */
	@RequestMapping(value="todo/detail/todo", method= RequestMethod.GET)
	public ModelAndView getTodoDetailView (HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {
		LOGGER.debug("todo/getTodoDetailView");
		
		if (session.getAttribute("userId") == null) {
			LOGGER.debug("todo/getTodoDetailView/fail");
			request.getSession().invalidate();
			return new ModelAndView("login/login", "fail", "로그인이 필요한 서비스 입니다.");
		}	
		
		Map<String, Object> inMap = CommUtils.getFormParam(request);
		inMap.put("todocateId",session.getAttribute("userId").toString());
		TodoVO resultVO = todoService.getTodoDetail(inMap);
		
		if (!(inMap.get("todoId").equals(resultVO.getTodoId()))) { // 요청한 할일아이디와 조회된 할일 아이디가 다른지 확인
			LOGGER.debug("todo/getTodoDetailView/fail");
			return new ModelAndView("todo/detail/detail", "fail", "할일이 없습니다.");
		} else {
			LOGGER.debug("todo/getTodoDetailView/success");
			session.removeAttribute("fail");
			return new ModelAndView("todo/detail/detail", "success", resultVO);
		}
		
	}
	
	/**
	 * todoUpdatView : 수정 요청한 할일의(todoId)의 상태를 수정(todoState='TU')으로 변경 후 수정화면 조회 
	 * @param todoId (조회할 할일 아이디), todocateId=userId (회원아이디)
	 * @return todo/update/update
	 */
	@RequestMapping(value="todo/update/todo", method= RequestMethod.GET)
	public ModelAndView todoUpdateView(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {
		LOGGER.debug("todoUpdateView");
		
		if (session.getAttribute("userId") == null) {
			LOGGER.debug("todoUpdateView/fail");
			request.getSession().invalidate();
			return new ModelAndView("login/login", "fail", "로그인이 필요한 서비스 입니다.");
		}	
		
		Map<String, Object> inMap = CommUtils.getFormParam(request);
		
		inMap.put("todocateId",session.getAttribute("userId").toString());
		
		TodoVO resultVO = todoService.getTodoDetail(inMap);
		
		if (!(session.getAttribute("userId").equals(resultVO.getUserId()))) {
			LOGGER.debug("todoUpdateView/fail");
			return new ModelAndView("login/login", "fail", "로그인이 필요한 서비스 입니다.");
		}		
		
		if (!(inMap.get("todoId").equals(resultVO.getTodoId()))) { // 조회요청건과 같은지
			
			LOGGER.debug("todoUpdateView/fail");
			return new ModelAndView("todo/update/update", "fail", "요청한 할일과 일치하는 할일이 없습니다.");
			
		} else {
			
			if(todoService.todoUpdating(inMap)==0) {
				LOGGER.debug("todoUpdateView/fail");
				return new ModelAndView("todo/update/update", "fail", "수정화면 조회에 실패했습니다.");
			} else {
				LOGGER.debug("todoUpdateView/success");
				session.removeAttribute("fail");
				return new ModelAndView("todo/update/update", "success", resultVO);
			}
			
		}			
	}


}






