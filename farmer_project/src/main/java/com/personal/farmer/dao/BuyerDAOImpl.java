package com.personal.farmer.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.personal.farmer.dto.BuyerVO;

public class BuyerDAOImpl implements BuyerDAO{
	
	private SqlSession sqlSession;

	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}

	@Override
	public List<BuyerVO> selectBuyerAll() throws SQLException {
		List<BuyerVO> buyerList = sqlSession.selectList("Buyer-Mapper.selectBuyerAll");
		return buyerList;
	}

	@Override
	public List<BuyerVO> selectBuyerByName(String buy_name) throws SQLException{
		List<BuyerVO> buyerList = sqlSession.selectList("Buyer-Mapper.selectBuyerByName",buy_name);
		return buyerList;
	}

	@Override
	public void insertBuyer(BuyerVO vo) throws SQLException{
		sqlSession.insert("Buyer-Mapper.insertBuyer",vo);
	}

	@Override
	public void updateBuyer(BuyerVO vo) throws SQLException{
		sqlSession.update("Buyer-Mapper.updateBuyer",vo);
	}

	@Override
	public void deleteBuyer(String buy_no) throws SQLException{
		sqlSession.delete("Buyer-Mapper.deleteBuyer",buy_no);
	}

}
