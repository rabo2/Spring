package kr.or.ddit.security;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

import kr.or.ddit.dto.MemberVO;
import kr.or.ddit.service.MemberService;

public class LoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws ServletException, IOException {
		
		UserDetails user = (UserDetails) authentication.getPrincipal();
		
		String username = user.getUsername();
		
		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext("classpath:kr/or/ddit/context/root-context.xml");
		MemberService service = ctx.getBean("memberService",MemberService.class);
		
		try {
			MemberVO loginUser = service.getMember(username);
			HttpSession session = request.getSession();
			
			session.setAttribute("loginUser", loginUser);
			session.setMaxInactiveInterval(20);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		super.onAuthenticationSuccess(request, response, authentication);
	}
}
