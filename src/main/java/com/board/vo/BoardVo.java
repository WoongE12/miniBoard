package com.board.vo;

import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

public class BoardVo {

	private int page = 1; 					// 현재 페이지
	private int perPageNum = 10; 			// 페이지당 데이터 개수
	private String searchType; 				// 검색할 컬럼
	private String keyword; 				// 검색 내용
	private String bCategory; 				// 카테고리
	private String searchKeyword; 			// *****이거 확인 후 삭제

	private int totalCount; 				// 전체 데이터 개수
	private int totalStartPage = 1; 		// 첫 페이지
	private int totalEndPage; 				// 끝 페이지
	private int startPage; 					// 페이지 시작 번호
	private int endPage; 					// 페이지 끝 번호
	private boolean prev; 					// 이전 페이지 목록 이동
	private boolean next; 					// 다음 페이지 목록 이동
	private int displayPageNum = 10; 		// 한 화면에서 보여줄 페이지


	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		if(page<=0) {
			page=1;
		}
		this.page = page;
	}

	public int getPerPageNum() {
		return perPageNum;
	}

	public void setPerPageNum(int perPageNum) {
		if(perPageNum<=0||perPageNum>100) {
			perPageNum=10;
		}
		this.perPageNum = perPageNum;
	}

	public String getSearchType() {
		return searchType;
	}

	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public String getbCategory() {
		return bCategory;
	}

	public void setbCategory(String bCategory) {
		this.bCategory = bCategory;
	}

	public String getSearchKeyword() {
		return searchKeyword;
	}

	public void setSearchKeyword(String searchKeyword) {
		this.searchKeyword = searchKeyword;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
		calcData();

	}

	private void calcData() {
		totalStartPage = 1; 
		totalEndPage = (int) Math.ceil(totalCount / (double) perPageNum);
		endPage = (int) (Math.ceil(page / (double) displayPageNum) * displayPageNum);
		startPage = endPage - displayPageNum + 1;

		if (endPage > totalEndPage) {
	        endPage = totalEndPage;
	    }
	    if (startPage < 1) {
	        startPage = 1;
	    }
	    
	    prev = startPage > 1;
	    next = endPage < totalEndPage;
	}

	public int getTotalStartPage() {
		return totalStartPage;
	}

	public void setTotalStartPage(int totalStartPage) {
		this.totalStartPage = totalStartPage;
	}

	public int getTotalEndPage() {
		return totalEndPage;
	}

	public void setTotalEndPage(int totalEndPage) {
		this.totalEndPage = totalEndPage;
	}

	public int getStartPage() {
		return startPage;
	}

	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}

	public int getEndPage() {
		return endPage;
	}

	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}

	public boolean isPrev() {
		return prev;
	}

	public void setPrev(boolean prev) {
		this.prev = prev;
	}

	public boolean isNext() {
		return next;
	}

	public void setNext(boolean next) {
		this.next = next;
	}

	public int getDisplayPageNum() {
		return displayPageNum;
	}

	public void setDisplayPageNum(int displayPageNum) {
		this.displayPageNum = displayPageNum;
	}

	@Override
	public String toString() {
		return "BoardVo [page=" + page + ", perPageNum=" + perPageNum + ", searchType=" + searchType + ", keyword="
				+ keyword + ", bCategory=" + bCategory + ", searchKeyword=" + searchKeyword + ", totalCount="
				+ totalCount + ", totalStartPage=" + totalStartPage + ", totalEndPage=" + totalEndPage + ", startPage="
				+ startPage + ", endPage=" + endPage + ", prev=" + prev + ", next=" + next + ", displayPageNum="
				+ displayPageNum + "]";
	}

	public String makeSearch() {
		UriComponents u = UriComponentsBuilder.newInstance()
				.queryParam("b_category", bCategory)
				.queryParam("page", page)
				.queryParam("perPageNum", perPageNum)
				.queryParam("searchType", searchType)
				.queryParam("keyword", keyword)
				.build();
		return u.toUriString();
	}

	public String makeSearch(int page) {
		UriComponents u = UriComponentsBuilder.newInstance()
				.queryParam("b_category", bCategory)
				.queryParam("page", page)
				.queryParam("perPageNum", perPageNum)
				.queryParam("searchType", searchType)
				.queryParam("keyword", keyword)
				.build();
		return u.toUriString();
	}

	public String makePage(int page) {
		UriComponents u = UriComponentsBuilder.newInstance()
				.queryParam("b_category", bCategory)
				.queryParam("page", page)
				.queryParam("perPageNum", perPageNum)
				.build();
		return u.toUriString();
	}
}
