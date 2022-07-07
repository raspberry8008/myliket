package com.myliket.dao.impl;

import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.myliket.dao.TodoTotalDAO;
import com.myliket.domain.TodoTotalVO;

@Repository
public class TodoTotalDAOImpl implements TodoTotalDAO {
	
	@Inject
	private SqlSession sqlSession;
	
	private static final String nameSpace = "com.myliket.TodoTotalMapper";
	
	@Override
	public int todoTotalInsert(TodoTotalVO todoTotalVO) throws Exception {
		return sqlSession.insert(nameSpace + ".todoTotalInsert", todoTotalVO);
	}

	@Override
	public TodoTotalVO todoDailytotal(Map<String, Object> inMap) throws Exception {
		return sqlSession.selectOne(nameSpace + ".todoDailytotal", inMap);
	}

	@Override
	public int todayReadyCheck(Map<String, Object> inMap) throws Exception {
		return sqlSession.selectOne(nameSpace + ".todayReadyCheck", inMap);
	}
	@Override
	public int pastReadyCheck(Map<String, Object> inMap) throws Exception {
		return sqlSession.selectOne(nameSpace + ".pastReadyCheck", inMap);
	}
	@Override
	public int beforeReadyCheck(Map<String, Object> inMap) throws Exception {
		return sqlSession.selectOne(nameSpace + ".beforeReadyCheck", inMap);
	}
	
	@Override
	public int todayComCheck(Map<String, Object> inMap) throws Exception {
		return sqlSession.selectOne(nameSpace + ".todayComCheck", inMap);
	}
	@Override
	public int totalComCheck(Map<String, Object> inMap) throws Exception {
		return sqlSession.selectOne(nameSpace + ".totalComCheck", inMap);
	}
	
	@Override
	public boolean totalCheck(Map<String, Object> inMap) throws Exception {
		return sqlSession.selectOne(nameSpace + ".totalCheck", inMap);
	}
	
	@Override
	public int todayReadyUpdate(Map<String, Object> inMap) throws Exception {
		return sqlSession.update(nameSpace + ".todayReadyUpdate", inMap);
	}
	
	@Override
	public int beforeReadyUpdate(Map<String, Object> inMap) throws Exception {
		return sqlSession.update(nameSpace + ".beforeReadyUpdate", inMap);
	}
	
	@Override
	public int todaycomUpdate(Map<String, Object> inMap) throws Exception {
		return sqlSession.update(nameSpace + ".todaycomUpdate", inMap);
	}
	
	@Override
	public int pastcomUpdate(Map<String, Object> inMap) throws Exception {
		return sqlSession.update(nameSpace + ".pastcomUpdate", inMap);
	}
	
	@Override
	public int beforecomUpdate(Map<String, Object> inMap) throws Exception {
		return sqlSession.update(nameSpace + ".beforecomUpdate", inMap);
	}
	
	@Override
	public int todaydeleteUpdate(Map<String, Object> inMap) throws Exception {
		return sqlSession.update(nameSpace + ".todaydeleteUpdate", inMap);
	}
	
	@Override
	public int pastdeleteUpdate(Map<String, Object> inMap) throws Exception {
		return sqlSession.update(nameSpace + ".pastdeleteUpdate", inMap);
	}
	
	@Override
	public int beforedeleteUpdate(Map<String, Object> inMap) throws Exception {
		return sqlSession.update(nameSpace + ".beforedeleteUpdate", inMap);
	}
	@Override
	public int todoDailytotalUpdate (TodoTotalVO todoTotalVO) throws Exception {
		return sqlSession.update(nameSpace + ".todoDailytotalUpdate", todoTotalVO);
	}




}
