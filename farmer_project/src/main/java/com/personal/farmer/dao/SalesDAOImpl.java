package com.personal.farmer.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.personal.farmer.dto.SalesVO;

public class SalesDAOImpl implements SalesDAO{

	private SqlSession sqlSession;
	
	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}

	@Override
	public List<SalesVO> selectSalesByMemId(String mem_id) throws SQLException {
		List<SalesVO> salesList = sqlSession.selectList("Sales-Mapper.selectSalesByMemId", mem_id);
		return salesList;
	}

	@Override
	public List<SalesVO> selectSalesByDateAndMemId(Map paraMap) throws SQLException {
		List<SalesVO> salesList = sqlSession.selectList("Sales-Mapper.selectSalesByDateAndMemId", paraMap);
		return salesList;
	}

	@Override
	public SalesVO selectSalesBySalNo(String sal_no) throws SQLException {
		SalesVO sales = sqlSession.selectOne("Sales-Mapper.selectSalesBySalNo", sal_no);
		return sales;
	}

	@Override
	public void insertSales(SalesVO vo) throws SQLException {
		sqlSession.insert("Sales-Mapper.insertSales", vo);
	}

	@Override
	public void updateSales(SalesVO vo) throws SQLException {
		sqlSession.update("Sales-Mapper.updateSales", vo);
	}

	@Override
	public void deleteSales(String sal_no) throws SQLException {
		sqlSession.delete("Sales-Mapper.deleteSales", sal_no);
	}

}
