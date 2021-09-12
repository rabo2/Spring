package com.personal.farmer.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.personal.farmer.dao.HarvestDAO;
import com.personal.farmer.dto.HarvestVO;

public class HarvestServiceImpl implements HarvestService{
	private HarvestDAO harvestDAO;
	
	public void setHarvestDAO(HarvestDAO harvestDAO) {
		this.harvestDAO = harvestDAO;
	}

	@Override
	public List<HarvestVO> selectHarvestAll() throws SQLException {
		List<HarvestVO> list = harvestDAO.selectHarvestAll();
		return list;
	}

	@Override
	public List<HarvestVO> selectHarvestByDateAndProd(Map paraMap) throws SQLException {
		List<HarvestVO> list = harvestDAO.selectHarvestByDateAndProd(paraMap);
		return list;
	}

	@Override
	public void insertHarvest(HarvestVO vo) throws SQLException {
		harvestDAO.insertHarvest(vo);
		
	}

	@Override
	public void updateHarvest(HarvestVO vo) throws SQLException {
		harvestDAO.updateHarvest(vo);
	}

	@Override
	public void deleteHarvest(HarvestVO vo) throws SQLException {
		harvestDAO.deleteHarvest(vo);
	}

}
