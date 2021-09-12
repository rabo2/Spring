package com.personal.farmer.service;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.personal.farmer.dao.LprodDAO;
import com.personal.farmer.dto.LprodVO;

public class LprodServiceImpl implements LprodService{
	private LprodDAO lprodDAO;
	
	public void setLprodDAO(LprodDAO lprodDAO) {
		this.lprodDAO = lprodDAO;
	}

	@Override
	public List<LprodVO> selectLprodAll() throws SQLException {
		List<LprodVO> lprodList = lprodDAO.selectLprodAll();
		return lprodList;
	}

	@Override
	public List<LprodVO> selectLprodByName(String lprod_name) throws SQLException {
		List<LprodVO> lprodList = lprodDAO.selectLprodByName(lprod_name);
		return lprodList;
	}

	@Override
	public void insertLprod( String lprod_name) throws SQLException {
		lprodDAO.insertLprod(lprod_name);
	}

	@Override
	public void updateLprod( String lprod_name) throws SQLException {
		lprodDAO.updateLprod(lprod_name);
	}

	@Override
	public void deleteLprod( String lprod_no) throws SQLException {
		lprodDAO.deleteLprod(lprod_no);
	}

}
