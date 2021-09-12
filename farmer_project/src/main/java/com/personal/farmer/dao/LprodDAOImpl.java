package com.personal.farmer.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.personal.farmer.dto.LprodVO;

public class LprodDAOImpl implements LprodDAO{
	
	private SqlSession sqlSession;
	
	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}

	@Override
	public List<LprodVO> selectLprodAll() throws SQLException{
		List<LprodVO> lprodList = sqlSession.selectList("Lprod-Mapper.selectLprodAll");
		return lprodList;
	}

	@Override
	public List<LprodVO> selectLprodByName(String lprod_name) throws SQLException{
		List<LprodVO> lprodList = sqlSession.selectList("Lprod-Mapper.selectLprodByName", lprod_name);
		return lprodList;
	}

	@Override
	public void insertLprod(String lprod_name) throws SQLException{
		sqlSession.insert("Lprod-Mapper.insertLprod", lprod_name);
	}

	@Override
	public void updateLprod(String lprod_name) throws SQLException{
		sqlSession.update("Lprod-Mapper.updateLprod", lprod_name);
	}

	@Override
	public void deleteLprod(String lprod_no) throws SQLException{
		sqlSession.delete("Lprod-Mapper.deleteLprod", lprod_no);
	}

}
