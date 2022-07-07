package kr.co.imh.service;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.co.imh.respository.IMHDAO;
import kr.co.imh.utils.Criteria;
import kr.co.imh.domain.IMHDTO;

@Service
public class IMHServiceImpl implements IMHService{
	@Inject
	private IMHDAO imhDAO;
	//로그인
	@Override
	public Map login(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return imhDAO.login(map);
	}

	//회원리스트
	@Override
	public List<IMHDTO> userAll() {
		// TODO Auto-generated method stub
		return imhDAO.userAll();
	}
	//회원리스트(탈퇴회원)
	@Override
	public List<IMHDTO> userDeleteAll() {
		// TODO Auto-generated method stub
		return imhDAO.userDeleteAll();
	}
	//회원리스트(블랙리스트)
	@Override
	public List<IMHDTO> userBlackAll() {
		// TODO Auto-generated method stub
		return imhDAO.userBlackAll();
	}
	
	//회원 상세보기
	@Override
	public IMHDTO userOne(String userId) {
		// TODO Auto-generated method stub
		return imhDAO.userOne(userId);
	}
	//회원가입
	@Override
	public int memberjoin(IMHDTO imhDTO) {
		// TODO Auto-generated method stub
		return imhDAO.memberjoin(imhDTO);
	}
	//회원 정보수정
	@Override
	public int userUpdate(IMHDTO imhDTO) {
		// TODO Auto-generated method stub
		return imhDAO.userUpdate(imhDTO);
	}
	//회원탈퇴
	@Override
	public int userDelete(String userId) {
		// TODO Auto-generated method stub
		return imhDAO.userDelete(userId);
	}

	//============================== 페이징 ==============================
	//일반회원 총 명수
	@Override
	public int count(Criteria criteria) {
		// TODO Auto-generated method stub
		return imhDAO.count(criteria);
	}

	//탈퇴회원 총 명수
	@Override
	public int countDelete() {
		// TODO Auto-generated method stub
		return imhDAO.countDelete();
	}
	//블랙리스트회원 총 명수
	@Override
	public int countBlack() {
		// TODO Auto-generated method stub
		return imhDAO.countBlack();
	}
			

	//회원리스트 + 페이징
	@Override
	public List<IMHDTO> listPage(Criteria criteria) {
		// TODO Auto-generated method stub
		return imhDAO.listPage(criteria);
	}
	//회원리스트(탈퇴회원) + 페이징
	@Override
	public List<IMHDTO> listPageDelete(Criteria criteria) {
		// TODO Auto-generated method stub
		return imhDAO.listPageDelete(criteria);
	}
	//회원리스트(블랙리스트) + 페이징
	@Override
	public List<IMHDTO> listPageBlack(Criteria criteria) {
		// TODO Auto-generated method stub
		return imhDAO.listPageBlack(criteria);
	}

	//============================== 검색(search) ==============================
	@Override
	public int idCheck(String userId) {
		// TODO Auto-generated method stub
		return imhDAO.idCheck(userId);
	}

}
