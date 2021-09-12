package com.personal.farmer.service;

import java.sql.SQLException;
import java.util.List;

import com.personal.farmer.dto.LprodVO;

public interface LprodService {
	List<LprodVO> selectLprodAll() throws SQLException;
	
	List<LprodVO> selectLprodByName(String lprod_name) throws SQLException;
	
	void insertLprod(String lprod_name) throws SQLException;
	
	void updateLprod(String lprod_name) throws SQLException;
	
	void deleteLprod(String lprod_no) throws SQLException;
}	
