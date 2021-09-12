package com.personal.farmer.service;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.personal.farmer.dao.ProductDAO;
import com.personal.farmer.dto.ProdVO;

public class ProductServiceImpl implements ProductService{
	private ProductDAO productDAO;
	
	public void setProductDAO(ProductDAO productDAO) {
		this.productDAO = productDAO;
	}

	@Override
	public List<ProdVO> selectProductAll() throws SQLException {
		List<ProdVO> prodList = productDAO.selectProductAll();
		return prodList;
	}

	@Override
	public List<ProdVO> selectProdByName(String prod_name) throws SQLException {
		List<ProdVO> prodList = productDAO.selectProdByName(prod_name);
		return prodList;
	}

	@Override
	public void insertProduct(ProdVO vo) throws SQLException {
		productDAO.insertProduct(vo);
	}

	@Override
	public void updateProduct(ProdVO vo) throws SQLException {
		productDAO.updateProduct(vo);
	}

	@Override
	public void deleteProduct(ProdVO vo) throws SQLException {
		productDAO.deleteProduct(vo);
	}
	
}
