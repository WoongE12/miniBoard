<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<meta name="logout-status"
	content="${sessionScope.userId == null ? 'logged-out' : 'logged-in'}">
<title>웅이</title>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/boardList.css">

<script
	src="${pageContext.request.contextPath}/resources/js/listAllJs.js"></script>
<body>
	<div>
		<%@include file="../header/board_header.jsp"%>
	</div>

	<div class="main">

		<div class="main-content">
			
			<div class="button-container">
				<button class="cButton" data-category="">전체글보기</button>
				<button class="cButton" data-category="공지사항">공지사항</button>
				<button class="cButton" data-category="자유게시판">자유게시판</button>
				<button class="cButton" data-category="질문게시판">질문게시판</button>
				<button class="cButton" data-category="정보게시판">정보게시판</button>
			</div>
			
			<table id='customers'>
				<thead>
					<tr>
						<th style="width: 20px;">No.</th>
						<th style="width: 50px;">카테고리</th>
						<th style="width: 200px;">제목</th>
						<th style="width: 30px;">작성자</th>
						<th style="width: 50px;">작성일</th>
						<th style="width: 20px;">조회</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${list}" var="boardDto">
						<tr>
							<td>${boardDto.b_id }</td>
							<td>${boardDto.b_category }</td>
							<td><a href="/ex/board/read?b_id=${boardDto.b_id}">
									${boardDto.b_title} <c:if test="${boardDto.reply_count ne 0}">
										<small class="red-text"> <b>[${boardDto.reply_count}]</b>
										</small>
									</c:if>
							</a></td>
							<td>${boardDto.u_id}</td>
							<td><fmt:formatDate value="${boardDto.b_date}"
									pattern="yyyy-MM-dd" /></td>
							<td>${boardDto.b_view}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>

			<div class="writeBtn-container">
				<button class="writeBtn">글쓰기</button>
			</div>

			<div class="pagination">
				<c:if test="${boardVo.page !=1}">
					<a href='listAll${boardVo.makeSearch(1)}'>&laquo;</a>
				</c:if>

				<c:if test="${boardVo.prev }">
					<a href='listAll${boardVo.makeSearch(boardVo.startPage-1)}'>&lt;</a>
				</c:if>

				<c:forEach begin="${boardVo.startPage }" end="${ boardVo.endPage}"
					var="idx">
					<a href='listAll${boardVo.makeSearch(idx)}'
						<c:out value="${boardVo.page==idx?' class=active ':'' }"/>>
						${idx}</a>
				</c:forEach>

				<c:if test="${boardVo.next }">
					<a href='listAll${boardVo.makeSearch(boardVo.endPage+1)}'>&gt;</a>
				</c:if>

				<c:if test="${boardVo.page != boardVo.totalEndPage}">
					<a href='listAll${boardVo.makeSearch(boardVo.totalEndPage)}'>&raquo;</a>
				</c:if>
			</div>

			<!-- 검색창 추가 -->
			<div class="search-container">
				<select name="searchType">
					<option value="-----">-----</option>
					<option value="공지사항">공지사항</option>
					<option value="자유게시판">자유게시판</option>
					<option value="질문게시판">질문게시판</option>
					<option value="정보게시판">정보게시판</option>
				</select> <input type="text" id="keywordInput" placeholder="검색어 입력">
				<button id="searchBtn">검색</button>
			</div>
		</div>
	</div>
</body>
</html>
