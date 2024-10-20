$(document).ready(function() {
    reloadComm();

    $("#commentAddBtn").click(function() {
        console.log($("#newCommentText").val());
        console.log($("input[name='b_id']").val());

        $.ajax({
            url: "/ex/comments/create",
            type: "post",
            contentType: "application/json",
            data: JSON.stringify({
                comm_content: $("#newCommentText").val(),
                b_id: $("input[name='b_id']").val(),
                u_id: "로그인한 사용자 ID", // 실제 로그인한 사용자 ID로 바꿔주세요
                comm_date : new Date().toISOString(),
                parent_id: null // 부모 댓글 ID가 없다면 null로 설정
            }),
            success: function(res) {
                if (res === "SUCCESS") {
                    reloadComm();
                } else {
                    alert("통신 실패");
                }
            }
        });
    });

    function reloadComm() {
        var commArea = $("#comments");
        console.log(commArea);
        commArea.empty();

        $.ajax({
            url: "/ex/comments/listAll",
            type: "get",
            data: { b_id: $("input[name='b_id']").val() },
            success: function(response) {
                response.forEach(function(res) {
                    console.log(res);
                    if (res.parent_id === null) {
                        commArea.append(
                            "<div id='c_" + res.comm_id + "' class='comment'>" +		// 부모 댓글 전체 영역
                            "<p>" + res.u_id + "</p>" +									// 아이디
                            "<p>" + res.comm_date+ "</p>" +								// 작성일차
                            "<p class='comment-content'>" + res.comm_content + "</p>" +	// 내용
                            "<button class='edit-btn' data-id='" + res.comm_id + "'>수정</button>" +
                            "<button class='del-btn' data-id='" + res.comm_id + "'>삭제</button>" +
                            "<button class='reply-btn' data-id='" + res.comm_id + "'>답글</button>" +
                            "<div class='reply_area' id='reply-section" + res.comm_id + "' style='display:none;'>" + // 답글 입력 영역
                            "<textarea cols='30' rows='5' data-parent-id='" + res.comm_id + "'></textarea>" +		// 답글 인풋창
                            "<button class='reply_btn' data-parent-id='" + res.comm_id + "'>등록</button>" +			// 답글 등록 버튼
                            "</div>" +
                            "<div id='reply_" + res.comm_id + "'></div>" +											// 부모 댓글의 답글 영역 
                            "</div><br>"
                        );
                    } else {
                        $("#reply_" + res.parent_id).append(
                            "<div id='c_" + res.comm_id + "' class='reply'>" +										// 자식 댓글 아이디
                            "<p>" + res.u_id + "</p>" +																// 자식 댓글 작성자 아이디
                            "<p>" + res.comm_date+ "</p>" +															// 날짜
                            "<p class='reply-content'>" + res.comm_content + "</p>" +								// 내용
                            "<button class='edit-btn' data-id='" + res.comm_id + "'>수정</button>" +
                            "<button class='del-btn' data-id='" + res.comm_id + "'>삭제</button>" +
                            "</div><br>"
                        );
                    }
                });
            }
        });
    }

    // 댓글 및 답글 수정 버튼 클릭 이벤트
    $(document).on("click", ".edit-btn", function() {
        var commId = $(this).data("id");
        var currentContent = $(this).siblings(".comment-content").text().trim();

        var newContent = prompt('수정할 내용을 입력해주세요.', currentContent);

        if (newContent !== null && newContent.trim() !== "") {
            $.ajax({
                url: "/ex/comments/update",
                type: "POST",
                contentType: "application/json",
                data: JSON.stringify({
                    comm_id: commId,
                    comm_content: newContent,
                    b_id: $("input[name='b_id']").val(),
                    u_id: "로그인한 사용자 ID" // 실제 로그인한 사용자 ID로 바꿔주세요
                }),
                success: function(response) {
                    if (response === "SUCCESS") {
                        reloadComm(); // 댓글 목록 새로 고침
                    } else {
                        alert("댓글 수정 실패");
                    }
                },
                error: function(xhr) {
                    alert("댓글 수정에 실패했습니다. " + xhr.responseText);
                }
            });
        }
    });
    
    $(document).on("click", ".del-btn", function() {
        var commId = $(this).data("id");
        console.log(commId);

        var del_check = confirm('삭제하시겠습니까 ?');

        if (del_check) {
            $.ajax({
                url: "/ex/comments/delete",
                type: "POST",
                data: {
                		comm_id : commId
                		},
                success: function(response) {
                    if (response === "SUCCESS") {
                        reloadComm(); // 댓글 목록 새로 고침
                    } else {
                        alert("댓글 삭제 실패");
                    }
                },
                error: function(xhr) {
                    alert("댓글 삭제에 실패했습니다. " + xhr.responseText);
                }
            });
        }
    });

    // 이벤트 위임을 위한 .on() 사용
    $(document).on("click", ".reply-btn", function() {
        console.log('이벤트 클릭됨');
        $("#reply-section" + $(this).data("id")).toggle();
    });

    $(document).on("click", ".reply_btn", function() {
        var comment = $(this).siblings('textarea').val(); // textarea의 값을 가져옴
        var parentCommentId = $(this).data("parent-id");

        console.log(comment);
        console.log(parentCommentId);

        $.ajax({
            url: "/ex/comments/reCreate",
            type: "POST",
            contentType: "application/json",
            data: JSON.stringify({
                comm_content: comment,
                b_id: $("input[name='b_id']").val(),
                u_id: "로그인한 사용자 ID", // 실제 로그인한 사용자 ID로 바꿔주세요
                comm_date : new Date().toISOString(),
                parent_id: parentCommentId
            }),
            success: function(response) {
                if (response === "SUCCESS") {
                    reloadComm(); // 댓글 목록 새로 고침
                } else {
                    alert("답글 등록 실패");
                }
            },
            error: function(xhr) {
                alert("답글 등록에 실패했습니다. " + xhr.responseText);
            }
        });
    });
});
