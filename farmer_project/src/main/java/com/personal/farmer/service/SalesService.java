package com.personal.farmer.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.personal.farmer.dto.SalesVO;

public interface SalesService {
	List<SalesVO> selectSalesByMemId(String mem_id) throws SQLException;
	
	List<SalesVO> selectSalesByDateAndMemId(Map paraMap) throws SQLException;

	SalesVO selectSalesBySalNo(String sal_no) throws SQLException;
	
	void insertSales(SalesVO vo) throws SQLException;
	
	void updateSales(SalesVO vo) throws SQLException;
	
	void deleteSales(String sal_no) throws SQLException;
}
