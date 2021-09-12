package kr.or.ddit.controller.common;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.or.ddit.controller.JSONResolver;
import kr.or.ddit.dto.MenuVO;
import kr.or.ddit.exception.InvalidPasswordException;
import kr.or.ddit.exception.NotFoundIDException;
import kr.or.ddit.service.MemberService;
import kr.or.ddit.service.MenuService;

@Controller
public class CommonController {

	@Autowired
	private MemberService memberService;
	
	@Autowired
	private MenuService menuService;

	
	@RequestMapping(value = "common/login", method = RequestMethod.GET)
	public void loginPage() throws Exception {
	}
	
	
	@RequestMapping(value="common/login",method=RequestMethod.POST)
	public String login(String id, String pwd, HttpServletRequest request) {
		String url = "redirect:/index.do";

		try {
			memberService.login(id, pwd);

			HttpSession session = request.getSession();
			session.setAttribute("loginUser", memberService.getMember(id));
			session.setMaxInactiveInterval(6 * 60);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (InvalidPasswordException | NotFoundIDException e) {
			request.setAttribute("message", e.getMessage());
			url = "common/loginFail";
		}
		return url;
	}

	
	@RequestMapping(value="/index.do", method=RequestMethod.GET)
	public String mainMenu(String mCode, HttpServletRequest request)throws Exception{
		String url = "common/indexPage";

		if (mCode == null)
			mCode = "M000000";

		List<MenuVO> menuList = menuService.getMainMenuList();
		MenuVO menu = menuService.getMenuByMcode(mCode);

		request.setAttribute("menuList", menuList);
		request.setAttribute("menu", menu);

		return url;
	}

	@RequestMapping(value="/main.do", method=RequestMethod.GET)
	public String home()throws Exception{
		String url = "common/main";
		return url;
	}
	
	@RequestMapping(value="/subMenu.do", method=RequestMethod.GET)
	public void subMenu(String mCode, HttpServletResponse response) throws Exception {
	      List<MenuVO> subMenu = null;
	      try {
	         subMenu = menuService.getSubMenuList(mCode);
	         JSONResolver.view(response, subMenu);
	          
	      } catch (Exception e) {
	         response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
	         e.printStackTrace();
	      }
	   }
}
