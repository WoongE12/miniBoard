<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>아이디 조회 결과</title>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/find.css">
	
</head>
<body>
	<%@include file="/WEB-INF/views/header/board_header.jsp"%>
	<div id="main">
		<h1>아이디 조회 결과</h1>
		
		<form action="/ex/find/pwInquiry" method="GET">
			<div class="table-container">			
				<table>
	            	<tr> 
	            		<th> </th><th>조회한 결과</th> 
	            	</tr>
					<c:forEach var="UserDto_list" items="${UserDto_list}">
            		<tr> 
            			<td style="background-color: #ddd; padding-right: 10px;">
            				<input type="radio" name="radio" value="${UserDto_list.u_id}"> 
						</td> 
            			<td>아이디 : ${UserDto_list.u_id},  가입 날짜 : ${UserDto_list.u_joinDate}</td> 
            		</tr>
            		</c:forEach>
       			</table>
       		</div>
       		<button type="submit" class="find-button">비밀번호 찾기</button><br>
       	</form>
		
	</div>
</body>
</html>