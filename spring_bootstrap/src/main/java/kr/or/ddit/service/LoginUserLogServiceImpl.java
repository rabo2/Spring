package kr.or.ddit.service;

import java.sql.SQLException;
import java.util.List;

import kr.or.ddit.dao.LoginUserLogDAO;
import kr.or.ddit.dto.LoginUserLogVO;

public class LoginUserLogServiceImpl implements LoginUserLogService{
	
	private LoginUserLogDAO logDAO;
	
	public void setLoginUserLogDAO(LoginUserLogDAO loginUserLogDAO) {
		this.logDAO = loginUserLogDAO;
	}

	@Override
	public void write(List<LoginUserLogVO> logList) throws SQLException {
		logDAO.deleteLoginUserLog();
		
		for (LoginUserLogVO logVO : logList) {
			logDAO.insertLoginUserLog(logVO);
		}
		
	}

}
