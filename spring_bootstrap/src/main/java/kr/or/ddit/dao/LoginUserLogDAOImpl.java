package kr.or.ddit.dao;

import java.sql.SQLException;

import org.apache.ibatis.session.SqlSession;

import kr.or.ddit.dto.LoginUserLogVO;

public class LoginUserLogDAOImpl implements LoginUserLogDAO{
	
	private SqlSession session;
	
	public void setSqlSession(SqlSession session) {
		this.session = session;
	}

	@Override
	public void insertLoginUserLog(LoginUserLogVO log) throws SQLException {
		session.update("LoginUserLog-Mapper.insertLoginUserLog", log);
	}

	@Override
	public void deleteLoginUserLog() throws SQLException {
		session.update("LoginUserLog-Mapper.deleteLoginUserLog");
	}

}
