package com.personal.farmer.service;

import java.sql.SQLException;
import java.util.List;

import com.personal.farmer.dto.BuyerVO;

public interface BuyerService {
	
	List<BuyerVO> selectBuyerAll() throws SQLException;

	List<BuyerVO> selectBuyerByName(String buy_name)throws SQLException;

	void insertBuyer(BuyerVO vo) throws SQLException;
	
	void updateBuyer(BuyerVO vo) throws SQLException;
	
	void deleteBuyer(String buy_no) throws SQLException;
	
}
