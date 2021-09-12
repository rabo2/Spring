package com.personal.farmer.service;

import java.sql.SQLException;

import com.personal.farmer.dto.MemberVO;
import com.personal.farmer.exception.InvalidPasswordException;
import com.personal.farmer.exception.NotFoundIDException;

public interface MemberService {
	MemberVO login(String mem_id, String mem_pwd) throws SQLException, NotFoundIDException, InvalidPasswordException;
	
	void signup(MemberVO vo) throws SQLException;
	
	void modifyMemberInfo(MemberVO vo) throws SQLException;
	
	void deleteAccount(MemberVO vo) throws SQLException;
}
