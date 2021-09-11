package kr.or.ddit.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import kr.or.ddit.dto.ReplyVO;
import kr.or.ddit.request.SearchCriteria;

public interface ReplyDAO {
	List<ReplyVO> selectReplyList (int bno, SearchCriteria cri) throws SQLException;
	
	int selectReplySeqNextValue() throws SQLException;
	
	int countReply( int bno) throws SQLException;
	
	void insertReply( ReplyVO reply) throws SQLException;
	
	void updateReply( ReplyVO reply) throws SQLException;
	
	void deleteReply( int rno) throws SQLException;
}
