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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.myliket.domain.TodoVO;
import com.myliket.domain.TodocateVO;
import com.myliket.service.TodoService;
import com.myliket.service.TodocateService;
import com.myliket.utils.CommUtils;

@Controller
public class AjaxController {
	private static final Logger LOGGER = LoggerFactory.getLogger(AjaxController.class);
	
	@Inject
	private TodoService todoService;
	
	@Inject
	private TodocateService todocateService;	
	
	/**
	 * ajaxSelectTodoCate: todocate 의 code 조회 기능
	 * 
	 * @param todocateId (회원아이디)
	 * @return JSON ArrayList<TodocateVO>
	 * @throws Exception 
	 */
	@RequestMapping(value="todocate/list/todocate", method=RequestMethod.GET, produces="text/plain;charset=UTF-8")
	@ResponseBody 
	public String ajaxSelectTodoCate(HttpServletRequest request, HttpSession session ) throws Exception{
	    LOGGER.debug("todocate/list/ajaxSelectTodoCate");

	    Map<String, Object> inMap = new HashMap <String, Object> ();
		inMap.put("userId",session.getAttribute("userId").toString());

	    List<TodocateVO> resultList = new ArrayList<>();
	    resultList=todocateService.todocateList(inMap);
	    return new Gson().toJson(resultList);
	}

	/**
	 * ajaxTodoComplete : 할일 완료 기능
	 * 
	 * @param todoId (할일상세코드), userId(회원 아이디)
	 * @return Map<String, Object> outMap
	 * @throws Exception 
	 */
	@RequestMapping(value="todo/complete/todo", method=RequestMethod.GET, produces="text/plain;charset=UTF-8")
	@ResponseBody
	public String ajaxTodoComplete (HttpServletRequest request) throws Exception {
		LOGGER.debug("todo/complete/ajaxTodoComplete");
		
		Map<String, Object> inMap = CommUtils.getFormParam(request);
		
		int result = todoService.todoComplete(inMap);
		
		Map<String, Object> outMap = new HashMap<String, Object>(); 
		
		if (result!=0) {
			LOGGER.debug("todo/complete/success");
			outMap.put("complete", "success");
		} else {
			LOGGER.debug("todo/complete/fail");
			outMap.put("complete", "fail");
		}
	    return new Gson().toJson(outMap);
	}	

}






