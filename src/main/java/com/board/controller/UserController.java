package com.board.controller;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.board.dto.AuthoritiesDto;
import com.board.dto.BoardDto;
import com.board.dto.UserDto;
import com.board.service.AuthoritiesService;
import com.board.service.BoardService;
import com.board.service.UserService;
import com.board.util.fileUtil;

@Controller
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private AuthoritiesService authoritiesService; 
	
	@Autowired
    PasswordEncoder bcryptPasswordEncoder;
	
	@Autowired
	private BoardService service;
	
	// 유저 로그인
	@RequestMapping(value = "/user/login", method = RequestMethod.GET)
	public String login(HttpSession session) throws Exception{
		
		String auto_login_Checked = (String) session.getAttribute("auto_login_Checked");
		
		if (auto_login_Checked != null && auto_login_Checked.equals("checked")) {
			System.out.println("자동 로그인이 체크되어있어서, 자동 로그인이 되었습니다.");
			return "/user/index";
		}
		return "/user/login";
	}
	
	// 회원가입 이동
	@RequestMapping(value = "/user/join", method = RequestMethod.GET)
	public void join() throws Exception{
		
	}
	
	// 아이디 중복 체크
	@ResponseBody
	@RequestMapping(value = "/user/id_duplicate_check", method = RequestMethod.POST)
	public String id_duplicate_check(@RequestParam("u_id") String u_id) throws Exception {
		System.out.println("u_id" + u_id);
	    boolean isDuplicate = userService.selectUser(u_id) != null;
	    System.out.println(isDuplicate);
	    // boolean 값을 "true" 또는 "false" 문자열로 반환
	    return Boolean.toString(isDuplicate);
	}
	
	// 회원가입 처리
	@RequestMapping(value = "/user/joinDB", method = RequestMethod.POST)
	public String joinDB(UserDto dto, HttpServletRequest request, Model model) throws Exception{
		System.out.println("User insertDB");
		
		String encPassword = bcryptPasswordEncoder.encode(dto.getU_pw());
		dto.setU_pw(encPassword);
		
		String u_email = request.getParameter("email") + "@" + request.getParameter("select");
    	System.out.println("u_email : " + u_email);
    	dto.setU_email(u_email);
    	
		String u_phoneNumber = request.getParameter("phoneNumber1") + "-" + request.getParameter("phoneNumber2") + "-" + request.getParameter("phoneNumber3");
        System.out.println("u_phoneNumber : " + u_phoneNumber);
        dto.setU_phoneNumber(u_phoneNumber);
        
		userService.insert(dto);
		System.out.println(dto);
		
		
		AuthoritiesDto authDto = new AuthoritiesDto();
		String join_id = dto.getU_id();
		authDto.setU_id(join_id);
		
		// *
		if (join_id.equals("admin1234")) {
			authDto.setAuthority("ROLE_ADMIN");
			authoritiesService.insert(authDto);
		}else {
			authDto.setAuthority("ROLE_MEMBER");
			authoritiesService.insert(authDto);
		}
		
		return "redirect:/";
	}
	
	//유저 탈퇴 처리, 비활성화
	@RequestMapping(value = "/user/deactivateDB", method = RequestMethod.GET)
	public String deactivateDB(HttpServletRequest request, HttpSession session) throws Exception{
		System.out.println("유저 계정이 비활성화 되었습니다.");
		
		String u_id = (String) session.getAttribute("userId");
		userService.deactivate(u_id);
		
		return "/";
	}
	
	@RequestMapping(value = "/user/Logincomplete", method = RequestMethod.GET)
	public String Logincomplete(HttpSession session) throws Exception{
		String userid = (String)session.getAttribute("userId");
		
		session.setAttribute("dto", userService.selectUser(userid));
		UserDto dto = (UserDto)session.getAttribute("dto");
		System.out.println(dto);
		
		return "redirect:/";
	}
	
	@RequestMapping(value = "/user/myPage", method = RequestMethod.GET)
	public void myPage() throws Exception{
		
	}
	@RequestMapping(value = "/user/myInfo", method = RequestMethod.GET)
	public void myInfo(UserDto dto, Model model, HttpSession session) throws Exception{
		String userid = (String)session.getAttribute("userId");
		dto = userService.selectUser(userid);
		model.addAttribute("dto", dto);
		System.out.println("/user/myInfo : " + dto);
		
		String u_email = dto.getU_email();
        String[] u_emailArr = u_email.split("@");
        model.addAttribute("u_emailArr", u_emailArr);
            
        String u_phoneNumber = dto.getU_phoneNumber();
        String[] u_phoneNumberArr = u_phoneNumber.split("-");
        model.addAttribute("u_phoneNumberArr", u_phoneNumberArr);
		
	}
	
	@RequestMapping(value = "/user/myInfo/menber_upDB", method = RequestMethod.POST)
	public String menber_upDB(UserDto dto, String u_pw, Model model, HttpServletRequest request) throws Exception{
		
		String u_email = request.getParameter("email") + "@" + request.getParameter("select");
	    System.out.println("u_email : " + u_email);
	    dto.setU_email(u_email);
	    	
		String u_phoneNumber = request.getParameter("phoneNumber1") + "-" + request.getParameter("phoneNumber2") + "-" + request.getParameter("phoneNumber3");
	    System.out.println("u_phoneNumber : " + u_phoneNumber );
	    dto.setU_phoneNumber(u_phoneNumber);
		
	    userService.update(dto);
		System.out.println(dto);
		
		return "/user/myPage";
	}
	
	@RequestMapping(value = "/user/myInfo/pw_upDB", method = RequestMethod.POST)
	public String pw_upDB(UserDto dto, String u_pw, Model model, HttpSession session) throws Exception{
		String u_id = (String) session.getAttribute("userId");
	    
		String encPassword = bcryptPasswordEncoder.encode(u_pw);

		userService.pw_update(u_id, encPassword);
		
		System.out.println("u_id : " + u_id);
		System.out.println("u_pw : " + encPassword);
		return "/user/myPage";
	}
	
	
	
	@RequestMapping(value="/user/myInfo/updateImg", method=RequestMethod.POST)
	public String updateImg(UserDto dto, MultipartHttpServletRequest mpRequest, Model model, HttpSession session)throws Exception {
		String u_id = (String) session.getAttribute("userId");
		System.out.println("u_id : " + u_id);
		dto.setU_id(u_id);
		
		// 현재 사용자의 기존 프로필 이미지 경로를 가져옴
        UserDto currentUser = userService.selectUser(u_id);
        String oldImgPath = currentUser.getU_profile_img();
        
	    
		String user_Img = fileUtil.updateImg(mpRequest, oldImgPath); 
		dto.setU_profile_img(user_Img);
		userService.updateImg(user_Img, u_id);
		
		// 세션 정보 갱신
	    session.setAttribute("dto", userService.selectUser(u_id));
	    
		System.out.println("/user/myInfo/updateImg : " + dto);
				
		return "redirect:/user/myInfo?t=" + System.currentTimeMillis();
	}
	@RequestMapping(value = "/user/myBoard", method = RequestMethod.GET)
	public String myBoard(Model model, HttpSession session) throws Exception {
	    // 세션에서 UserDto 객체를 가져옴
	    UserDto dto = (UserDto) session.getAttribute("dto");

	    // 사용자가 로그인하지 않은 경우 처리 (로그인 페이지로 리다이렉트)
	    if (dto == null) {
	        return "redirect:/login";
	    }

	    // 사용자 ID를 가져옴
	    String userId = dto.getU_id();

	    // 게시글 조회 서비스 호출
	    try {
	        // 사용자가 작성한 게시글을 서비스에서 가져오기
	        List<BoardDto> userPosts = service.getUserPosts(userId);

	        // 게시글이 없을 경우 처리
	        if (userPosts == null || userPosts.isEmpty()) {
	            model.addAttribute("message", "작성된 게시글이 없습니다.");
	        } else {
	            // 모델에 게시글 추가하여 JSP에 전달
	            model.addAttribute("userPosts", userPosts);
	        }
	    } catch (Exception e) {
	        // 에러 발생 시 로그 출력
	        e.printStackTrace();
	        model.addAttribute("errorMessage", "게시글 조회 중 오류가 발생했습니다.");
	    }

	    return "/user/myBoard";  // myBoard.jsp로 이동
	}
	
	
	@RequestMapping(value = "/user/notification", method = RequestMethod.GET)
	public void notification() throws Exception{
		
	}
	
	
}
