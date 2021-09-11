package kr.or.ddit.controller.member;

import java.sql.SQLException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.or.ddit.request.SearchCriteria;
import kr.or.ddit.service.MemberService;

@Controller
public class MemberController {
	@Autowired
	private MemberService memberService;
	
	@RequestMapping(value="member/list.do", method=RequestMethod.GET)
	public String getMemberList(HttpServletRequest request, SearchCriteria cri) throws SQLException{
		String url = "member/list";
		Map<String, Object> dataMap = memberService.getMemberList(cri);
		request.setAttribute("memberList", dataMap.get("memberList"));
		request.setAttribute("pageMaker", dataMap.get("pageMaker"));
		return url;
	}
	
}
