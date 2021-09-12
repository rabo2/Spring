package com.personal.farmer.controller;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.personal.farmer.dto.MemberVO;
import com.personal.farmer.dto.MenuVO;
import com.personal.farmer.exception.InvalidPasswordException;
import com.personal.farmer.exception.NotFoundIDException;
import com.personal.farmer.service.MemberService;
import com.personal.farmer.service.MenuService;

@Controller
public class CommonCotroller {
	@Autowired
	private MenuService menuService;
	
	@Autowired
	private MemberService memberService;
	
	@RequestMapping(value="/index.do", method=RequestMethod.GET)
	public String indexPage(String mCode, HttpServletRequest request) throws Exception{
		String url = "common/indexPage";
		
		List<MenuVO> menuList = menuService.getMainMenuList();
		MenuVO menu = menuService.getMenuByMcode(mCode);
		
		request.setAttribute("menuList", menuList);
		request.setAttribute("menu", menu);
		return url;
	}
	
	@RequestMapping(value="/subMenu.do", method=RequestMethod.GET)
	public void subMenu(String mCode, HttpServletResponse response) throws Exception{
		List<MenuVO> subMenuList = menuService.getSubMenuList(mCode);
		JSONResolver.view(response, subMenuList);
	}
	
	@RequestMapping(value="/common/login.do", method=RequestMethod.GET)
	public String loginPage() throws Exception{
		String url = "common/login";
		return url;
	}
	
	@RequestMapping(value="/common/login.do", method=RequestMethod.POST)
	public String login(MemberVO member, HttpSession session)throws SQLException{
		String url = "redirect:index.do";
		
		MemberVO loginUser = null;
		try {
			loginUser = memberService.login(member.getMem_id(), member.getMem_pwd());
		} catch (NotFoundIDException | InvalidPasswordException e) {
			url = "common/loginFail";
		}
		session.setAttribute("loginUser", loginUser);
		return url;
	}
	
	
}
