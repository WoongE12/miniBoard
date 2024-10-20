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
	.find-button {
		width: 400px;
		height: 70px;
		border-radius: 0px; /* 버튼의 모서리를 0px로 둥글지 않게 설정 */
	}
	.find-input-container label {
		width: 145px; /* 레이블 너비 설정 */
	}
	</style>

</head>
<body>
	<%@include file="/WEB-INF/views/header/board_header.jsp"%>
	<div id="main">
		<h1 style="margin-bottom: 20px;">비밀번호를 변경해주세요.</h1>
		
		<p style="display: inline;">01. 아이디 입력 </p> 
		-> <p style="display: inline;">02. 본인 확인 </p> 
		-> <p style="display: inline; color: #054184;">03. 비밀번호 재설정 </p> <br>
		
		<br>
		<br>
		
		<c:if test="${not empty errorMessage}">
			<p style="color: red; font-weight: bold;">${errorMessage}</p>
		</c:if>
		<form action="/ex/find/pwInquiry_upDB" method="post">
			<div class="find-input-container">
        		<label>변경할 비밀번호 입력</label> 
        		<input type="text" class="find-input" name="u_pw" />
        	</div>
        	<div class="find-input-container">
       	 		<label>비밀번호 확인</label> 
       	 		<input type="password" class="find-input" name="u_pw_check" />
       	 	</div>
       	 	<div style="color: red;" id="error-message"></div> <!-- 오류 메시지를 표시할 영역 -->
	        <input type="submit" class="find-button" value="변경하기">
    	</form>
	</div>
	

</body>
</html>