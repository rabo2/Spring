package com.personal.farmer.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.personal.farmer.dto.ProdVO;

public interface ProductDAO {
	
	List<ProdVO> selectProductAll() throws SQLException;

	List<ProdVO> selectProdByName(String prod_name) throws SQLException;
	 
	void insertProduct(ProdVO vo) throws SQLException;
	
	void updateProduct(ProdVO vo) throws SQLException;
	
	void deleteProduct(ProdVO vo) throws SQLException;
	
}
