package kr.or.ddit.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.or.ddit.dto.MemberVO;
import kr.or.ddit.request.Criteria;
import kr.or.ddit.request.SearchCriteria;

@Repository
public class MemberDAOImpl implements MemberDAO {
	
	private SqlSession sqlSession;
	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}

	@Override
	public MemberVO selectMemberById(String id) throws SQLException {
		MemberVO member = sqlSession.selectOne("Member-Mapper.selectMemberById", id);
		System.out.println(member.getId());
		return member;
	}
	
	@Override
	public List<MemberVO> selectMemberList( Criteria cri) throws SQLException {
		int offset = cri.getStartRowNum();
		int limit = cri.getPerPageNum();
		
		RowBounds rowBounds = new RowBounds(offset, limit);
		List<MemberVO> memberList = sqlSession.selectList("Member-Mapper.selectSearchMemberList", null, rowBounds);
		
		return memberList;
	}

	@Override
	public List<MemberVO> selectMemberList() throws SQLException {
		
		List<MemberVO> memberList = sqlSession.selectList("Member-Mapper.selectSearchMemberList");
		return memberList;
	}

	@Override
	public List<MemberVO> selectMemberList( SearchCriteria cri) throws SQLException {
		int offset = cri.getStartRowNum();
		int limit = cri.getPerPageNum();
		
		RowBounds rowBounds = new RowBounds(offset, limit);
		
		List<MemberVO> memberList = sqlSession.selectList("Member-Mapper.selectSearchMemberList", cri, rowBounds);
		
		return memberList;
	}
	
	@Override
	public int selectSearchMemberListCount( SearchCriteria cri) throws SQLException {
		int cnt = 0;
		cnt = sqlSession.selectOne("Member-Mapper.selectSearchMemberListCount", cri);
		return cnt;
	}
	
	@Override
	public void insertMember( MemberVO member) throws SQLException {
		sqlSession.update("Member-Mapper.insertMember", member);
	}

	@Override
	public void updateMember( MemberVO member) throws SQLException {
		sqlSession.update("Member-Mapper.updateMember", member);
	}

	@Override
	public void deleteMember( String id) throws SQLException {
		sqlSession.update("Member-Mapper.deleteMember", id);
	}

	@Override
	public void disabledMember( String id) throws SQLException {
		sqlSession.update("Member-Mapper.disabledMember", id);
	}

	@Override
	public void enabledMember( String id) throws SQLException {
		sqlSession.update("Member-Mapper.enabledMember", id);
	}


}
