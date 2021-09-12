package com.personal.farmer.service;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.personal.farmer.dao.CalenderDAO;
import com.personal.farmer.dto.CalenderVO;

public class CalenderServiceImpl implements CalenderService{
	private CalenderDAO calenderDAO;

	public void setCalenderDAO(CalenderDAO calenderDAO) {
		this.calenderDAO = calenderDAO;
	}

	@Override
	public List<CalenderVO> selectCalenderAllById(String mem_id) throws SQLException {
		List<CalenderVO> calList = calenderDAO.selectCalenderAllById(mem_id);
		return calList;
	}

	@Override
	public List<CalenderVO> selectCalenderByDateAndMemId(CalenderVO vo) throws SQLException {
		List<CalenderVO> calList = calenderDAO.selectCalenderByDateAndMemId(vo);
		return calList;
	}

	@Override
	public void insertCalender(CalenderVO vo) throws SQLException {
		calenderDAO.insertCalender(vo);
	}

	@Override
	public void updateCalender(CalenderVO vo) throws SQLException {
		calenderDAO.updateCalender(vo);
	}

	@Override
	public void deleteCalender(CalenderVO vo) throws SQLException {
		calenderDAO.deleteCalender(vo);
	}

}
