package kr.or.ddit.controller;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import kr.or.ddit.command.SearchCriteria;
import kr.or.ddit.dto.MemberVO;
import kr.or.ddit.service.MemberService;
import kr.or.ddit.util.MakeFileName;

@Controller
@RequestMapping("/member")
public class MemberController {
	
	@Autowired
	private MemberService memberService;
	
	@Resource(name="picturePath")
	private String picturePath;
	
	

	@RequestMapping("/main")
	public void main() throws Exception {}

	@RequestMapping("/list")
	public ModelAndView getMemberList(ModelAndView mnv, SearchCriteria cri) throws SQLException {
		String url = "member/list";
		
		Map<String, Object> dataMap = memberService.getMemberList(cri);
		mnv.addAllObjects(dataMap);
		mnv.setViewName(url);
		
		return mnv;
	}

	@RequestMapping(value = "/registForm", method = RequestMethod.GET)
	public String memberRegistForm() throws Exception {
		String url = "member/regist";
		return url;
	}
	
	
	@RequestMapping(value="/picture", method=RequestMethod.POST, produces="text/plain;charset=utf-8")
	@ResponseBody
	public ResponseEntity<String> picture(@RequestParam("pictureFile") MultipartFile multi, String oldPicture) throws Exception{
		
		ResponseEntity<String> entity = null;
		
		String result = "";
		HttpStatus status = null;
				
		if((result=savePicture(oldPicture, multi))==null) {
			result="업로드 실패했습니다.";
			status = HttpStatus.BAD_REQUEST;
		}else {
			status=HttpStatus.OK;
		}
		
		return entity;
	}

	@RequestMapping(value = "/regist", method = RequestMethod.POST)
	public String memberRegist(MemberVO member) {
		String url = "member/regist_success";

		try {
			memberService.regist(member);
		} catch (SQLException e) {
			url = "member/regist_fail";
		}
		return url;
	}

	
	@RequestMapping("/detail")
	public void memberDetail(Model model, String id) throws SQLException {
		
		MemberVO member = memberService.getMember(id);
		model.addAttribute("member",member);
	}


	@RequestMapping(value="/idCheck", method=RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<String> idCheck(String id) throws Exception {
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

	@RequestMapping("/modifyForm")
	public String memberModifyForm(String id, HttpServletRequest request) throws SQLException {
		String url = "member/modify";

		MemberVO member = memberService.getMember(id);
		request.setAttribute("member", member);

		return url;
	}

	@RequestMapping(value = "/modify", method = RequestMethod.POST)
	public String memberModify(MemberVO member) throws SQLException {
		String url = "member/modify_success";

		memberService.modify(member);

		return url;
	}
	
	
	private String savePicture(String oldPicture, MultipartFile multi) throws Exception {
		String fileName = null;
		
		if(!(multi!=null || multi.isEmpty() || multi.getSize() > 1024*1024*5)) {
			String uploadPath = picturePath;
			fileName = MakeFileName.toUUIDFileName(multi.getOriginalFilename(), "$$");
			
			File storeFile = new File(uploadPath, fileName);
			
			storeFile.mkdirs();
			
			multi.transferTo(storeFile);
			
			if(oldPicture != null && !oldPicture.isEmpty()) {
				File oldFile = new File(uploadPath, oldPicture);
				if(oldFile.exists()) {
					oldFile.delete();
				}
			}
		}
		
		return fileName;
	}
	
	
}
