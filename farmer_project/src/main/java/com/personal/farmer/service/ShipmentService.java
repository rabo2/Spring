package com.personal.farmer.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.personal.farmer.dto.ShipmentVO;

public interface ShipmentService {
	List<ShipmentVO> selectShipmentByMemId(String mem_id) throws SQLException;
	
	List<ShipmentVO> selectShipmentByDate(Map paraMap) throws SQLException;

	void insertShipment(ShipmentVO vo) throws SQLException;
	
	void updateShipment(ShipmentVO vo) throws SQLException;
	
	void deleteShipment(String ship_no) throws SQLException;
}
