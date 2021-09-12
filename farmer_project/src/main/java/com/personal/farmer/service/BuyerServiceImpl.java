package com.personal.farmer.service;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.personal.farmer.dao.BuyerDAO;
import com.personal.farmer.dto.BuyerVO;

public class BuyerServiceImpl implements BuyerService{
	private BuyerDAO buyerDAO;

	public void setBuyerDAO(BuyerDAO buyerDAO) {
		this.buyerDAO = buyerDAO;
	}

	@Override
	public List<BuyerVO> selectBuyerAll() throws SQLException {
		List<BuyerVO> buyerList = buyerDAO.selectBuyerAll();
		return buyerList;
	}

	@Override
	public List<BuyerVO> selectBuyerByName(String buy_name) throws SQLException {
		List<BuyerVO> buyerList = buyerDAO.selectBuyerByName(buy_name);
		return buyerList;
	}

	@Override
	public void insertBuyer(BuyerVO vo) throws SQLException {
		buyerDAO.insertBuyer(vo);
	}

	@Override
	public void updateBuyer(BuyerVO vo) throws SQLException {
		buyerDAO.updateBuyer(vo);
	}

	@Override
	public void deleteBuyer(String buy_no) throws SQLException {
		buyerDAO.deleteBuyer(buy_no);
	}
	

}
