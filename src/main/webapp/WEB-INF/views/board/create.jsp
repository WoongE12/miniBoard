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
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/boardCreate.css">
</head>
<body>
	<c:if test="${sessionScope.dto eq null }">
		<script>
			alert('로그인이 필요합니다.');
			location.href = "/ex/user/login";
		</script>
	</c:if>
	<div>
		<%@include file="../header/board_header.jsp"%>
	</div>

	<div class="main">
		<div class="box-header">
			<h1 class="box-title">글쓰기</h1>
		</div>

		<div class="horizontal-line"></div>

		<!-- action에 경로가 없으면 현재 페이지 주소로 이동한다.-->
		<!-- 	<form role="form" method="post"> -->
		<form role="form" method="post">

			<div class="form-group">
				<h2 class="b_category">
					<select name="b_category">
						<option value="공지사항">공지사항</option>
						<option value="자유게시판">자유게시판</option>
						<option value="질문게시판">질문게시판</option>
						<option value="정보게시판">정보게시판</option>
					</select>
				</h2>

				<div class="submitBtn">
					<button type="submit">등록</button>
				</div>
			</div>

			<h2 class="b_title">
				<input type="text" name='b_title' placeholder="제목 입력"
					style="width: 100%">
			</h2>


			<h2 class="b_content">
				<textarea name="b_content" rows="35" style="width: 100%"
					placeholder="내용 입력"></textarea>
			</h2>

		</form>
	</div>
</body>
</html>
