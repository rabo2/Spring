package kr.or.ddit.service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import kr.or.ddit.dao.MemberDAO;
import kr.or.ddit.dao.ReplyDAO;
import kr.or.ddit.dto.MemberVO;
import kr.or.ddit.dto.ReplyVO;
import kr.or.ddit.request.PageMaker;
import kr.or.ddit.request.SearchCriteria;

public class ReplyServiceImpl implements ReplyService{
	private ReplyDAO replyDAO;
	private MemberDAO memberDAO;

	public void setReplyDAO(ReplyDAO replyDAO) {
		this.replyDAO = replyDAO;
	}
	
	public void setMemberDAO(MemberDAO memberDAO) {
		this.memberDAO = memberDAO;
	}

	@Override
	public Map<String, Object> getReplyList(int bno, SearchCriteria cri) throws SQLException {
		
		Map<String, Object> dataMap = new HashMap<String, Object>();
		
		List<ReplyVO> replyList = replyDAO.selectReplyList(bno, cri);
		
		if(replyList != null) for (ReplyVO reply : replyList) {
			MemberVO member = memberDAO.selectMemberById(reply.getReplyer());
			reply.setPicture(member.getPicture());
		}
		
		
		int count = replyDAO.countReply(bno);
		
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(cri);
		pageMaker.setTotalCount(count);
		
		dataMap.put("replyList", replyList);
		dataMap.put("pageMaker", pageMaker);
		
		return dataMap;
		
	}

	@Override
	public int getReplyListCount(int bno) throws SQLException {
			int count = replyDAO.countReply(bno);
			return count;
	}
	
	@Override
	public void registReply(ReplyVO reply) throws SQLException {
			int rno = replyDAO.selectReplySeqNextValue();
			reply.setRno(rno);
			
			replyDAO.insertReply(reply);
		
	}

	@Override
	public void modifyReply(ReplyVO reply) throws SQLException {
			replyDAO.updateReply(reply);
	}

	@Override
	public void removeReply(int rno) throws SQLException {
			replyDAO.deleteReply(rno);
	}
}
