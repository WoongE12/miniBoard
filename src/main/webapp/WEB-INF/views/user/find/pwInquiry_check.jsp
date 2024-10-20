<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>비밀번호 찾기</title>
	<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
	<script src="${pageContext.request.contextPath}/resources/js/findPwJs.js"></script>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/find.css">
	

</head>
<body>
	<%@include file="/WEB-INF/views/header/board_header.jsp"%>
	<div id="main">
		<h1 style="margin-bottom: 20px;">본인확인을 해주세요.</h1>
		
		<p style="display: inline;">01. 아이디 입력 </p> 
		-> <p style="display: inline; color: #054184;">02. 본인 확인 </p> 
		-> <p style="display: inline;">03. 비밀번호 재설정 </p> <br>
		
		<br>
		<br>
		<p class="check_info" style="font-weight: bold;"></p>
		<div id="contents"> 
		
			<div class="find-box-container">
				<div class="find-box" onclick="selectBox(this)">
					<a href="#" onclick="reg_type_select('email'); return false;"> 
						<img src="${pageContext.request.contextPath}/resources/img/free-icon-email.png"
						alt="" style="width: 40px; height: auto;"> 이메일 확인
					</a>
				</div>
				<div class="find-box" onclick="selectBox(this)">
					<a href="#" onclick="reg_type_select('pcs'); return false;"> 
						<img src="${pageContext.request.contextPath}/resources/img/free-icon-phone.png"
						alt="" style="width: 40px; height: auto;"> 휴대폰 확인
					</a>
				</div>
			</div>
		
			
			<div class="type_email" style="display: none;">
				<h1 style="margin-top: 30px;">이메일 인증</h1>
				<p>이메일 인증을 선택하셨습니다.</p> 
				<p>등록하신 이메일주소를 입력 후 인증번호 전송버튼을 눌러주세요.</p>
				
				<div class="find-container" style="display: flex; justify-content: center; align-items: center;">
					<div class="find-input-container" style="margin: 20px 5px;">
						<label>이메일</label> 
						<input type="text" class="email1 find-input" name="email" style="text-align: right;">
						<label style="width: 10px;">@</label> 
						<select class="email2 find-input" name="select">
							<option value="naver.com">naver.com</option>
							<option value="gmail.com">gmail.com</option>
							<option value="daum.net">daum.net</option>
						</select>
					</div>
					<div class="find-button-container" >
						<input class="email_btn find-certification-button" type="button" value="인증번호 전송">
					</div>
				</div>
				
				<form action="/ex/find/pwInquiry_check_complete" method="post">
					<div class="find-input-container"  style="width: 525px;">
						<label>인증번호</label> 
						<input class="check_number find-input" type="text">   <!-- 인증번호 입력창 -->
					</div>
					<input type="button" class="check_btn find-button" value="인증번호 확인" placeholder="인증번호 6자리를 입력해주세요." maxlength="6">  <!-- 인증번호와 내가 입력창에 입력한 인증번호 비교하는 창 -->
				</form>		
			</div>
			
			<div class="type_pcs" style="display:none;">
				<h1 style="margin-top: 30px;">휴대폰 인증</h1>
				<p>휴대폰 인증을 선택하셨습니다.</p>
				<p>등록하신 휴대폰번호를 입력 후 인증번호 전송버튼을 눌러주세요.</p>
				
				<div class="find-container" style="display: flex; justify-content: center; align-items: center;">
					<div class="find-input-container" style="margin: 20px 5px;">
						<label>휴대폰 번호</label>
						<input type="text" class="phoneNum1 find-input" style="text-align: right;"> - 
						<input type="text" class="phoneNum2 find-input" style="text-align: center;"> - 
						<input type="text" class="phoneNum3 find-input" >
					</div>  <!-- 인증번호 받을사람 휴대폰 번호 -->
					<div class="find-button-container" >
						<input class="phone_btn find-certification-button" type="button" value="인증번호 전송" >
					</div>
				</div>
				
				<form action="/ex/find/pwInquiry_check_complete" method="post">
					<div class="find-input-container" style="width: 525px;">
						<label>인증번호</label> 
						<input class="check_number find-input" type="text">   <!-- 인증번호 입력창 -->
					</div>
					<input type="button" class="check_btn find-button" value="인증번호 확인" placeholder="인증번호 6자리를 입력해주세요." maxlength="6">  <!-- 인증번호와 내가 입력창에 입력한 인증번호 비교하는 창 -->
			</form>		
			</div>
    	</div>
	</div>
</body>
	
</html>