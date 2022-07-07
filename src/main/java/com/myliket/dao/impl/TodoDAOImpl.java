package com.myliket.dao.impl;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.myliket.dao.TodoDAO;
import com.myliket.domain.TodoVO;

@Repository
public class TodoDAOImpl implements TodoDAO {
	
	@Inject
	private SqlSession sqlSession;
	
	private static final String nameSpace = "com.myliket.TodoDetailMapper";

	@Override
	public int todoIdInsert(TodoVO todoVO) throws Exception {
		return sqlSession.insert(nameSpace + ".todoIdInsert" , todoVO );
	}
	
	@Override
	public boolean todoUpdateCheck(Map<String, Object> inMap) throws Exception {
		return sqlSession.selectOne(nameSpace + ".todoUpdateCheck" , inMap);
	}
	
	@Override
	public boolean todoDeleteCheck(Map<String, Object> inMap) throws Exception {
		return sqlSession.selectOne(nameSpace + ".todoDeleteCheck" , inMap);
	}
	
	@Override
	public int todoInsertUpdate(TodoVO todoVO) throws Exception {
		return sqlSession.update(nameSpace + ".todoInsertUpdate" , todoVO);
	}
	
	@Override
	public int todoListCount(Map<String, Object> inMap) throws Exception {
		return sqlSession.selectOne(nameSpace + ".todoListCount" , inMap);
	}

	@Override
	public List<TodoVO> todoReadyList(Map<String, Object> inMap) throws Exception {
		return sqlSession.selectList(nameSpace + ".todoReadyList" , inMap );
	}
	
	@Override
	public List<TodoVO> todayReadyList(Map<String, Object> inMap) throws Exception {
		return sqlSession.selectList(nameSpace + ".todayReadyList" , inMap );
	}
	
	@Override
	public List<TodoVO> todoPastList(Map<String, Object> inMap) throws Exception {
		return sqlSession.selectList(nameSpace + ".todoPastList" , inMap);
	}
	
	@Override
	public List<TodoVO> beforeReadyList(Map<String, Object> inMap) throws Exception {
		return sqlSession.selectList(nameSpace + ".beforeReadyList" , inMap );
	}

	@Override
	public boolean todoCheck(Map<String, Object> inMap) throws Exception {
		return sqlSession.selectOne(nameSpace + ".todoCheck" , inMap);
	}
	
	@Override
	public TodoVO getTodoDetail(Map<String, Object> inMap) throws Exception {
		return sqlSession.selectOne(nameSpace + ".getTodoDetail" , inMap);
	}

	@Override
	public int todoUpdating (Map<String, Object> inMap)  throws Exception {
		return sqlSession.update(nameSpace + ".todoUpdating" , inMap );
	}
	@Override
	public int todoUpdate(TodoVO todoVO) throws Exception {
		return sqlSession.update(nameSpace + ".todoUpdate" , todoVO );
	}

	@Override
	public int todoComplete(Map<String, Object> inMap) throws Exception {
		return sqlSession.update(nameSpace + ".todoComplete" , inMap);
	}

	@Override
	public int todoDelete(Map<String, Object> inMap) throws Exception {
		return sqlSession.update(nameSpace + ".todoDelete" , inMap );
	}

}
