<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>웅이</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/reset.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/header.css">
<style>
	.header {
		background-color: #8EC5FC;
		background-image: linear-gradient(62deg, #8EC5FC 31%, #E0C3FC 100%);
	}
	.header_wrapper {
	    height: 150px;
	}
	
	.login_navi {
 	   	margin-top: 150px; /* header의 height만큼 여백 추가 */
 	   	height: 80px;
 	   	width: 100%;
    	display: flex;
    	align-items: center; /* 수직으로 중앙 정렬 */
    	justify-content: center; /* 수평 중앙 정렬 */
    	background-color: #fefefe; /* 거의 흰색에 가까운 매우 연한 회색 */ 
    	border-bottom: 1px solid #d0d0d0; /* 연한 회색 밑에 선 추가 */
	}
	.login_navi ul {
    	display: flex; /* 수평 정렬을 위해 flex 사용 */
	}

	.login_navi li {
    	margin: 0 20px; /* 각 항목 사이의 간격 */
	}

	.login_navi a {
    	text-decoration: none; /* 링크의 기본 밑줄 제거 */
    	color: #000; /* 기본 링크 색상 설정 */
    	font-weight: normal; /* 기본 글자 굵기 설정 (기본값) */
	}

	.login_navi a:hover {
    	color: #4158D0; /* 마우스를 올렸을 때 링크 색상 변경 */
    	font-weight: bold; /* 마우스를 올렸을 때 글자 굵기 변경 */
	}
</style>
</head>
<body>
	<header class="header">
		<div class="header_wrapper">
			<div class="header_start">
				
			</div>
			<div class="header_center">
				<!-- 로고 -->
				<a href="/ex/">
					<img src="${pageContext.request.contextPath}/resources/img/logo-Photoroom.png" alt="" style="width: 300px; height: auto;">
				</a>
			</div>
			<div class="header_end">
				
			</div>
		</div>
	</header>
	<div class="login_navi">
		<ul>
			<li><a href="/ex/">Home</a></li>
			<li><a href="/ex/user/login">로그인</a></li>
			<li><a href="/ex/user/join">회원가입</a></li>
			<li><a href="/ex/find/inquiry">정보찾기</a></li>
		</ul>
	</div>
</body>
</html>