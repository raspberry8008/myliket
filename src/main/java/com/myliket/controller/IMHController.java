package kr.co.imh.controller;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import kr.co.imh.domain.IMHDTO;
import kr.co.imh.service.IMHService;
import kr.co.imh.utils.Criteria;
import kr.co.imh.utils.PageMaker;

@Controller
public class IMHController {
	private static final Logger logger = LoggerFactory.getLogger(IMHController.class);
	
	@Inject
	private IMHService imhService;
	
	//로그인 페이지 이동
	@RequestMapping(value = "login", method = RequestMethod.GET)
	public String login() {
		logger.info("로그인 페이지 이동");
		return "login/login";
	}
	@RequestMapping(value = "login", method = RequestMethod.POST)
	public String login(@RequestParam Map<String, Object> map, HttpServletRequest request, HttpServletResponse response,
			HttpSession session) throws Exception {
		request.setCharacterEncoding("UTF-8");
		logger.info("요청정보 보내기");
		
		Map admin = imhService.login(map);
		if(admin == null) {
			logger.info("로그인 안됨");
			return "redirect:/";
		} else {
			session.setAttribute("admin", admin);
			return "redirect:/home";
		}
	}
	//로그아웃
	@RequestMapping(value = "logout", method = RequestMethod.GET)
	public String logout(HttpSession session) {
		logger.info("로그아웃 페이지 이동");
		session.invalidate();	//바인딩 세션 해제
		return "redirect:/";
	}
	
	//회원관리
	//회원리스트
	@RequestMapping(value = "userAll", method = RequestMethod.GET)
	public String userAll(Model model, Criteria criteria) {
		logger.info("회원리스트 페이지 이동");

		PageMaker pageMaker =new PageMaker();
		pageMaker.setCriteria(criteria);
		pageMaker.setCount(imhService.count(criteria));

		List<IMHDTO> userAll = imhService.listPage(criteria);
		model.addAttribute("userAll", userAll);
		model.addAttribute("pageMaker", pageMaker);
		
		return "user/userAll";
	}
	//회원리스트(탈퇴회원)
	@RequestMapping(value = "userDeleteAll", method = RequestMethod.GET)
	public String userDeleteAll(Model model, Criteria criteria) {
		logger.info("탈퇴회원리스트 페이지 이동");
		
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCriteria(criteria);
		pageMaker.setCount(imhService.countDelete());
		
//		List<IMHDTO> userDeleteAll = imhService.userDeleteAll();
		List<IMHDTO> userDeleteAll = imhService.listPageDelete(criteria);
		model.addAttribute("userDeleteAll", userDeleteAll);
		model.addAttribute("pageMaker", pageMaker);
		
		return "user/userDeleteAll";
	}
	//회원리스트(블랙리스트)
	@RequestMapping(value = "userBlackAll", method = RequestMethod.GET)
	public String userBlackAll(Model model, Criteria criteria) {
		logger.info("블랙리스트 페이지 이동");
		
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCriteria(criteria);
		pageMaker.setCount(imhService.countBlack());
		
//		List<IMHDTO> userBlackAll = imhService.userBlackAll();
		List<IMHDTO> userBlackAll = imhService.listPageBlack(criteria);
		model.addAttribute("userBlackAll", userBlackAll);
		model.addAttribute("pageMaker", pageMaker);
		
		return "user/userBlackAll";
	}
	
	//회원 상세보기
	@RequestMapping(value = "userOne", method = RequestMethod.GET)
	public String userOne(@RequestParam("userId") String userId, Model model) {
		logger.info("회원 상세보기 페이지 이동");
		
		IMHDTO imhDTO = imhService.userOne(userId);
		model.addAttribute("imhDTO", imhDTO);
		
		return "user/userOne";
	}
	//회원가입
	@RequestMapping(value = "memberjoin", method = RequestMethod.GET)
	public String memberjoin() {
		logger.info("회원가입 페이지 이동");
		return "user/memberjoin";
	}
	@RequestMapping(value = "memberjoin", method = RequestMethod.POST)
	public String memberjoin(IMHDTO imhDTO, HttpServletRequest request) throws Exception {
		request.setCharacterEncoding("UTF-8");
		
		int memberjoin = imhService.memberjoin(imhDTO);
		if(memberjoin >= 0) {
			logger.info("회원가입 성공");
			return "redirect:/";
		} else {
			logger.info("회원가입 실패!!!");
			return "redirect:memberjoin";
		}
	}
	//회원 정보수정
	@RequestMapping(value = "userUpdate", method = RequestMethod.GET)
	public String userUpdate(@RequestParam("userId") String userId, Model model) {
		logger.info("회원수정 페이지 이동");
		
		IMHDTO imhDTO = imhService.userOne(userId);
		model.addAttribute("imhDTO", imhDTO);
		return "user/userUpdate";
	}
	@RequestMapping(value = "userUpdate", method = RequestMethod.POST)
	public String userUpdate(IMHDTO imhDTO, HttpServletRequest request) throws Exception {
		request.setCharacterEncoding("UTF-8");
		int update = imhService.userUpdate(imhDTO);
		
		if(update >= 0) {	//수정 성공
			logger.info("회원 정보가 수정되었습니다.");
			return "redirect:userAll?num=1";
		} else {	//수정 실패
			logger.info("수정 실패!!!!");
			return "redirect:userOne?userId=" + imhDTO.getUserId();
		}
	}
	//회원삭제
	@RequestMapping(value = "userDelete", method = RequestMethod.GET)
	public String userDelete(@RequestParam("userId") String userId) {
		int delete = imhService.userDelete(userId);
		
		if(delete >= 0) {	//삭제 성공
			logger.info("회원 정보가 삭제되었습니다.");
			return "redirect:userAll?num=1";
		} else {	//삭제 실패
			logger.info("회원 정보가 남아있습니다.");
			return "redirect:userOne?userId=" + userId;
		}
	}
		
//	==================================== 페이징 ====================================
//	//회원리스트 + 페이징
//	@RequestMapping(value = "listPage", method = RequestMethod.GET)
//	public String listPage(@RequestParam("num") int num, Model model) {
//
//		PageMaker pageMaker = new PageMaker();
//		pageMaker.setNum(num);
//		pageMaker.setCount(imhService.count());
//		
//		List<IMHDTO> list = imhService.listPage(pageMaker.getDisplayPost(), pageMaker.getPostNum());
//		model.addAttribute("list", list);
//		model.addAttribute("pageMaker", pageMaker);
//		
//		return "paging/listPage";
//	}
	
//	==================================== 아이디 중복확인 ====================================
}
