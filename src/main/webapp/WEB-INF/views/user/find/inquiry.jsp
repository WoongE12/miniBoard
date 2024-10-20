<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>아이디 / 비밀번호 찾기</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/find.css">
</head>
<body>
	<%@include file="/WEB-INF/views/header/board_Login_header.jsp"%>
	<div id="main">

		<h1>나의 정보 찾기</h1>
		<p>인증을 통해 회원님의 정보를 찾으세요.</p>
		<br>
		
		<button type="button" class="find-button" onclick="location.href='/ex/find/idInquiry'">아이디 찾기</button>
		<button type="button" class="find-button" onclick="location.href='/ex/find/pwInquiry'">비밀번호 찾기</button>

	</div>
</body>
</html>