package kr.co.imh.respository;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import kr.co.imh.domain.IMHDTO;
import kr.co.imh.utils.Criteria;

@Repository
public class IMHDAOImpl implements IMHDAO{
	@Inject
	private SqlSession sqlSession;
	
	private static final String namespace="kr.co.imh.IMHMapper";
	//로그인
	@Override
	public Map login(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne(namespace + ".login", map);
	}

	//회원리스트
	@Override
	public List<IMHDTO> userAll() {
		// TODO Auto-generated method stub
		return sqlSession.selectList(namespace + ".userAll");
	}
	//회원리스트(탈퇴회원)
	@Override
	public List<IMHDTO> userDeleteAll() {
		// TODO Auto-generated method stub
		return sqlSession.selectList(namespace + ".userDeleteAll");
	}
	//회원리스트(블랙리스트)
	@Override
	public List<IMHDTO> userBlackAll() {
		// TODO Auto-generated method stub
		return sqlSession.selectList(namespace + ".userBlackAll");
	}

	//회원 상세보기
	@Override
	public IMHDTO userOne(String userId) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne(namespace + ".userOne", userId);
	}
	//회원가입
	@Override
	public int memberjoin(IMHDTO imhDTO) {
		// TODO Auto-generated method stub
		return sqlSession.insert(namespace + ".memberjoin", imhDTO);
	}
	//회원 정보수정
	@Override
	public int userUpdate(IMHDTO imhDTO) {
		// TODO Auto-generated method stub
		return sqlSession.update(namespace + ".userUpdate", imhDTO);
	}
	//회원탈퇴
	@Override
	public int userDelete(String userId) {
		// TODO Auto-generated method stub
		return sqlSession.update(namespace + ".userDelete", userId);
	}

	//============================== 페이징 ==============================
	//일반회원 총 명수
	@Override
	public int count(Criteria criteria) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne(namespace + ".count");
	}

	//탈퇴회원 총 명수
	@Override
	public int countDelete() {
		// TODO Auto-generated method stub
		return sqlSession.selectOne(namespace + ".countDelete");
	}
	//블랙리스트회원 총 명수
	@Override
	public int countBlack() {
		// TODO Auto-generated method stub
		return sqlSession.selectOne(namespace + ".countBlack");
	}
		
	//회원리스트 + 페이징
	@Override
	public List<IMHDTO> listPage(Criteria criteria) {
		// TODO Auto-generated method stub
		return sqlSession.selectList(namespace + ".listPage", criteria);
	}
	//회원리스트(탈퇴회원) + 페이징
	@Override
	public List<IMHDTO> listPageDelete(Criteria criteria) {
		// TODO Auto-generated method stub
		return sqlSession.selectList(namespace + ".listPageDelete", criteria);
	}
	//회원리스트(블랙리스트) + 페이징
	@Override
	public List<IMHDTO> listPageBlack(Criteria criteria) {
		// TODO Auto-generated method stub
		return sqlSession.selectList(namespace + ".listPageBlack", criteria);
	}

	//============================== 검색(search) ==============================
	@Override
	public int idCheck(String userId) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne(namespace + ".idCheck", userId);
	}

}
