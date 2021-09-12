package com.personal.farmer.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.personal.farmer.dto.ProdVO;

public class ProductDAOImpl implements ProductDAO{

	private SqlSession sqlSession;
	
	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}

	@Override
	public List<ProdVO> selectProductAll()  throws SQLException{
		List<ProdVO> prodList = sqlSession.selectList("Prod-Mapper.selectProductAll");
		return prodList;
	}

	@Override
	public List<ProdVO> selectProdByName(String prod_name) throws SQLException{
		List<ProdVO> prodList = sqlSession.selectList("Prod-Mapper.selectProdByName", prod_name);
		return prodList;
	}

	@Override
	public void insertProduct(ProdVO vo) throws SQLException{
		sqlSession.insert("Prod-Mapper.insertProduct", vo);
	}

	@Override
	public void updateProduct(ProdVO vo) throws SQLException{
		sqlSession.update("Prod-Mapper.updateProduct", vo);
	}

	@Override
	public void deleteProduct(ProdVO vo) throws SQLException{
		sqlSession.delete("Prod-Mapper.deleteProduct", vo);
	}

}
