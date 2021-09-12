package com.personal.farmer.dao;

import java.sql.SQLException;

import org.apache.ibatis.session.SqlSession;

import com.personal.farmer.dto.MemberVO;

public interface MemberDAO {
	MemberVO selecMemberById(String mem_id) throws SQLException;
	
	void insertMember(MemberVO vo) throws SQLException;
	
	void updateMember(MemberVO vo) throws SQLException;
	
	void deleteMember(MemberVO vo) throws SQLException;
}
