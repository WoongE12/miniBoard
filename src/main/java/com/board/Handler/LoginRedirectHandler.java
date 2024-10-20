package com.board.Handler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

public class LoginRedirectHandler implements AuthenticationSuccessHandler {

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		String user_id = getUsername(authentication);
		//System.out.println("Logged in user ID: " + user_id);

		// HttpSession 객체 가져오기
		HttpSession session = request.getSession();

		String idSave = request.getParameter("id_save");
		if (idSave != null && idSave.equals("id_save")) {
			//System.out.println("아이디 저장이 선택되었습니다.");
			session.setAttribute("ids", user_id);
			session.setAttribute("id_save_Checked", "checked");
		}
		String autoLogin = request.getParameter("auto_login");
		if (autoLogin != null && autoLogin.equals("auto_login")) {
			//System.out.println("자동 로그인이 선택되었습니다.");
			session.setAttribute("ids", user_id);
			session.setAttribute("auto_login_Checked", "checked");
		}

		if ((idSave == null || !idSave.equals("id_save")) && (autoLogin == null || !autoLogin.equals("auto_login"))) {
			//System.out.println("아이디 저장이 선택되지 않았습니다.");
			//System.out.println("자동 로그인이 선택되지 않았습니다.");
			// 아이디 저장과 자동 로그인 모두 체크되지 않았을 때 세션 초기화
			session.removeAttribute("ids");

			session.removeAttribute("id_save_Checked");
			session.removeAttribute("auto_login_Checked");
		} else if ((idSave != null && idSave.equals("id_save"))
				&& (autoLogin == null || !autoLogin.equals("auto_login"))) {
			System.out.println("아이디 저장이 선택되었습니다.");
			System.out.println("자동 로그인이 선택되지 않았습니다.");
			// 아이디 저장이 체크되고, 자동 로그인 체크되지 않았을 때 세션 초기화
			session.removeAttribute("auto_login_Checked");
		} else if ((idSave == null || !idSave.equals("id_save"))
				&& (autoLogin != null && autoLogin.equals("auto_login"))) {
			//System.out.println("아이디 저장이 선택되지 않았습니다.");
			//System.out.println("자동 로그인이 선택되었습니다.");
			session.removeAttribute("id_save_Checked");
			// 아이디 저장이 체크되지 않고, 자동 로그인 체크되었을 때 세션 초기화
		}

		final List<String> roleNames = new ArrayList<String>();
		authentication.getAuthorities().forEach(authority -> {
			roleNames.add(authority.getAuthority());
			//System.out.println("==role.authority==" + authority.getAuthority());
		});

		
		if (roleNames.contains("ROLE_ADMIN")) {
			session.setAttribute("userId", user_id);
			session.setAttribute("action", "login");
			response.sendRedirect("/ex/admin/admin");
			return;
		}
		if (roleNames.contains("ROLE_MEMBER")) {
			session.setAttribute("userId", user_id);
			session.setAttribute("action", "login");
			response.sendRedirect("/ex/user/Logincomplete");
			return;
		}

	}

	private String getUsername(Authentication authentication) {
		if (authentication.getPrincipal() instanceof UserDetails) {
			return ((UserDetails) authentication.getPrincipal()).getUsername();
		} else {
			return authentication.getPrincipal().toString();
		}
	}
}
