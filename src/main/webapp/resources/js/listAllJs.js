	$(document).ready(
			function() {
				// 등록 성공 알림창
				var result = '${msg}';
				if (result === 'success') {
			        alert("등록이 완료되었습니다.");
				} else if (result === 'update_success') {
				    alert("수정이 완료되었습니다.");
			    } else if (result === 'delete_success') {
			        alert("삭제가 완료되었습니다.");
			    } else if (result === 'login_required') {
			        alert("로그인이 필요합니다.");
			    }

				// 카테고리별 조회? 버튼
				$('.cButton').on(
						"click",
						function() {
							var category = $(this).data('category');
							var url = "listAll?bCategory="
									+ encodeURIComponent(category);
							window.location.href = url;
						});

				// 검색하기 버튼
				$('#searchBtn')
						.on(
								"click",
								function(event) {
									var searchType = $(
											"select[name='searchType']").val();
									var keyword = $('#keywordInput').val();
									var url = "listAll?bCategory="
											+ encodeURIComponent(searchType)
											+ "&keyword="
											+ encodeURIComponent(keyword);
									window.location.href = url;
								});
				$(".csButton").click(function() {
			        window.location.href = "http://localhost:8081/ex/cs/main";
			    });

				// 글쓰기 버튼
				$('.writeBtn').on("click", function(event) {
					location.href = "/ex/board/create";
				});
			});
