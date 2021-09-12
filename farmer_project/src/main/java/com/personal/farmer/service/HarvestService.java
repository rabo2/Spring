package com.personal.farmer.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.personal.farmer.dto.HarvestVO;

public interface HarvestService {

	List<HarvestVO> selectHarvestAll() throws SQLException;
	
	List<HarvestVO> selectHarvestByDateAndProd(Map paraMap) throws SQLException;
	
	void insertHarvest(HarvestVO vo) throws SQLException;
	
	void updateHarvest(HarvestVO vo) throws SQLException;
	
	void deleteHarvest(HarvestVO vo) throws SQLException;
}
