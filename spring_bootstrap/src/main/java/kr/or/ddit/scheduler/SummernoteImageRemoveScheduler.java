package kr.or.ddit.scheduler;

import java.io.File;
import java.util.List;

import kr.or.ddit.command.SearchCriteria;
import kr.or.ddit.dto.BoardVO;
import kr.or.ddit.dto.NoticeVO;
import kr.or.ddit.dto.PdsVO;
import kr.or.ddit.service.BoardService;
import kr.or.ddit.service.NoticeService;
import kr.or.ddit.service.PdsService;

public class SummernoteImageRemoveScheduler {
	
	private NoticeService noticeService;
	private BoardService boardService;
	private PdsService pdsService;
	private String imgPath;
	
	public void setNoticeService(NoticeService noticeService) {
		this.noticeService = noticeService;
	}
	public void setBoardService(BoardService boardService) {
		this.boardService = boardService;
	}
	public void setPdsService(PdsService pdsService) {
		this.pdsService = pdsService;
	}
	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}
	
	public void fileRemove() throws Exception{
		File dir = new File(imgPath);
		File[] files = dir.listFiles();
		
		if(files!=null) {
			for (File file : files) {
				String fileName = file.getName();
				
				SearchCriteria cri = new SearchCriteria();
				cri.setKeyword(fileName);
				cri.setSearchType("c");
				
				boolean existFile = false;
				
				List<NoticeVO> noticeList = (List<NoticeVO>) noticeService.getNoticeList(cri).get("noticeList");
				List<BoardVO> boardList = (List<BoardVO>) boardService.getBoardList(cri).get("boardList");
				List<PdsVO> pdsList = (List<PdsVO>) pdsService.getList(cri).get("pdsList");
				
				existFile = noticeList.size() > 0
							|| boardList.size() > 0
							|| pdsList.size() > 0;
				
				if(!existFile) {
					if(file.exists()) file.delete();
				}
			}
		}
	}
}
