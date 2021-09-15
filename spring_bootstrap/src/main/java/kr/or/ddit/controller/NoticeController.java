package kr.or.ddit.controller;

import java.sql.SQLException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.or.ddit.command.SearchCriteria;
import kr.or.ddit.dto.NoticeVO;
import kr.or.ddit.service.NoticeService;

@Controller
public class NoticeController {
	
	@Autowired
	private NoticeService noticeService;
	
	@RequestMapping(value="/notice/main.do", method=RequestMethod.GET)
	public String noticeMain() throws Exception{
		System.out.println("masterbranch");
		String url = "notice/main";
		return url;
	}
	
	@RequestMapping(value="/notice/list.do", method=RequestMethod.GET)
	public String getNoticeList(HttpServletRequest request, SearchCriteria cri) throws SQLException{
		String url = "notice/list";
		
		Map<String, Object> dataMap = noticeService.getNoticeList(cri);
		request.setAttribute("dataMap", dataMap);
		
		return url;
	}
	
	@RequestMapping(value="/notice/detail.do", method=RequestMethod.GET)
	public String noticeDetail(HttpServletRequest request, int nno) throws SQLException{
		String url = "notice/detail";
		
		NoticeVO notice = noticeService.getNotice(nno);
		
		if(notice!=null) {
			request.setAttribute("notice", notice);
		}
		
		return url;
	}
}
