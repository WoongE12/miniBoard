<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>웅이</title>

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/boardRead.css">
<script
	src="${pageContext.request.contextPath}/resources/js/boardRead.js"></script>
</head>
<body>
	<div>
		<%@include file="../header/board_header.jsp"%>
	</div>

	<div class="main">

		<form role="form" method="post">
			<input type='hidden' name='b_id' value="${boardDto.b_id}">
		</form>

		<h2 class="b_category">
			<input type="text" name='b_category' style="width: 20%"
				value="${boardDto.b_category}" readonly="readonly">
		</h2>

		<h2 class="b_title">
			<input type="text" name='b_title' style="width: 100%"
				value="${boardDto.b_title}" readonly="readonly">
		</h2>

		<h2 class="u_id">
			<input type="text" name="u_id" style="width: 20%"
				value="${boardDto.u_id}" readonly="readonly">
		</h2>

		<h2 class="u_date">
			<input type="text" name="u_date" style="width: 20%"
				value="<fmt:formatDate value='${boardDto.b_date}' pattern='yyyy-MM-dd HH:mm'/>"
				readonly="readonly">
		</h2>

		<h2 class="b_content">
			<textarea style="width: 100%" name="b_content" rows="35"
				readonly="readonly">${boardDto.b_content}</textarea>
		</h2>

		<div class="box-footer">
			<c:if
				test="${sessionScope.dto != null && sessionScope.dto.u_id == boardDto.u_id}">
				<button type="button" class="btn btn-warning"
					onclick="location.href='/ex/board/update?b_id=${boardDto.b_id}'">수정</button>
				<button type="button" class="btn btn-danger"
					onclick="confirmBoardDelete('${boardDto.b_id}')">삭제</button>
			</c:if>
			<button type="button" class="btn btn-primary"
				onclick="location.href='/ex/board/listAll'">목록</button>
		</div>

		<div class="horizontal-line"></div>

		<c:choose>
			<c:when test="${sessionScope.dto eq null}">
				<p>로그인 후 댓글 작성 가능 합니다.</p>
			</c:when>
			<c:otherwise>
				<div class="comment-container">
					<form action="/ex/comments/create" method="post">
						<input type="hidden" name="b_id" value="${boardDto.b_id}" />
						<div>
							<textarea name="comm_content" required placeholder="댓글을 남겨보세요."></textarea>
							<button type="submit">등록</button>
						</div>
					</form>
				</div>
			</c:otherwise>
		</c:choose>

		<div id="comments">
			<c:if test="${not empty comments}">
				<c:forEach var="comment" items="${comments}">
					<div style="margin-bottom: 5px;">
						<p style="margin-bottom: 10px;">
							<strong style="font-weight: bold;">${comment.u_id}</strong>
							<button class="btn btn-secondary"
								onclick="toggleButtons('${comment.comm_id}')"
								style="float: right;">≡</button>
						</p>
						<p style="margin-left: 10px;">${comment.comm_content}</p>
						<p style="margin-left: 10px; margin-top: 10px; font-size: 13px;">
							<c:if test="${comment.comm_date != null}">
								<fmt:formatDate value='${comment.comm_date}'
									pattern="yyyy-MM-dd HH:mm" />
							</c:if>
							<!--<c:out value="${comment.comm_date}"/>-->
						</p>
						<c:if
							test="${sessionScope.dto != null && sessionScope.dto.u_id == comment.u_id}">
							<div id="buttons-${comment.comm_id}" style="display: none; text-align: right;">
								<form action="/ex/comments/update" method="post"
									style="display: inline;">
									<input type="hidden" name="comm_id" value="${comment.comm_id}" />
									<input type="hidden" name="b_id" value="${boardDto.b_id}" />
									<textarea class="update-content" name="comm_content" required>${comment.comm_content}</textarea>
									<button type="submit" class="btn btn-warning">수정</button>
								</form>
								<form action="/ex/comments/delete" method="post"
									style="display: inline;" onsubmit="return confirmDelete();">
									<input type="hidden" name="comm_id" value="${comment.comm_id}" />
									<input type="hidden" name="b_id" value="${boardDto.b_id}" />
									<button type="submit" class="btn btn-danger">삭제</button>
								</form>
							</div>
						</c:if>
					</div>
				</c:forEach>
			</c:if>
		</div>
</body>
</html>