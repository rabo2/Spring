package com.personal.farmer.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.personal.farmer.dao.ShipmentDAO;
import com.personal.farmer.dto.ShipmentVO;

public class ShipmentServiceImpl implements ShipmentService{
	private ShipmentDAO shipmentDAO;

	public void setShipmentDAO(ShipmentDAO shipmentDAO) {
		this.shipmentDAO = shipmentDAO;
	}

	@Override
	public List<ShipmentVO> selectShipmentByMemId(String mem_id) throws SQLException {
		List<ShipmentVO> shipList = shipmentDAO.selectShipmentByMemId(mem_id);
		return shipList;
	}

	@Override
	public List<ShipmentVO> selectShipmentByDate(Map paraMap) throws SQLException {
		List<ShipmentVO> shipList = shipmentDAO.selectShipmentByDate(paraMap);
		return shipList;
	}

	@Override
	public void insertShipment(ShipmentVO vo) throws SQLException {
		shipmentDAO.insertShipment(vo);
	}

	@Override
	public void updateShipment(ShipmentVO vo) throws SQLException {
		shipmentDAO.updateShipment(vo);
	}

	@Override
	public void deleteShipment(String ship_no) throws SQLException {
		shipmentDAO.deleteShipment(ship_no);
	}
}
