package kr.or.ddit.controller;

import java.sql.SQLException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import kr.or.ddit.command.PageMaker;
import kr.or.ddit.command.ReplyModifyCommand;
import kr.or.ddit.command.ReplyRegistCommand;
import kr.or.ddit.command.SearchCriteria;
import kr.or.ddit.dto.ReplyVO;
import kr.or.ddit.service.ReplyService;

//url : replies

//@pathVariable을 제외하고는  엔드포인트가 똑같기 때문에 메소드형식을 기준으로 컨트롤러를 구분한다
//다만 , 메소드 형식은 브라우저마다 지원하는 것이 다르기 때문에 header를 조작한다

//bno번 게시글의 페이징 처리된 댓글 목록
// /replies/{bno}/{page}		page list : GET방식
// /replies/					regist : POST 방식 : 댓글 입력
// /replies/{rno}				modify : PUT, PATCH방식, rno 댓글의 수정
// /replies/{bno}/{rno}/{page}	remove : DELETE방식, rno 댓글의 삭제
// /relies

@RestController
@RequestMapping("/replies")
public class ReplyController {

	@Autowired
	private ReplyService service;

	@RequestMapping(value = "/{bno}/{page}", method = RequestMethod.GET)
	public ResponseEntity<Map<String, Object>> replyList(@PathVariable("bno") int bno, @PathVariable("page") int page)
			throws Exception {

		ResponseEntity<Map<String, Object>> entity = null;
		SearchCriteria cri = new SearchCriteria();

		cri.setPage(page);
		try {
			Map<String, Object> dataMap = service.getReplyList(bno, cri);
			entity = new ResponseEntity<Map<String, Object>>(dataMap, HttpStatus.OK);
		} catch (SQLException e) {
			e.printStackTrace();
			entity = new ResponseEntity<Map<String, Object>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return entity;
	}

	@RequestMapping(value = "", method = RequestMethod.POST)
	public ResponseEntity<String> register(@RequestBody ReplyRegistCommand replyReq) throws Exception {
		ResponseEntity<String> entity = null;

		ReplyVO reply = replyReq.toReolyVO();

		try {

			service.registReply(reply);

			SearchCriteria cri = new SearchCriteria();

			Map<String, Object> dataMap = service.getReplyList(reply.getBno(), cri);
			PageMaker pageMaker = (PageMaker) dataMap.get("pageMaker");
			int realEndPage = pageMaker.getRealEndPage();

			entity = new ResponseEntity<String>(realEndPage + "", HttpStatus.OK);
		} catch (SQLException e) {
			e.printStackTrace();
			entity = new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return entity;
	}

	@RequestMapping(value = "/{rno}", method = { RequestMethod.PUT, RequestMethod.PATCH })
	public ResponseEntity<String> modify(@PathVariable("rno") int rno, @RequestBody ReplyModifyCommand modifyReq)
			throws Exception {

		ResponseEntity<String> entity = null;

		ReplyVO reply = modifyReq.toReolyVO();
		reply.setRno(rno);

		try {
			service.modifyReply(reply);
			entity = new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
		} catch (SQLException e) {
			e.printStackTrace();
			entity = new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return entity;
	}
	
	@RequestMapping(value="/{bno}/{rno}/{page}", method= {RequestMethod.DELETE})
	public ResponseEntity<String> remove(@PathVariable("bno")int bno, @PathVariable("rno")int rno, @PathVariable("page")int page) throws Exception{
		ResponseEntity<String> entity = null;
		
		try {
			service.removeReply(rno);
			
			SearchCriteria cri = new SearchCriteria();
			Map<String, Object> dataMap = service.getReplyList(bno, cri);
			PageMaker pageMaker = (PageMaker) dataMap.get("pageMaker");
			
			int realEndPage = pageMaker.getRealEndPage();
			if(page>realEndPage) {
				page = realEndPage;
			}
			
			entity = new ResponseEntity<String>(""+page,HttpStatus.OK);
			
		}catch (SQLException e) {
			e.printStackTrace();
			entity = new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return entity;
	}
}
