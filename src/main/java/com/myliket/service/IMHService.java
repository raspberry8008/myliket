package kr.co.imh.service;

import java.util.List;
import java.util.Map;

import kr.co.imh.domain.IMHDTO;
import kr.co.imh.utils.Criteria;
import kr.co.imh.utils.SearchCriteria;

public interface IMHService {
	//로그인
	public Map login(Map<String, Object> map);

	//회원리스트
	public List<IMHDTO> userAll();
	//회원리스트(탈퇴회원)
	public List<IMHDTO> userDeleteAll();
	//회원리스트(블랙리스트)
	public List<IMHDTO> userBlackAll();
	//회원 상세보기
	public IMHDTO userOne(String userId);
	//회원가입
	public int memberjoin(IMHDTO imhDTO);
	//회원 정보수정
	public int userUpdate(IMHDTO imhDTO);
	//글 삭제
	public int userDelete(String userId);	

	//============================== 페이징 ==============================
	//일반회원 총 명수
	public int count(Criteria criteria);
	//탈퇴회원 총 명수
	public int countDelete();
	//블랙리스트회원 총 명수
	public int countBlack();

	//회원리스트 + 페이징
	public List<IMHDTO> listPage(Criteria criteria);
	//회원리스트(탈퇴회원) + 페이징
	public List<IMHDTO> listPageDelete(Criteria criteria);
	//회원리스트(블랙리스트) + 페이징
	public List<IMHDTO> listPageBlack(Criteria criteria);

	//============================== 검색(search) ==============================
	public int idCheck(String userId);
}
