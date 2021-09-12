package com.personal.farmer.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.personal.farmer.dto.ShipmentVO;

public class ShipmentDAOImpl implements ShipmentDAO{
	
	private SqlSession sqlSession;
	
	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}

	@Override
	public List<ShipmentVO> selectShipmentByMemId(String mem_id) throws SQLException{
		List<ShipmentVO> shipList = sqlSession.selectList("Shipment-Mapper.selectShipmentByMemId", mem_id);
		return shipList;
	}

	@Override
	public List<ShipmentVO> selectShipmentByDate(Map paraMap) throws SQLException{
		List<ShipmentVO> shipList = sqlSession.selectList("Shipment-Mapper.selectShipmentByDate", paraMap);
		return shipList;
	}

	@Override
	public void insertShipment(ShipmentVO vo) throws SQLException{
		sqlSession.insert("Shipment-Mapper.insertShipment", vo);
	}

	@Override
	public void updateShipment(ShipmentVO vo) throws SQLException{
		sqlSession.update("Shipment-Mapper.updateShipment", vo);
	}

	@Override
	public void deleteShipment(String ship_no) throws SQLException{
		sqlSession.delete("Shipment-Mapper.deleteShipment", ship_no);
	}

}
