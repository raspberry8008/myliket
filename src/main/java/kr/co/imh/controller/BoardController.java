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
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.co.imh.domain.BoardDTO;
import kr.co.imh.domain.BoardReply;
import kr.co.imh.service.BoardService;

@Controller
public class BoardController {
	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);
	
	@Inject
	BoardService boardService;
	
	@RequestMapping(value="board/login", method= RequestMethod.GET)
	public String login() {
		logger.info("login view 이동");
		return "login";
	}
	
	@RequestMapping(value="board/login", method= RequestMethod.POST)
	public String login(@RequestParam Map<String,Object> map,
			HttpServletRequest request, HttpServletResponse response,HttpSession session) throws Exception {
		request.setCharacterEncoding("utf-8");
		logger.info("요청정보 받아서 보내기");
		Map user = boardService.login(map);
		
		if(user == null) { //로그인 실패
			logger.info("로그인 안됨");
			return "redirect:login";
		}else {  // 로그인 성공
			//세션부여
			session.setAttribute("user", user);
			return "redirect:/";
		}		
	}
	
	@RequestMapping(value = "board/logout", method = RequestMethod.GET)
	public String logout(HttpSession session, RedirectAttributes rttr) {
		session.invalidate();
		rttr.addFlashAttribute("msg", "로그아웃되었습니다");
		return "redirect:/";
	}	
	
	@RequestMapping(value="board/list",method= RequestMethod.GET)
	public ModelAndView list() {
		logger.info("====list=====");
		ModelAndView mav = new ModelAndView();
		
		List<BoardDTO> list = boardService.list();
		mav.addObject("list", list);
		mav.setViewName("list");
		return mav;
	}
	
	@RequestMapping(value="board/register", method= RequestMethod.GET)
	public String register() {
		logger.info("글쓰기 이동");
		return "register";
	}
	@RequestMapping(value="board/register", method= RequestMethod.POST)
	public String register(BoardDTO boardDTO, HttpServletRequest request,RedirectAttributes rttr) throws Exception {
		request.setCharacterEncoding("UTF-8");
		logger.info("내용" + boardDTO);
		int r = boardService.register(boardDTO);
		
		if(r>0) {
			rttr.addFlashAttribute("msg","추가에 성공하였습니다.");
		}
		return "redirect:list";
	}	
	
	@RequestMapping(value = "board/detail", method = RequestMethod.GET)
//	public ModelAndView detail(@RequestParam("bno")int bno) {
//		ModelAndView mav = new ModelAndView();
//		//비지니스모델 , 서비스
//		BoardDTO boardDTO = boardService.getDetail(bno);
//		boardService.updateReadCnt(bno);
//		
//		mav.setViewName("detail");
//		return mav;
//	}
	public String detail(@RequestParam("bno")int bno, Model model) {
		
		//비지니스모델 , 서비스
		BoardDTO boardDTO = boardService.getDetail(bno);
		boardService.updateReadCnt(bno);
		model.addAttribute("board", boardDTO);
		
		//댓글 목록 조회
		List<BoardReply> replylist = boardService.getDetail1(bno);
		model.addAttribute("list", replylist);
		
		return "detail";
	}
	@RequestMapping(value = "board/update", method = RequestMethod.GET)
	public String update(@RequestParam("bno") int bno, Model model) {
		BoardDTO board = boardService.getDetail(bno);
		model.addAttribute("board", board);
		return "update";
	}
	
	@RequestMapping(value = "board/update", method = RequestMethod.POST)
	public String update(BoardDTO boardDTO, RedirectAttributes attr,HttpServletRequest request) throws Exception {
		request.setCharacterEncoding("utf-8");
		int r = boardService.update(boardDTO);
		// 수정에 성공하면 목록보기로 이동
		if (r > 0) {
			attr.addFlashAttribute("msg", "수정에 성공 하였습니다.");
			return "redirect:list";
		}
		// 수정에 실패하면 수정보기 화면으로 이동
		return "redirect:update?bno=" + boardDTO.getBno();
	}
	@RequestMapping(value="board/delete",method = RequestMethod.GET)
	public String delete(@RequestParam("bno") int bno, RedirectAttributes rttr) {
		int r = boardService.delete(bno);
		
		if(r > 0) {
			rttr.addFlashAttribute("msg","글삭제에 성공하였습니다.");
			return "redirect:list";
		}
		return "redirect:detail?bno=" + bno;
	}
	
	@RequestMapping(value="board/reply", method = RequestMethod.GET)
	public String reply() {
		return "reply";
	}
	
	@RequestMapping(value="board/reply", method = RequestMethod.POST)
	public String reply(BoardReply boardReply) {
		int r = boardService.reply(boardReply);
		if(r>0) {
			return "redirect:detail?bno=" + boardReply.getBno();
		}
		return "reply";
	}
	
	@RequestMapping(value="board/replyupdate", method = RequestMethod.GET)
	public String replyUpdate(@RequestParam("reno")int reno, Model model) {
		BoardReply boardReply = boardService.detailreply(reno);
		model.addAttribute("boardReply", boardReply);
		return "replyupdate";
	}
	
	@RequestMapping(value="board/replyupdate", method = RequestMethod.POST)
	public String replyUpdate(BoardReply boardReply) {
		int r = boardService.replyupdate(boardReply);
		if(r>0)
			return "redirect:detail?bno=" + boardReply.getBno();
		return "redirect:list";
	}
	
}




