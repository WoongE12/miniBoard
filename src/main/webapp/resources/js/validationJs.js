    function id_check() {
        $.ajax({
            url: "/ex/user/id_duplicate_check",
            type: "POST",
            contentType: "application/x-www-form-urlencoded; charset=UTF-8",  // 폼 데이터 형식
            data: { "u_id": $("#u_id").val() },  // 쿼리 파라미터 형식으로 데이터 전송
            success: function(data) {
                // 문자열 "true" 또는 "false"를 반환 받음
                if (data === "true") {
                    //alert("중복된 아이디입니다.");
                    $("#idChk").text("중복된 아이디입니다.").css("color", "red");  // idChk div에 텍스트 추가
                } else {
                    //alert("사용 가능한 아이디입니다.");
                    $("#idChk").text("사용 가능한 아이디입니다.").css("color", "green");  // idChk div에 텍스트 추가
                }
            },
            error: function() {
                alert("서버 오류 발생");
            }
        });
    }

//주소검색
function sample4_execDaumPostcode() {
	new daum.Postcode({
		oncomplete : function(data) {
			// 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

			// 도로명 주소의 노출 규칙에 따라 주소를 표시한다.
			// 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
			var roadAddr = data.roadAddress; // 도로명 주소 변수
			var extraRoadAddr = ''; // 참고 항목 변수

			// 법정동명이 있을 경우 추가한다. (법정리는 제외)
			// 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
			if (data.bname !== '' && /[동|로|가]$/g.test(data.bname)) {
				extraRoadAddr += data.bname;
			}
			// 주소 정보를 해당 필드에 넣는다.
			document.getElementById("u_address").value = roadAddr;

		}
	}).open();
}

// 비밀번호 가리기/보기 기능
function togglePassword(fieldId, buttonId) {
    const passwordField = document.getElementById(fieldId);
    const toggleImg = document.getElementById('toggleImg');
    /*const contextPath = '${pageContext.request.contextPath}';*/
    
    if (passwordField.type === "password") {
        passwordField.type = "text";
        toggleImg.src = contextPath + "/resources/img/close.png"; // 비밀번호가 보일 때 이미지
        toggleImg.alt = "가리기";
    } else {
        passwordField.type = "password";
        toggleImg.src = contextPath + "/resources/img/open.png"; // 비밀번호가 가려질 때 이미지
        toggleImg.alt = "보기";
    }
}

function validateField(fieldName) {
	let isValid = true;

	// 필드와 값 가져오기
	const field = document.forms["signupForm"][fieldName];
	const value = field.value;
	const errorSpan = document.getElementById(fieldName + '-error');

	// 패턴 정의
	const idPattern = /^[a-zA-Z0-9]{6,20}$/;
	const pwPattern = /^(?=.*[0-9])(?=.*[a-zA-Z])(?=.*[\W_]).{8,20}$/;
	const nicknamePattern = /^[ㄱ-ㅎ가-힣a-zA-Z0-9-_]{2,10}$/;

	// 필드 이름에 따라 필드 값 검사
	if (fieldName === 'u_id') {
		if (!idPattern.test(value)) {
			errorSpan.textContent = "아이디는 6~20자의 영문 대 소문자 및 숫자로만 이루어져야 합니다.";
			isValid = false;
		} else {
			errorSpan.textContent = '';
		}
	}

	if (fieldName === 'u_pw') {
		if (!pwPattern.test(value)) {
			errorSpan.textContent = "비밀번호는 8~20자의 영문, 숫자, 특수문자를 사용해야 합니다.";
			isValid = false;
		} else {
			errorSpan.textContent = '';
		}
	}

	if (fieldName === 'u_pw_check') {
		const pw = document.forms["signupForm"]["u_pw"].value;
		if (value !== pw) {
			errorSpan.textContent = "비밀번호와 비밀번호 확인이 일치하지 않습니다.";
			errorSpan.classList.remove('correct-message');
			errorSpan.classList.add('error-message');
			isValid = false;
		} else {
			errorSpan.textContent = "비밀번호가 일치합니다"
			errorSpan.classList.remove('error-message');
			errorSpan.classList.add('correct-message');
		}
	}

	if (fieldName === 'u_nickname') {
		if (!nicknamePattern.test(value)) {
			errorSpan.textContent = "별명은 2~10자의 특수문자를 제외한 영문, 숫자, 한글로 이루어져야 합니다.";
			isValid = false;
		} else {
			errorSpan.textContent = '';
		}
	}

	if (fieldName === 'u_name') {
		if (value === '') {
			errorSpan.textContent = '이름을 입력해 주세요.';
			isValid = false;
		} else {
			errorSpan.textContent = '';
		}
	}

	if (fieldName === 'u_gender') {
		if (value === '') {
			errorSpan.textContent = '성별을 선택해 주세요.';
			isValid = false;
		} else {
			errorSpan.textContent = '';
		}
	}
	if (fieldName === 'u_birthday') {
		if (value === '') {
			errorSpan.textContent = ' 생년월일을 선택해 주세요.';
			isValid = false;
		} else {
			errorSpan.textContent = '';
		}
	}

	if (fieldName === 'email') {
		if (value === '') {
			errorSpan.textContent = ' 이메일을 입력해 주세요.';
			isValid = false;
		} else {
			errorSpan.textContent = '';
		}
	}

	if (fieldName === 'phoneNumber3') {
		if (value === '') {
			errorSpan.textContent = ' 전화번호를 입력해 주세요.';
			isValid = false;
		} else {
			errorSpan.textContent = '';
		}
	}

	if (fieldName === 'u_address') {
		if (value === '') {
			errorSpan.textContent = ' 주소을 입력해 주세요.';
			isValid = false;
		} else {
			errorSpan.textContent = '';
		}
	}
	return isValid;
}

function validateForm() {
	let isValid = true;

	// 각 필드 검사
	const fields = [ 'u_id', 'u_pw', 'u_pw_check', 'u_nickname', 'u_name',
			'u_gender', 'u_birthday', 'email', 'phoneNumber3', 'u_address' ];
	fields.forEach(function(fieldName) {
		if (!validateField(fieldName)) {
			isValid = false;
		}
	});

	return isValid;
}