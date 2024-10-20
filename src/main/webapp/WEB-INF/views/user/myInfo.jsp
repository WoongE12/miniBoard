<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>계정 관리</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/reset.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/myInfo.css">
	<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
	<script src="${pageContext.request.contextPath}/resources/js/myInfo.js"></script>
</head>
<style>
</style>
<body>
	<%@include file="/WEB-INF/views/header/board_header.jsp"%>
	<div id="main">
		<div id="left_main">
			<div class="sidebar_menu">		
				<c:choose>
    				<c:when test="${not empty sessionScope.dto.u_profile_img}">
    					<div class="menu_user_container">
        					<img class="menu_user" src="${pageContext.request.contextPath}/resources/profile_img/${sessionScope.dto.u_profile_img}" 
        					onclick="reg_type_select('img'); return false;" id="profile_img"  >
        					<img class="menu_user_change" src="${pageContext.request.contextPath}/resources/img/Bsetup.png"
        					onclick="reg_type_select('img'); return false;" id="profile_img">
        				</div>
    				</c:when>
    				<c:otherwise>
    					<div class="menu_user_container">
        					<img class="menu_user" src="${pageContext.request.contextPath}/resources/img/user.png" onclick="reg_type_select('img'); return false;" id="profile_img">
        					<img class="menu_user_change" src="${pageContext.request.contextPath}/resources/img/Bsetup.png"
        					onclick="reg_type_select('img'); return false;" id="profile_img">
        				</div>
    				</c:otherwise>
				</c:choose>
				<div class="all_info">
					<p class="user_info">${dto.u_nickname}</p>
					<p class="email_info">${dto.u_email}</p>
				</div>
				<div class="menu_left">
					<ul>
						<li><a href="#" onclick="reg_type_select('1'); return false;" id="profile-link">내 프로필</a></li>
        				<li><a href="#" onclick="reg_type_select('2'); return false;" id="info-link">회원 정보 수정</a></li>
        				<li><a href="#" onclick="reg_type_select('3'); return false;" id="password-link">비밀번호 변경</a></li>
        				<li><a href="#" onclick="reg_type_select('4'); return false;" id="cancel-account">회원 탈퇴</a></li>
					</ul>
				</div>
				
			</div>
			
		</div>
		<div id="right_main">
			<div class="right_menu">
				
				<div class="my_info type_1">
					<h2 class="profile-title">내 프로필</h2>
					<p><img src="${pageContext.request.contextPath}/resources/img/user.png">${dto.u_name}</p>
					<p><img src="${pageContext.request.contextPath}/resources/img/free-icon-phone.png">${dto.u_phoneNumber}</p>
					<p><img src="${pageContext.request.contextPath}/resources/img/free-icon-email.png">${dto.u_email}</p>
					<p><img src="${pageContext.request.contextPath}/resources/img/cake.png">${dto.u_birthday}</p>
				</div>
				<div class="my_info2 type_2" style="display: none;" >
					<h2 class="profile-title">회원 정보 수정</h2>
					<form action="/ex/user/myInfo/menber_upDB" method="post">
						<div class="my_info-container">
							<label>아이디</label> 
							<input type="text" class="input-text" name="u_id" value="${dto.u_id}" readonly />
						</div>
						<div class="my_info-container">
							<label>닉네임</label> 
							<input type="text" class="input-text" name="u_nickname" placeholder="별명을 변경하시오" value="${dto.u_nickname}" />
						</div>
						<div class="my_info-container">
            				<label>이름</label> 
							<input type="text" class="input-text" name="u_name" placeholder="이름을 변경하시오" value="${dto.u_name}" />
						</div>
						
						<div class="my_info-container">
							<label>성별</label> 
							<label>남자</label><input type="radio" name="u_gender" value="남성" ${dto.u_gender.equals("남성") ? "checked" : ""}>
          					<label>여자</label><input type="radio" name="u_gender" value="여성" ${dto.u_gender.equals("여성") ? "checked" : ""}>
     					</div>
     					
     					<div class="my_info-container">
	       					<label>생일</label> 
	       					<input type="date" class="input-date" name="u_birthday" value="${dto.u_birthday}"><br>
	       				</div>

						<div class="my_info-container">
							<label>이메일</label> 	      
	       					<input type="text" class="input-text-email" name="email"  placeholder="이메일을 입력하시오" value="${u_emailArr[0] }">
            				<label style="font-weight: bold;"> @ </label>
            				<select class="email-select" name="select" ${u_emailArr[2] }>
                				<option value="naver.com">naver.com</option>
                				<option value="gmail.com">gmail.com</option>
                				<option value="daum.net">daum.net</option>
            				</select><br>
            			</div>
            			<div class="my_info-container">
            				<label>전화번호</label> 
				      		<input type="text" class="input-phone1" name="phoneNumber1" maxlength="3" placeholder="010" value="${u_phoneNumberArr[0] }">
				      		<label style="font-weight: bold;"> - </label> 
	      					<input type="text" class="input-phone2" name="phoneNumber2" maxlength="4" value="${u_phoneNumberArr[1] }">
	      					<label style="font-weight: bold;"> - </label> 
	      					<input type="text" class="input-phone3" name="phoneNumber3" maxlength="4" value="${u_phoneNumberArr[2] }"><br>
            			</div>
            			
            			<div style="display: flex;">
    	        			<div class="my_info-container" style="width: 550px;">
	            				<label>주소</label> 
	       						<input type="text" class="input-text-address" name="u_address" placeholder="주소를 입력하시오" value="${dto.u_address }" id="u_address">
							</div>
							<button type="button" class="my_info-container-button" onclick="sample4_execDaumPostcode()">주소 검색</button><br>
						</div>
						
						<div class="my_info-container">
							<label>가입날짜</label>
							<label class="join-date">${dto.u_joinDate }</label>
	       				</div>
	       				
	        			<input type="submit" class="myinfo-button" value="회원정보 변경하기">
    				</form>
					
				</div>
				<div class="my_info type_3" style="display: none;">
					<h2 class="profile-title">비밀번호 변경</h2>
					<p>안전한 비밀번호를 만들고 같은 비밀번호를 다른 계정에 사용하지 마세요.</p>
					<form name="passwordForm" action="/ex/user/myInfo/pw_upDB" method="post" onsubmit="return validateForm()">
						<div class="password-container">
        					<input type="password" class="myinfo-input" name="u_pw" id="u_pw" oninput="validateField('u_pw')" placeholder="변경할 비밀번호 입력하시오"/>
        					<button type="button" id="togglePw" onclick="togglePassword('u_pw', 'togglePw')">
           						<img src="${pageContext.request.contextPath}/resources/img/open.png" id="toggleImg" alt="보기">
        					</button>        					 	
        				</div>   
        				<span id="u_pw-error" class="error-message"></span><br>   
        								
       	 				<input type="password" class="myinfo-input2" name="u_pw_check" oninput="validateField('u_pw_check')" placeholder="비밀번호를 한번 더 입력하시오" />
       	 				<span id="u_pw_check-error" class="error-message"></span> 	 			
       	 				
	        			<input type="submit" class="myinfo-button" value="변경하기">
    				</form>
				</div>
				<div class="my_info2 type_4" style="display: none; height: 550px;">
					<h2 class="profile-title">회원 탈퇴</h2>
					<div class="withdrawal-container">						
              			<h2>회원 탈퇴 안내</h2>
    					<p>board를 이용해 주셔서 감사합니다. 회원 탈퇴 시 다음과 같은 사항이 적용됩니다:</p>
    					<p>현재 사용 중인 아이디(<label>${dto.u_id}</label>)는 탈퇴 후 복구할 수 없으며, 재사용도 불가능합니다.</p>
    					<ul>
        					<li>회원 정보 삭제 : 모든 개인 정보와 데이터가 영구적으로 삭제됩니다.<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        					삭제된 정보는 복구할 수 없습니다.</li>
        					<li>서비스 이용 중지 : 탈퇴 후 board의 대부분의 서비스에 접근할 수 없습니다.<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
        					재이용 시 새롭게 회원가입을 해야 합니다.</li>
        					<li>탈퇴 후 취소 불가 : 탈퇴 완료 후에는 취소할 수 없습니다. 신중하게 결정해 주세요.</li>
    					</ul>
    					<p>이용해 주셔서 감사드리며, 나중에 다시 찾아뵙기를 바랍니다.<br> 문의 사항이 있으면 언제든지 연락해 주세요.</p>
    					<p>감사합니다.</p>
					</div>

					<div class="myinfo-checkbox-container">
						<label for="cancel-account-check">회원 탈퇴하는 것에 동의합니다.</label>
						<input type="checkbox" id="cancel-account-check" name="cancel-account-check">
					</div>
					<button type="button" class="myinfo-button" id="delete-account-btn" onclick="confirmDelete()" disabled>회원 탈퇴</button>
				</div>
				<div class="my_info type_img" style="display: none; height: 300px;">
					<h2 class="profile-title">프로필 이미지 수정</h2>
					<div class="card">						
 			        	<form  action="/ex/user/myInfo/updateImg" method="post" enctype="multipart/form-data">
            				<label class="file-label" for="file-input">파일 선택</label>
    						<input type="file" id="file-input" class="file-input" name="file" onchange="updateFileName()">
    						<span class="file-name" id="file-name">파일이 선택되지 않았습니다.</span>
            				<input type="submit" class="myinfo-button" value="프로필 사진변경">
        				</form>
					</div>
				</div>
			</div>
		</div>
	</div>
<body>
<script>

</script>
</html>