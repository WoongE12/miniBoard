<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
   
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>나의 게시판</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/myBoard.css">
    <script>
    function showSection(sectionId, clickedElement) {
        // 모든 섹션 숨기기
        document.getElementById('myPosts').style.display = 'none';
        document.getElementById('myComments').style.display = 'none';
        
        // 모든 탭 비활성화
        var tabs = document.querySelectorAll('.board-typeItem');
        tabs.forEach(function(tab) {
            tab.classList.remove('active');
        });

        // 선택된 섹션 보이기
        document.getElementById(sectionId).style.display = 'block';
        
        // 클릭된 탭 활성화
        clickedElement.classList.add('active');
    }

    window.onload = function() {
        var defaultTab = document.querySelector('.board-typeItem');
        showSection('myPosts', defaultTab);
    }
    </script>
</head>
<body>
    <%@include file="/WEB-INF/views/header/board_header.jsp"%>
   
    <div id="board">
        <h1>나의 게시판</h1>
        
        <ul class="board-list">
            <li class="board-typeItem" onclick="showSection('myPosts',this)">
                <span class="board-type">작성글</span>
            </li>
            <!-- <li class="board-typeItem" onclick="showSection('myComments',this)">
                <span class="board-type">작성댓글</span>
            </li> -->
        </ul>
        
        <!-- 나의 게시글 목록 -->
        <ul id="myPosts" class="board-list">
            <li class="board-header">
                <span class="board-header-kategorie">카테고리</span>
                <span class="board-header-title">제목</span>
                <span class="board-header-date">작성일</span>
            </li>
            <c:forEach var="post" items="${userPosts}">
                <li class="board-item" onclick="window.location.href='/ex/board/read?b_id=${post.b_id}'">
                    <span class="board-kategorie">${post.b_category}</span>
                    <span class="board-title">${post.b_title}</span>
                    <span class="board-date">
        				<fmt:formatDate value="${post.b_date}" pattern="yyyy-MM-dd"/>
    				</span>
                    
                </li>
            </c:forEach>
            <ul class="button-container">
                <li class="button-write" onclick="window.location.href='/ex/board/create'">
                    <span>글쓰기</span>
                </li>
        	</ul>
        </ul>
        
        <!-- 나의 댓글 목록 -->
        <ul id="myComments" class="board-list" style="display: none;">
            <li class="board-header">
                <span class="board-header-title">댓글</span>
                <span class="board-header-date">작성일</span>
            </li>
            <c:forEach var="comment" items="${userComments}">
                <li class="board-item" onclick="window.location.href='/ex/comment/read?c_id=${comment.c_id}'">
                    <span class="board-title">${comment.c_content}</span>
                    <span class="board-date">${comment.c_date}</span>
                </li>
            </c:forEach>
            <ul class="button-container">
                <li class="button-delete" onclick="window.location.href='/ex/board/'">
                    <span>삭제</span>
                </li>
            </ul>
        </ul>
    </div>
</body>
</html>
