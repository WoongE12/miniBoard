<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>비밀번호 찾기</title>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/find.css">
	<style>
	/* 버튼 스타일 */
	.find-button {
		width: 400px;
		height: 70px;
		border-radius: 0px; /* 버튼의 모서리를 0px로 둥글지 않게 설정 */
	}
	</style>
</head>
<body>
	<%@include file="/WEB-INF/views/header/board_header.jsp"%>
	<div id="main">
		<h1 style="margin-bottom: 20px;">비밀번호를 찾고자하는 아이디를 입력해주세요.</h1>
		
		<p style="display: inline; color: #054184;">01. 아이디 입력 </p> 
		-> <p style="display: inline;">02. 본인 확인 </p> 
		-> <p style="display: inline;">03. 비밀번호 재설정 </p> <br>
		
		<br>
		<c:if test="${not empty errorMessage}">
			<p style="color: red; font-weight: bold;">${errorMessage}</p>
		</c:if>
		<br>
		
		<form action="/ex/find/pwInquiryDB" method="post">
			<div class="find-input-container">
				<label>이름</label> 
				<input type="text" class="find-input" name="u_id" value="${u_id_result}" />
			</div>
			<input type="submit" class="find-button" value="확인">
		</form>
	</div>
	

</body>
</html>