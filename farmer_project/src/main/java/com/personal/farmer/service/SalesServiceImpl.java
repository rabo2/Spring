package com.personal.farmer.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.personal.farmer.dao.SalesDAO;
import com.personal.farmer.dto.SalesVO;

public class SalesServiceImpl implements SalesService {
	private SalesDAO salesDAO;

	public void setSalesDAO(SalesDAO salesDAO) {
		this.salesDAO = salesDAO;
	}

	@Override
	public List<SalesVO> selectSalesByMemId(String mem_id) throws SQLException {
		List<SalesVO> salesList = salesDAO.selectSalesByMemId(mem_id);
		return salesList;
	}

	@Override
	public List<SalesVO> selectSalesByDateAndMemId(Map paraMap) throws SQLException {
		List<SalesVO> salesList = salesDAO.selectSalesByDateAndMemId(paraMap);
		return salesList;
	}

	@Override
	public SalesVO selectSalesBySalNo(String sal_no) throws SQLException {
		SalesVO sales = salesDAO.selectSalesBySalNo(sal_no);
		return sales;
	}

	@Override
	public void insertSales(SalesVO vo) throws SQLException {
		salesDAO.insertSales(vo);
	}

	@Override
	public void updateSales(SalesVO vo) throws SQLException {
		salesDAO.updateSales(vo);
	}

	@Override
	public void deleteSales(String sal_no) throws SQLException {
		salesDAO.deleteSales(sal_no);
	}

}
