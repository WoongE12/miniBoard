<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>완료 페이지</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/myPage.css">
</head>
<body>
<%@include file="/WEB-INF/views/header/board_header.jsp"%>
	<div id="main">
		<button type="button" class="myPage-button" onclick="location.href='/ex/user/myInfo'">
			<img src="${pageContext.request.contextPath}/resources/img/Nuser.png" id="Img">계정관리
		</button>
		<button type="button" class="myPage-button" onclick="window.location.href='/ex/user/myBoard'">
			<img src="${pageContext.request.contextPath}/resources/img/Nboard.png" id="Img">나의 게시판
		</button>
	</div>
<body>
</html>