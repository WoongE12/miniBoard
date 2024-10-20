<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/css/find.css">
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

		<h1>로그인</h1>
		<p>회원님의 아이디와 비밀번호를 입력해 주세요.</p>
		<br>

		<c:if test="${param.error != null}">
			<p>아이디와 비밀번호가 잘못되었습니다.</p>
		</c:if>
		<c:if test="${param.logout != null}">
			<p>로그아웃 하였습니다.</p>
		</c:if>

		<c:url value="/login" var="loginUrl" />
		<form name="frmLogin" action="${loginUrl}" method="POST">
			<input type="hidden" name="${_csrf.parameterName}"
				value="${_csrf.token }" />
			<div class="find-input-container">
				<label style="display: block; text-align: center;">ID</label> <input type="text" class="find-input" name="u_id"
					value="${ids}" />
			</div>
			<div class="find-input-container">
				<label style="display: block; text-align: center;">PW</label> <input type="password" class="find-input"
					name="u_pw" value="${pws}" />
			</div>
			<%-- <div class="find-checkbox-container">
				<div class="find-checkbox-container1">
					<input type="checkbox" id="id_save" name="id_save" value="id_save"
						${id_save_Checked}> <label for="id_save">아이디 저장</label>
				</div>
				<div class="find-checkbox-container2">
					<input type="checkbox" id="auto_login" name="auto_login"
						value="auto_login" ${auto_login_Checked}> <label
						for="auto_login">자동 로그인</label>
				</div>
			</div> --%>

			<button type="submit" class="find-button">LOGIN</button>
			<br>
			<div class="inquiry">
				<ul>
					<li><a href="/ex/find/idInquiry">아이디 찾기</a></li>
					<li><a href="/ex/find/pwInquiry">비밀번호 찾기</a></li>
				</ul>
			</div>
		</form>

	</div>
</body>
</html>