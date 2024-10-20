package com.board.service;

import java.util.List;

import com.board.dto.BoardDto;
import com.board.vo.BoardVo;

public interface BoardService {

	public void create(BoardDto board) throws Exception;	//게시판 생성
	public BoardDto read(int b_id) throws Exception;		//특정 게시판 항목 조회
	public void update(BoardDto board) throws Exception;	//게시판 항목 업데이트
	public void delete(int b_id) throws Exception;			//특정 게시판 항목 삭제
	public List<BoardDto> listAll() throws Exception;		//모든 게시판 항목 조회
	
	public void b_viewUpdate(int b_id) throws Exception;				//조회수 업데이트
	public List<BoardDto> listSearch(BoardVo vo) throws Exception;		//검색 조건에 맞는 게시판 항목 조회

	public int getTotalCountByCategory(BoardVo vo) throws Exception;	//카테고리 및 검색 조건에 따른 전체 게시글 수 조회
	public List<BoardDto> getUserPosts(String u_id) throws Exception; //나의 게시판
	
	public List<BoardDto> getLatestPosts(int limit) throws Exception; // 최신 게시글 조회 메서드 추가
	public List<BoardDto> getPostsByCategory(String category) throws Exception; // 카테고리로 게시글 조회 메서드 추가
	public List<BoardDto> getPostsByViewCount(int limit) throws Exception; // 조회수 기준으로 게시글 가져오기
}
