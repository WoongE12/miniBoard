package com.board.controller;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.board.dto.BoardDto;
import com.board.service.BoardService;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {

	@Autowired
	private BoardService boardService;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) throws Exception {

		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);

		String formattedDate = dateFormat.format(date);

		model.addAttribute("serverTime", formattedDate);

		// 최신 게시글 5개 가져오기
		List<BoardDto> latestPosts = boardService.getLatestPosts(5);
		model.addAttribute("latestPosts", latestPosts); // 모델에 추가

		// 공지사항 게시글 가져오기
		List<BoardDto> noticePosts = boardService.getPostsByCategory("공지사항");
		model.addAttribute("noticePosts", noticePosts);

		// 조회수 기준으로 상위 5개 게시글 가져오기
		List<BoardDto> popularPosts = boardService.getPostsByViewCount(5);
		model.addAttribute("popularPosts", popularPosts); // 모델에 추가

		return "main/main";
	}
}
