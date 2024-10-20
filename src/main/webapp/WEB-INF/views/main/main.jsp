<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/main.css">
</head>
<body>
	<div>
		<%@include file="../header/board_header.jsp"%>
	</div>

	<div class="banner">
		<img class="banner1"
			src="${pageContext.request.contextPath}/resources/img/banner.jpg" />

		<div class="container">
			<!-- 상단 공지사항과 최신글 및 인기글 표시 -->
			<div class="top-container">
				<!-- 공지사항 게시글 표시 -->
				<div class="column">
					<h2
						style="display: flex; justify-content: space-between; align-items: center;">
						공지사항 <a
							href="${pageContext.request.contextPath}/board/listAll?bCategory=공지사항"
							style="font-size: 0.8em; text-decoration: none; color: black;">more</a>
					</h2>
					<hr style="border: 1px solid #000000; margin: 10px 0;">
					<ul>
						<c:forEach var="notice" items="${noticePosts}">
							<li><a
								href="${pageContext.request.contextPath}/board/read?b_id=${notice.b_id}">${notice.b_title}</a>
								<span class="date">(<fmt:formatDate
										value="${notice.b_date}" pattern="yyyy-MM-dd" />)
							</span></li>
						</c:forEach>
					</ul>
				</div>

				<!-- 최신 게시글 표시 -->
				<div class="column">
					<h2
						style="display: flex; justify-content: space-between; align-items: center;">
						최신글 <a
							href="${pageContext.request.contextPath}/board/listAll?bCategory="
							style="font-size: 0.8em; text-decoration: none; color: black;">more</a>
					</h2>
					<hr style="border: 1px solid #000000; margin: 10px 0;">
					<ul>
						<c:forEach var="post" items="${latestPosts}">
							<li><a
								href="${pageContext.request.contextPath}/board/read?b_id=${post.b_id}">${post.b_title}</a>
								<span class="date">(<fmt:formatDate
										value="${post.b_date}" pattern="yyyy-MM-dd" />)
							</span></li>
						</c:forEach>
					</ul>
				</div>

				<!-- 인기글 표시 -->
				<div class="column">
					<h2>인기글</h2>
					<hr style="border: 1px solid #000000; margin: 10px 0;">
					<ul>
						<c:forEach var="post" items="${popularPosts}">
							<li><a
								href="${pageContext.request.contextPath}/board/read?b_id=${post.b_id}">${post.b_title}</a>
								<span class="date">(<fmt:formatDate
										value="${post.b_date}" pattern="yyyy-MM-dd" />)
							</span></li>
						</c:forEach>
					</ul>
				</div>
			</div>
		</div>
</body>
</html>
