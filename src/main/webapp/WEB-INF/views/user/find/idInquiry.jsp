<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>아이디 찾기</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/find.css">
<script src="${pageContext.request.contextPath}/resources/js/findPwJs.js"></script>
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
		<h1>아이디 찾기</h1>
		<p>아이디 찾기를 위해 이메일이나 휴대폰을 선택해주세요.</p>
		<br>

		<div class="find-box-container">
			<div class="find-box" onclick="selectBox(this)">
				<a href="#" onclick="reg_type_select('email'); return false;"> <img
					src="${pageContext.request.contextPath}/resources/img/free-icon-email.png"
					alt="" style="width: 40px; height: auto;"> 이메일 확인
				</a>
			</div>
			<div class="find-box" onclick="selectBox(this)">
				<a href="#" onclick="reg_type_select('pcs'); return false;"> <img
					src="${pageContext.request.contextPath}/resources/img/free-icon-phone.png"
					alt="" style="width: 40px; height: auto;"> 휴대폰 확인
				</a>
			</div>
		</div>

		<c:if test="${not empty errorMessageEmail}">
			<p style="color: red; font-weight: bold;">${errorMessageEmail}</p>
		</c:if>
		<div class="type_email" style="display: none;">
			<h1 style="margin-top: 30px;">이메일 확인</h1>
			<p>이메일 확인을 선택하셨습니다.</p>
			<p>가입당시 이름과 이메일주소를 입력 후 확인을 눌러주세요.</p>

			<form action="/ex/find/idInquiryDB?type=email" method="post">
				<div class="find-input-container">
					<label>이름</label> 
					<input type="text" class="find-input" name="u_name" value="${UserDto_list.u_name }" /><br>
				</div>
				<div class="find-input-container">
					<label>이메일</label> 
					<input type="text" class="find-input" name="email" style="text-align: right;">
					<label style="width: 10px;">@</label> 
					<select class="find-select" name="select">
						<option value="naver.com">naver.com</option>
						<option value="gmail.com">gmail.com</option>
						<option value="daum.net">daum.net</option>
					</select>
				</div>
				<input type="submit" class="find-button" value="확인">
			</form>
		</div>

		<c:if test="${not empty errorMessagePhone}">
			<p style="color: red; font-weight: bold;">${errorMessagePhone}</p>
		</c:if>
		<div class="type_pcs" style="display: none;">
			<h1 style="margin-top: 30px;">휴대폰 확인</h1>
			<p>휴대폰 확인을 선택하셨습니다.</p>
			<p>가입당시 이름과 휴대폰번호를 입력 후 확인을 눌러주세요.</p>

			<form action="/ex/find/idInquiryDB?type=phone" method="post">
				<div class="find-input-container">
					<label>이름</label> 
					<input type="text" class="find-input" name="u_name" value="${UserDto_list.u_name }" /><br> 
				</div>
				<div class="find-input-container">
					<label>휴대폰 번호</label>
					<input type="text" class="find-input" name="phoneNumber1" style="text-align: right;"> - 
					<input type="text" class="find-input" name="phoneNumber2" style="text-align: center;"> - 
					<input type="text" class="find-input" name="phoneNumber3">
				</div>
					 
				<input type="submit" class="find-button" value="확인">
			</form>
		</div>


	</div>


</body>
</html>