package kr.or.ddit.security;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

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
	
	private String savePath = "c:\\log";
	private String saveFileName = "login_user_log.csv";
	
	public void setSavePath(String savePath) {
		this.savePath = savePath;
	}

	public void setSaveFileName(String saveFileName) {
		this.saveFileName = saveFileName;
	}

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws ServletException, IOException {

//		UserDetails user = (UserDetails) authentication.getPrincipal();
//		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext("classpath:kr/or/ddit/context/root-context.xml");
//		MemberService service = ctx.getBean("memberService",MemberService.class);

		User user = (User) authentication.getDetails();
		MemberVO loginUser = user.getMember();

//		MemberVO loginUser = service.getMember(username);

		HttpSession session = request.getSession();
		session.setAttribute("loginUser", loginUser);
		session.setMaxInactiveInterval(6 * 60);

		//log 작성
		loginUserlogFile(loginUser, request);
		
		
		super.onAuthenticationSuccess(request, response, authentication);
	}

	private void loginUserlogFile(MemberVO loginUser, HttpServletRequest request) throws IOException{
		
		String tag ="[login:user]";
		String log =tag
					+loginUser.getId()+","					
					+loginUser.getPhone()+","
					+loginUser.getEmail()+","
					+request.getRemoteAddr()+","
					+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
		
		File file = new File(savePath);
		if(!file.exists()) {
			file.mkdirs();
		}
		
		String logFilePath = savePath+File.separator+saveFileName;
		BufferedWriter out = new BufferedWriter(new FileWriter(logFilePath,true));
		
		try {
			out.write(log);
			out.newLine();
		}finally {
			if(out!=null) out.close();
		}
	}
}
