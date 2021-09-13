package kr.or.ddit.controller;

import java.sql.SQLException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.or.ddit.command.SearchCriteria;
import kr.or.ddit.dto.MemberVO;
import kr.or.ddit.service.MemberService;

@Controller
public class MemberController {
	@Autowired
	private MemberService memberService;

	@RequestMapping("/member/main")
	public void memberMain() throws Exception {}

	@RequestMapping("/member/list")
	public void getMemberList(Model model, SearchCriteria cri) throws SQLException {
		Map<String, Object> dataMap = memberService.getMemberList(cri);
		model.addAttribute("memberList", dataMap.get("memberList"));
		model.addAttribute("pageMaker",dataMap.get("pageMaker"));
	}

	@RequestMapping("/member/detail")
	public void memberDetail(Model model, String id) throws SQLException {

		MemberVO member = memberService.getMember(id);
		model.addAttribute("member",member);
	}

	@RequestMapping(value = "/member/registForm", method = RequestMethod.GET)
	public String memberRegistForm() throws Exception {
		String url = "member/regist";
		return url;
	}

	@RequestMapping(value="/member/idCheck", method=RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<String> idCheck(String id, HttpServletResponse response) throws Exception {
		ResponseEntity<String> entity = null;
		
		MemberVO member = memberService.getMember(id);
		String result ="";
		
		if(member != null) {
			result = "duplicated";
			entity = new ResponseEntity<String>(result, HttpStatus.OK);
		}else {
			entity = new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return entity;
	}
	
	@RequestMapping(value = "/member/regist", method = RequestMethod.POST)
	public String memberRegist(MemberVO member) {
		String url = "member/regist_success";

		try {
			memberService.regist(member);
		} catch (SQLException e) {
			url = "member/regist_fail";
		}
		return url;
	}

	@RequestMapping("/member/modifyForm")
	public String memberModifyForm(String id, HttpServletRequest request) throws SQLException {
		String url = "member/modify";

		MemberVO member = memberService.getMember(id);
		request.setAttribute("member", member);

		return url;
	}

	@RequestMapping(value = "/member/modify", method = RequestMethod.POST)
	public String memberModify(MemberVO member) throws SQLException {
		String url = "member/modify_success";

		memberService.modify(member);

		return url;
	}
}
