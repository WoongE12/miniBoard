package com.board.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.board.dto.UserDto;
import com.board.service.CertifiedService;
import com.board.service.UserService;

@Controller
public class FindController {
	@Autowired
	private UserService UserService;

	@Autowired
	private CertifiedService service;

	@Autowired
	PasswordEncoder bcryptPasswordEncoder;

	// 아이디 / 비밀번호 찾기 페이지로 이동
	@RequestMapping(value = "/find/inquiry", method = RequestMethod.GET)
	public String Inquiry() throws Exception {
		return "/user/find/inquiry";
			
	}
		
	// 아이디 찾기 이동
	@RequestMapping(value = "/find/idInquiry", method = RequestMethod.GET)
	public String idInquiry() throws Exception {
		return "/user/find/idInquiry";
		
	}
	
	// 아이디 찾기
	@RequestMapping(value = "/find/idInquiryDB", method = RequestMethod.POST)
	public String idInquiryDB(String u_name, Model model, HttpServletRequest request, String type) throws Exception {
		String move = "";
		if (type.equals("email")) {
			String u_email = request.getParameter("email") + "@" + request.getParameter("select");
			List<UserDto> dto = UserService.select_idInquiry_email(u_name, u_email);

			if (dto.isEmpty()) {
				model.addAttribute("errorMessageEmail", "입력하신 이름과 E-mail 주소로 가입된 회원이 없습니다.");
				move = "/user/find/idInquiry";
			} else {
				model.addAttribute("UserDto_list", dto);
				System.out.println(dto);
				move = "/user/find/idInquiry_true";
			}
		} else if (type.equals("phone")) {
			String u_phoneNumber = request.getParameter("phoneNumber1") + "-" + request.getParameter("phoneNumber2")
					+ "-" + request.getParameter("phoneNumber3");
			List<UserDto> dto = UserService.select_idInquiry_phone(u_name, u_phoneNumber);

			if (dto.isEmpty()) {
				model.addAttribute("errorMessagePhone", "입력하신 이름과 전화번호로 가입된 회원이 없습니다.");
				move = "/user/find/idInquiry";
			} else {
				model.addAttribute("UserDto_list", dto);
				System.out.println(dto);
				move = "/user/find/idInquiry_true";
			}
		}
		return move;
	}

	// 비밀번호 찾기 이동
	@RequestMapping(value = "/find/pwInquiry", method = RequestMethod.GET)
	public String pwInquiry(Model model,  @RequestParam(value = "radio", required = false) String u_id) throws Exception {
		// 아이디 찾은 다음에 비밀번호 찾기로 이동시에 필요한 코드로, 찾은 id 저장
		//System.out.println(u_id);
		model.addAttribute("u_id_result", u_id);
		return "/user/find/pwInquiry";
	}

	// 아이디 입력 처리
	@RequestMapping(value = "/find/pwInquiryDB", method = RequestMethod.POST)
	public String pwInquiryDB(Model model,String u_id, HttpSession session) throws Exception {
		String move = "";
		UserDto dto = UserService.selectUser(u_id);

		if (dto == null) {
			model.addAttribute("errorMessage", "입력하신 아이디는 없는 아이디입니다.");
			move = "/user/find/pwInquiry";
		} else {
			move = "redirect:/find/pwInquiry_check";
			session.setAttribute("u_id_Find", u_id);
		}
		return move;
	}

	// 본인확인 이동
	@RequestMapping(value = "/find/pwInquiry_check", method = RequestMethod.GET)
	public String pwInquiry_ckeck() throws Exception {
		return "/user/find/pwInquiry_check";
	}

	// 본인 확인 (이메일 처리)
	@PostMapping("/find/pwInquiry_email")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> findPw_email(@RequestParam String email, @RequestParam String select,
			HttpSession session) {
		// 인증번호 생성
		Random rand = new Random();

		String numStr = "";
		for (int i = 0; i < 6; i++) {
			String ran = Integer.toString(rand.nextInt(10));
			numStr += ran;
		}
		System.out.println("인증 번호 : " + numStr);
		 
		String u_id = (String) session.getAttribute("u_id_Find");
		String u_email = email + "@" + select;

		boolean email_check = UserService.email_compare(u_id, u_email);

		Map<String, Object> response = new HashMap<>();

		if (email_check) {
			service.certifiedEmail(u_email, numStr);
			response.put("success", true);

		} else {
			response.put("success", false);
		}

		session.setAttribute("numStr", numStr);
		return ResponseEntity.ok(response);
	}

	// 본인 확인 (전화번호 처리)
	@PostMapping("/find/pwInquiry_phone")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> findPw_phone(@RequestParam String phoneNum1,
			@RequestParam String phoneNum2, @RequestParam String phoneNum3, HttpSession session) {
		// 인증번호 생성
		Random rand = new Random();

		String numStr = "";
		for (int i = 0; i < 6; i++) {
			String ran = Integer.toString(rand.nextInt(10));
			numStr += ran;
		}
		System.out.println("인증 번호 : " + numStr);
		
		String u_id = (String) session.getAttribute("u_id_Find");
		String u_phoneNumber = phoneNum1 + "-" + phoneNum2 + "-" + phoneNum3;

		boolean phoneNumber_Check = UserService.phoneNumber_compare(u_id, u_phoneNumber);

		Map<String, Object> response = new HashMap<>();

		if (phoneNumber_Check) {
			service.certifiedPhoneNumber(u_phoneNumber, numStr);
			response.put("success", true);
			session.setAttribute("numStr", numStr);
		} else {
			response.put("success", false);
		}
		System.out.println(u_phoneNumber);
		return ResponseEntity.ok(response);
	}
	
	// 본인 인증 인증번호 입력 처리
	@PostMapping("/find/pwInquiry_check_complete")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> check_number(@RequestParam String check_num, HttpSession session) {
		String numStr = (String) session.getAttribute("numStr");
		
		Map<String,Object> response = new HashMap<>();
		
		if (numStr.equals(check_num)) {
			response.put("success", true);
			session.removeAttribute("numStr");
		} else {
			response.put("success", false);
		}

		return ResponseEntity.ok(response);
	}

	// 비밀번호 재설정 이동
	@RequestMapping(value = "/find/pwInquiry_up", method = RequestMethod.GET)
	public String pwInquiry_up() throws Exception {
		return "/user/find/pwInquiry_up";

	}

	// 비밀번호 재설정 처리
	@RequestMapping(value = "/find/pwInquiry_upDB", method = RequestMethod.POST)
	public String pwInquiry_upDB(String u_pw, HttpSession session) throws Exception {
		String u_id = (String) session.getAttribute("u_id_Find");

		String encPassword = bcryptPasswordEncoder.encode(u_pw);

		UserService.pw_update(u_id, encPassword);
		
		System.out.println("u_id : " + u_id);
		System.out.println("u_pw : " + encPassword);
		
		session.removeAttribute("u_id_Find");
		session.setAttribute("action", "pw_update");
		return "/user/login";
	}
}
