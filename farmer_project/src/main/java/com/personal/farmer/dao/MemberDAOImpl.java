package com.personal.farmer.dao;

import java.sql.SQLException;

import org.apache.ibatis.session.SqlSession;

import com.personal.farmer.dto.MemberVO;

public class MemberDAOImpl implements MemberDAO {
	
	private SqlSession sqlSession;
	
	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}

	@Override
	public MemberVO selecMemberById(String mem_id) throws SQLException{
		MemberVO member = sqlSession.selectOne("Member-Mapper.selectMemberById", mem_id);
		return member;
	}

	@Override
	public void insertMember(MemberVO vo) throws SQLException{
		sqlSession.insert("Member-Mapper.insertMember", vo);
	}

	@Override
	public void updateMember(MemberVO vo) throws SQLException{
		sqlSession.update("Member-Mapper.updateMember", vo);
	}

	@Override
	public void deleteMember(MemberVO vo) throws SQLException{
		sqlSession.delete("Member-Mapper.deleteMember", vo);
	}

}
