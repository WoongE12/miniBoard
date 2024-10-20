package com.board.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.board.dto.BoardDto;
import com.board.dto.CommentDto;
import com.board.dto.UserDto;
import com.board.service.BoardService;
import com.board.service.CommentService;
import com.board.vo.BoardVo;

@Controller
@RequestMapping("/board/*")
public class BoardController {

	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);

	@Autowired
	private BoardService service;

	@Autowired
	private CommentService cs;

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public String createGET(BoardDto board, Model model, HttpSession session) throws Exception {
		UserDto dto = (UserDto) session.getAttribute("dto");
		System.out.println("UserDto TEst : " + dto);
		logger.info("create get..........");
		return "/board/create";
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public String createPOST(BoardDto board, Model model, RedirectAttributes rttr, HttpSession session)
			throws Exception {
		logger.info("create post..........");
		logger.info(board.toString());
		System.out.println(board);

		// 현재 로그인한 사용자 정보 가져오기
		UserDto dto = (UserDto) session.getAttribute("dto");
		String userid = dto.getU_id();
		board.setU_id(userid);
		service.create(board);
		rttr.addFlashAttribute("msg", "success");
		return "redirect:/board/listAll";
	}

	@RequestMapping(value = "/read", method = RequestMethod.GET)
	public void read(@RequestParam("b_id") int b_id, Model model, HttpSession session) throws Exception {
		logger.info("read.........." + b_id);
		service.b_viewUpdate(b_id); // 조회수
		BoardDto board = service.read(b_id);
		model.addAttribute("boardDto", board);

		// 현재 로그인한 사용자 정보 추가
		UserDto userDto = (UserDto) session.getAttribute("dto");
		model.addAttribute("userDto", userDto);

		// 댓글 리스트 가져오기
		List<CommentDto> comments = cs.commentList(b_id);
		model.addAttribute("comments", comments);
	}

	@RequestMapping(value = "/update", method = RequestMethod.GET)
	public void updateGET(int b_id, Model model) throws Exception {
		model.addAttribute(service.read(b_id));
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String updatePOST(BoardDto board, RedirectAttributes rttr, HttpSession session) throws Exception {
		System.out.println("--------------------------확인");
		System.out.println(board.toString());
		logger.info("mod post..........");

		// 현재 로그인한 사용자 정보 가져오기
		UserDto dto = (UserDto) session.getAttribute("dto");
		if (dto == null) {
			rttr.addFlashAttribute("msg", "login_required");
			return "redirect:/login";
		}

		String userid = dto.getU_id();

		// 게시글의 현재 작성자와 로그인한 사용자 비교
		BoardDto existingBoard = service.read(board.getB_id());
		if (existingBoard == null) {
			// rttr.addFlashAttribute("msg", "board_not_found");
			return "redirect:/board/listAll";
		}
		if (!userid.equals(existingBoard.getU_id())) {
			// rttr.addFlashAttribute("msg", "not_authorized");
			return "redirect:/board/listAll";
		}

		// 게시글 수정
		board.setU_id(userid);
		service.update(board);

		rttr.addFlashAttribute("msg", "update_success");
		return "redirect:/board/listAll";
	}

	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public String delete(@RequestParam("b_id") int b_id, RedirectAttributes rttr) throws Exception {
		service.delete(b_id);
		rttr.addFlashAttribute("msg", "delete_success");
		return "redirect:/board/listAll";
	}

	@RequestMapping(value = "/listAll", method = RequestMethod.GET)
	public void listAll(@RequestParam(value = "b_category", required = false) String b_category,
			@RequestParam(value = "searchType", required = false) String searchType,
			@RequestParam(value = "keyword", required = false) String keyword, @ModelAttribute("boardVo") BoardVo vo,
			Model model) throws Exception {

		System.out.println("현재페이지: " + vo.getPage());

		logger.info("show all list..........");
		System.out.println("--list pm " + vo);

		if (b_category != null && !b_category.isEmpty()) {
			vo.setbCategory(b_category);
		}

		if (searchType != null && !searchType.isEmpty()) {
			vo.setSearchType(searchType);
		}

		if (keyword != null && !keyword.isEmpty()) {
			vo.setKeyword(keyword);
		} else {
			vo.setKeyword(null); // 검색창 비어있을 때 관련 카테고리 전부 나오게
		}

		// 카테고리별 전체 게시글 수 조회
		int totalCnt = service.getTotalCountByCategory(vo);
		vo.setTotalCount(totalCnt);

		// 게시글 목록 조회
		List<BoardDto> boardList = service.listSearch(vo);
		for(BoardDto dto : boardList) {
			System.out.println(dto.getReply_count());
		}

		// 댓글 수 업데이트
		model.addAttribute("list", boardList);
		model.addAttribute("boardVo", vo);

	}

	@RequestMapping(value = "/user/myBoard", method = RequestMethod.GET)
	public String myBoard(Model model, HttpSession session) throws Exception {
		// 세션에서 UserDto 객체를 가져옴
		UserDto dto = (UserDto) session.getAttribute("dto");

		// 사용자가 로그인하지 않은 경우 처리 (로그인 페이지로 리다이렉트)
		if (dto == null) {
			return "redirect:/login";
		}

		// 사용자 ID를 가져옴
		String userId = dto.getU_id();

		// 게시글 조회 서비스 호출
		try {
			// 사용자가 작성한 게시글을 서비스에서 가져오기
			List<BoardDto> userPosts = service.getUserPosts(userId);

			// 게시글이 없을 경우 처리
			if (userPosts == null || userPosts.isEmpty()) {
				model.addAttribute("message", "작성된 게시글이 없습니다.");
			} else {
				// 모델에 게시글 추가하여 JSP에 전달
				model.addAttribute("userPosts", userPosts);
			}
		} catch (Exception e) {
			// 에러 발생 시 로그 출력
			e.printStackTrace();
			model.addAttribute("errorMessage", "게시글 조회 중 오류가 발생했습니다.");
		}

		return "/user/myBoard"; // myBoard.jsp로 이동
	}

}