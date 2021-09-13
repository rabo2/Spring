package kr.or.ddit.controller.member;

import java.sql.SQLException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.or.ddit.command.SearchCriteria;
import kr.or.ddit.controller.JSONResolver;
import kr.or.ddit.dto.MemberVO;
import kr.or.ddit.service.MemberService;

@Controller
public class MemberController {
	@Autowired
	private MemberService memberService;

	@RequestMapping(value = "/member/main.do", method = RequestMethod.GET)
	public String memberMain() throws Exception {
		String url = "member/main";
		return url;
	}

	@RequestMapping(value = "/member/list.do", method = RequestMethod.GET)
	public String getMemberList(HttpServletRequest request, SearchCriteria cri) throws SQLException {
		String url = "member/list";
		Map<String, Object> dataMap = memberService.getMemberList(cri);
		request.setAttribute("memberList", dataMap.get("memberList"));
		request.setAttribute("pageMaker", dataMap.get("pageMaker"));
		return url;
	}

	@RequestMapping(value = "/member/detail.do", method = RequestMethod.GET)
	public String memberDetail(HttpServletRequest request, String id) throws SQLException {
		String url = "member/detail";

		MemberVO member = memberService.getMember(id);
		request.setAttribute("member", member);

		return url;
	}

	@RequestMapping(value = "/member/regist.do", method = RequestMethod.GET)
	public String memberRegistForm() throws Exception {
		String url = "member/regist";
		return url;
	}

	@RequestMapping(value="/member/idCheck.do", method=RequestMethod.GET)
	public void idCheck(String id, HttpServletResponse response) throws Exception {
		
		MemberVO member = memberService.getMember(id);
		String result ="";
		
		if(member != null) {
			result = "duplicated";
			JSONResolver.view(response, result);
		}
		
	}
	
	@RequestMapping(value = "/member/regist.do", method = RequestMethod.POST)
	public String memberRegist(MemberVO member) {
		String url = "member/regist_success";

		try {
			memberService.regist(member);
		} catch (SQLException e) {
			url = "member/regist_fail";
		}
		return url;
	}

	
	
	@RequestMapping(value = "/member/modify.do", method = RequestMethod.GET)
	public String memberModifyForm(String id, HttpServletRequest request) throws SQLException {
		String url = "member/modify";

		MemberVO member = memberService.getMember(id);
		request.setAttribute("member", member);

		return url;
	}

	@RequestMapping(value = "/member/modify.do", method = RequestMethod.POST)
	public String memberModify(MemberVO member) throws SQLException {
		String url = "member/modify_success";

		memberService.modify(member);

		return url;
	}
}
