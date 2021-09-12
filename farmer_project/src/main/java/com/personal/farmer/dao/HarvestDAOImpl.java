package com.personal.farmer.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.personal.farmer.dto.HarvestVO;

public class HarvestDAOImpl implements HarvestDAO{

	private SqlSession sqlSession;
	
	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}

	@Override
	public List<HarvestVO> selectHarvestAll() throws SQLException{
		List<HarvestVO> harvestList = sqlSession.selectList("Harvest-Mapper.selectHarvestAll");
		return harvestList;
	}

	@Override
	public List<HarvestVO> selectHarvestByDateAndProd(Map paraMap) throws SQLException{
		List<HarvestVO> harvestList = sqlSession.selectList("Harvest-Mapper.selectHarvestByDateAndProd", paraMap);
		return harvestList;
	}

	@Override
	public void insertHarvest(HarvestVO vo) throws SQLException{
		sqlSession.insert("Harvest-Mapper.insertHarvest", vo);
	}

	@Override
	public void updateHarvest(HarvestVO vo) throws SQLException{
		sqlSession.update("Harvest-Mapper.updateHarvest", vo);
	}

	@Override
	public void deleteHarvest(HarvestVO vo) throws SQLException{
		sqlSession.delete("Harvest-Mapper.deleteHarvest", vo);
	}

}
