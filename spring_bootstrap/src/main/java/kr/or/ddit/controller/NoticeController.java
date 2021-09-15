package kr.or.ddit.controller;

import java.sql.SQLException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.omg.CosNaming.NamingContextPackage.NotEmpty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.josephoconnell.html.HTMLInputFilter;

import kr.or.ddit.command.NoticeModifyCommand;
import kr.or.ddit.command.NoticeRegistCommand;
import kr.or.ddit.command.SearchCriteria;
import kr.or.ddit.dto.NoticeVO;
import kr.or.ddit.service.NoticeService;

@Controller
@RequestMapping("/notice")
public class NoticeController {
	
	@Autowired
	private NoticeService noticeService;
	
	@RequestMapping("/main")
	public void noticeMain() throws Exception{
	}
	
	@RequestMapping("/list")
	public void getNoticeList(Model model, SearchCriteria cri) throws SQLException{
		Map<String, Object> dataMap = noticeService.getNoticeList(cri);
		model.addAllAttributes(dataMap);
	}
	
	@RequestMapping("/registForm")
	public String registForm(){
		String url = "notice/regist";
		return url;
	}
	
	@RequestMapping("/regist")
	public String regist(NoticeRegistCommand regReq, RedirectAttributes rttr) throws Exception{
		String url = "redirect:/notice/list";
		
		NoticeVO notice = regReq.toNoticeVO();
		
		notice.setTitle(HTMLInputFilter.htmlSpecialChars(notice.getTitle()));
		noticeService.regist(notice);
		
		rttr.addFlashAttribute("from", "regist");
		
		return url;
	}
	
	@RequestMapping("/detail")
	public ModelAndView detail(int nno, @RequestParam(defaultValue="")String from, HttpServletRequest requset,ModelAndView mnv) throws SQLException{
		String url = "notice/detail";
		NoticeVO notice = null;
		
		if(!from.equals("list")) {
			notice = noticeService.getNoticeForModify(nno);
		}else {
			notice = noticeService.getNotice(nno);
			url = "redirect:/notice/detail.do?nno="+nno;
		}
		
		mnv.addObject("notice",notice);
		mnv.setViewName(url);
		
		return mnv;
	}
	
	@RequestMapping("/modifyForm")
	public ModelAndView modifyForm(int nno, ModelAndView mnv) throws Exception{
		String url = "notice/modify";
		
		NoticeVO notice = noticeService.getNoticeForModify(nno);
		
		mnv.addObject("notice", notice);
		mnv.setViewName(url);
		
		return mnv;
	}
	
	@RequestMapping(value="/modify", method=RequestMethod.POST)
	public String modify(NoticeModifyCommand modReq,RedirectAttributes rttr) throws SQLException{
		String url = "redirect:/notice/detail.do";
		
		NoticeVO notice = modReq.toNoticeVO();
		notice.setTitle(HTMLInputFilter.htmlSpecialChars(notice.getTitle()));
		noticeService.modify(notice);
		
		rttr.addAttribute("nno", notice.getNno());
		rttr.addAttribute("from", "modify");
		
		return url;
	}
	
	@RequestMapping(value="/remove", method=RequestMethod.GET)
	public String remove(int nno, RedirectAttributes rttr) throws Exception{
		String url = "redirect:/notice/list.do";
		noticeService.remove(nno);
		
		rttr.addFlashAttribute("from","remove");
		
		return url;
	}
}
