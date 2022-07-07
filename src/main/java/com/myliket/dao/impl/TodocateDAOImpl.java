package com.myliket.dao.impl;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.myliket.dao.TodocateDAO;
import com.myliket.domain.TodocateVO;

@Repository
public class TodocateDAOImpl implements TodocateDAO{
	
	@Inject
	private SqlSession sqlSession;
	
	private static final String nameSpace = "com.myliket.TodocateMapper";
	

	@Override
	public int todocateIdInsert (TodocateVO todocateVO) throws Exception {
		return sqlSession.insert(nameSpace + ".todocateIdInsert", todocateVO);
	}
	
	@Override
	public boolean todocateUpdateCheck (Map<String, Object> inMap) throws Exception {
		return sqlSession.selectOne(nameSpace + ".todocateUpdateCheck", inMap);
	}
	
	@Override
	public int todocateInsertUpdate (TodocateVO todocateVO) throws Exception {
		return sqlSession.update(nameSpace + ".todocateInsertUpdate", todocateVO);
	}

	@Override
	public List<TodocateVO> todocateList(Map<String, Object> inMap) throws Exception {
		return sqlSession.selectList(nameSpace + ".todocateList", inMap);
	}
	
	@Override
	public boolean todocateCheck (Map<String, Object> inMap) throws Exception {
		return sqlSession.selectOne(nameSpace + ".todocateCheck", inMap);
	}

	@Override
	public TodocateVO getTodocateDetail(Map<String, Object> inMap) throws Exception {
		return sqlSession.selectOne(nameSpace + ".getTodocateDetail", inMap);
	}


	@Override
	public int todocateUpdating (Map<String, Object> inMap)throws Exception {
		return sqlSession.update(nameSpace + ".todocateUpdating", inMap);
	}
	
	@Override
	public int todocateUpdate(TodocateVO todocateVO) throws Exception {
		return sqlSession.update(nameSpace + ".todocateUpdate", todocateVO);
	}

	@Override
	public int todocateDelete(Map<String, Object> inMap) throws Exception {
		return sqlSession.update(nameSpace + ".todocateDelete", inMap);
	}
}
