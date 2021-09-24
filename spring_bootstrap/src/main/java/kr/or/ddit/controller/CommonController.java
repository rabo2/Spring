package kr.or.ddit.controller;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.or.ddit.dto.MenuVO;
import kr.or.ddit.service.MenuService;

@Controller
public class CommonController {

	@Autowired
	private MenuService menuService;

//	@Autowired
//	private MemberService memberService;

	@RequestMapping("/index")
	public String indexPage(@RequestParam(defaultValue = "M000000") String mCode, Model model) throws Exception {
		String url = "common/indexPage";

		try {
			List<MenuVO> menuList = menuService.getMainMenuList();
			MenuVO menu = menuService.getMenuByMcode(mCode);

			model.addAttribute("menuList", menuList);
			model.addAttribute("menu", menu);

		} catch (Exception e) {
			e.printStackTrace();
			url = null;
			throw e;
		}

		return url;
	}

	@RequestMapping("/subMenu")
	@ResponseBody
	public ResponseEntity<List<MenuVO>> subMenu(@RequestParam(defaultValue = "M000000") String mCode,
			HttpServletResponse response) throws SQLException {
		ResponseEntity<List<MenuVO>> entity = null;

		try {
			List<MenuVO> subMenuList = menuService.getSubMenuList(mCode);
			entity = new ResponseEntity<List<MenuVO>>(subMenuList, HttpStatus.OK);
		} catch (Exception e) {
			entity = new ResponseEntity<List<MenuVO>>(HttpStatus.INTERNAL_SERVER_ERROR);
			e.printStackTrace();
		}
		return entity;
	}

	@RequestMapping(value = "/main", method = RequestMethod.GET)
	public String homePage() {
		String url = "common/main";
		return url;
	}

	@RequestMapping(value = "/getMcode", method = RequestMethod.GET)
	public ResponseEntity<MenuVO> getMcode(String mName) throws Exception {
		ResponseEntity<MenuVO> entity = null;
		try {
			MenuVO menu = menuService.getMenuByMname(mName);
			entity = new ResponseEntity<MenuVO>(menu, HttpStatus.OK);
		} catch (SQLException e) {
			entity = new ResponseEntity<MenuVO>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return entity;
	}

	@RequestMapping(value = "/common/loginForm", method = RequestMethod.GET)
	public String loginForm(@RequestParam(defaultValue="")String error, HttpServletResponse response) {
		String url = "common/loginForm";
		
		if(error.equals("1")) {
			response.setStatus(302);
		}
		return url;
	}

	@RequestMapping("/common/loginTimeOut")
	public String lginTimeOut(Model model) throws Exception {
		String url = "security/sessionOut";
		
		model.addAttribute("message","세션이 만료되었습니다.\\n다시 로그인 하세요.");
		
		return url;
	}
	
	@RequestMapping("/common/loginExpired")
	public String loginExpired(Model model) throws Exception{
		String url = "security/sessionOut";
		
		model.addAttribute("message","중복 로그인이 확인되었습니다.\\n다시 로그인하면 다른 장치에서 로그아웃 됩니다");
		
		return url;
	}
	
//	@RequestMapping(value = "/common/login", method = RequestMethod.POST)
//	public String login(String id, String pwd, HttpServletRequest request, HttpSession session, RedirectAttributes rttr)
//			throws Exception {
//		String url = "redirect:/index.do";
//
//		try {
//			memberService.login(id, pwd);
//			session.setAttribute("loginUser", memberService.getMember(id));
//
//		} catch (InvalidPasswordException | NotFoundIDException e) {
//			rttr.addFlashAttribute("message", e.getMessage());
//			url = "redirect:/common/loginForm";
//		} catch (SQLException e) {
//			ExceptionLoggerHelper.write(request, e, memberService);
//			e.printStackTrace();
//			throw e;
//		}
//		return url;
//	}
//
//	@RequestMapping(value = "/common/logout", method = RequestMethod.GET)
//	public String logout(HttpSession session) {
//		String url = "redirect:/";
//		session.invalidate();
//		return url;
//	}

}
