/**
 * 
 */
var form = document.updateForm;
// 생년월일 월 선택

var monthForm = form.month;
monthForm.value = monthVal.value;

// 이메일 도메인 선택

var emailForm = form.select;
emailForm.value = emailVal.value;

function confirmDelete() {
	// 확인 및 취소 버튼이 있는 메시지 표시
	var confirmed = confirm("정말로 회원 탈퇴하시겠습니까? 회원탈퇴 취소");

	// 사용자가 '확인'을 클릭한 경우
	if (confirmed) {
		// 페이지 이동
		location.href = '/ex/user/deleteDB';
	}
	// 사용자가 '취소'를 클릭하면 아무 작업도 하지 않음
}