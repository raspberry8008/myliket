package com.myliket.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.myliket.dao.TodocateDAO;
import com.myliket.domain.TodocateVO;
import com.myliket.service.TodocateService;
import com.myliket.utils.CommUtils;
import com.myliket.utils.TodoUtils;

@Service
@Transactional
public class TodocateServiceImpl implements TodocateService {
	
	@Inject
	private TodocateDAO todocateDAO;
	
	@Inject
	private TodoUtils todoUtils;

	@Override
	public Map<String, Object>  todocateIdInsert(Map<String, Object> inOutMap) throws Exception {
		
		// 유효성 체크
		if (inOutMap.get("userId") == "") { 
			inOutMap.clear();
			return inOutMap;
		}
		String userId = inOutMap.get("userId").toString();
		
		boolean insertComplete = false;

		while (!insertComplete) {
			
			TodocateVO todocateVO = new TodocateVO();

			// uuid 생성
			String isTodocateCode = null;
			isTodocateCode = todoUtils.uuidMaking();
			todocateVO.setTodocateCode(isTodocateCode);
			todocateVO.setTodocateId(userId);

			int result = 0;
			result = todocateDAO.todocateIdInsert(todocateVO);

			if (result!=0) {
				inOutMap.put("todocateId", userId);
				inOutMap.put("todocateCode", isTodocateCode);
				insertComplete = true;
			}
		}
		return inOutMap;		
	}
	
	@Override
	public Map<String, Object> todocateInsertUpdate(TodocateVO todocateVO) throws Exception {
		
		Map<String, Object> outMap = new HashMap <String, Object> ();
		
		if (CommUtils.isNull(todocateVO)) {
			outMap.put("fail", "TODO리킷 등록을 완료하지 못했습니다.(입력값이 없습니다.)");
			return outMap;
		}		

		outMap.put("todocateCode",todocateVO.getTodocateCode());
		outMap.put("todocateId",todocateVO.getTodocateId());
		
		// 등록완료 재시도건인지 확인
		if (todocateDAO.todocateUpdateCheck(outMap)) {
			outMap.clear();
			outMap.put("fail", "이미 등록된 TODO리킷 입니다.");
			return outMap;
		}
		
		
		String cateName = todocateVO.getTodocateName().trim().toString();
		
		if(cateName.length() < 1 || 15 < cateName.length()) {
			outMap.clear();
			outMap.put("fail", "TODO리킷 등록을 완료하지 못했습니다.(1~15자리로 입력해주세요.)");
			return outMap;
		}

		
		if (todocateDAO.todocateInsertUpdate(todocateVO) == 0) {
			outMap.clear();
			outMap.put("fail", "TODO리킷 등록을 완료하지 못했습니다.");
			return outMap;

		} else {
			outMap.clear();
			outMap.put( "success", "TODO리킷을 등록했습니다.");
			return outMap;
		}		 

	}

	@Override
	public List<TodocateVO> todocateList(Map<String, Object> inMap) throws Exception {
		return todocateDAO.todocateList(inMap);
	}

	@Override
	public TodocateVO getTodocateDetail(Map<String, Object> inMap) throws Exception {
		
		TodocateVO todocateVO = new TodocateVO ();
		
		if (inMap.isEmpty()) {
			return todocateVO;
		} 

		// 조건과 일치하는 todo 있는지 확인
		System.out.println("체크:"+ todocateDAO.todocateCheck(inMap));
		if(!(todocateDAO.todocateCheck(inMap))) { 
			return todocateVO;
		} else {			
			return todocateDAO.getTodocateDetail(inMap);
		}
	}

	@Override
	public int todocateUpdating(Map<String, Object> inMap) throws Exception {
		return todocateDAO.todocateUpdating(inMap);
	}
	
	@Override
	public Map<String, Object> todocateUpdate(TodocateVO todocateVO) throws Exception {
		
		Map<String, Object> outMap = new HashMap <String, Object> ();
			
			if (CommUtils.isNull(todocateVO)) {
				outMap.put("fail", "TODO리킷 수정을 완료하지 못했습니다.(입력값이 없습니다.)");
				return outMap;
			}		
	
			outMap.put("todocateCode",todocateVO.getTodocateCode());
			outMap.put("todocateId",todocateVO.getTodocateId());
			
			// 수정완료 재시도건인지 확인
			if (todocateDAO.todocateUpdateCheck(outMap)) {
				outMap.clear();
				outMap.put("fail", "이미 수정된 TODO리킷 입니다.");
				return outMap;
			}			
			
			String cateName = todocateVO.getTodocateName().trim().toString();
			
			if(cateName.length() < 1 || 15 < cateName.length()) {
				outMap.clear();
				outMap.put("fail", "TODO리킷 수정을 완료하지 못했습니다.(1~15자리로 입력해주세요.)");
				return outMap;
			}
	
			todocateVO.setTodocateName(cateName);
			
			if (todocateDAO.todocateUpdate(todocateVO) == 0) {
				outMap.clear();
				outMap.put("fail", "TODO리킷 수정을 완료하지 못했습니다.");
				return outMap;
	
			} else {
				outMap.clear();
				outMap.put( "success", "TODO리킷을 수했습니다.");
				return outMap;
			}		 
	}

	@Override
	public Map<String, Object> todocateDelete(Map<String, Object> inMap) throws Exception {		
		
		Map<String, Object> outMap = new HashMap <String, Object> ();
		
		// 유효성 체크
		if (inMap.get("todocateCode") == "" || inMap.get("todocateId") == "") { 
			outMap.put("fail", "TODO리킷 삭제를 완료하지 못했습니다.");
			return outMap;
		}
		
		// 삭제 재시도건인지 확인
		if (!(todocateDAO.todocateUpdateCheck(inMap))) {
			outMap.put("fail", "이미 삭제된 TODO리킷 입니다.");
			return outMap;
		}
		
		if (todocateDAO.todocateDelete(inMap)==0) {
			outMap.put("fail", "TODO리킷 삭제를 완료하지 못했습니다.");
			return outMap;
		} else {
			outMap.put("success", "TODO리킷을 삭제를 완료 했습니다.");
			return outMap;
		}

	}

}
