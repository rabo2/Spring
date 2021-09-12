package com.personal.farmer.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.personal.farmer.dto.LprodVO;

public interface LprodDAO {
	List<LprodVO> selectLprodAll() throws SQLException;
	
	List<LprodVO> selectLprodByName(String lprod_name) throws SQLException;
	
	void insertLprod(String lprod_name) throws SQLException;
	
	void updateLprod(String lprod_name) throws SQLException;
	
	void deleteLprod(String lprod_no) throws SQLException;
}	
