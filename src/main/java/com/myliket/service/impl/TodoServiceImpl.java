package com.myliket.service.impl;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.myliket.dao.TodoDAO;
import com.myliket.dao.TodoTotalDAO;
import com.myliket.domain.TodoTotalVO;
import com.myliket.domain.TodoVO;
import com.myliket.service.TodoService;
import com.myliket.utils.CommUtils;
import com.myliket.utils.TodoUtils;

@Service
@Transactional
public class TodoServiceImpl implements TodoService {
	
	@Inject
	private TodoDAO todoDAO;
	
	@Inject
	private TodoTotalDAO todoTotalDAO;
	
	@Inject
	private TodoUtils todoUtils;
	
	@Override
	public Map<String, Object> todoIdInsert(Map<String, Object> inOutMap) throws Exception {
		
		// 유효성 체크
		if (inOutMap.get("userId") == "") { 
			inOutMap.clear();
			return inOutMap;
		}
		
		boolean insertComplete = false;

		while (!insertComplete) {
			
			TodoVO todoVO = new TodoVO();

			// todoId uuid 생성
			String isTodoId = null;
//			String todoId = "cf1b38964c204d97949e0b19e9f3131f";
			isTodoId = todoUtils.uuidMaking();
			todoVO.setTodoId(isTodoId);
			todoVO.setCateCode(isTodoId);

			int result = 0;
			result = todoDAO.todoIdInsert(todoVO);

			if (result!= 0) {
				inOutMap.put("todoId", isTodoId);
				insertComplete = true;
			}	

		}
		return inOutMap;		
	}


	@Override
	public Map<String, Object> todoInsertUpdate(TodoVO todoVO) throws Exception {
		
		Map<String, Object> outMap = new HashMap <String, Object> ();
		
		if (CommUtils.isNull(todoVO)) {
			outMap.put( "fail", "할일 추가를 완료하지 못했습니다.(입력된 값이 없습니다.)");
			return outMap;
		}		

		outMap.put("todoId",todoVO.getTodoId());
		outMap.put("userId",todoVO.getUserId());
		
		// 등록완료 재시도건인지 확인
		if (todoDAO.todoUpdateCheck(outMap)) {
			outMap.clear();
			outMap.put("fail", "이미 등록된 할일 입니다.");
			return outMap;
		}
		
		if (todoVO.getCateCode()=="") {
			outMap.clear();
			outMap.put("fail", "카테고리를 선택해주세요.");
			return outMap;
		}
		
		if (todoVO.getTododay()=="") {
			outMap.clear();
			outMap.put("fail", "할일 일정을 입력하지 않았습니다.");
			return outMap;
		}
		
		if (todoVO.getTododay().length() != 8) {
			outMap.clear();
			outMap.put("fail", "날짜는 숫자로 8자리 입력해야합니다.");
			return outMap;
		}
		
		if (todoUtils.isPastDay(todoVO.getTododay())) { // 할일 예정일 과거 확인
			outMap.clear();
			outMap.put("fail", "오늘 이전으로 할일을 등록할 수 없습니다.");
			return outMap;
		} else {
			
			if (todoUtils.isToDay(todoVO.getTododay()) & todoUtils.isPastTime(todoVO.getTodotime())) { // 할일 예정일이 오늘이고 할일 예정시간 있다면				
				outMap.clear();
				outMap.put("fail", "오늘 과거시간으로 할일을 등록할 수 없습니다.");
				return outMap;
			}			
			
			if (todoVO.getTodotime().isEmpty()) { // 할일 예정시간 없다면 null로 변경한다				
				todoVO.setTodotime(null);
			}
			
			String title = null;
			String content = null;
			
			title = todoVO.getTodoTitle().toString().trim();
			content = todoVO.getTodoContent().toString().trim();
			
			if( title.length() < 1 || 15 < title.length() ) {
				outMap.clear();
				outMap.put("fail", "할일 추가를 완료하지 못했습니다.(제목은 1~15자리까지 입력할 수 있습니다.)");
				return outMap;
			}
			if(100 < content.length()) {
				outMap.clear();
				outMap.put("fail", "할일 추가를 완료하지 못했습니다.(본문은 100자리까지 입력할 수 있습니다.)");
				return outMap;
			}
			
			todoVO.setTodoTitle(title);
			todoVO.setTodoContent(content);
			
			int result = 0;
			result=todoDAO.todoInsertUpdate(todoVO);
			
			// todoTotal 집계
			if (result!=0) {
				
				Map <String,Object> cntMap= new HashMap <String,Object>();		
				cntMap.put("totalId", todoVO.getUserId());
				
				if (todoUtils.isToDay(todoVO.getTododay())) { // 오늘 
					todoTotalDAO.todayReadyUpdate(cntMap);
				}

				if (todoUtils.isBeforeDay(todoVO.getTododay())) { // 오늘 이후
					todoTotalDAO.beforeReadyUpdate(cntMap);
				}
				
				outMap.put("success", "할일 추가를 완료 했습니다.");
				return outMap;

			} 
			outMap.clear();
			outMap.put( "fail", "할일 추가를 완료하지 못했습니다.");
			return outMap;				
		}

	}
	
	@Override
	public int todoListCount ( Map<String, Object> inMap) throws Exception {
		return todoDAO.todoListCount(inMap);
	}

	@Override
	public List<TodoVO> todoReadyList(Map<String, Object> inMap) throws Exception {
		
		List<TodoVO> resultList = new ArrayList <TodoVO> ();
		 
		if (inMap.get("todocateId") == "" || inMap.get("todoId") == "") {
			return resultList;
		} 
		return todoDAO.todoReadyList(inMap);
	}
	
	@Override
	public List<TodoVO> todayReadyList(Map<String, Object> inMap) throws Exception {
		
		List<TodoVO> resultList = new ArrayList <TodoVO> ();
		 
		if (inMap.get("todocateId") == "" || inMap.get("todoId") == "") {
			return resultList;
		} 
		return todoDAO.todayReadyList(inMap);
	}
	
	@Override
	public List<TodoVO> todoPastList(Map<String, Object> inMap) throws Exception {
		
		List<TodoVO> resultList = new ArrayList <TodoVO> ();
		 
		if (inMap.get("todocateId") == "" || inMap.get("todoId") == "") {
			return resultList;
		} 
		return todoDAO.todoPastList(inMap);
	}
	
	@Override
	public List<TodoVO> beforeReadyList(Map<String, Object> inMap) throws Exception {
		
		List<TodoVO> resultList = new ArrayList <TodoVO> ();
		 
		if (inMap.get("todocateId") == "" || inMap.get("todoId") == "") {
			return resultList;
		} 
		return todoDAO.beforeReadyList(inMap);
	}

	@Override
	public TodoVO getTodoDetail(Map<String, Object> inMap) throws Exception {
		
		TodoVO todoVO = new TodoVO();
		
		if (inMap.get("todocateId") == "" || inMap.get("todoId") == "") {
			return todoVO;
		} 

		// 조건과 일치하는 todo 있는지 확인
		if(!(todoDAO.todoCheck(inMap))) { 
			return todoVO;
		} else {			
			return todoDAO.getTodoDetail(inMap);
		}
		
	}
	
	@Override
	public int todoUpdating(Map<String, Object> inMap) throws Exception {
		
		if (inMap.get("todocateId") == "" || inMap.get("todoId") == "") {
			return 0;
		} 
		return todoDAO.todoUpdating(inMap);
	}

	@Override
	public Map<String, Object> todoUpdate(TodoVO todoVO) throws Exception {
		
		Map<String, Object> outMap = new HashMap <String, Object> ();
		
		if (CommUtils.isNull(todoVO)) {
			outMap.put( "fail", "할일 수정을 완료하지 못했습니다.(입력된 값이 없습니다.)");
			return outMap;
		}		

		outMap.put("todoId",todoVO.getTodoId());
		outMap.put("userId",todoVO.getUserId());
		
		// 수정완료 재시도건인지 확인
		if (todoDAO.todoUpdateCheck(outMap)) {
			outMap.clear();
			outMap.put("fail", "이미 수정된 할일 입니다.");
			return outMap;
		}
		
		if (todoVO.getCateCode()=="") {
			outMap.clear();
			outMap.put("fail", "카테고리를 선택해주세요.");
			return outMap;
		}
		
		if (todoVO.getTododay()=="") {
			outMap.clear();
			outMap.put("fail", "할일 일정을 입력하지 않았습니다.");
			return outMap;
		}
		
		if (todoVO.getTododay().length() != 8) {
			outMap.clear();
			outMap.put("fail", "날짜는 숫자로 8자리 입력해야합니다.");
			return outMap;
		}
		
		
		if (todoUtils.isPastDay(todoVO.getTododay())) { // 할일 예정일 과거 확인
			outMap.clear();
			outMap.put("fail", "오늘 이전으로 할일을 수정 할 수 없습니다.");
			return outMap;
		} else {
			
			if (todoUtils.isToDay(todoVO.getTododay()) & todoUtils.isPastTime(todoVO.getTodotime())) { // 할일 예정일이 오늘이고 할일 예정시간 있다면				
				outMap.clear();
				outMap.put("fail", "오늘 과거시간으로 할일을 수정 할 수 없습니다.");
				return outMap;
			}			
			
			if (todoVO.getTodotime().isEmpty()) { // 할일 예정시간 없다면 null로 변경한다				
				todoVO.setTodotime(null);
			}
			
			String title = null;
			String content = null;
			
			title = todoVO.getTodoTitle().toString().trim();
			content = todoVO.getTodoContent().toString().trim();
			
			if(title.length() < 1 || 15 < title.length()) {
				outMap.clear();
				outMap.put("fail", "할일 수정을 완료하지 못했습니다.(제목은 1~15자리까지 입력할 수 있습니다.)");
				return outMap;
			}
			if( content.length() < 1 || 100 < content.length()) {
				outMap.clear();
				outMap.put("fail", "할일 수정를 완료하지 못했습니다.(본문은 1~100자리까지 입력할 수 있습니다.)");
				return outMap;
			}
			
			todoVO.setTodoTitle(title);
			todoVO.setTodoContent(content);
			
			int result = 0;
			result=todoDAO.todoUpdate(todoVO);
				
			// todo 수정 후 todoTotal 수정
			if (result!=0) {
				
				Map <String,Object> cntMap= new HashMap <String,Object>();		
				cntMap.put("totalId", todoVO.getUserId());
				
				// 수정 전 : 오늘, 수정 후 : 예정
				if (todoUtils.isToDay(todoVO.getOriginalTododay()) && todoUtils.isBeforeDay(todoVO.getTododay())) { 
					todoTotalDAO.todaydeleteUpdate(cntMap); // 오늘 할일 카운트 - 1
					todoTotalDAO.beforeReadyUpdate(cntMap); // 예정 할일 카운트 + 1
				}
				
				// 수정 전 : 과거 , 수정 후 : 오늘
				if (todoUtils.isPastDay(todoVO.getOriginalTododay()) && todoUtils.isToDay(todoVO.getTododay())) { 
					todoTotalDAO.pastdeleteUpdate(cntMap); // 지난 할일 카운트 - 1
					todoTotalDAO.todayReadyUpdate(cntMap); // 오늘 할일 카운트 + 1
					
				}
				
				// 수정 전 : 과거 , 수정 후 : 예정
				if (todoUtils.isPastDay(todoVO.getOriginalTododay()) && todoUtils.isBeforeDay(todoVO.getTododay())) { 
					todoTotalDAO.pastdeleteUpdate(cntMap); // 지난 할일 카운트 - 1
					todoTotalDAO.beforeReadyUpdate(cntMap); // 예정 할일 카운트 + 1
					
				}
				
				// 수정 전 : 예정 , 수정 후 : 오늘
				if (todoUtils.isBeforeDay(todoVO.getOriginalTododay()) && todoUtils.isToDay(todoVO.getTododay())) { 
					todoTotalDAO.beforedeleteUpdate(cntMap); // 예정 할일 카운트 -1
					todoTotalDAO.todayReadyUpdate(cntMap); // 오늘 할일 카운트 + 1
				}
				
				outMap.put("success", "할일 수정을 완료 했습니다.");
				return outMap;
			} 	

				
		}
		outMap.clear();
		outMap.put( "fail", "할일 수정을 완료하지 못했습니다.");
		return outMap;				
	}

	

//	@Transactional(isolation = Isolation.READ_COMMITTED)
	@Override
	public int todoComplete(Map<String, Object> inMap) throws Exception {
		
		if (inMap.get("userId") == "" ) {
			return 0;
		} 
		
		int result=todoDAO.todoComplete(inMap);
		
		Map <String,Object> cntMap= new HashMap <String,Object>();
		cntMap.put("totalId", inMap.get("userId"));
		int today = Integer.parseInt(LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd")));
		int tododay=Integer.parseInt(inMap.get("tododay").toString());

		
		// 완료로 수정완료 후 집계
		if (result!=0) {
			if (tododay == today) {  // 오늘
				todoTotalDAO.todaycomUpdate(cntMap);
			} 
			if (tododay < today) { // 오늘 이전
				todoTotalDAO.pastcomUpdate(cntMap);
			} 
			if (tododay > today) { // 오늘 이후
				todoTotalDAO.beforecomUpdate(cntMap);
			} 
		}
		return result;
	}
	
	@Override
	public Map<String, Object> todoDelete(Map<String, Object> inMap) throws Exception {
		
		Map <String,Object> cntMap= new HashMap <String,Object>();
		
		if (inMap.get("userId") == "" ) {
			cntMap.put("fail", "할일 삭제를 완료하지 못했습니다.");
			return cntMap;
		} 
		
		cntMap.put("totalId", inMap.get("userId"));
		
		int today = Integer.parseInt(LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd")));
		int tododay=Integer.parseInt(inMap.get("tododay").toString());
		
		// 삭제 재시도건인지 확인
		if (todoDAO.todoDeleteCheck(inMap)) {
			cntMap.clear();
			cntMap.put("fail", "이미 삭제된 할일 입니다.");
			return cntMap;
		}
		
		int result=todoDAO.todoDelete(inMap);

		if (result!=0){
			if (tododay==today) {
				todoTotalDAO.todaydeleteUpdate(cntMap);
			} 
			if (tododay < today) {
				todoTotalDAO.pastdeleteUpdate(cntMap);
			} 
			if (tododay > today) {
				todoTotalDAO.beforedeleteUpdate(cntMap);
			} 
			cntMap.put("success", "할일 삭제를 완료 했습니다.");
			return cntMap;
			
		} else {
			cntMap.clear();
			cntMap.put("fail", "할일 삭제를 완료하지 못했습니다.");
			return cntMap;
		}

	}

	@Override
	public TodoTotalVO todoDailytotal(Map<String, Object> inMap) throws Exception {
		
		// 유효성체크(로그인 회원정보 있는지 확인)
		if (inMap.get("userId") == "") {
			return new TodoTotalVO();
		} 
		
		// 회원아이디의 오늘 통계 있는지 확인
		if (!(todoTotalDAO.totalCheck(inMap))) {
			
			TodoTotalVO todoTotalVO = new TodoTotalVO();
			
			// 회원 아이디
			todoTotalVO.setTotalId(inMap.get("userId").toString());
			
			// 오늘 할일 건수
			todoTotalVO.setTodayReadyCnt(todoTotalDAO.todayReadyCheck(inMap));
			// 지난 할일 건수
			todoTotalVO.setPastReadyCnt(todoTotalDAO.pastReadyCheck(inMap));
			// 오늘 이후 할일 건수
			todoTotalVO.setBeforeReadyCnt(todoTotalDAO.beforeReadyCheck(inMap));
			// 오늘 완료 건수
			todoTotalVO.setTodaycomCnt(todoTotalDAO.todayComCheck(inMap));
			// 총 완료 건수
			todoTotalVO.setTotalcomCnt(todoTotalDAO.totalComCheck(inMap));
			
			// 회원 아이디의 통계 insert 후 조회
			todoTotalDAO.todoTotalInsert(todoTotalVO);
			return todoTotalDAO.todoDailytotal(inMap);

		} else {
			
			TodoTotalVO todoTotalVO = new TodoTotalVO();
			
			// 회원 아이디
			todoTotalVO.setTotalId(inMap.get("userId").toString());
			
			// 오늘 할일 건수
			todoTotalVO.setTodayReadyCnt(todoTotalDAO.todayReadyCheck(inMap));
			// 지난 할일 건수
			todoTotalVO.setPastReadyCnt(todoTotalDAO.pastReadyCheck(inMap));
			// 오늘 이후 할일 건수
			todoTotalVO.setBeforeReadyCnt(todoTotalDAO.beforeReadyCheck(inMap));
			// 오늘 완료 건수
			todoTotalVO.setTodaycomCnt(todoTotalDAO.todayComCheck(inMap));
			// 총 완료 건수
			todoTotalVO.setTotalcomCnt(todoTotalDAO.totalComCheck(inMap));
			// 오늘날짜
			todoTotalVO.setTotaldate(LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
			
			// 회원 아이디의 통계 조회
			TodoTotalVO resultVO=todoTotalDAO.todoDailytotal(inMap);
			 
			// DB 통계와 실제 통계가 동일한지 확인 
			if (Objects.equals(resultVO, todoTotalVO)) {
				return resultVO;
			} else {
				
				// 다르다면 TodoTotalVO 수정
				boolean updateComplete = false;

				while (!updateComplete) {
					int result = 0;
					result = todoTotalDAO.todoDailytotalUpdate(todoTotalVO);					
					resultVO=todoTotalDAO.todoDailytotal(inMap);
					
					if (result != 0 && Objects.equals(resultVO, todoTotalVO)) {
						updateComplete = true;
					}

				}
				return todoTotalDAO.todoDailytotal(inMap);
			}
		}
	}

}
