package kr.or.ddit.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import kr.or.ddit.dto.MemberVO;
import kr.or.ddit.request.Criteria;
import kr.or.ddit.request.SearchCriteria;

public interface MemberDAO {
	
	//회원정보 조회
	MemberVO selectMemberById(String id) throws SQLException;
	
	List<MemberVO> selectMemberList() throws SQLException;
	List<MemberVO> selectMemberList(Criteria cri) throws SQLException;
	List<MemberVO> selectMemberList(SearchCriteria cri) throws SQLException;
	
	int selectSearchMemberListCount(SearchCriteria cri) throws SQLException;
	
	public void insertMember(MemberVO member) throws SQLException;
	
	public void updateMember(MemberVO member) throws SQLException;
	
	void deleteMember(String id) throws SQLException;
	
	void disabledMember(String id) throws SQLException;
	
	void enabledMember(String id) throws SQLException;
}
