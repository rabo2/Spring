package kr.or.ddit.controller.notice;

import java.sql.SQLException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.or.ddit.request.SearchCriteria;
import kr.or.ddit.service.NoticeService;

@Controller
public class NoticeController {
	
	@Autowired
	private NoticeService noticeService;
	
	@RequestMapping(value="notice/list.do", method=RequestMethod.GET)
	public String getNoticeList(HttpServletRequest request, SearchCriteria cri) throws SQLException{
		String url = "notice/list";
		
		Map<String, Object> dataMap = noticeService.getNoticeList(cri);
		request.setAttribute("dataMap", dataMap);
		
		return url;
	}
	
	
}
