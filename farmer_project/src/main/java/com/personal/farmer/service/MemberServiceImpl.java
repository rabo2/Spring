package com.personal.farmer.service;

import java.sql.SQLException;

import org.apache.ibatis.session.SqlSession;

import com.personal.farmer.dao.MemberDAO;
import com.personal.farmer.dto.MemberVO;
import com.personal.farmer.exception.InvalidPasswordException;
import com.personal.farmer.exception.NotFoundIDException;

public class MemberServiceImpl implements MemberService {
	private MemberDAO memberDAO;

	public void setMemberDAO(MemberDAO memberDAO) {
		this.memberDAO = memberDAO;
	}

	@Override
	public MemberVO login(String mem_id, String mem_pwd)
			throws SQLException, NotFoundIDException, InvalidPasswordException {
		MemberVO member = memberDAO.selecMemberById(mem_id);
		if (member == null) {
			throw new NotFoundIDException();
		}
		if (!mem_pwd.equals(member.getMem_pwd())) {
			throw new InvalidPasswordException();
		}
		return member;
	}

	@Override
	public void signup(MemberVO vo) throws SQLException {
		memberDAO.insertMember(vo);
	}

	@Override
	public void modifyMemberInfo(MemberVO vo) throws SQLException {
		memberDAO.updateMember(vo);
	}

	@Override
	public void deleteAccount(MemberVO vo) throws SQLException {
		memberDAO.deleteMember(vo);
	}

}
