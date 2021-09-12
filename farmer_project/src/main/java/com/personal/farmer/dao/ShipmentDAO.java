package com.personal.farmer.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.personal.farmer.dto.ShipmentVO;

public interface ShipmentDAO {
	List<ShipmentVO> selectShipmentByMemId(String mem_id) throws SQLException;
	
	List<ShipmentVO> selectShipmentByDate(Map paraMap) throws SQLException;

	void insertShipment(ShipmentVO vo) throws SQLException;
	
	void updateShipment(ShipmentVO vo) throws SQLException;
	
	void deleteShipment(String ship_no) throws SQLException;
}
