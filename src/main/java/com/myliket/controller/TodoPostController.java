package com.myliket.controller;

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
public class TodoPostController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(TodoPostController.class);
	
	@Inject
	private TodoService todoService;
	
	@Inject
	private TodocateService todocateService;
	
	
	/**
	 * todocateInsertUpdate : "todocate/insert/register" 에서 사용자가 입력한 카테고리 정보 및 상태 사용(todocateState='CY')으로 수정
	 * @param todocateVO (사용자가 입력한 카테고리 정보)
	 * @return "todocate/insert/result"
	 * @throws Exception 
	 */
	@RequestMapping(value="todocate/insert", method= RequestMethod.POST)
	public ModelAndView todocateInsertUpdate (TodocateVO todoCateVO, HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {
		LOGGER.debug("todocate/insertUpdate");
				
		Map<String, Object> inMap = new HashMap <String, Object> ();
		
		if (session.getAttribute("userId") == null) {
			LOGGER.debug("todocate/insert/fail");
			request.getSession().invalidate();
			return new ModelAndView("login/login", "fail", "로그인이 필요한 서비스 입니다.");
		} else {
			
			inMap.put("userId",session.getAttribute("userId").toString());
			
			Map<String, Object> outMap = new HashMap <String, Object> ();
			outMap = todocateService.todocateInsertUpdate(todoCateVO);

			if (outMap.get("fail") != null) {				
				LOGGER.debug("todocate/insert/fail");
				return new ModelAndView("todocate/insert/result", "fail", outMap.get("fail").toString() );
				
			} else {
				LOGGER.debug("todocate/insert/success");				
				session.removeAttribute("fail");				
				return new ModelAndView("todocate/insert/result", "success", "TODO리킷을 등록했습니다.");
			}
		}
	}
	
	
	/**
	 * todocateUpdate : "todocate/update/update" 에서 사용자가 입력한 카테고리 정보 및 상태 사용(todocateState='CY')로 수정 
	 * @param todocateVO (수정할 todo 카테고리 정보)
	 * @return todocate/update/result
	 */
	@RequestMapping(value="todocate/update", method= RequestMethod.POST)
	public ModelAndView todocateUpdate (TodocateVO todocateVO, HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {
		LOGGER.debug("todocate/update");
		
		if (CommUtils.isNull(todocateVO)) {
			LOGGER.debug("todocate/update/fail");
			return new ModelAndView("todocate/update/result", "fail", "입력한 정보가 없습니다." );
		}	

		Map<String, Object> outMap = new HashMap <String, Object> ();
		outMap = todocateService.todocateUpdate(todocateVO);

		if (outMap.get("fail") != null) {
			
			LOGGER.debug("todocate/update/fail");
			return new ModelAndView("todocate/update/result", "fail", outMap.get("fail").toString() );
			
		} else {
			LOGGER.debug("todocate/update/success");			
			session.removeAttribute("fail");			
			return new ModelAndView("todocate/update/result", "success", "TODO리킷을 수정을 완료 했습니다.");
		}
	}
	
	/**
	 * todocateDelete : 카테고리를 삭제로 상태 수정 (todocateState='CN')
	 * @param String todocateCode (삭제할 카테고리코드), todocateId=userId(회원 아이디)
	 * @return todocate/delete/result
	 */
	@RequestMapping(value="todocate/delete", method= RequestMethod.POST)
	public ModelAndView todocateDelete (HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {
		LOGGER.debug("todocate/delete");

		Map<String, Object> inMap = CommUtils.getFormParam(request);
		
		List<TodoVO> list= todoService.todoReadyList(inMap);

		if (0 < list.size()) {
			
			LOGGER.debug("todocate/delete/fail");
			return new ModelAndView("todocate/delete/result", "fail", "카테고리에 남아있는 할일이 있습니다.");
			
		} else {
			
			Map<String, Object> outMap = new HashMap <String, Object> ();
			
			outMap = todocateService.todocateDelete(inMap);
			
			if (outMap.get("fail") != null) {
				LOGGER.debug("todocate/delete/fail");
				return new ModelAndView("todocate/delete/result","fail", outMap.get("fail").toString() );
			} else {
				LOGGER.debug("todocate/delete/success");
				session.removeAttribute("fail");
				return new ModelAndView("todocate/delete/result", "success", "TODO리킷을 삭제를 완료 했습니다.");
			}	
		}
	}	
	
	/**
	 * todoInsertUpdate : "todo/insert/register" 에서 사용자가 입력한 할일상세 정보 및 할일상세 상태 예정(todoState='TR')으로 수정
	 * @param todoVO (사용자가 입력한 할일 상세 정보)
	 * @return "todo/insert/result"
	 * @throws Exception 
	 */
	@RequestMapping(value="todo/insert", method= RequestMethod.POST)
	public ModelAndView todoInsertUpdate (TodoVO todoVO, HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {
		LOGGER.debug("todo/insert");
		
		if (session.getAttribute("userId") == null) {
			LOGGER.debug("todo/insert/fail");
			request.getSession().invalidate();
			return new ModelAndView("login/login", "fail", "로그인이 필요한 서비스 입니다.");
		}	
		
		if (!(session.getAttribute("userId").equals(todoVO.getUserId()))) {
			LOGGER.debug("todo/insert/fail");
			request.getSession().invalidate();
			return new ModelAndView("login/login", "fail", "로그인이 필요한 서비스 입니다.");
		}
		
		
		Map<String, Object> outMap = new HashMap <String, Object> ();
		outMap = todoService.todoInsertUpdate(todoVO);

		if (outMap.get("fail") != null) {
			
			LOGGER.debug("todo/insert/fail");
			return new ModelAndView("todo/insert/result", "fail", outMap.get("fail").toString() );
			
		} else {
			LOGGER.debug("todo/insert/success");			
			session.removeAttribute("fail");			
			return new ModelAndView("todo/insert/result", "success", "할일 추가를 완료 했습니다.");
		}
	}
	
	/**
	 * todoUpdate : "todo/update/update" 에서 사용자가 입력한 할일 정보 및 상태 예정(todocateState='TR')로 수정 
	 * @param todoVO (수정할 todo 정보)
	 * @return todo/update/result 
	 */
	@RequestMapping(value="todo/update", method= RequestMethod.POST)
	public ModelAndView todoUpdate (TodoVO todoVO, HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {
		LOGGER.debug("todo/update");
		
		if (session.getAttribute("userId") == null) {
			LOGGER.debug("todo/update/fail");
			request.getSession().invalidate();
			return new ModelAndView("login/login", "fail", "로그인이 필요한 서비스 입니다.");
		}	
		
		if (!(session.getAttribute("userId").equals(todoVO.getUserId()))) {
			LOGGER.debug("todo/update/fail");
			request.getSession().invalidate();
			return new ModelAndView("login/login", "fail", "로그인이 필요한 서비스 입니다.");
		}		
		
		Map<String, Object> outMap = new HashMap <String, Object> ();
		outMap = todoService.todoUpdate(todoVO);

		if (outMap.get("fail") != null) {
			
			LOGGER.debug("todo/update/fail");
			return new ModelAndView("todo/update/result", "fail", outMap.get("fail").toString() );
			
		} else {
			LOGGER.debug("todocate/update/success");			
			session.removeAttribute("fail");			
			return new ModelAndView("todo/update/result", "success", "할일 수정을 완료 했습니다.");
		}
	}
	
	/**
	 * todoDelete : 할일 삭제 기능
	 * @param todoId (삭제할 할일ID), todocateId=userId(회원 아이디)
	 * @return todo/delete/result
	 */
	@RequestMapping(value="todo/delete", method= RequestMethod.POST)
	public ModelAndView todoDelete (HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {
		LOGGER.debug("todo/delete");
		
		if (session.getAttribute("userId") == null) {
			LOGGER.debug("todo/delete/fail");
			request.getSession().invalidate();
			return new ModelAndView("login/login", "fail", "로그인이 필요한 서비스 입니다.");
		}	
		
		Map<String, Object> inMap = CommUtils.getFormParam(request);
		inMap.put("todocateId",session.getAttribute("userId").toString());
		
		Map<String, Object> outMap = new HashMap <String, Object> ();
		
		outMap = todoService.todoDelete(inMap);
		
		if (outMap.get("fail") != null) {
			LOGGER.debug("todo/delete/fail");
			return new ModelAndView("todo/delete/result", "fail", outMap.get("fail").toString());
		} else {
			LOGGER.debug("todo/delete/success");
			session.removeAttribute("fail");
			return new ModelAndView("todo/delete/result", "success", "할일 삭제를 완료 했습니다.");
		}
		
	}	

}






