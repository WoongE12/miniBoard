<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/join.css">
	<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
	<script src="${pageContext.request.contextPath}/resources/js/validationJs.js"></script>
	<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
	<style>
	</style>
   	</head>
<body>
	<%@include file="/WEB-INF/views/header/board_header.jsp"%>
	
	<div id="main">
		<h1>회원가입</h1><br> 
		<h3>정보를 정확히 입력해 주세요</h3><br><br>	
		<form name="signupForm" action="/ex/user/joinDB" method="post" onsubmit="return validateForm()">
			<input type="hidden" name="${_csrf.parameterName }" value="${_csrf.token }"/>
			<br>
			<input type="text" class ="input" id="u_id" name="u_id" oninput="validateField('u_id')" placeholder="아이디를 입력하시오">
			<button type="button" name="button" onclick="id_check()">중복 검사</button><br>
            <span id="u_id-error" class="error-message"></span>
			<div id="idChk"></div>
			
			<div class="password-container">
    			<div class="password-input-wrapper">
        			<input type="password" class="input" name="u_pw" id="u_pw" oninput="validateField('u_pw')" placeholder="비밀번호를 입력하시오">
        			<button type="button" id="togglePw" onclick="togglePassword('u_pw', 'togglePw')">
          				<img src="${pageContext.request.contextPath}/resources/img/open.png" id="toggleImg" alt="보기">
        			</button>
    			</div>
    			<span id="u_pw-error" class="error-message"></span>
			</div>
            <%-- src="${pageContext.request.contextPath}/resources/img/open.png" --%>
            
         	
         	<input type="password" class="input" name="u_pw_check" oninput="validateField('u_pw_check')" placeholder="비밀번호를 한번 더 입력하시오"><br>
         	<span id="u_pw_check-error" class="error-message"></span><br> 
        
			
			<input type="text" class="input" name="u_nickname" oninput="validateField('u_nickname')" placeholder="별명을 입력하시오"><br>
            <span id="u_nickname-error" class="error-message"></span><br>
          
			
			<input type="text" class="input" name="u_name" oninput="validateField('u_name')" placeholder="이름을 입력하시오"><br>
           	<span id="u_name-error" class="error-message"></span><br><br>
           
			
			남자<input type="radio" name="u_gender" class="u_gender" value="남성" oninput="validateField('u_gender')">
          	여자<input type="radio" name="u_gender" class="u_gender" value="여성" oninput="validateField('u_gender')"><br><br>
     		<span id="u_gender-error" class="error-message"></span><br>
     		
	       	
	       	<input type="date" name="u_birthday" oninput="validateField('u_birthday')"><br>
            <span id="u_birthday-error" class="error-message"></span><br>
	      
	       	
	       	<input type="text" class="input" name="email" id="email" oninput="validateField('email')"placeholder="이메일을 입력하시오">
            <label> @ </label>
            <select class="input" name="select" id="select">
                <option value="naver.com">naver.com</option>
                <option value="gmail.com">gmail.com</option>
                <option value="daum.net">daum.net</option>
            </select><br>
            <span id="email-error" class="error-message"></span><br>
            
	      	
	      	<input type="text" class="input" name="phoneNumber1" id="phoneNumber" maxlength="3" placeholder="010"> - 
	      	<input class="input" type="text" name="phoneNumber2" id="phoneNumber" maxlength="4"> - 
	      	<input class="input" type="text" name="phoneNumber3" id="phoneNumber" maxlength="4" oninput="validateField('phoneNumber3')"><br>
            <span id="phoneNumber3-error" class="error-message"></span><br>
             
	       	  
	       	<input type="text" name="u_address" id="u_address" placeholder="주소를 입력하시오" oninput="validateField('u_address')">
			<button type="button" name="button" onclick="sample4_execDaumPostcode()">주소 검색</button><br>
			<span id="u_address-error" class="error-message"></span><br><br><br>
	       	
	       	<input type="submit" class="button-join" value="가입완료">
	       	
			
		</form>
	</div>
	
</body>
</html>