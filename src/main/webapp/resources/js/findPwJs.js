function selectBox(box) {
	// 기존에 선택된 박스가 있으면 선택 해제
	const selectedBox = document.querySelector('.find-box.selected');
	if (selectedBox) {
		selectedBox.classList.remove('selected');
	}

	// 클릭된 박스를 선택 상태로 변경
	box.classList.add('selected');
}

function reg_type_select(type) {
	// 모든 섹션을 숨기기
	document.querySelector('.type_email').style.display = 'none';
	document.querySelector('.type_pcs').style.display = 'none';

	// 선택된 타입에 따라 해당 섹션을 표시
	if (type === 'email') {
		document.querySelector('.type_email').style.display = 'block';
	} else if (type === 'pcs') {
		document.querySelector('.type_pcs').style.display = 'block';
	}
}

// 이메일, 전화번호로 비밀번호 찾기
$(document).ready(function() {
	$('.email_btn').click(function() {
		$.ajax({
			url : "/ex/find/pwInquiry_email",
			type : "POST",
			data : {
				email : $('.email1').val(),
				select : $('.email2').val()
			},
			success : function(response) {
				var info = $('.check_info');
				console.log(info);
				if (response.success) {
					info.text("이메일로 인증번호가 발송되었습니다.");
					info.css("color", "green");
				} else {
					info.text("입력하신 정보와 이메일이 일치하지 않습니다.");
					info.css("color", "red");
				}
			}
		})
	});	
	
	$('.phone_btn').click(function() {
		$.ajax({
			url : "/ex/find/pwInquiry_phone",
			type : "POST",
			data : {
				phoneNum1 : $('.phoneNum1').val(),
				phoneNum2 : $('.phoneNum2').val(),
				phoneNum3 : $('.phoneNum3').val()
			},
			success : function(response) {
				var info = $('.check_info');
				console.log(info);
				if (response.success) {
					info.text("전화번호로 인증번호가 발송되었습니다.");
					info.css("color", "green");
				} else {
					info.text("입력하신 정보와 전화번호가 일치하지 않습니다.");
					info.css("color", "red");
				}
			}
		})
	});
	
	$('.check_btn').click(function() {
		$.ajax({
			url : "/ex/find/pwInquiry_check_complete",
			type : "POST",
			data : {
				check_num : $('.check_number').val(),
			},
			success : function(response) {
				var info = $('.check_info');
				if (!response.success) {
					info.text("인증번호를 다시 확인해주세요.");
					info.css("color", "red");
				} else {
					 window.location.href = "/ex/find/pwInquiry_up";
				}
			}
		})
	});
});
