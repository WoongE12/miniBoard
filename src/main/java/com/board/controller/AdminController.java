package com.board.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.board.dto.AuthoritiesDto;
import com.board.dto.UserDto;
import com.board.service.AdminService;
import com.board.service.UserService;


@Controller
public class AdminController {

	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);

	@Autowired
	private AdminService adminService;
	
	@Autowired
	private UserService userService;
	


	@RequestMapping(value = "/admin/admin", method = RequestMethod.GET)
	public String admin(Model model,HttpSession session) throws Exception {
		String userid = (String)session.getAttribute("userId");
		session.setAttribute("dto", userService.selectUser(userid));
		
		ArrayList<UserDto> userList = adminService.selectAll(); // DB에서 데이터 가져오기
		model.addAttribute("userList", userList); // 모델에 추가하여 JSP로 전달
		
		ArrayList<AuthoritiesDto> authorityList = adminService.selectAllAuthorities(); // DB에서 권한 데이터 가져오기
	    model.addAttribute("authorityList", authorityList); // 모델에 추가하여 JSP로 전달
	    
		return "/admin/admin";
	}
	
	// 권한 업데이트를 처리하는 POST 요청
    @RequestMapping(value = "/admin/updateAuthority", method = RequestMethod.POST)
    public String updateAuthority(@RequestParam String u_id, @RequestParam String authority, RedirectAttributes redirectAttributes) {
        try {
            AuthoritiesDto authoritiesDto = new AuthoritiesDto(u_id, authority);
            adminService.updateAuthority(authoritiesDto);
            redirectAttributes.addFlashAttribute("message", "권한변경 성공!");
        } catch (Exception e) {
            logger.error("Error updating authority", e);
            redirectAttributes.addFlashAttribute("error", "권한변경 실패.");
        }
        return "redirect:/admin/admin"; // 권한 업데이트 후 다시 관리자 페이지로 리다이렉트
    }
    // 계정상태 업데이트를 처리하는 POST 요청
    @RequestMapping(value = "/admin/updateStatus", method = RequestMethod.POST)
    public String updateStatus(@RequestParam String u_id, @RequestParam int enabled, RedirectAttributes redirectAttributes) {
        try {
            adminService.updateStatus(u_id, enabled);
            redirectAttributes.addFlashAttribute("message", "상태변경 성공!");
        } catch (Exception e) {
            logger.error("Error updating status", e);	
            redirectAttributes.addFlashAttribute("error", "상태변경 실패.");
        }
        return "redirect:/admin/admin"; // 상태 업데이트 후 다시 관리자 페이지로 리다이렉트
    }
    
    @RequestMapping(value = "/admin/updateUser", method = RequestMethod.POST)
    public String updateUser(@ModelAttribute UserDto userDto, RedirectAttributes redirectAttributes) {
        try {
            adminService.updateUser(userDto);
            redirectAttributes.addFlashAttribute("message", "정보가 성공적으로 수정되었습니다.");
        } catch (Exception e) {
            logger.error("Error updating user", e);
            redirectAttributes.addFlashAttribute("error", "정보 수정에 실패했습니다.");
        }
        return "redirect:/admin/admin";
    }
    
    
}
    

    
   


